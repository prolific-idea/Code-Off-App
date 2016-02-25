<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h2>Register New User</h2>

<form:form  method="post" action="/test/crud" commandName="testAppUserModel">

    <fieldset>
        <!-- Email Address -->
        <div class="form-group">
            <form:label path="username" class="control-label">Email Address</form:label>
            <div class="controls">
                <form:input path="username" class="form-control"/>
                <span class="help-inline"><form:errors path="username" /></span>
            </div>
        </div>
        <!-- Password -->
        <div class="form-group">
            <form:label path="password" class="control-label">Password</form:label>
            <div class="controls">
                <form:password path="password" class="form-control"/>
                <span class="help-inline"><form:errors path="password" /></span>
            </div>
        </div>
        <!-- First Name -->
        <div class="form-group">
            <form:label path="firstName" class="control-label">First Name</form:label>
            <div class="controls">
                <form:input path="firstName" class="form-control"/>
                <span class="help-inline"><form:errors path="firstName" /></span>
            </div>
        </div>
        <!-- LastName -->
        <div class="form-group">
            <form:label path="lastName" class="control-label">Last Name</form:label>
            <div class="controls">
                <form:input path="lastName" class="form-control"/>
                <span class="help-inline"><form:errors path="lastName" /></span>
            </div>
        </div>
        <button class="btn btn-primary" type="submit">Register User</button>
    </fieldset>
</form:form>
<br/>
<table class="table table-bordered">
    <tr>
        <td>Username</td>
        <td>First Name</td>
        <td>Last Name</td>
        <td>Enabled</td>
    </tr>
    <c:forEach items="${appUserList}" var="appUser">
        <tr>
            <td>${appUser.username}</td>
            <td>${appUser.appUserDetails.firstName}</td>
            <td>${appUser.appUserDetails.lastName}</td>
            <td>
                <c:choose>
                    <c:when test="${appUser.enabled}">
                        Enabled
                    </c:when>
                    <c:otherwise>
                        Disabled
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
    </c:forEach>
</table>