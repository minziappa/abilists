<#macro myLayout title="FreeMarker example">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<#include "abilistsHeader.ftl"/>
	<body>
		<#include "abilistsNavi.ftl"/>
	    <div class="container">

			<#nested/>

			<#include "footer.ftl">
	    </div><!-- /container -->
	</body>
</html>
</#macro>