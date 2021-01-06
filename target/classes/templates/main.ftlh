<#import "parts/common.ftlh" as c>
<@c.page>
    <div class="row col-sm-4">
        <form method="get" action="/main" >
            <div class="input-group mb-3">
                <input type="text" class="form-control" placeholder="Input tag" aria-label="Input tag" aria-describedby="button-search" name="filter" value="${filter?ifExists}">
                <button class="btn btn-outline-primary" type="submit" id="button-search">Search</button>
            </div>
        </form>
    </div>
    <button class="btn btn-primary" type="button" data-bs-toggle="collapse" data-bs-target="#collapseExample" aria-expanded="false" aria-controls="collapseExample">
        Add new message
    </button>
    <div class="collapse mt-3" id="collapseExample">
        <form method="post" enctype="multipart/form-data">
            <div class="row mb-3">
                <label for="input-message" class="form-label col-sm-2" >Input message</label>
                <div class="col-sm-4" >
                    <input type="text" class="form-control col-sm-4"  id="input-message" placeholder="Input message" name="text">
                </div>
            </div>
            <div class="row mb-3">
                <label for="input-tag" class="form-label col-sm-2" >Input tag</label>
                <div class="col-sm-4" >
                    <input type="text" class="form-control"  id="input-tag" placeholder="Input tag" name="tag">
                </div>
            </div>

            <div class="row mb-3">
                <div class="col-sm-6" >
                    <input type="file" class="form-control" id="input-file" name="file">
                </div>
            </div>

            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <button class="btn btn-primary px-5" type="submit">Add</button>
        </form>
    </div>

    <h3 class="mt-5">List messages<h5>
        <div class="row row-cols-1 row-cols-md-4 g-4">
            <#list messages as message>
                <div class="col">
                    <div class="card m-1">
                        <#if message.filename??>
                            <img src="/img/${message.filename}" class="card-img-top">
                        </#if>
                        <div class="card-body">
                            <h5 class="card-title">${message.tag}</h5>
                            <p class="card-text">${message.text}</p>
                        </div>
                        <div class="card-footer">
                            <small class="text-muted">${message.authorName}</small>
                        </div>
                    </div>
                </div>
            <#else>
                <h5>No message</h5>
            </#list>
        </div>
    <div>
</@c.page>
