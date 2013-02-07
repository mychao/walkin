/**
 * 添加新标签
 * @param classSelect div选择器
 * @param title
 * @param url
 */
function addTab(divId, title, url){
	if ($('#'+divId).tabs('exists', title)){
		$('#'+divId).tabs('select', title);
	} else {
		var content = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';
		$('#'+divId).tabs('add',{
			title:title,
			content:content,
			closable:true
		});
	}
}