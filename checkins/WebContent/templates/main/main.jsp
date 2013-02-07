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
			<div style="text-align:center"><h2>walkin checkin cms</h2></div>
		</div>
		<div class="footer" data-options="region:'south',split:true" style="border-style:none;height:50px;text-align:center">
			Copyright ©2013 www.walkin.com.cn
		</div>
		<div data-options="region:'west',split:true" title="系统菜单" style="width:200px;">
			<div class="easyui-accordion" data-options="fit:true,border:false">
				<div title="系统管理" style="padding:10px;" data-options="selected:true">
					<ul id="t-channels" class="easyui-tree" data-options="url:'./tree_data1.json',animate:true,dnd:true"></ul>
				</div>
				<div title="Title2" style="padding:10px;border-style:none;">
					<ul >
						<li><a href='#' onclick=''>管理员</a></li>
					</ul>
				</div>
			</div>
		</div>
		
		<div data-options="region:'center',title:'Main Title',iconCls:'icon-ok'">
			<div id="content" class="easyui-tabs" data-options="fit:true,border:false,plain:true">
				<div title="About" id="about" data-options="href:'./_content.html'" style="padding:10px"></div>
				<div title="DataGrid" style="padding:5px">
					<table class="easyui-datagrid"
							data-options="url:'./datagrid_data1.json',singleSelect:true,fit:true,fitColumns:true">
						<thead>
							<tr>
								<th data-options="field:'itemid'" width="80">Item ID</th>
								<th data-options="field:'productid'" width="100">Product ID</th>
								<th data-options="field:'listprice',align:'right'" width="80">List Price</th>
								<th data-options="field:'unitcost',align:'right'" width="80">Unit Cost</th>
								<th data-options="field:'attr1'" width="150">Attribute</th>
								<th data-options="field:'status',align:'center'" width="50">Status</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
</html>