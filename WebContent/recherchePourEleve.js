
function recherche() {
	$.get("RechercheServlet",{research:$("#search").val()}, function(data,status) {
		$("#message").text(data);
	});
}