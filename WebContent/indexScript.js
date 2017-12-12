/**
 * transfer login and password to servlet and check it then return the message
 */

function validate() {
	$.post("UserServlet",{login:$("#login").val(),password:$("#password").val()}, function(data,status) {
		$("#message").text(data);
	});
}






