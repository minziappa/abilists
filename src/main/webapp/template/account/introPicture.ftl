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
		<li class="active"><a href="#"><@spring.message "settings.subtitle.change.picture"/></a></li>
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
		  <div class="row">
	  	  	<div class="col-sm-10 col-sm-offset-2">
	  	  	  <#include "../common/errorMessageNoButton.ftl"/>	
	  		  <form class="form-horizontal" action="/file/uploadUserFile" enctype="multipart/form-data" method="post">
	  			<ul class="list-inline">
		  			<li><img src="${myPicture?if_exists}" height="60" width="60" alt="your picture" /></li>
		  			<li>
		  		    	<span class="btn btn-default btn-file">
		  		    	<@spring.message "picture.button.title"/> <input type="file" name="userImg"/>
		  		    	</span>
		  		    </li>
		  		</ul>
	            <div class="form-group">
	            	<label class="col-sm-0 control-label"></label>
	            	<div class="col-sm-12">
	            	<button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-picture"></span> <@spring.message "picture.button.upload"/></button>
	            	</div>
            	</div>
	  		  </form>
	  		</div><!-- /col-sm-10 col-sm-offset-2-->
		   </div><!-- /row-->
		</div><!-- /item-box-->

		<hr width="100%" align="right">

	  </div><!-- /col-md-8-->
	</div><!-- /row-->

  </div><!-- /col-md-12-->
</div> <!-- /row -->

<#include "../common/abilistsFooder.ftl"/>

</@layout.myLayout>