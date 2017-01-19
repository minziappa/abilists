<#import "../layout/abilistsProjectLayout.ftl" as layout>
<#import "/spring.ftl" as spring/>
<@layout.myLayout>

<style>

/*
 * Change the modal size.
 */
@media screen and (min-width: 768px) {
	#myModal .modal-dialog  {width:900px;}
}

</style>


<div class="item-box">
<div class="row">
	<div class="col-md-6">
		<nav class="breadcrumbs">
		<ul>
		<li><a href="/project/sltProjectsList"><@spring.message "navi.title.abilists"/></a></li>
		<li class="active"><a href="#"><@spring.message "navi.title.project.tech"/></a></li>
		</ul>
		</nav>
	</div>
	<div class="col-md-6"></div>
</div>
</div>

<div class="row">
  <div class="col-md-12">

	<#include "../common/errorMessage.ftl"/>

	  <div class="item-box">
		<table id="userProjectTechId" class="table table-striped">
	      <thead>
	        <tr>
	          <th><@spring.message "tech.title.number"/></th>
	          <th><@spring.message "tech.title.kind"/></th>
	          <th><@spring.message "tech.title.tech"/></th>
	          <th><@spring.message "tech.title.level"/></th>
	          <th><@spring.message "tech.title.detail"/></th>
	          <th><@spring.message "tech.title.update"/></th>
	        </tr>
	      </thead>
	      <tbody>
	
	      <#if model??>
	      <#if model.userProjectTechList?has_content>
	      <#list model.userProjectTechList as userProjectTech>
	      <tr>
	      	<td>${userProjectTech.uptNo?if_exists}</td>
	      	<td>${userProjectTech.uptKind?if_exists}</td>
	      	<td>
				<#if commonBean??>
				<#if commonBean.getMtechMap("skills")?has_content>
					<#list commonBean.getMtechMap("skills")[userProjectTech.uptKind] as tech>
						<#if tech.mtNo == userProjectTech.mtNo>
							${tech.mtName?if_exists}
						</#if>
					</#list>
				</#if>
				</#if>
	      	</td>
	      	<td>
				<#if commonBean??>
				<#if commonBean.mTechDetailMap[userProjectTech.mtNo?string]??>
					<#list commonBean.mTechDetailMap[userProjectTech.mtNo?string] as techDetail>
						<#if techDetail.mtdLevel?string == userProjectTech.uptLevel>
						<#if techDetail.mlCode == lang?if_exists>
							${techDetail.mtdLevelExplain?if_exists}
						</#if>
						</#if>
					</#list>
				</#if>
				</#if>
	      	</td>
	      	<td>${userProjectTech.uptDetail?if_exists}</td>
	      	<td>${userProjectTech.updateTime?string('yyyy/MM/dd')?if_exists}</td>
	      </tr>
	      </#list>
	      </#if>
	      </#if>
	      </tbody>
		</table>
	</div>

    <nav class="text-center">
	    <ul class="pagination">
    <#if model?exists>
    	<#if model.paging?exists>
			<#if model.paging.prevPage?exists>
			<li><a href="/project/sltProjectTechList?nowPage=${model.paging.prevPage.nowPage}&allCount=${model.paging.allCount?c}" title="Prev" accesskey="*">Prev</span></a></li>
			</#if>
			<#if model.paging.pagingInfoList?has_content>
				<#list model.paging.pagingInfoList as pageList>
					<#if model.paging.nowPage?if_exists == pageList.pageNumber?if_exists>
					<li class="active"><a href="#">${pageList.pageNumber} <span class="sr-only">(current)</span></a></li>
					<#else>
					<li><a href="/project/sltProjectTechList?nowPage=${pageList.pageNumber}&allCount=${model.paging.allCount?c}">${pageList.pageNumber}</a></li>
					</#if>
				</#list>
			</#if>
			<#if model.paging.nextPage?exists>
			<li><a href="/project/sltProjectTechList?nowPage=${model.paging.nextPage.nowPage}&allCount=${model.paging.allCount?c}" accesskey="#" title="Next">Next</a></li>
			</#if>
		</#if>
	  </#if>
    	</ul>
    </nav><!-- end #nav -->
  
  </div><!-- col-md-12 -->
</div><!-- row -->


</@layout.myLayout>