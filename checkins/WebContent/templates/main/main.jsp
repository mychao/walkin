<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<meta charset="UTF-8">
	<title>CHECKIN CMS</title>
	<jsp:include page="/templates/main/easyui-include.jsp"/>
	<script type="text/javascript">
		$(function(){
			$('#t-channels').tree({
				onSelect: function(node){
					var url = node.attributes.url;
					var title = node.text;
					if ($('#content').tabs('exists', title)){
						$('#content').tabs('select', title);
					} else {
						var content = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';
						$('#content').tabs('add',{
							title:title,
							content:content,
							closable:true
						});
					}
				},
				onLoadSuccess:function(node,data){
					if (data.length > 1){
						var id = data[0].children[0].children[0].id;
						var n = $(this).tree('find', id);
						$(this).tree('select', n.target);
					}
				}
			});
		});
	</script>
<body>
	<div class="easyui-layout" style="text-align:center;margin-right:auto;margin-left:auto;width:100%;height:550px;">
		<div data-options="region:'north'" style="border-style:none;height:50px">
			<div style="text-align:center"><h2>walkin cms test version</h2></div>
		</div>
		<div class="footer" data-options="region:'south',split:true" style="border-style:none;height:50px;text-align:center">
			Copyright ©2013 www.walkin.com.cn
		</div>
		<div data-options="region:'west',split:true" title="System Menu" style="width:200px;">
			<div class="easyui-accordion" data-options="fit:true,border:false">
				<div title="base info manage" style="padding:10px;" data-options="selected:true">
					<ul id="t-channels" class="easyui-tree" data-options="url:'/templates/main/tree_data1.json',animate:true,dnd:true"></ul>
				</div>
			</div>
		</div>
		
		<div data-options="region:'center',title:'Content Area'">
			<div id="content" class="easyui-tabs" data-options="fit:true,border:false,plain:true">
				<div title="About" id="about" data-options="href:'/templates/main/about.html'" style="padding:10px"></div>
			</div>
		</div>
	</div>
</body>
</html>