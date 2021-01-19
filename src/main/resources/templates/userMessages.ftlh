<#import "parts/common.ftlh" as c>
<#import "parts/messageEdit.ftlh" as e>
<@c.page>
    <h3>${userChannel.username}</h3>
    <#if !isCurrentUser>
        <#if isSubcriber>
            <a href="/user/unsubscribe/${userChannel.id}" class="btn btn-info">Unsubscribe</a>
        <#else>
            <a href="/user/subscribe/${userChannel.id}" class="btn btn-info">Subscribe</a>
        </#if>
    </#if>
    <div class="container">
        <div class="row">
            <div class="col">
                <div class="card p-3 my-3">
                    <h5 class="card-title">Subscriptions</h5>
                    <h3 class="card-text"><a href="/user/subscriptions/${userChannel.id}/list">${subscriptionsCount}</a></h3>
                </div>
           </div>
            <div class="col">
                <div class="card p-3 my-3">
                    <h5 class="card-title">Subscribers</h5>
                    <h3 class="card-text"><a href="/user/subscribers/${userChannel.id}/list">${subscribersCount}</a></h3>
                </div>
            </div>
        </div>
    </div>
    <#if isCurrentUser>
        <@e.messageEdit "Message editor"></@e.messageEdit>
    </#if>

    <#include "parts/messageList.ftlh">
</@c.page>
