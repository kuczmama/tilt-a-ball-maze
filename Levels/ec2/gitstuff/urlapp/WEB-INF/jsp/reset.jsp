<%@ include file="head.jsp" %>
<div id="message">${message}</div>
<h1>Password Reset</h1>
<div id="loginform">
<form action="reset" method="post">
<div class="formitem">
<label for="name">Name</label><input type="text" name="name" id="name" value="${name}">
</div>
<div class="formitem">
<label for="oldPassword">Old Password</label><input type="text" name="oldPassword" id="oldPassword" value="${oldPassword}">
</div> 
<div class="formitem">
<label for="newPassword">New Password</label><input type="text" name="newPassword" id="newPassword" value="${newPassword}">
</div>
<div class="formitem">
<label for="confirmNewPassword">Confirm New Password</label><input type="text" name="confirmNewPassword" id="confirmNewPassword" value="${confirmNewPassword}">
</div>
<div class="formitem">
<label>&nbsp;</label>
<input type="submit">
</div>
</form>
</div>
<%@ include file="footer.jsp" %>
