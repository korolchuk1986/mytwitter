<#macro login path isRegisterForm>
<form action="${path}" method="post" class="mt-3">
    <div class="mb-3 row">
        <label for="staticUsername" class="col-sm-1 col-form-label">Username</label>
        <div class="col-sm-4">
            <input type="text" class="form-control" id="staticUsername" value="Username" name="username">
        </div>
    </div>
    <div class="mb-3 row">
        <label for="inputPassword" class="col-sm-1 col-form-label">Password</label>
        <div class="col-sm-4">
            <input type="password" class="form-control" id="inputPassword" name="password">
        </div>
    </div>

    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <#if !isRegisterForm><div class="pb-3"><a href="/registration">Add new user</a></div></#if>
    <button type="submit" class="btn btn-primary"><#if isRegisterForm>Create<#else>Sign In</#if></button>
</form>
</#macro>
<#macro logout>
<form action="/logout" method="post">
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <button type="submit" class="btn btn-primary">Sign Out</button>
</form>
</#macro>
