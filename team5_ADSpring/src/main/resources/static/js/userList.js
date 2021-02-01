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
  
  document.getElementById('button').addEventListener('click', loadUsers);
}

function loadUsers(e){
  e.preventDefault();
  let xhr = new XMLHttpRequest();

  xhr.open("POST","/user/users");
  xhr.setRequestHeader("Content-Type","application/json; charset=utf-8")

  xhr.onload=function(){
    if(this.status===200){
      let users = JSON.parse(this.responseText);
      let output = `
      	<tr>
      		<th></th>
      		<th>Username</th>
      		<th>Role</th>
      		<th>Edit User</th>
      	</tr>`;

      for (let i in users){
        output += `
		  <tr>
		  	<td>
			  <input type="checkbox" id="`+users[i].userName+`" name="deleteUser" value="`+users[i].userName+`">
			</td>
			<td>
			  <label for="`+users[i].userName+`"> `+users[i].userName+`</label>
			</td>  
			<td>`+users[i].role+`</td>
			<td><a href="/user/edit/`+users[i].id+`">Edit</a></td>
		  </tr>`
      }
	  output += `<input type="submit" class="btn btn-warning" value="Delete">`;
      document.getElementById('result').innerHTML = output;
    }
  }
  
  let roleType = document.getElementsByClassName("roleType")[0].value;
  let data = {
    "roleType":roleType
  };
  xhr.send(JSON.stringify(data));
}

