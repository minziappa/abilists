<#import "../layout/abilistsProfileLayout.ftl" as layout>
<#import "/spring.ftl" as spring/>
<@layout.myLayout>

<style>
.new-box {
    border: 1px solid #ccc;
    box-shadow: 1px 1px 2px #eee;
    margin: 10px 0;
    position: relative;
	overflow:hidden;
//	padding-top: 5px;
//	padding-left: 5px;
//	padding-right: 5px;
//	padding-bottom: 5px;
}

.margin-middle {
	margin-bottom: 10px;
	margin-left: 0px;
	margin-top: 10px;
}

.margin-small {
	margin-bottom: 5px;
	margin-left: 5px;
	margin-top: 5px;
}

div .test2 {
	width: 100%;
}
div .div-img {
	float:left;
	//background-color:blue;
	width:40px;
	height:40px;
//	padding-top: 5px;
//	padding-left: 5px;
//	padding-right: 5px;
//	padding-bottom: 5px;
	margin-bottom: 5px;
	margin-left: 5px;
	margin-right: 5px;
	margin-top: 5px;
}

</style>

<div class="item-box left-line-box">
	<nav class="breadcrumbs">
		<ul>
		<li><a href="#"><@spring.message "path.abilists.profile"/></a></li>
		<li class="active"><a href="#"><@spring.message "profile.menu.techs"/></a></li>
		</ul>
	</nav>
</div>

	<div class="row">
		<div class="col-sm-3 right-col-cus">
			<div class="item-box">
				<img style="border-radius: 4px;" src="${userPicture?if_exists}" id="showImg" height="220" width="210" alt="your picture" /> <br/> 
				${user.userName?if_exists} <br/>
			    <div>
	        	<span class="glyphicon glyphicon-user"></span>
	        		${user.userProfile?if_exists}
	        	</div>
				<hr class="margin-middle">
		        <div>
		        	<span class="glyphicon glyphicon-lock"></span>
		        	${user.userName?if_exists}
		        </div>
			    <div>
		        	<span class="glyphicon glyphicon-envelope"></span>
		        	${user.userEmail?if_exists}
		        </div>
	        </div>
		</div>
		<div class="col-sm-9 left-col-cus">
			<div class="caption"><h3>Tech</h3></div>
			<div class="item-box">
				<div id="graph0" style="height: 250px;" class="graph"></div>
			</div>
		</div>

	</div>

	
	<div class="row">
	  <div class="col-md-6">
	  	
	  	<div class="graph-box">
	      <div class="caption"><@spring.message "profile.graph.tech"/></div>
	      <div id="graph1" style="height: 250px;" class="graph"></div>
	    </div>
	    
	  </div>
	  <div class="col-md-6">
	  	
	  	<div class="graph-box">
	      <div class="caption"><@spring.message "profile.graph.skills"/></div>
	      <div id="graph2" style="height: 250px;" class="graph"></div>
	    </div>

	   </div>
	</div>

<script>

function overChangeColor(obj) {
	obj.style.cursor = "pointer";
	obj.style.borderColor = "#e0e6f9";
	var deep1 = obj.childNodes;
	deep1[5].style.backgroundColor = "#e0e6f9";
}

function outChangeColor(obj) {
	obj.style.cursor = "pointer";
	obj.style.borderColor = "#ccc";
	var deep1 = obj.childNodes;
	deep1[5].style.backgroundColor = "#eee";
}

function overUp(changedNum) {

}

</script>
</@layout.myLayout>