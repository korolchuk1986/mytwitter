<#import "parts/common.ftlh" as c>
<#import "parts/messageEdit.ftlh" as e>
<@c.page>
    <#if isCurrentUser>
        <@e.messageEdit "Message editor"></@e.messageEdit>
    </#if>

    <#include "parts/messageList.ftlh">
</@c.page>
