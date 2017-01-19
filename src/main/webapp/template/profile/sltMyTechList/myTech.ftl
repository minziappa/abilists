<#import "../../layout/abilistsMyProfileLayout.ftl" as layout>
<#import "/spring.ftl" as spring/>
<@layout.myLayout>

<style>

.widget .hvr-bounce-to-right.bg-green {
    background-color: #2ecc71 !important;
    color: white !important;
}
.widget .hvr-bounce-to-right.bg-green::before {
    background: #4ddd89 none repeat scroll 0 0 !important;
}
.hvr-bounce-to-right {
    backface-visibility: hidden;
    box-shadow: 0 0 1px rgba(0, 0, 0, 0);
    display: inline-block;
    position: relative;
    transform: translateZ(0px);
    transition-duration: 0.5s;
    transition-property: color;
    vertical-align: middle;
}
.hvr-bounce-to-right::before {
    background: #2098d1 none repeat scroll 0 0;
    bottom: 0;
    content: "";
    left: 0;
    position: absolute;
    right: 0;
    top: 0;
    transform: scaleX(0);
    transform-origin: 0 50% 0;
    transition-duration: 0.5s;
    transition-property: transform;
    transition-timing-function: ease-out;
    z-index: -1;
}
.hvr-bounce-to-right:active, .hvr-bounce-to-right:focus, .hvr-bounce-to-right:hover {
    color: #fff;
}

.add-plus-symbol {
	cursor: pointer;
	float: right;
	font-size: 20px;
	margin-right: 10px;
}

</style>

<script>

/* Load select data from the server.*/
var mtechList = {};
<#if commonBean??>
<#if commonBean.getMtechMap("${model.menu?if_exists}")?has_content>
	<#list commonBean.getMtechMap("${model.menu?if_exists}")?keys as key>
	<#if key != "">
	mtechList['${key?if_exists}'] = [       
	    <#list commonBean.getMtechMap("${model.menu?if_exists}")[key] as tech>
	    	{mtNo : "${tech.mtNo?if_exists}",mtName: "${tech.mtName?if_exists}"}<#if tech?has_next>,</#if>
	    </#list>
	];
	</#if>
	</#list>
</#if>
</#if>

/*
 * Add a input element which is inputed by user
 */
function addElementSkill(tableName, cntName) {

	  var cntId = document.getElementById(cntName);
	  var currentNum = cntId.value;
	  var addedNum = (parseInt(currentNum) + 1);

	  console.log("cnt >> " + addedNum);

	  // Save the increase number.
	  cntId.value = addedNum;

	  // Create a table
	  var table = document.getElementById(tableName);

	  // Create a row
	  var row = table.insertRow(cntId.value);
	  var trNum = addedNum - 1;

	  // Create cells
	  var cell1 = row.insertCell(0);
	  var cell2 = row.insertCell(1);
	  var cell3 = row.insertCell(2);
	  var cell4 = row.insertCell(3);
	  var cell5 = row.insertCell(4);

	  var tdHtml1 = '<select name="utKind" style="width: 100%;" onchange="changeUtKindSelect('+trNum+')">' + 
	  	'<option value="0"><@spring.message "select.default.item"/></option>' +
		<#if commonBean??>
		<#if commonBean.getMtechMap("${model.menu?if_exists}")?has_content>
			<#list commonBean.getMtechMap("${model.menu?if_exists}")?keys as key>
			'<option value="${key?if_exists}">${key?if_exists}</option>' +
			</#list>
		</#if>
		</#if>
	  	'</select>';

      var tdHtml2 = '<select name="mtNo" style="width: 100%;" onchange="changeUtLevelSelect('+trNum+')">' +
    	  '<option value="0"><@spring.message "select.default.item"/></option>' +
    	  '</select>';

      var tdHtml3 = '<select name="utLevel" style="width: 100%;"><option value="0"><@spring.message "select.default.proficiency"/></option></select>';

      var tdHtml4 = '<textarea class="taForm" style="height: 45px;" name="utDetail" placeholder="Detail" rows="1"></textarea>';
      var tdHtml5 = '<button type="button" class="btn btn-danger" onclick=\'removeElement("'+tableName+'", "'+cntName+'", "'+trNum+'")\'>Remove</button>';
	  cell1.innerHTML = tdHtml1;
	  cell2.innerHTML = tdHtml2;
	  cell3.innerHTML = tdHtml3;
	  cell4.innerHTML = tdHtml4;
	  cell5.innerHTML = tdHtml5;

	  var inputHtml = document.createElement("input");
	  inputHtml.setAttribute("type", "hidden");
	  inputHtml.setAttribute("name", "req");
	  inputHtml.setAttribute("value", "insert");
	  row.appendChild(inputHtml);
}
</script>


<link href="/static/css/addRows.css" rel="stylesheet">
<script src="/static/js/addRows.js"></script>

<div class="item-box left-line-box">
<nav class="breadcrumbs">
	<ul>
	<li><a href="#"><@spring.message "path.abilists.profile"/></a></li>
	<li class="active">
		<a href="#">
			<#if model.menu == "techs">
			<@spring.message "profile.menu.techs"/>
			<#elseif model.menu == "skills">
				<@spring.message "profile.menu.skills"/>
			<#elseif model.menu == "lang">
				<@spring.message "profile.menu.lang"/>
			<#elseif model.menu == "certi">
				<@spring.message "profile.menu.certi"/>
			</#if>
		</a>
	</li>
	</ul>
</nav>

</div>

<div class="row">
<div class="col-md-12">
	<#include "../../common/errorMessage.ftl"/>

	<div align="center" id="completeId" class="list-group-item list-group-item-danger">complete</div>

  	<div class="caption mittle-size-title">
  		<h4>
			<#if model.menu == "techs">
				<@spring.message "profile.menu.techs"/>
			<#elseif model.menu == "skills">
				<@spring.message "profile.menu.skills"/>
			<#elseif model.menu == "lang">
				<@spring.message "profile.menu.lang"/>
			<#elseif model.menu == "certi">
				<@spring.message "profile.menu.certi"/>
			</#if>
  			<span id="newToggleId" class="glyphicon glyphicon-plus add-plus-symbol" style="color:#337ab7;" aria-hidden="true" onclick="addElementSkill('t01', 'cnt01')"></span>
  		</h4>
  	</div>
  	<div class="item-box">
		<form name="formUserTechSkill" action="/profile/udtUserTech" method="post" onkeypress="return captureReturnKey(event);">

			<table id="t01">
	  		<tr style="background-color: #eeeeec;">
			  <th class="my_profile">
				<#if model.menu == "skills">
			  		<@spring.message "profile.table.tech.title"/>
				<#elseif model.menu == "lang">
			  		<@spring.message "profile.table.language.title"/>
				<#elseif model.menu == "certi">
			  		<@spring.message "profile.table.item.title"/>
				</#if>
			  </th>
			  <th class="my_profile">
				<#if model.menu == "skills">
			  		<@spring.message "profile.table.skill.title"/>
				<#elseif model.menu == "lang">
			  		<@spring.message "profile.table.part.title"/>
				<#elseif model.menu == "certi">
			  		<@spring.message "profile.table.certi.title"/>
				</#if>
			  </th>
			  <th class="my_profile">
			  	<@spring.message "profile.table.proficiency.title"/>
			  </th>
			  <th class="my_profile">
			  	<@spring.message "profile.table.addition.title"/>
			  </th>
			  <th><@spring.message "profile.table.remove.title"/></th>
			</tr>
			<#if model??>
			<#if model.userTechList?has_content>
			<#list model.userTechList as userTech>
			<tr>
				<td>
					<select name="utKind" style="width: 100%;" onchange="changeUtKindSelect(${userTech_index})">
						<option value="0">
						<#if model.menu == "skills">
							<@spring.message "select.default.tech"/>
						<#elseif model.menu == "lang">
							<@spring.message "select.default.lang"/>
						<#elseif model.menu == "certi">
							<@spring.message "select.default.certi"/>
						</#if>
						</option>
						<#if commonBean??>
						<#if commonBean.getMtechMap("${model.menu?if_exists}")?has_content>
							<#list commonBean.getMtechMap("${model.menu?if_exists}")?keys as key>
							<option value="${key?if_exists}" <#if userTech.utKind == "${key?if_exists}">selected</#if>>${key?if_exists}</option>
							</#list>
						</#if>
						</#if>
					</select>
				</td>
				<td>
					<select name="mtNo" style="width: 100%;" onchange="changeUtLevelSelect(${userTech_index})">
						<option value="0">
						<#if model.menu == "skills">
							<@spring.message "select.default.skill"/>
						<#elseif model.menu == "lang">
							<@spring.message "select.default.part"/>
						<#elseif model.menu == "certi">
							<@spring.message "select.default.certi"/>
						</#if>
						</option>
						<#if commonBean??>
						<#if commonBean.getMtechMap("${model.menu?if_exists}")?has_content>
							<#list commonBean.getMtechMap("${model.menu?if_exists}")?keys as key>
								<#if userTech.utKind == "${key?if_exists}">
									<#list commonBean.getMtechMap("${model.menu?if_exists}")[key] as tech>
										<option value="${tech.mtNo?if_exists}" <#if userTech.mtNo == tech.mtNo>selected</#if>>${tech.mtName?if_exists}</option>
									</#list>
								</#if>
							</#list>
						</#if>
						</#if>
					</select>
				</td>
				<td>
					<select name="utLevel" style="width: 100%;">
					<option value="0"><@spring.message "select.default.proficiency"/></option>
						<#if commonBean??>
						<#if commonBean.mTechDetailMap?has_content>
							<#list commonBean.mTechDetailMap?keys as key>
								<#if userTech.mtNo?string = "${key?if_exists}">
									<#list commonBean.mTechDetailMap[key] as techDetail>
	
										<#if techDetail.mlCode?has_content>
										<#if techDetail.mlCode == "${lang?if_exists}">
										<option value="${techDetail.mtdLevel?string?if_exists}" <#if userTech.utLevel?string == techDetail.mtdLevel?string>selected</#if>>${techDetail.mtdLevelExplain?if_exists}</option>
										</#if>
										</#if>
	
									</#list>
								</#if>
							</#list>
						</#if>
						</#if>
					</select>
				</td>
				<td>
					<textarea class="taForm" style="height: 45px;" name="utDetail" placeholder="Detail" rows="1">${userTech.utDetail?if_exists}</textarea>
				</td>
				<td><button type="button" class="btn btn-danger" onclick="removeElement('t01', 'cnt01', ${userTech_index + 1})"><@spring.message "profile.table.remove.title"/></button></td>
			</tr>
			<input type="hidden" name="req" value="update"/>
			</#list>
			<input type="hidden" name="cnt" value="${model.userTechList?size}" id="cnt01" autocomplete="off" />
			<#else>
			<tr>
				<td>
					<select name="utKind" style="width: 100%;" onchange="changeUtKindSelect(0)">
						<option value="0">
						<#if model.menu == "skills">
							<@spring.message "select.default.skill"/>
						<#elseif model.menu == "lang">
							<@spring.message "select.default.lang"/>
						<#elseif model.menu == "certi">
							<@spring.message "select.default.certi"/>
						</#if>
						</option>
					<#if commonBean??>
					<#if commonBean.getMtechMap("${model.menu?if_exists}")?has_content>
						<#list commonBean.getMtechMap("${model.menu?if_exists}")?keys as key>
						<option value="${key?if_exists}">${key?if_exists}</option>
						</#list>
					</#if>
					</#if>
					</select>
				</td>
				<td>
					<select name="mtNo" style="width: 100%;" onchange="changeUtLevelSelect(0)">
					</select>
				</td>
				<td>
					<select name="utLevel" style="width: 100%;">
					</select>
				</td>
				<td>
					<textarea class="taForm" name="utDetail" placeholder="Detail" rows="1"></textarea>
				</td>
				<td><button type="button" onclick="removeElement('t01', 'cnt01', 0)">Remove</button></td>
			</tr>
			<input type="hidden" name="cnt" value="0" id="cnt01" autocomplete="off" />
			<input type="hidden" name="req" value="insert"/>
			</#if>
			</#if>
			</table>
			<input type="hidden" name="utSkill" value="${model.menu?if_exists}"/>
		</form>
		<br/>
	
		<p align="center">
			<button type="button" onclick="return confirmData('t01','cnt01');" class="btn btn-primary" data-toggle="modal">Confirm</button>
		</p>
	</div>
  </div><!-- col-sm-12 -->
</div><!-- row -->

<!-- Modal to confirm and save-->
<div class="modal fade" id="submitFormUserSettings" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel"><@spring.message "profile.table.title.title"/></h4>
      </div>

      <div class="modal-body">
	  	<table id="t03">
	  		<tr>
			  <th><@spring.message "profile.table.tech.title"/></th>
			  <th><@spring.message "profile.table.skill.title"/></th>
			  <th><@spring.message "profile.table.proficiency.title"/></th>
			  <th><@spring.message "profile.table.addition.title"/></th>
			</tr>
		</table>
      </div>

      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal" onclick="deleteTr('t03');">Close</button>
        <button id="submitForm" type="button" class="btn btn-primary" onclick="javascript: submitform();">Save Data</button>
      </div>
    </div>
  </div>
</div>

<script>

/* Change the next select list depending on the first select.*/
function changeUtKindSelect(changedNum) {
	// This is the first select.
	var utKindList = document.getElementsByName("utKind")[changedNum];
	// This is the second select.
	var mtNoList = document.getElementsByName("mtNo")[changedNum];
	var userSelected = utKindList.options[utKindList.selectedIndex].value;

	// Delete the select's options
	while (mtNoList.options.length) {
		mtNoList.remove(0);
	}

	// Get a option of the first select.
	var mtechs = mtechList[userSelected];
	if (mtechs) {
		var i;
		var mtech = new Option('<@spring.message "select.default.item"/>', '0');
		mtNoList.options.add(mtech);
		for (i = 0; i < mtechs.length; i++) {
			mtech = new Option(mtechs[i].mtName, mtechs[i].mtNo);
			mtNoList.options.add(mtech);
		}
	}
}

/* Change the next select list depending on the first select.*/
function changeUtLevelSelect(changedNum) {

	// This is the second select.
	var mtNoList = document.getElementsByName("mtNo")[changedNum];
	var mtNoListSelected = mtNoList.options[mtNoList.selectedIndex].value;
	var cdata = '{ "mtNo" : "' + mtNoListSelected + '"}';
	var curl = "/profile/sltMTechDetailListAjax";
	var cresult = requestbyAjax(curl,cdata);

	var utLevelList = document.getElementsByName("utLevel")[changedNum];

	// Delete the select's options
	while (utLevelList.options.length) {
		utLevelList.remove(0);
	}

	if(cresult) {
		var mTechDetail = new Option('<@spring.message "select.default.proficiency"/>', '0');
		utLevelList.options.add(mTechDetail);
		for (i = 0; i < cresult.mTechDetailList.length; i++) {
			var mTechDetail = new Option(cresult.mTechDetailList[i].mtdLevelExplain, cresult.mTechDetailList[i].mtdLevel);
			utLevelList.options.add(mTechDetail);
		}
	}

}

</script>

<#include "../../common/abilistsFooder.ftl"/>

</@layout.myLayout>