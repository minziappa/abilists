<#import "../layout/abilistsMyProfileLayout.ftl" as layout>
<#import "/spring.ftl" as spring/>
<@layout.myLayout>

<style>
.graph-box {
    border: 1px solid #ccc;
    box-shadow: 1px 1px 2px #eee;
    margin: 10px 0;
    padding: 15px;
    position: relative;
}

.margin-middle {
	margin-bottom: 10px;
	margin-left: 10px;
	margin-top: 10px;
}

.margin-small {
	margin-bottom: 5px;
	margin-left: 5px;
	margin-top: 5px;
}

</style>

<div class="item-box left-line-box">
	<nav class="breadcrumbs">
		<ul>
		<li><a href="#"><@spring.message "path.abilists.profile"/></a></li>
		<li class="active"><a href="#"><@spring.message "profile.menu.overview"/></a></li>
		</ul>
	</nav>
</div>

<div class="row">
	<div class="col-sm-3 right-col-cus">
		<div class="item-box">
			<img style="border-radius: 4px;" src="${userPicture?if_exists}" id="showImg" height="200" width="210" alt="your picture" /><br/>
				<#if model.users??>${model.users.userName?if_exists}</#if> <br/>
		    <div>
        	<span class="glyphicon glyphicon-user"></span>
        		<#if model.users??>${model.users.userProfile?if_exists}</#if>
        	</div>
			<hr class="margin-middle">
	        <div>
	        	<span class="glyphicon glyphicon-lock"></span>
	        	<#if model.users??>${model.users.userName?if_exists}</#if>
	        </div>
		    <div>
	        	<span class="glyphicon glyphicon-envelope"></span>
	        	<#if model.users??>${model.users.userEmail?if_exists}</#if>
	        </div>
        </div>
        
	</div>
	<div class="col-sm-9 left-col-cus">
		<div class="caption mittle-size-title"><h4>Summary</h4></div>
		<div class="item-box">
			<div id="graph0" style="height: 250px;" class="graph"></div>
		</div>
	</div>

</div>


<div class="caption mittle-size-title"><h4>OOOOOOOOO company</h4></div>
<div class="row fw-row" style="background-color: white;">
<div class="col-md-8"> 
    <h2><@spring.message "index.heading.title3"/></h2>
    <p style="font-size: 120%;"><@spring.message "index.heading.body3"/></p>
</div>
<div class="col-md-4 text-center">
	<h1 style="font-size: 300%; padding-top: 20px;"><span class="glyphicon glyphicon-refresh"></span></h1>
</div>
</div>

</@layout.myLayout>