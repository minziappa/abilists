<#import "/spring.ftl" as spring/>
	<ul class="nav nav-tabs" role="tablist">
		<li <#if model??><#if model.menu??><#if model.menu == "usersList">class="active"</#if></#if></#if>><a href="/admin/usersList"><@spring.message "admin.menu.users"/></a></li>
		<li <#if model??><#if model.menu??><#if model.menu == "sltMTechList">class="active"</#if></#if></#if>><a href="/admin/sltMTechList"><@spring.message "admin.menu.mtech"/></a></li>
		<li <#if model??><#if model.menu??><#if model.menu == "sltMIndustryList">class="active"</#if></#if></#if>><a href="/admin/sltMIndustryList"><@spring.message "admin.menu.mindustry"/></a></li>
		<li <#if model??><#if model.menu??><#if model.menu == "sltMRoleList">class="active"</#if></#if></#if>><a href="/admin/sltMRoleList"><@spring.message "admin.menu.mrole"/></a></li>
		<li <#if model??><#if model.menu??><#if model.menu == "sltNotiList">class="active"</#if></#if></#if>><a href="/noti/sltNotiList"><@spring.message "noti.menu.notification"/></a></li>
	</ul>