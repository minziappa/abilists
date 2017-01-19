<#import "../layout/abilistsProjectLayout.ftl" as layout>
<#import "/spring.ftl" as spring/>
<@layout.myLayout>

<script type="text/javascript" src="/static/js/userProjects.js">
</script>

<script>

/*
 * Make the table on the Modal for confirming the data.
 */
function confirmData(tableName) {

	if(!validateForm(tableName)) {
		return;
	}

	// Call the modal
	$(window).ready(function(){
		$('#submitFormUserProjects').modal('show');
	});

	var table1 = document.getElementById(tableName);
	// Create a table on Motal 
	var table3 = document.getElementById("t03");

	var row11, row12, row13, row31, row32, row33;

	row11 = table1.rows[0];
	row12 = table1.rows[1];
	row13 = table1.rows[2];
	row31 = table3.rows[0];
	row32 = table3.rows[1];
	row33 = table3.rows[2];

	var inputUpNameHtml;
	var inputUpMembersHtml;
	var textareaUpExplainHtml;
	var selectUpRoleHtml;
	var selectUpIndustrialHtml;

	inputUpNameHtml = row11.cells[1].getElementsByTagName("input")[0].value;
	row31.cells[1].innerHTML = inputUpNameHtml;
	inputUpMembersHtml = row11.cells[3].getElementsByTagName("select")[0].value;
	row31.cells[3].innerHTML = inputUpMembersHtml;

	textareaUpExplainHtml = row12.cells[1].getElementsByTagName("textarea")[0].value;
	row32.cells[1].innerHTML = textareaUpExplainHtml;
	selectUpRoleHtml = row12.cells[3].getElementsByTagName("select")[0].value;
    <#if commonBean??>
    <#if commonBean.mRoleMap?has_content>
	    <#if commonBean.mRoleMap["${lang?if_exists}"]??>
	    <#list commonBean.mRoleMap["${lang?if_exists}"] as mRole>
			var mrCode = "${mRole.mrCode?if_exists}";
			if(mrCode == selectUpRoleHtml) {
				selectUpRoleHtml = "${mRole.mrName?if_exists}";
			}
	    </#list>
	    </#if>
    </#if>
    </#if>
	row32.cells[3].innerHTML = selectUpRoleHtml;

	selectUpIndustrialHtml = row13.cells[1].getElementsByTagName("select")[0].value;
	<#if commonBean??>
	<#if commonBean.mIndustryMap?has_content>
		<#if commonBean.mIndustryMap["${lang?if_exists}"]??>
		<#list commonBean.mIndustryMap["${lang?if_exists}"] as mIndustry>
			var miCode = "${mIndustry.miCode?if_exists}";
			if(miCode == selectUpIndustrialHtml) {
				selectUpIndustrialHtml = "${mIndustry.miLargeCategory?if_exists}";
			}
		</#list>
		</#if>
	</#if>
	</#if>
	row33.cells[1].innerHTML = selectUpIndustrialHtml;

	if(tableName == "newFormId") {
		document.getElementById("submitForm").setAttribute( "onClick", "javascript: submitNewFormProjects();" );
	} else {
		document.getElementById("submitForm").setAttribute( "onClick", "javascript: submitUpdateFormProjects();" );
	}

}

</script>

<div class="item-box">
	<div class="row">
		<div class="col-md-6">
			<nav class="breadcrumbs">
			<ul>
			<li><a href="#"><@spring.message "navi.title.abilists"/></a></li>
			<li class="active"><a href="#"><@spring.message "path.abilists.projects"/></a></li>
			</ul>
			</nav>
		</div>
		<div class="col-md-6">
			<span id="newToggleId" class="glyphicon glyphicon-chevron-down add-symbol" style="color:#337ab7;" aria-hidden="true" onClick="newFormToggle();"></span>
		</div>
	</div>
</div>

<div class="row">
  <div class="col-md-12">

	<#include "../common/errorMessageNoButton.ftl"/>

	<div align="center" id="completeId" class="list-group-item list-group-item-danger">success</div>

	<div class="item-box" id="newUserProjectForm">
		<form name="newForm" class="form-horizontal" action="istProjects" method="post" id="contact_form" onkeypress="return captureReturnKey(event);">
			<table id="newFormId" style="width:100%">
			<tr>
				<td width="12%"><@spring.message "projects.title.name"/></td>
				<td>
					<input type="text" style="width:100%" name="upName" />
				</td>
				<td width="12%"><@spring.message "projects.title.team.number"/></td>
				<td width="15%">
					<select class="taForm" name="upMembers">
						<option value="0"><@spring.message "projects.title.team.number"/></option>
						<option value="1">1</option>
						<option value="3">2-3</option>
						<option value="7">4-7</option>
						<option value="12">8-12</option>
						<option value="18">13-18</option>
						<option value="25">19-25</option>
						<option value="30">26-30</option>
						<option value="40">31-40</option>
						<option value="50">more than 41</option>
					</select>
				</td>
			</tr>
			<tr>
				<td rowspan="2"><@spring.message "projects.title.explain"/></td>
				<td rowspan="2">
					<textarea class="taForm" name="upExplain" placeholder="explian" rows="1"></textarea>
				</td>
				<td><@spring.message "projects.title.role"/></td>
				<td>
					<select class="taForm" name="upRole">
					<option value="0"><@spring.message "projects.title.role"/></option>
				    <#if commonBean??>
				    <#if commonBean.mRoleMap?has_content>
					    <#if commonBean.mRoleMap["${lang?if_exists}"]??>
					    <#list commonBean.mRoleMap["${lang?if_exists}"] as mRole>
					    	<option value="${mRole.mrCode?if_exists}">${mRole.mrName?if_exists}</option>
					    </#list>
					    </#if>
				    </#if>
				    </#if>
				    </select>
				</td>
			</tr>
			<tr>
				<td><@spring.message "projects.title.industy"/></td>
				<td>
					<select class="taForm" name="upIndustrial">
						<option value="0"><@spring.message "projects.title.industy"/></option>
						<#if commonBean??>
						<#if commonBean.mIndustryMap?has_content>
							<#if commonBean.mIndustryMap["${lang?if_exists}"]??>
							<#list commonBean.mIndustryMap["${lang?if_exists}"] as mIndustry>
								<option value="${mIndustry.miCode?if_exists}">${mIndustry.miLargeCategory?if_exists}</option>
							</#list>
							</#if>
						</#if>
						</#if>
				    </select>
				</td>
			</tr>
			</table>
			<br/>
			<p align="center">
				<button type="button" onclick="return confirmData('newFormId');" class="btn btn-primary" data-toggle="modal"><@spring.message "projects.button.confirm"/></button>
				<button class="btn btn-primary" type="button" onClick="newFormCancel();"><@spring.message "projects.button.cancel"/></button>
			</p>
		  </form>
	  </div>

	  <div class="item-box" id="updateUserProjectForm">
		<form name="updateForm" class="form-horizontal" action="udtProjects" method="post" id="contact_form" onkeypress="return captureReturnKey(event);">
			<table id="updateForm" style="width:100%">
			<tr>
				<td width="12%"><@spring.message "projects.title.name"/></td>
				<td>
					<input id="upNameId" style="width:100%" type="text" name="upName"/>
				</td>
				<td width="12%"><@spring.message "projects.title.team.number"/></td>
				<td width="15%">
					<select id="upMembersId" class="taForm" name="upMembers">
						<option value="0"><@spring.message "projects.title.team.number"/></option>
						<option value="1">1</option>
						<option value="3">2-3</option>
						<option value="7">4-7</option>
						<option value="12">8-12</option>
						<option value="18">13-18</option>
						<option value="25">19-25</option>
						<option value="30">26-30</option>
						<option value="40">31-40</option>
						<option value="50"><@spring.message "projects.select.option1"/></option>
					</select>
				</td>
			</tr>
			<tr>
				<td rowspan="2"><@spring.message "projects.title.explain"/></td>
				<td rowspan="2">
					<textarea id="upExplainId" class="taForm" name="upExplain" placeholder="explian" ></textarea>
				</td>
				<td><@spring.message "projects.title.role"/></td>
				<td>
					<select id="upRoleId" class="taForm" name="upRole">
						<option value="0"><@spring.message "projects.title.role"/></option>
					    <#if commonBean??>
					    <#if commonBean.mRoleMap?has_content>
						    <#if commonBean.mRoleMap["${lang?if_exists}"]??>
						    <#list commonBean.mRoleMap["${lang?if_exists}"] as mRole>
						    	<option value="${mRole.mrCode?if_exists}">${mRole.mrName?if_exists}</option>
						    </#list>
						    </#if>
					    </#if>
					    </#if>
					</select>
				</td>
			</tr>
			<tr>
				<td><@spring.message "projects.title.industy"/></td>
				<td>
					<select id="upIndustrialId" class="taForm" name="upIndustrial">
						<option value="0"><@spring.message "projects.title.industy"/></option>
						<#if commonBean??>
						<#if commonBean.mIndustryMap?has_content>
							<#if commonBean.mIndustryMap["${lang?if_exists}"]??>
							<#list commonBean.mIndustryMap["${lang?if_exists}"] as mIndustry>
								<option value="${mIndustry.miCode?if_exists}">${mIndustry.miLargeCategory?if_exists}</option>
							</#list>
							</#if>
						</#if>
						</#if>
				    </select>
				</td>
			</tr>
			</table>
			<input type="hidden" id="upNoId" name="upNo" />
			<br/>
			<p align="center">
				<button type="button" onclick="return confirmData('updateForm');" class="btn btn-primary" data-toggle="modal"><@spring.message "projects.button.confirm"/></button>
				<button class="btn btn-primary" type="button" onClick="updateFormCancel();"><@spring.message "projects.button.cancel"/></button>
				<a id="projectTechId" href="#" class="btn btn-success" style="width: 90px;" role="button" ><@spring.message "projects.button.tech.input"/></a>
				<button type="button" class="btn btn-danger" style="width: 80px;" onclick="javascript: removeProject();"><@spring.message "projects.button.delete"/></button>
			</p>
		</form>
	  </div>

	  <div class="item-box">
		<table id="userProjectsId" class="table table-striped">
	      <thead>
	        <tr>
	          <th>#</th>
	          <th><@spring.message "projects.title.name"/></th>
	          <th><@spring.message "projects.title.industy"/></th>
	          <th><@spring.message "projects.title.explain"/></th>
	          <th><@spring.message "projects.title.team.number"/></th>
	          <th><@spring.message "projects.title.role"/></th>
	          <th><@spring.message "projects.title.update"/></th>
	        </tr>
	      </thead>
	      <tbody>

	      <#if model??>
	      <#if model.userProjectsList?has_content>
	      <#list model.userProjectsList as userProjects>
	      <tr onmouseover="overChangeColor(this);"  onmouseout="outChangeColor(this);" onclick="selectUserProjects(this, '${userProjects.upNo?if_exists}');">
	      	<td>${userProjects.upNo?if_exists}</td>
	      	<td>${userProjects.upName?if_exists}</td>
	      	<td>
				<#if commonBean??>
				<#if commonBean.mIndustryMap?has_content>
					<#if commonBean.mIndustryMap["${lang?if_exists}"]??>
					<#list commonBean.mIndustryMap["${lang?if_exists}"] as mIndustry>
						<#if userProjects.upIndustrial??>
						<#if userProjects.upIndustrial == "${mIndustry.miCode?if_exists}">
						${mIndustry.miLargeCategory?if_exists}
						</#if>
						</#if>
					</#list>
					</#if>
				</#if>
				</#if>
	      	</td>
	      	<td>${userProjects.upExplain?if_exists}</td>
	      	<td>${userProjects.upMembers?if_exists}</td>
	      	<td>
			    <#if commonBean??>
			    <#if commonBean.mRoleMap?has_content>
				    <#if commonBean.mRoleMap["${lang?if_exists}"]??>
				    <#list commonBean.mRoleMap["${lang?if_exists}"] as mRole>
						<#if userProjects.upRole??>
			    		<#if userProjects.upRole == "${mRole.mrCode?if_exists}">
			    			${mRole.mrName?if_exists}
			    		</#if>
			    		</#if>
				    </#list>
				    </#if>
			    </#if>
			    </#if>
	      	</td>
	      	<td>${userProjects.updateTime?string('yyyy/MM/dd')?if_exists}</td>
	      </tr>
	      </#list>
	      </#if>
	      </#if>
	      </tbody>
	      </table>
      </div><!-- item-box -->

      <nav class="text-center">
	    <ul class="pagination">
	    <#if model?exists>
      	<#if model.paging?exists>
			<#if model.paging.prevPage?exists>
			<li><a href="/project/sltProjectsList?nowPage=${model.paging.prevPage.nowPage}&allCount=${model.paging.allCount?c}" title="Prev" accesskey="*">Prev</span></a></li>
			</#if>
			<#if model.paging.pagingInfoList?has_content>
				<#list model.paging.pagingInfoList as pageList>
					<#if model.paging.nowPage?if_exists == pageList.pageNumber?if_exists>
					<li class="active"><a href="#">${pageList.pageNumber} <span class="sr-only">(current)</span></a></li>
					<#else>
					<li><a href="/project/sltProjectsList?nowPage=${pageList.pageNumber}&allCount=${model.paging.allCount?c}">${pageList.pageNumber}</a></li>
					</#if>
				</#list>
			</#if>
			<#if model.paging.nextPage?exists>
			<li><a href="/project/sltProjectsList?nowPage=${model.paging.nextPage.nowPage}&allCount=${model.paging.allCount?c}" accesskey="#" title="Next">Next</a></li>
			</#if>
		</#if>
		</#if>
      	</ul>
      </nav><!-- end #nav -->

	</div><!-- col-md-12 -->
</div><!-- row -->


<!-- Insert and Update Modal -->
<div class="modal fade" id="submitFormUserProjects" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span>
        	<span class="sr-only"><@spring.message "projects.button.close"/></span>
        </button>
        <h4 class="modal-title" id="myModalLabel"><@spring.message "projects.title.modal"/></h4>
      </div>

      <div id="confirmMessage" class="modal-body">

		<table id="t03" style="width:100%">
		<tr>
			<td width="12%"><@spring.message "projects.title.name"/></td>
			<td></td>
			<td width="12%"><@spring.message "projects.title.team.number"/></td>
			<td width="15%"></td>
		</tr>
		<tr>
			<td rowspan="2"><@spring.message "projects.title.explain"/></td>
			<td rowspan="2"></td>
			<td><@spring.message "projects.title.role"/></td>
			<td></td>
		</tr>
		<tr>
			<td><@spring.message "projects.title.industy"/></td>
			<td></td>
		</tr>
		</table>

      </div>

      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal"><@spring.message "projects.button.close"/></button>
        <button id="submitForm" type="button" class="btn btn-primary"><@spring.message "projects.button.save"/></button>
      </div>
    </div>
  </div>
</div>

<!-- Delete Modal -->
<div class="modal fade" id="submitFormDeleteProjects" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
<div class="modal-dialog">
  <div class="modal-content">
    <div class="modal-header">
      <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span>
      	<span class="sr-only">Close</span>
      </button>
      <h4 class="modal-title" id="dltProjectTileId"><@spring.message "projects.title.modal"/></h4>
    </div>

    <div id="confirmMessage" class="modal-body">
    	<@spring.message "projects.confirm.message.delete"/>
    </div>

    <div class="modal-footer">
      <button type="button" class="btn btn-default" data-dismiss="modal"><@spring.message "projects.button.close"/></button>
      <button id="submitForm" type="button" class="btn btn-danger" onclick="javascript: submitDeleteFormProjects();">
      	  <@spring.message "projects.button.delete"/>
      </button>
    </div>
  </div>
</div>
</div>

<script type="text/javascript"> 

var table = document.getElementById("userProjectsId");
var tr = table.getElementsByTagName("tr");

function clearBackGroundColor() {
	for(var j=0; j< tr.length; j++) {
		tr[j].style.backgroundColor = "";
	}
}

var upNoInput = document.getElementById("upNoId");
var upNameInput = document.getElementById("upNameId");
var upMembersInput = document.getElementById("upMembersId");
var upIndustrialInput = document.getElementById("upIndustrialId");
var upExplainInput = document.getElementById("upExplainId");
var upRoleInput = document.getElementById("upRoleId");
var projectTechInput = document.getElementById("projectTechId");

function selectUserProjects(x, num) {

	clearBackGroundColor();

	$(document).ready(function() {

		var availableKeys;

        $.ajax({
            type: 'POST',
            url: 'sltProjectsAjax',
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
            dataType: "json",
            data: 'body={ "upNo" : "' + num + '"}',
            cache: false,
            beforeSend: function(xhr, settings) {
            	console.log("before send");
            },
            success: function(data, textStatus, request) {
            	if(!isBlank(data.result)) {
            		availableKeys = data.result;
            		upNoInput.value = availableKeys.upNo;
            		upNameInput.value = availableKeys.upName;
            		upMembersInput.value = availableKeys.upMembers;
            		upIndustrialInput.value = availableKeys.upIndustrial;
            		upExplainInput.value = availableKeys.upExplain;
            		upRoleInput.value = availableKeys.upRole;
            		projectTechInput.href = "sltProjectTechList/" + availableKeys.upNo
            	}
            },
            complete: function(xhr, textStatus) {
            	console.log("complete");

            	// Set a project title into Model
            	var dltProjectTile = document.getElementById("dltProjectTileId");
            	dltProjectTile.innerHTML = upNameInput.value;

            	formSlideDown();
            	newFormCancel();

            	x.style.backgroundColor = "#9999CC";
            },
            error: function(xhr, status) {
            	console.log("error >> " + xhr.responseText);
                var contentType = xhr.getResponseHeader("Content-Type");
                if (xhr.status === 200 && contentType.toLowerCase().indexOf("text/html") >= 0) {
                    // Login has expired - reload our current page
                    window.location.reload();
                }
            }
        });
	});

}


function newFormToggle() {
	$("#newUserProjectForm").slideToggle("slow");
	$("#newToggleId").toggleClass('glyphicon-chevron-down glyphicon-chevron-up');

	upNoInput.value = "";
	upNameInput.value = "";
	upMembersInput.value = "";
	upIndustrialInput.value = "";
	upExplainInput.value = "";
	upRoleInput.value = "";

	clearBackGroundColor();
	updateFormCancel();
}

function formSlideDown() {
	$("#updateUserProjectForm").slideDown("slow");
}

function newFormCancel() {
	$("#newUserProjectForm").slideUp("slow");
	$("#newToggleId").addClass('glyphicon-chevron-down').removeClass('glyphicon-chevron-up');
	clearBackGroundColor();
}

function updateFormCancel() {
	$("#updateUserProjectForm").slideUp("slow");
	$("#newToggleId").addClass('add-symbol');
	clearBackGroundColor();
}

function submitNewFormProjects() {
	document.newForm.submit();
}

function submitUpdateFormProjects() {
	document.updateForm.submit();
}

function submitDeleteFormProjects() {
	document.updateForm.action = "dltProjects";
	document.updateForm.submit();
}

</script>

<#include "../common/abilistsFooder.ftl"/>

</@layout.myLayout>