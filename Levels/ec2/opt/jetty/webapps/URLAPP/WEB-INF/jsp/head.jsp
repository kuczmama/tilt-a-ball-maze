  <%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title>URL APP</title>
<link rel="stylesheet" type="text/css" href="static/style.css">
</head>
<body>
<div id="ads"></div>
<div id="page">
<div id="header">
<h1>URL Application</h2>
<script src="http://code.jquery.com/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
		localStorage.index = 1;
		$(document).ready(function(){
			showAds();
			setInterval("showAds()",20000);	
		});		
		function showAds(){
			$.ajax({
				url: "ads",
				type: "GET",
				data: "index=" + localStorage.index,
				success: function(response){
					$("#ads").html(response);
					localStorage.index = Number(localStorage.index)+1;
					if(Number(localStorage.index) == 3){
							      localStorage.index = 0;
					}		 
				}
			});//end ajax
		}
		
</script>
</div>

