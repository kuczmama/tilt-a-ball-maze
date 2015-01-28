<%@ include file="head.jsp" %>
<div id="message">${message}</div>
Enter item to edit
<div id="loginform">
<form action="edit" method="post">
<div class="formitem">
<label for="name">Name</label><input type="text" name="name" id="name" value="${name}">
</div>
<div class="formitem">
<label for="url">url</label><input type="text" name="url" id="url" value="${url}">
</div>
<div class="formitem">
<label>&nbsp;</label>
<input type="submit">
</div>
</form>
</div>
<%@ include file="footer.jsp" %>
