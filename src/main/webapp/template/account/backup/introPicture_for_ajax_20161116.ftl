<#import "../layout/abilistsAccountLayout.ftl" as layout>
<#import "/spring.ftl" as spring/>
<@layout.myLayout>

<div id="page-wrapper">

<br/>

	<div class="panel panel-default">
		<div class="panel-heading"><@spring.message "path.setting.profile.picture"/></div>
		<div class="panel-body">

		<div class="row">

		  <div class="col-sm-4">
		  	<#include "sidebar.ftl"/>
		  </div>

		  <div class="col-sm-8">

		  	  <div class="row">
		  		<div class="col-sm-10 col-sm-offset-2">
			  		<ul class="list-inline">
			  			<li><img src="${userPicture?if_exists}" id="showImg" height="50" width="50" alt="your picture" /></li>
			  			<li>
			  		    	<span class="btn btn-default btn-file">
			  		    	<@spring.message "picture.button.title"/> <input type="file" name="userImg"/>
			  		    	</span>
			  		    </li>
			  		</ul>
			  		<ul>
			  			<li><button type="submit" class="btn btn-primary" onclick="uploadFile();"><@spring.message "picture.button.upload"/><span class="glyphicon glyphicon-picture"></span></button></li>
		  			</ul>
		  		</div>
		  	  </div>

		  	  <hr width="100%" align="right">
		  </div><!-- /col-md-8-->
		</div><!-- /row -->
        </div><!-- /panel-body-->
	</div> <!-- /panel panel-default -->
</div><!-- /.page-wrapper -->

<script>

function uploadFile() {

	var userImg = document.getElementsByName("userImg")[0];
	var showImgId = document.getElementById("showImg");

	var formData = new FormData();
	formData.append("userImg", userImg.files[0]);

	$(document).ready(function() {

        $.ajax({
            type: 'POST',
            url: '/file/uploadUserFileAjax',
            contentType: "multipart/form-data; charset=UTF-8",
            data: formData,
            cache: false,
            contentType: false,
            processData: false,
            beforeSend: function(xhr, settings) {
            	console.log("beforeSend");
            },
            success: function(data, textStatus, request) {
            	if(!isBlank(data.imageData)) {
            		showImgId.src = data.imageData;
            	}
            },
            complete: function(xhr, textStatus) {
            	console.log("complete");
            },
            error: function(xhr, status) {
            	console.log("error >> " + xhr.responseText);
            }
        });
	});
}

</script>

</@layout.myLayout>