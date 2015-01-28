<%@ include file="head.jsp" %>
<div id="message">${message}</div>
Please login:
<div id="loginform">
<form action="login" method="post">
<div class="formitem">
<label for="name">Name</label><input type="text" name="name" id="name" value="${name}">
</div>
<div class="formitem">
<label for="password">Password</label><input type="password" name="password" id="pasword">
</div>
<div class="formitem">
<label>&nbsp;</label>
<input type="submit">
</div>
</form>
<a href="add_user">New User</a>
</br>
<a href="reset">Reset Password</a>
</div>
<%@ include file="footer.jsp" %>
