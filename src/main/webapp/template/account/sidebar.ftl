<#import "/spring.ftl" as spring/>

    <div class="list-group">
        <a href="/account/introInfo" class="list-group-item <#if model??><#if model.sidebar??><#if model.sidebar == "introInfo">active</#if></#if></#if>">
            <@spring.message "settings.subtitle.change.info"/>
            <span class="pull-right"><#if model??><#if model.sidebar??><#if model.sidebar == "introInfo"> <i class="glyphicon glyphicon-menu-right"></i></#if></#if></#if></span>
        </a>
        <a href="/account/introPicture" class="list-group-item <#if model??><#if model.sidebar??><#if model.sidebar == "introPicture">active</#if></#if></#if>">
	        <@spring.message "settings.subtitle.change.picture"/>
	        <span class="pull-right"><#if model??><#if model.sidebar??><#if model.sidebar == "introPicture"> <i class="glyphicon glyphicon-menu-right"></i></#if></#if></#if></span>
        </a>
	    <a href="/account/introChangingPwd" class="list-group-item <#if model??><#if model.sidebar??><#if model.sidebar == "introChangingPwd">active</#if></#if></#if>">
		    <@spring.message "settings.subtitle.change.password"/>
		    <span class="pull-right"><#if model??><#if model.sidebar??><#if model.sidebar == "introChangingPwd"> <i class="glyphicon glyphicon-menu-right"></i></#if></#if></#if></span>
	    </a>
		<a href="/account/introChangingEmail" class="list-group-item <#if model??><#if model.sidebar??><#if model.sidebar == "introChangingEmail">active</#if></#if></#if>">
			<@spring.message "settings.subtitle.change.email"/>
			<span class="pull-right"><#if model??><#if model.sidebar??><#if model.sidebar == "introChangingEmail"> <i class="glyphicon glyphicon-menu-right"></i></#if></#if></#if></span>
		</a>
    </div>

    
    
    
