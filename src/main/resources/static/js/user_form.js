"use strict";

  function forminter() {
	  var user = document.user;
	  console.log(user.name)
	  $("#name").val(user.name)
	  $("#email").val(user.email)
	  $("#zipcode").val(user.zipcode)
	  $("#address").val(user.address)
	  $("#tel").val(user.telephone)
  }
  window.onload =forminter();