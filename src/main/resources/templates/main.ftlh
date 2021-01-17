<#import "parts/common.ftlh" as c>
<#import "parts/messageEdit.ftlh" as e>
<@c.page>
    <div class="row col-sm-4">
        <form method="get" action="/main" >
            <div class="input-group mb-3">
                <input type="text" class="form-control" placeholder="Input tag" aria-label="Input tag" aria-describedby="button-search" name="filter" value="${filter?ifExists}">
                <button class="btn btn-outline-primary" type="submit" id="button-search">Search</button>
            </div>
        </form>
    </div>


    <@e.messageEdit "Add message"></@e.messageEdit>
    <#include "parts/messageList.ftlh">

</@c.page>
