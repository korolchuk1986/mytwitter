<#import "parts/common.ftlh" as c>

<@c.page>
    <h5>User editor</h5>
    <form action="/user" method="post">
        <div class="mb-3 row">
            <label for="staticUsernameEditor" class="col-sm-1 col-form-label">Username</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" id="staticUsernameEditor" value="${user.username}" name="username"/>
            </div>
        </div>
        <#list roles as role>
            <div class="form-check">
                <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault" name="${role}" ${user.roles?seq_contains(role)?string("checked","")}/>
                <label class="form-check-label" for="flexCheckDefault">
                    ${role}
                </label>
            </div>
        </#list>
        <input type="hidden" value="${user.id}" name="userId"/>
        <input type="hidden" value="${_csrf.token}" name="_csrf"/>
        <button  class="btn btn-primary" type="submit">Save</button>
    </form>

</@c.page>
