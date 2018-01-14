/**
 * 
 */

function change() {
    var pic = document.getElementById("preview"),
        file = document.getElementById("f");

    var ext=file.value.substring(file.value.lastIndexOf(".")+1).toLowerCase();

     
     if(ext!='png'&&ext!='jpg'&&ext!='jpeg'){
         alert("formation jpg or jpeg"); 
         return;
     }
     var isIE = navigator.userAgent.match(/MSIE/)!= null,
         isIE6 = navigator.userAgent.match(/MSIE 6.0/)!= null;

     if(isIE) {
        file.select();
        var reallocalpath = document.selection.createRange().text;

        
         if (isIE6) {
            pic.src = reallocalpath;
         }else {
          
             pic.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod='image',src=\"" + reallocalpath + "\")";
             pic.src = 'data:image/gif;base64,R0lGODlhAQABAIAAAP///wAAACH5BAEAAAAALAAAAAABAAEAAAICRAEAOw==';
         }
     }else {
        html5Reader(file);
     }
}

 function html5Reader(file){
     var file = file.files[0];
     var reader = new FileReader();
     reader.readAsDataURL(file);
     reader.onload = function(e){
     var pic = document.getElementById("preview");
     pic.src=this.result;
     }
 }
 


