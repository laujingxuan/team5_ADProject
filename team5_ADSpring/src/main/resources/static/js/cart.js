window.onload = function ()
{
    let elemlist = document.getElementsByClassName("quantity");
    for (let i = 0; i < elemlist.length; i++) {
        elemlist[i].addEventListener('keyup', onChange);
        elemlist[i].addEventListener('mouseup', onChange);
    }
    
    let elem1list = document.getElementsByClassName("remarks");
    for (let i = 0; i < elem1list.length; i++) {
        elem1list[i].addEventListener('keyup', onChange);
    }
    
    let elem2list = document.getElementsByClassName("numGuest");
    for (let i = 0; i < elem2list.length; i++) {
        elem2list[i].addEventListener('keyup', onChange);
        elem2list[i].addEventListener('mouseup', onChange);
    }
    
    UpdateTotal();
}

function onChange(event) {
    let elem = event.currentTarget;
    let quantity = elem.parentElement.parentElement.parentElement.querySelector(".quantity").value;
    let remarks = elem.parentElement.parentElement.parentElement.querySelector(".remarks").value;
    let numGuest = elem.parentElement.parentElement.parentElement.querySelector(".numGuest").value;
    let cartId = elem.parentElement.parentElement.parentElement.querySelector(".quantity").getAttribute("cartid");
    console.log(cartId);
    
    UpdateTotal();
    
    sendChange(cartId, quantity, remarks, numGuest);
}

function sendChange(cartId, value, remarks, numGuest) {
	let wrapper = {
      "cartId" : cartId,
      "quantity" : value,
      "remarks" : remarks,
      "numGuest" : numGuest
	}
//	var token = $("meta[name='_csrf']").attr("content");
    $.ajax({
       type: "POST",
       contentType : 'application/json; charset=utf-8',
//       headers: {"X-CSRF-TOKEN": token}, 
       dataType : 'json',
       url: "/cart/update",
       data: JSON.stringify(wrapper),
       success :function(result) {
       	console.log(result)
       }
   });
}

function UpdateTotal() {
	let pricelist = document.getElementsByClassName("price");
	let total = 0;
	for (let i = 0; i < pricelist.length; i++) {
		let singlePrice = parseFloat(pricelist[i].parentElement.parentElement.querySelector(".price").textContent);
		let quantity = parseFloat(pricelist[i].parentElement.parentElement.querySelector(".quantity").value);
		total += singlePrice * quantity;
	}

	let totalTag = document.getElementsByClassName("total")[0];
	totalTag.textContent = total;
}
