<#include "security.ftlh">
<#import "login.ftlh" as l>
<nav class="navbar navbar-expand-lg navbar-light bg-light mb-3">
    <div class="container-fluid">
        <a class="navbar-brand" href="/">MyTwitter</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link"  href="/">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link"  href="/main">Messages</a>
                </li>
                <#if user??>
                    <li class="nav-item">
                        <a class="nav-link"  href="/user-messages/${currentUserId}">My messages</a>
                    </li>
                </#if>
                <#if isAdmin>
                    <li class="nav-item">
                        <a class="nav-link"  href="/user">User List</a>
                    </li>
                </#if>
                <#if user??>
                    <li class="nav-item">
                        <a class="nav-link"  href="/user/profile">Profile</a>
                    </li>
                </#if>
            </ul>
            <span class="navbar-text me-3">
                ${name}
            </span>
            <@l.logout ></@l.logout>
        </div>
    </div>
</nav>

