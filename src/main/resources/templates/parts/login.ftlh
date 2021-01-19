<#include "security.ftlh">
<#macro login path isRegisterForm>
<form action="${path}" method="post" class="mt-3">
    <div class="mb-3 row">
        <label for="staticUsername" class="col-sm-1 col-form-label">Username</label>
        <div class="col-sm-4">
            <input type="text" class="form-control <#if usernameError??>is-invalid</#if>" id="staticUsername" placeholder="Username" name="username" value="<#if user??>${user.name}</#if>">
            <#if usernameError??>
                <div class="invalid-feedback d-block">
                    ${usernameError}
                </div>
            </#if>
        </div>
    </div>
    <div class="mb-3 row">
        <label for="inputPassword" class="col-sm-1 col-form-label">Password</label>
        <div class="col-sm-4">
            <input type="password" class="form-control <#if passwordError??>is-invalid</#if>" id="inputPassword" name="password">
            <#if passwordError??>
                <div class="invalid-feedback d-block">
                    ${passwordError}
                </div>
            </#if>
        </div>
    </div>
    <#if isRegisterForm>
    <div class="mb-3 row">
        <label for="inputPassword" class="col-sm-1 col-form-label">Password</label>
        <div class="col-sm-4">
            <input type="password" class="form-control <#if password2Error??>is-invalid</#if>" id="inputPassword" name="password2">
            <#if password2Error??>
                <div class="invalid-feedback d-block">
                    ${password2Error}
                </div>
            </#if>
        </div>
    </div>
    </#if>
    <#if isRegisterForm>
        <div class="mb-3 row">
            <label for="inputEmail" class="col-sm-1 col-form-label">Email</label>
            <div class="col-sm-4">
                <input type="email" class="form-control <#if emailError??>is-invalid</#if>" id="inputEmail" name="email" placeholder="some@some.com" v="<#if user??>${user.email}</#if>">
                <#if emailError??>
                    <div class="invalid-feedback d-block">
                        ${emailError}
                    </div>
                </#if>
            </div>
        </div>
        <div class="col-sm-4">
            <div class="g-recaptcha" data-sitekey="6LdLOyQaAAAAALxdpQ6iIL2x4E405-ddbQuCJEHt"></div>
            <#if captchaError??>
                <div class="alert alert-danger" role="alert">
                    ${captchaError}
                </div>
            </#if>
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
    <button type="submit" class="btn btn-primary"><#if user??>Sign Out<#else>Log in</#if></button>
</form>
</#macro>
