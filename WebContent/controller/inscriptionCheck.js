/**
 * 
 */
function validatePassword() {
    var p = document.getElementById('password').value,
        errors = [];
    var cp = document.getElementById('ConfirmationPassword').value;
    if (p.length < 8) {
        errors.push("Your password must be at least 8 characters"); 
    }
    if (p.search(/[a-z]/i) < 0) {
        errors.push("Your password must contain at least one lowercase letters.");
    }
    if (p.search(/[A-Z]/i) < 0) {
        errors.push("Your password must contain at least one Capital letters.");
    }
    if (p.search(/[0-9]/) < 0) {
        errors.push("Your password must contain at least one digit."); 
    }
    if (p.search(/[!@#$%^&*-_']/) < 0) {
        errors.push("Your password must contain at least one special character."); 
    }
    if (errors.length > 0) {
        alert(errors.join("\n"));
        return false;
    }if(p!=cp){
    	document.getElementById("msg").innerHTML="Entered twice the password is not the same";
    	return false;
    }
    if(p==cp){
    	document.getElementById("msg").innerHTML=" ";
    	return true;
    }
    return true;
}