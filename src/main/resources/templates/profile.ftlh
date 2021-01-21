<#import "parts/common.ftlh" as c>
<@c.page>
    <h5>${username!""}</h5>
    ${message!""}
    <form method="post">
        <div class="mb-3 row">
            <label for="inputPassword" class="col-sm-1 col-form-label">Password</label>
            <div class="col-sm-4">
                <input type="password" class="form-control" id="inputPassword" name="password"/>
            </div>
        </div>
        <div class="mb-3 row">
            <label for="inputEmail" class="col-sm-1 col-form-label">Email</label>
            <div class="col-sm-4">
                <input type="email" class="form-control" id="inputEmail" name="email" placeholder="some@some.com" value="${email!""}"/>
            </div>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="submit" class="btn btn-primary">Save</button>
    </form>
</@c.page>
