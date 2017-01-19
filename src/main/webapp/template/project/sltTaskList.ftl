<#import "../layout/abilistsProjectLayout.ftl" as layout>
<#import "/spring.ftl" as spring/>
<@layout.myLayout>

<style>

</style>

<script>

$(document).ready(function(){
    $("#flip").click(function(){
        $("#newUserProjectForm").slideUp("slow");
    });
});

window.onload = function() {
	$("#newUserProjectForm").hide();
	$("#updateUserProjectForm").hide();
}

function removeTask() {
	// Call the modal for deleting
	$(window).ready(function(){
		$('#submitFormDeleteTask').modal('show')
	});
}


function validateForm(tableName) {

	var blnPopover = true;
	var isError = true;

	var table = document.getElementById(tableName);

	var inputTag = table.getElementsByTagName("input");
	if(!validateField(inputTag)) {
		return false;
	}
	var textareaTag = table.getElementsByTagName("textarea");
	if(!validateField(textareaTag)) {
		return false;
	}

	return true;
}

/*
 * Make the table on the Modal for confirming the data.
 */
function confirmData(tableName) {

	if(!validateForm(tableName)) {
		return;
	}

	// Call the modal
	$(window).ready(function(){
		$('#submitFormUserTask').modal('show')
	});

	var table1 = document.getElementById(tableName);
	// Create a table on Motal 
	var table3 = document.getElementById("t03");

	var tagInput, tagTextarea;
	var row3;
	var cell31, cell32

	var tagInput = table1.getElementsByTagName("input");
	var tagTextarea = table1.getElementsByTagName("textarea");

	row32 = table3.rows[1];

	row32.cells[0].innerHTML = tagInput[0].value;
	row32.cells[1].innerHTML = tagTextarea[0].value.replace(/\n|\r/g, "<br/>");

	if(tableName == "newFormId") {
		document.getElementById("submitForm").setAttribute( "onClick", "javascript: submitNewFormUserTask();" );
	} else {
		document.getElementById("submitForm").setAttribute( "onClick", "javascript: submitUpdateFormUserTask();" );
	}

}

function addProjectName(x, tagId) {
	var utkTaskId = document.getElementById(tagId);
	var project = "[" + x.options[x.selectedIndex].text + "] \r\r" +utkTaskId.value;
	utkTaskId.value = project;
}

function clearTask() {
	var utkTaskId = document.getElementById("utkTaskId");
	utkTaskId.value = "";
}

$( function() {
	  $( "#newUtkWorkDayId" ).datepicker({
	      showButtonPanel: true, 
	      currentText: '<@spring.message "calendar.day.today"/>', 
	      closeText: '<@spring.message "calendar.day.close"/>',
	      dateFormat: "yy-mm-dd",
	      nextText: '<@spring.message "calendar.day.next.month"/>',
	      prevText: '<@spring.message "calendar.day.previous.month"/>',
	      dayNames: [<@spring.message "calendar.day.name"/>],
	      dayNamesMin: [<@spring.message "calendar.day.name.min"/>]
	  });
	});

$( function() {
	  $( "#updateUtkWorkDayId" ).datepicker({
	      showButtonPanel: true, 
	      currentText: '<@spring.message "calendar.day.today"/>', 
	      closeText: '<@spring.message "calendar.day.close"/>',
	      dateFormat: "yy-mm-dd",
	      nextText: '<@spring.message "calendar.day.next.month"/>',
	      prevText: '<@spring.message "calendar.day.previous.month"/>',
	      dayNames: [<@spring.message "calendar.day.name"/>],
	      dayNamesMin: [<@spring.message "calendar.day.name.min"/>]
	  });
	});

</script>

<div class="item-box">
	<div class="row">
		<div class="col-md-6">
			<nav class="breadcrumbs">
			<ul>
			<li><a href="/project/sltProjectsList"><@spring.message "navi.title.abilists"/></a></li>
			<li class="active"><a href="#"><@spring.message "path.abilists.task"/></a></li>
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

	<#include "../common/errorMessage.ftl"/>
	<div align="center" id="completeId" class="list-group-item list-group-item-danger">success</div>

	  <div class="item-box" id="newUserProjectForm">

	  	<form name="newForm" class="form-horizontal" action="/task/istTask" method="post" id="newFormId" onkeypress="return captureReturnKey(event);">

	  	<div class="row">
	  		<div class="col-sm-4 col-md-4">
	  		  <div>
		  	  	<label class="control-label">Select your project</label>
				<select class="form-control" onchange="return addProjectName(this, 'newUtkTaskId');">
					<option value="0">Select your project</option>
				<#if model.userProjectsList??>
					<#list model.userProjectsList as userProjects>
					<option value="${userProjects.upNo?if_exists}">${userProjects.upName?if_exists}</option>
					</#list>
				</#if>
				</select>
		  	  </div>
	  		  <div>
	  		  	<label class="control-label">Select Calander</label>
	  		  	<div class="input-group" style="float:right; width: 100%;">
	  		  		<span class="input-group-addon"><span id="calendarId" class="glyphicon glyphicon-calendar" aria-hidden="true"></span></span>
	  		  		<input type="text" class="form-control" id="newUtkWorkDayId" name="utkWorkDay">
	  		  	</div>
		  	  </div>
	  		</div>
	  		<div class="col-sm-8 col-md-8">
	  			<label class="control-label">Today your task</label>
	  			<textarea class="taForm" style="height: 100px;" id="newUtkTaskId" name="utkTask" placeholder="Detail" rows="25"></textarea>
	  		</div>
	  	</div>
	  	<br/>

		<p align="center">
			<button type="button" class="btn btn-primary" onclick="return confirmData('newFormId');" >Send</button>
			<button class="btn btn-primary" type="button" onClick="newFormCancel();">Cancel</button>
		</p>
		</form>

	  </div>

	  <div class="item-box" id="updateUserProjectForm">

		<form name="updateForm" class="form-horizontal" action="udtTask" method="post" id="updateFormId" onkeypress="return captureReturnKey(event);">
		  	<div class="row">
		  		<div class="col-sm-4 col-md-4">
		  		  <div>
			  	  	<label class="control-label">Select your project</label>
					<select class="form-control" onchange="return addProjectName(this, 'updateUtkTaskId');">
						<option value="0">Select your project</option>
					<#if model.userProjectsList??>
						<#list model.userProjectsList as userProjects>
						<option value="${userProjects.upNo?if_exists}">${userProjects.upName?if_exists}</option>
						</#list>
					</#if>
					</select>
			  	  </div>
		  		  <div>
		  		  	<label class="control-label">Select Calander</label>
		  		  	<div class="input-group" style="float:right; width: 100%;">
		  		  		<span class="input-group-addon"><span id="calendarId" class="glyphicon glyphicon-calendar" aria-hidden="true"></span></span>
		  		  		<input type="text" class="form-control" id="updateUtkWorkDayId" name="utkWorkDay">
		  		  	</div>
			  	  </div>
		  		</div>
		  		<div class="col-sm-8 col-md-8">
		  			<label class="control-label">Today your task</label>
		  			<textarea class="taForm" style="height: 100px;" id="updateUtkTaskId" name="utkTask" placeholder="Detail" rows="25"></textarea>
		  		</div>
	  		</div>
			<input type="hidden" id="utkNoId" name="utkNo" />
			<br/>
			<p align="center">
				<button type="button" onclick="return confirmData('updateFormId');" class="btn btn-primary" data-toggle="modal">
					<@spring.message "task.button.confirm"/>
				</button>
				<button class="btn btn-primary" type="button" onClick="updateFormCancel();"><@spring.message "task.button.cancel"/></button>
				<button class="btn btn-danger" type="button" onclick="removeTask()"><@spring.message "task.title.delete"/></button>
			</p>
		</form>
	  </div>

	  <div class="item-box">
		<table id="userTaskId" class="table table-striped">
	      <thead>
	        <tr>
	          <th><@spring.message "task.title.no"/></th>
	          <th><@spring.message "task.title.workday"/></th>
	          <th><@spring.message "task.title.task"/></th>
	          <th><@spring.message "task.title.status"/></th>
	          <th><@spring.message "task.title.update"/></th>
	        </tr>
	      </thead>
	      <tbody>

	      <#if model??>
	      <#if model.userTaskList?has_content>
	      <#list model.userTaskList as userTask>
	      <tr onmouseover="overChangeColor(this);"  onmouseout="outChangeColor(this);" onclick="selectUserTask(this, '${userTask.utkWorkDay?string('yyyy-MM-dd')?if_exists}');">
	      	<td>${userTask.utkNo?if_exists}</td>
	      	<td>${userTask.utkWorkDay?string('yyyy-MM-dd')?if_exists}</td>
	      	<td class="text-left">${userTask.utkTask?if_exists}.....</td>
	      	<td>${userTask.status?if_exists}</td>
	      	<td>${userTask.updateTime?string('yyyy-MM-dd')?if_exists}</td>
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
			<li><a href="/task/sltTaskList?nowPage=${model.paging.prevPage.nowPage}&allCount=${model.paging.allCount?c}" title="Prev" accesskey="*">Prev</span></a></li>
			</#if>
			<#if model.paging.pagingInfoList?has_content>
				<#list model.paging.pagingInfoList as pageList>
					<#if model.paging.nowPage?if_exists == pageList.pageNumber?if_exists>
					<li class="active"><a href="#">${pageList.pageNumber} <span class="sr-only">(current)</span></a></li>
					<#else>
					<li><a href="/task/sltTaskList?nowPage=${pageList.pageNumber}&allCount=${model.paging.allCount?c}">${pageList.pageNumber}</a></li>
					</#if>
				</#list>
			</#if>
			<#if model.paging.nextPage?exists>
			<li><a href="/task/sltTaskList?nowPage=${model.paging.nextPage.nowPage}&allCount=${model.paging.allCount?c}" accesskey="#" title="Next">Next</a></li>
			</#if>
		</#if>
		</#if>
      	</ul>
      </nav><!-- end #nav -->

	</div><!-- col-md-12 -->
</div><!-- row -->


<!-- Modal -->
<div class="modal fade" id="submitFormUserTask" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel"><@spring.message "task.title.modal"/></h4>
      </div>

      <div id="confirmMessage" class="modal-body">
		<table id="t03" style="width:100%">
		<tr>
			<td><@spring.message "task.title.workday"/></td>
			<td><@spring.message "task.title.task"/></td>
		</tr>
		<tr>
			<td></td>
			<td></td>
		</tr>
		</table>
      </div>

      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button id="submitForm" type="button" class="btn btn-primary">Save Data</button>
      </div>
    </div>
  </div>
</div>

<!-- Delete Modal -->
<div class="modal fade" id="submitFormDeleteTask" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
<div class="modal-dialog">
  <div class="modal-content">
    <div class="modal-header">
      <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
      <h4 class="modal-title" id="dltTaskTileId"><@spring.message "projects.title.modal"/></h4>
    </div>

    <div id="confirmMessage" class="modal-body">
    	<@spring.message "projects.confirm.message.delete"/>
    </div>

    <div class="modal-footer">
      <button type="button" class="btn btn-default" data-dismiss="modal"><@spring.message "projects.button.close"/></button>
      <button id="submitForm" type="button" class="btn btn-danger" onclick="javascript: submitDeleteFormTask();"><@spring.message "projects.button.delete"/></button>
    </div>
  </div>
</div>
</div>

<script type="text/javascript"> 

var ajaxLastNum = 0;

var table = document.getElementById("userTaskId");
var tr = table.getElementsByTagName("tr");

function clearBackGroundColor() {
	for(var j=0; j< tr.length; j++) {
		tr[j].style.backgroundColor = "";
	}
}

var utkNoInput = document.getElementById("utkNoId");
var utkWorkDayInput = document.getElementById("updateUtkWorkDayId");
var utkTaskInput = document.getElementById("updateUtkTaskId");

function selectUserTask(x, day) {

	clearBackGroundColor();

	$(document).ready(function() {

		var availableKeys;
		var currentAjaxNum = ajaxLastNum;

        $.ajax({
            type: 'POST',
            url: 'sltTaskAjax',
            contentType: "application/json",
            dataType: "json",
            data: '{ "utkWorkDay" : "' + day + '"}',
            cache: false,
            beforeSend: function(xhr, settings) {
            	console.log("before send");
            },
            success: function(data, textStatus, request) {
            	if(!isBlank(data)) {
            		
            		// Sequence Number
            		utkNoInput.value = data.utkNo;

            		// Work Day
            		var workDay = new Date(data.utkWorkDay);
            		// utkWorkDayInput.value = workDay.toISOString().substr(0,10);

            		var dd = workDay.getDate();
            		// January is 0!
            		var mm = workDay.getMonth()+1;

            		var yyyy = workDay.getFullYear();
            		if(dd<10){
            		    dd='0'+dd
            		} 
            		if(mm<10){
            		    mm='0'+mm
            		} 
            		var today = yyyy + '-' + mm + '-' + dd;
            		utkWorkDayInput.value = today;

            		// Task for report
            		utkTaskInput.value = data.utkTask;
            		
            	}
            },
            complete: function(xhr, textStatus) {
            	console.log("complete");
            	
            	// Set a task title into Model
            	var dltTaskTileId = document.getElementById("dltTaskTileId");
            	dltTaskTileId.innerHTML = utkWorkDayInput.value;

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

	utkNoInput.value = "";
	utkWorkDayInput.value = "";
	utkTaskInput.value = "";

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

function submitNewFormUserTask() {
	document.newForm.submit();
}

function submitUpdateFormUserTask() {
	document.updateForm.submit();
}

/*
 * Remove a task
 */
function submitDeleteFormTask() {
	document.updateForm.action = "/task/dltTask";
	document.updateForm.submit();
}
</script>

<#include "../common/abilistsFooder.ftl"/>

</@layout.myLayout>