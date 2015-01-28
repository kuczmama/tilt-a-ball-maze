<%@ include file="head.jsp" %>
<div id="message">${message}</div>
<h1>New User</h1>
<div id="add_user">
<form action="add_user" method="post">
<div class="formitem">
<label for="name">user</label><input type="text" name="user" id="user" value="${user}">
</div>
<div class="formitem">
<label for="newPassword">New Password</label><input type="password" name="newPassword" id="newPassword" value="${newPassword}">
</div>
<div class="formitem">
<label for="confirmNewPassword">Confirm New Password</label><input type="password" name="confirmNewPassword" id="confirmNewPassword" value="${confirmNewPassword}">
</div>
<div class="formitem">
<label>&nbsp;</label>
<input type="submit">
</div>
</form>
</div>
<%@ include file="footer.jsp" %>
