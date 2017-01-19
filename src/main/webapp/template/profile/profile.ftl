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
		<div class="caption mittle-size-title"><h4><@spring.message "profile.graph.projects"/></h4></div>
		<div class="item-box">
			<div id="graph0" style="height: 250px;" class="graph"></div>
		</div>
	</div>

</div>


<div class="row">
  <div class="col-md-6 right-col-cus">
  	<div class="caption mittle-size-title"><h4><@spring.message "profile.graph.industry"/></h4></div>
  	<div class="item-box">
  	  <div id="graph8" style="height: 250px;"></div>
  	</div>
  </div>
  <div class="col-md-6 left-col-cus">
	<div class="caption mittle-size-title"><h4><@spring.message "profile.graph.skills"/></h4></div>
  	<div class="item-box">
      <div id="graph2" style="height: 250px;" class="graph"></div>
    </div>
  </div>
</div>

<div class="row">
  <div class="col-md-6 right-col-cus">
  	<div class="caption mittle-size-title"><h4><@spring.message "profile.graph.tech"/></h4></div>
  	<div class="item-box">
      <div id="graph1" style="height: 250px;" class="graph"></div>
    </div>
  </div>
  <div class="col-md-6 left-col-cus">

	<div class="caption mittle-size-title"><h4><@spring.message "profile.graph.certificate"/></h4></div>
  	<div class="item-box">
      <div id="graph4" style="height: 250px;" class="graph"></div>
    </div>

  </div>
</div>

<div class="row">
  <div class="col-md-6 right-col-cus">
  	<div class="caption mittle-size-title"><h4><@spring.message "profile.graph.language1"/></h4></div>
  	<div class="item-box">
      <div id="graph3" style="height: 250px;" class="graph"></div>
    </div>
  </div>
  <div class="col-md-6 left-col-cus">
  	<div class="caption mittle-size-title"><h4><@spring.message "profile.graph.language2"/></h4></div>
	<div class="item-box">
	  <div id="graph9" style="height: 250px;"></div>
	</div>
  </div>
</div>

<script>

var day_data = [      
	<#if model.userProjectsJoinTechList?has_content>
		<#list model.userProjectsJoinTechList as userProjectsJoinTech>
      		{period: '${userProjectsJoinTech.insertTime?string('yyyy-MM-dd')?if_exists}', skills: '${userProjectsJoinTech.uptLevel?string}'}<#if userProjectsJoinTech?has_next>,</#if>
      	</#list>
    <#else>
      	{period: '2016-08-01', skills: '0'},
	</#if>
];

Morris.Line({
	element: 'graph0',
	data: day_data,
	xkey: 'period',
	ykeys: ['skills'],
	labels: ['skills'],
	pointSize: 2,
	resize: true,
	hideHover: 'auto'
	});

	//Use Morris.Bar
	Morris.Bar({
	  element: 'graph1',
	  data: [
	  <#if model.mTechJoinUserTechList?has_content>
		<#list model.mTechJoinUserTechList as mTechJoinUserTech>
			<#if mTechJoinUserTech.mtSkill == "techs">
			{x: '${mTechJoinUserTech.mtName?string?if_exists}', y: '${mTechJoinUserTech.utLevel?string?if_exists}' }<#if mTechJoinUserTech?has_next>,</#if>
			</#if>
		</#list>
	  <#else>
			{x: 'no data', y: '0' }
	  </#if>
	  ],
	  xkey: 'x',
	  ykeys: ['y'],
	  labels: ['Y'],
	  barRatio: 0.4,
	  xLabelAngle: 35,
	  barColors: function () {return 'rgb(70,130,215)'},
	  resize: true,
	  hideHover: 'auto'
	});

	Morris.Bar({
	  element: 'graph2',
	  data: [
	   	  <#if model.mTechJoinUserTechList?has_content>
	   		<#list model.mTechJoinUserTechList as mTechJoinUserTech>
	   			<#if mTechJoinUserTech.mtSkill == "skills">
	   			{x: '${mTechJoinUserTech.mtName?string?if_exists}', y: '${mTechJoinUserTech.utLevel?string?if_exists}' }<#if mTechJoinUserTech?has_next>,</#if>
	   			</#if>
	   		</#list>
	   	  <#else>
	   			{x: 'no data', y: '0' }
	   	  </#if>
	   	  ],
	  xkey: 'x',
	  ykeys: ['y'],
	  labels: ['Y'],
	  barRatio: 0.4,
	  xLabelAngle: 35,
	  barColors: function () {return 'rgb(135,70,215)'},
	  resize: true,
	  hideHover: 'auto'
	});
	
	Morris.Bar({
	  element: 'graph3',
	  data: [
	   	  <#if model.mTechJoinUserTechList?has_content>
	   		<#list model.mTechJoinUserTechList as mTechJoinUserTech>
	   			<#if mTechJoinUserTech.mtSkill == "lang">
	   			{x: '${mTechJoinUserTech.mtName?string?if_exists}', y: '${mTechJoinUserTech.utLevel?string?if_exists}' }<#if mTechJoinUserTech?has_next>,</#if>
	   			</#if>
	   		</#list>
	   	  <#else>
	   			{x: 'no data', y: '0' }
	   	  </#if>
	   	  ],
	  xkey: 'x',
	  ykeys: ['y'],
	  labels: ['Y'],
	  barRatio: 0.4,
	  xLabelAngle: 35,
	  barColors: function () {return 'rgb(215,138,70)'},
	  resize: true,
	  hideHover: 'auto'
	});
	
	Morris.Bar({
	  element: 'graph4',
	  data: [
	   	  <#if model.mTechJoinUserTechList?has_content>
	   		<#list model.mTechJoinUserTechList as mTechJoinUserTech>
	   			<#if mTechJoinUserTech.mtSkill == "certi">
	   			{x: '${mTechJoinUserTech.mtName?string?if_exists}', y: '${mTechJoinUserTech.utLevel?string?if_exists}' }<#if mTechJoinUserTech?has_next>,</#if>
	   			</#if>
	   		</#list>
		  <#else>
	   			{x: 'no data', y: '0' }
	   	  </#if>
	   	  ],
	  xkey: 'x',
	  ykeys: ['y'],
	  labels: ['Y'],
	  barRatio: 0.4,
	  xLabelAngle: 35,
	  barColors: function () {return 'rgb(70,215,128)'},
	  resize: true,
	  hideHover: 'auto'
	});

	<#assign sum = 0>
	// Industry
	Morris.Donut({
		element: 'graph8',
		data: [
			<#if model.mIndustryJoinUserProjectsList?has_content>
			<#list model.mIndustryJoinUserProjectsList as mIndustryJoinUserProjects>
				{label: '${mIndustryJoinUserProjects.miLargeCategory?if_exists}', value: '${mIndustryJoinUserProjects.cnt?string?if_exists}' }
				<#assign sum=sum + mIndustryJoinUserProjects.cnt>
			<#if mIndustryJoinUserProjects?has_next>,</#if>
			</#list>
			<#else>
				{value: 0, 
					label: 'no data', 
					value: '0' 
				}
			</#if>
		],
		formatter: function (x) { return (x/${sum}*100) + "%"}
      	}).on('click', function(i, row){
		  console.log(i, row);
		});

	// Language
	Morris.Bar({
	  element: 'graph9',
	  data: [

		   	  <#if model.mTechJoinUserTechList?has_content>
	   			<#assign blnFirst = true />
	   			{
		   		<#list model.mTechJoinUserTechList as mTechJoinUserTech>
		   			<#if mTechJoinUserTech.mtSkill == "lang">
		   			<#if blnFirst>
		   					x: '${mTechJoinUserTech.utKind?string?if_exists}', 
		   					'${mTechJoinUserTech.mtName?string?if_exists}': '${mTechJoinUserTech.utLevel?string?if_exists}'
		   						<#assign blnFirst = false />
		   			<#else>
			   				'${mTechJoinUserTech.mtName?string?if_exists}': '${mTechJoinUserTech.utLevel?string?if_exists}'
			   		</#if><#if mTechJoinUserTech?has_next>,</#if>
		   			</#if>
		   		</#list>
	   			}
	   		  <#else>
	   			{x: 'No data',
	   				'no data': '0'
	   			}
		   	  </#if>

	  ],
	  xkey: 'x',
	  ykeys: [
		   		<#list model.mTechJoinUserTechList as mTechJoinUserTech><#if mTechJoinUserTech.mtSkill == "lang">'${mTechJoinUserTech.mtName?string?if_exists}'<#if mTechJoinUserTech?has_next>,</#if></#if></#list>
	          ],
	  labels: [
	           	<#list model.mTechJoinUserTechList as mTechJoinUserTech><#if mTechJoinUserTech.mtSkill == "lang">'${mTechJoinUserTech.mtName?string?if_exists}'<#if mTechJoinUserTech?has_next>,</#if></#if></#list>
	           ],
	  barRatio: 0.4,
	  xLabelAngle: 35,
	  resize: true,
	  hideHover: 'auto',
	  stacked: true
	});

</script>
</@layout.myLayout>