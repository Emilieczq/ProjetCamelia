/**
 * 
 */
function validatePassword() {
    var p = document.getElementById('password').value,
        errors = [];
    var cp = document.getElementById('ConfirmationPassword').value;
    if (p.length < 8) {
        errors.push("Le mot de passe doit contenir au moins 8 caractères."); 
    }
    if (p.search(/[a-z]/i) < 0) {
        errors.push("Le mot de passe doit contenir au moins 1 minuscule.");
    }
    if (p.search(/[A-Z]/i) < 0) {
        errors.push("Le mot de passe doit contenir au moins 1 majuscule.");
    }
    if (p.search(/[0-9]/) < 0) {
        errors.push("Le mot de passe doit contenir au moins 1 chiffre."); 
    }
    if (p.search(/[!@#$%^&*-_']/) < 0) {
        errors.push("Le mot de passe doit contenir au moins 1 caractère spécial."); 
    }
    if (errors.length > 0) {
        alert(errors.join("\n"));
        return false;
    }
    if(p!=cp){
    	document.getElementById("msg").innerHTML="Vérifiez votre mot de passe.";
    	return false;
    }else{
    	document.getElementById("msg").innerHTML=" ";
    	return true;
    }
}