<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>
<@c.page>
<div>Add new user</div>
${message}
<@l.login "/registration"></@l.login>
</@c.page>

