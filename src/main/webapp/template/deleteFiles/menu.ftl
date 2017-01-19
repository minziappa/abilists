<#import "/spring.ftl" as spring/>
	<ul class="nav nav-tabs" role="tablist">
		<li <#if model??><#if model.menu??><#if model.menu == "usersList">class="active"</#if></#if></#if>><a href="/admin/usersList"><@spring.message "admin.menu.users"/></a></li>
		<li <#if model??><#if model.menu??><#if model.menu == "userProjectsList">class="active"</#if></#if></#if>><a href="/admin/userProjectsList"><@spring.message "admin.menu.projects"/></a></li>
		<li <#if model??><#if model.menu??><#if model.menu == "userRolesList">class="active"</#if></#if></#if>><a href="/admin/userRolesList"><@spring.message "admin.menu.roles"/></a></li>
	</ul>