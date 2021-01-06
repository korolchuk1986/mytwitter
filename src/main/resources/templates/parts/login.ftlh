<#include "security.ftlh">
<#macro login path isRegisterForm>
<form action="${path}" method="post" class="mt-3">
    <div class="mb-3 row">
        <label for="staticUsername" class="col-sm-1 col-form-label">Username</label>
        <div class="col-sm-4">
            <input type="text" class="form-control" id="staticUsername" placeholder="Username" name="username">
        </div>
    </div>
    <div class="mb-3 row">
        <label for="inputPassword" class="col-sm-1 col-form-label">Password</label>
        <div class="col-sm-4">
            <input type="password" class="form-control" id="inputPassword" name="password">
        </div>
    </div>
    <#if isRegisterForm>
        <div class="mb-3 row">
            <label for="inputEmail" class="col-sm-1 col-form-label">Email</label>
            <div class="col-sm-4">
                <input type="email" class="form-control" id="inputEmail" name="email" placeholder="some@some.com">
            </div>
        </div>
    </#if>

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
