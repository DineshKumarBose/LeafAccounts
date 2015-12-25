/**
 * 
 */
 $(function(){
	$("#register").click(function(){
		var username = $('#username').val();
		if(username === '') {
			alert("username can't be empty");
			$('#username').focus();
			return false;
		}
		var password = $("#password").val();
		if(password === '') {
			alert("password can't be empty");
			$("#password").focus();
			return false;
		}
		var email = $("#email").val();
		if(!isEmail(email)) {
			alert("Please enter valid emailid.");
			$("#email").focus();
			return false;
		}
		var dob = $("#dob").val();
		if(dob === "") {
			alert("date of birth can't be empty.");
			$("#dob").focus();
			return false;
		}
	});
	function isEmail(email) {
		  var regex = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
		  return regex.test(email);
		}
});