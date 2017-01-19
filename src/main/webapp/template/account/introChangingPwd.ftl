<#import "../layout/abilistsAccountLayout.ftl" as layout>
<#import "/spring.ftl" as spring/>
<@layout.myLayout>

<!-- To validate password -->
<script src="/static/js/validatePassword.js"></script>

<div class="item-box">
<div class="row">
	<div class="col-md-6">
		<nav class="breadcrumbs">
		<ul>
		<li><a href="/account"><@spring.message "navi.title.account"/></a></li>
		<li><a href="/account"><@spring.message "account.menu.settings"/></a></li>
		<li class="active"><a href="#"><@spring.message "settings.subtitle.change.password"/></a></li>
		</ul>
		</nav>
	</div>
	<div class="col-md-6">
	</div>
</div>
</div>

<div class="row">
  <div class="col-md-12">


		<div align="center" id="completeId" class="list-group-item list-group-item-danger">complete</div>

		<div class="row">
		  <div class="col-sm-4 right-col-cus">
		  	<div class="item-box">
		  	  <#include "sidebar.ftl"/>
		  	</div>
		  </div>

		  <div class="col-sm-8 left-col-cus">
		    <div class="item-box">
		  	  <form class="form-horizontal" action="udtChangingPwd" method="post" id="contact_form">
		  	  	<#include "../common/errorMessageNoButton.ftl"/>
		  	  	<div class="form-group">
		  			<label class="col-sm-3 control-label"><@spring.message "password.current.password"/></label>  
		  			<div class="col-sm-9">
		  				<div class="input-group">
		  					<span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
		  					<input id="currentPwdId" class="form-control" type="password" name="currentPwd" autocomplete="off" 
		  						placeholder="Current password">
	  					</div>
	  				</div>
		  		</div>
		  		<hr/>
		        <div class="form-group">
		        	<label class="col-sm-3 control-label"><@spring.message "password.new.password"/></label>
		        	<div class="col-sm-9">
			            <div class="input-group">
			                <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
			                <input id="passwordId" name="password" type="password" class="form-control" 
			    				placeholder="New password" autocomplete="off" onkeyup="validatePassword(this, 'pwdId1');" >
			            </div>
			            <p id="pwdId1" class="form-control-static" style="display: none;"></p>
		            </div>
		        </div>

		        <div class="form-group">
		        	<label class="col-sm-3 control-label"><@spring.message "password.new.confirm.password"/></label>  
		        	<div class="col-sm-9">
		        		<div class="input-group">
			        		<span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
		        			<input  id="password2Id" name="password2" type="password" class="form-control" 
		        				data-toggle="popover" data-trigger="manual" data-placement="bottom" 
		        					title="Passwords Do Not Match!" data-content="Please, check new password" onclick="releasPopover(this);"
		        						autocomplete="off" placeholder="For confirmation" onkeyup="validatePassword(this, 'pwdId2');">
		        		</div>
			        	<p id="pwdId2" class="form-control-static" style="display: none;"></p>
			        </div>
			    </div>

	            <div class="form-group">
	            	<label class="col-sm-3 control-label"></label>
	            	<div class="col-sm-9">
	            		<button type="submit" class="btn btn-primary" onclick="return validateConfirmePassword();">
	            			<span class="glyphicon glyphicon-send"></span> <@spring.message "password.button.update"/>
									</button>
	            	</div>
	            </div>
	            <input type="hidden" name="token" value="<#if model??>${model.token?if_exists}</#if>" />
	          </form>
	        </div>
		  </div><!-- /col-md-8-->
		</div><!-- /row -->

    </div><!-- /col-md-12-->
</div> <!-- /row -->

<script>

function validateConfirmePassword() {
	var currentPwdId = document.getElementById("currentPwdId");
	var passwordId = document.getElementById("passwordId");
	var password2Id = document.getElementById("password2Id");

	if(isEmpty(currentPwdId.value)){
		currentPwdId.style.border = "1px solid red";
        return false;
	} else {
		currentPwdId.style.border = "";
	}

	if(isEmpty(passwordId.value)){
		passwordId.style.border = "1px solid red";
        return false;
	} else {
		passwordId.style.border = "";
	}

	if(isEmpty(password2Id.value)){
		password2Id.style.border = "1px solid red";
        return false;
	} else {
		passwordId.style.border = "";
	}

	if(passwordId.value != password2Id.value){
		// how to convert javascript object to jquery object
		var $inputs = $(password2Id);

        $inputs.css("border", "1px solid red");
        $inputs.popover('show');
        return false;
	}
	return true;
}

</script>

<#include "../common/abilistsFooder.ftl"/>

</@layout.myLayout>