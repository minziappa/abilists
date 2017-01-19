<#import "../layout/abilistsAccountLayout.ftl" as layout>
<#import "/spring.ftl" as spring/>
<@layout.myLayout>

<div class="item-box">
<div class="row">
	<div class="col-md-6">
		<nav class="breadcrumbs">
		<ul>
		<li><a href="/account"><@spring.message "navi.title.account"/></a></li>
		<li><a href="/account"><@spring.message "account.menu.settings"/></a></li>
		<li class="active"><a href="#"><@spring.message "settings.subtitle.change.email"/></a></li>
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

	  	  <form class="form-horizontal" action="updatedChangingEmail" method="post" id="contact_form">
	  	  	<div class="form-group">
	  			<label class="col-sm-3 control-label"><@spring.message "email.current.email"/></label>  
	  			<div class="col-sm-9">
	  				<div class="input-group">
	  					<span class="input-group-addon"><i class="glyphicon glyphicon-envelope"></i></span>
	  					<span class="form-control" style="background-color: #ededed;">${user.userEmail?if_exists}</span>
  					</div>
  				</div>
	  		</div>
	  		<hr/>
	  	  	<div class="form-group">
	  			<label class="col-sm-3 control-label"><@spring.message "email.new.email"/></label>  
	  			<div class="col-sm-9">
	  				<div class="input-group">
	  					<span class="input-group-addon"><i class="glyphicon glyphicon-envelope"></i></span>
	  					<input id="newUserEmailId" type="text" name="newUserEmail" class="form-control" placeholder="new email">
  					</div>
  				</div>
			</div>
	        <div class="form-group">
	        	<label class="col-sm-3 control-label"><@spring.message "email.current.password"/></label>
	        	<div class="col-sm-9">
		            <div class="input-group">
		                <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
		                <input id="userPwdId" type="password" name="userPwd" class="form-control" placeholder="password" autocomplete="off" required>
		            </div>
	            </div>
	        </div>

            <div class="form-group">
            	<label class="col-sm-3 control-label"></label>
            	<div class="col-sm-9">
            		<button type="submit" class="btn btn-primary" onclick="return validateEmail();"><span class="glyphicon glyphicon-send"></span> <@spring.message "email.button.update"/></button>
            	</div>
            </div>
            <input type="hidden" name="token" value="<#if model??>${model.token?if_exists}</#if>" />
	      </form>

		</div><!-- /item-box-->
	  </div><!-- /col-sm-8 -->
	</div><!-- /row-->

  </div> <!-- /col-md-12 -->
</div><!-- /row -->

<script>

var email = document.getElementById("newUserEmailId");
function validateEmail() {
	// how to convert javascript object to jquery object
	var $inputs = $(email);

	if(!/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(email.value)){
		console.log(email.value);

        $inputs.css("border", "1px solid red");
        $inputs.popover('show');
		return false;
	}

    return true;
}

</script>

<#include "../common/abilistsFooder.ftl"/>

</@layout.myLayout>