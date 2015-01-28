<%@ include file="head.jsp" %>
<%@ include file="menu.jsp" %>
<div id="list">
<div id="message">${message}</div>
<div id="content">${content}</div>
</div>
<script src="http://code.jquery.com/jquery-1.7.2.min.js"></script>

<script type="text/javascript">
	if(window.onload){
			localStorage.start = 0
			localStorage.end = 5;
	}
	//var start = 0;
	//var end = 5;  
	function doAjax(isPrev) {
		 var start = Number(localStorage.start);
		 var end = Number(localStorage.end);
		 if(isPrev){
			start = (start > 5)? (start - 5): 0;
			end = (end > 5)? (end - 5): 5;
		 } else {
		   	start += 5;
			end += 5;
		 }		 
		 $.ajax({
		 	type: "POST",
			url: "list",
			data: "start=" + start + "&end=" + end,
			success: function(response){
				 // we have the response
				 //alert('start ' + start + ' end ' + end);
				 //localStorage.setItem('start',start);
				 localStorage.start = Number(start);
				 localStorage.end = Number(end);
				//alert('success');
				$("html").html(response);
			}		,
			error: function(e){
			       alert('start ' + start + ' end ' + end);
			       alert('Error: ' + e);
			}
		});
	}
</script>
<div id='displaytable'>

</div>
<input type='button' value='Previous' onclick="doAjax(true)">
<input type='button' value='Next' onclick="doAjax(false)">
<%@ include file="footer.jsp" %>
