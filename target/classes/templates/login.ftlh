<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>
<@c.page>
<div>Login</div>
<@l.login "/login"></@l.login>
<a href="/registration">Add new user</a>
</@c.page>
