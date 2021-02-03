window.onload=function(){
  var current = location.pathname.split('/')[1];
  if (current === ""){
  	var index = document.getElementsByClassName("index")[0];
  	index.className += " " + "active";
   	return;
  }
  
  var menuItems = document.querySelectorAll('.nav-link');
  for (let i = 0; i< menuItems.length; i++){
    if (menuItems[i].getAttribute("href").indexOf(current) !== -1){
      if (menuItems[i].getAttribute("href") =="/user/logout"){
      	break;
      }
      if (current == "user"){
      	menuItems[i].parentElement.className += " " + "active";
      }else{
        menuItems[i].className += " " + "active";
      }
    }
  }
}