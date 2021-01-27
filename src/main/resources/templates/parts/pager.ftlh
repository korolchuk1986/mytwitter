<#macro pager url page>
    <nav aria-label="Page navigation" class="mt-3">
        <ul class="pagination justify-content-center">
            <li class="page-item <#if page.getNumber() == 0>disabled</#if>">
                <a class="page-link" href="${url}?page=${page.getNumber() - 1}&size=${page.getSize()}" tabindex="-1">Previous</a>
            </li>
            <#list 0..page.getTotalPages() - 1 as p>
                <li class="page-item  <#if page.getNumber() = p>active</#if>"><a class="page-link" href="${url}?page=${p}&size=${page.getSize()}">${p + 1}</a></li>
            </#list>
            <li class="page-item <#if page.getNumber() = page.getTotalPages() - 1>disabled</#if>">
                <a class="page-link" href="${url}?page=${page.getNumber() + 1}&size=${page.getSize()}">Next</a>
            </li>
        </ul>
    </nav>
    <nav aria-label="Page navigation" class="mt-3">
        <ul class="pagination justify-content-center">
            <li class="page-item disabled">
                <a class="page-link" href="#" tabindex="-1">Count messages in page</a>
            </li>
            <#list [4, 8, 12, 16] as c>
                <li class="page-item  <#if page.getSize() = c>active</#if>"><a class="page-link" href="${url}?page=${page.getNumber()}&size=${c}">${c}</a></li>
            </#list>
        </ul>
    </nav>
</#macro>