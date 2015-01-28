<%@ include file="head.jsp" %>
<div id="message">${message}</div>
Are you sure you want to delete ${url}
<div id="loginform">
<form action="delete" method="post">
<div class="formitem">
<label for="yes">yes</label><input type="submit" name="submit" id="yes" value="yes">
</div>
<div class="formitem">
<label for="cancel">cancel</label><input type="submit" name="cancel" id="cancel" value="cancel">
<input type='hidden' name='name' value='${name}'>
<input type='hidden' name='key' value='${key}'>
<input type='hidden' name='url' value='${url}'>
</div>
</div>
</form>
</div>
<%@ include file="footer.jsp" %>
