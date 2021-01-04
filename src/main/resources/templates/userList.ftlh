<#import "parts/common.ftlh" as c>
<@c.page>
<h5>List of users</h5>
    <div class="row col-sm-6">
        <table class="table table-striped">
            <thead class="table-light">
            <tr>
                <th scope="col">Name</th>
                <th scope="col">Role</th>
                <th scope="col"></th>
            </tr>
            </thead>
            <tbody>
            <#list users as user>
                <tr>
                    <td>${user.username}</td>
                    <td><#list user.roles as role>${role}<#sep>, </#list></td>
                    <td><a href="/user/${user.id}">Edit</a></td>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>
</@c.page>
