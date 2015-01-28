<%@ include file="head.jsp" %>
<div id="message">${message}</div>
Enter item to edit
<div id="loginform">
<form action="edit" method="post">
<div class="formitem">
<label for="url">url</label><input type="text" name="newurl" id="newurl" value='${url}'>
<input type='hidden' name='newurl' value='${newurl}'>
</div>
<div class="formitem">
<label>&nbsp;</label>
<input type="submit">
</div>
</form>
</div>
<%@ include file="footer.jsp" %>
