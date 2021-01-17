<#macro messageEdit subject>
    <button class="btn btn-primary" type="button" data-bs-toggle="collapse" data-bs-target="#collapseExample" aria-expanded="false" aria-controls="collapseExample">
        ${subject}
    </button>
    <div class="collapse mt-3 <#if message??>show</#if>" id="collapseExample">
        <form method="post" enctype="multipart/form-data" novalidate>
            <div class="row mb-3">
                <label for="input-message" class="form-label col-sm-2" >Input message</label>
                <div class="col-sm-4" >
                    <input type="text" class="form-control <#if textError??>is-invalid</#if>"  id="input-message" placeholder="Input message" name="text" value="<#if message??>${message.text}</#if>">
                    <#if textError??>
                        <div class="invalid-feedback d-block">
                            ${textError}
                        </div>
                    </#if>
                </div>
            </div>
            <div class="row mb-3">
                <label for="input-tag" class="form-label col-sm-2" >Input tag</label>
                <div class="col-sm-4" >
                    <input type="text" class="form-control <#if tagError??>is-invalid</#if>"  id="input-tag" placeholder="Input tag" name="tag" value="<#if message??>${message.tag}</#if>">
                    <#if tagError??>
                        <div class="invalid-feedback d-block">
                            ${tagError}
                        </div>
                    </#if>
                </div>
            </div>

            <div class="row mb-3">
                <div class="col-sm-6" >
                    <input type="file" class="form-control" id="input-file" name="file">
                </div>
            </div>

            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <input type="hidden" name="id" value="<#if message??>${message.id}</#if>">
            <button class="btn btn-primary px-5" type="submit">Save</button>
        </form>
    </div>
</#macro>
