<#include "security.ftlh">
<h3 class="mt-3">List messages</h3>
<div class="row row-cols-1 row-cols-md-4 g-4">
    <#list messages as message>
        <div class="col">
            <div class="card m-1">
                <#if message.filename??>
                    <img src="/img/${message.filename}" class="card-img-top">
                </#if>
                <div class="card-body">
                    <p class="card-text">${message.text}</p>
                    <h5 class="card-title">#${message.tag}</h5>
                </div>
                <div class="card-footer">
                    <small class="text-muted"><a href="/user-messagers/${message.author.id}">${message.authorName}</a></small>
                    <#if message.author.id = currentUserId>
                        <small class="text-muted"><a class="btn btn-primary btn-sm " href="/user-messagers/${message.author.id}?message=${message.id}">Edit message</a></small>
                    </#if>
                </div>
            </div>
        </div>
    <#else>
        <h5>No message</h5>
    </#list>
</div>
