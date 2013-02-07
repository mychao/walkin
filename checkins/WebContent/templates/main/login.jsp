<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome to login</title>
<jsp:include page="/templates/main/easyui-include.jsp"></jsp:include>
</head>

<body>
<div id="loginWin" class="easyui-window" title="Login" style="width:350px;height:188px;padding:5px;"
   minimizable="false" maximizable="false" resizable="false" collapsible="false">
    <div class="easyui-layout" fit="true">
            <div region="center" border="false" style="padding:5px;background:#fff;border:1px solid #ccc;">
        <form id="loginForm" method="post">
            <div style="padding:5px 0;">
                <label for="login">Username:</label>
                <input type="text" name="login" style="width:200px;"></input>
            </div>
            <div style="padding:5px 0;">
                <label for="password">Password :</label>
                <input type="password" name="password" style="width:200px;"></input>
            </div>
             <div style="padding:5px 0;text-align: center;color: red;" id="showMsg"></div>
        </form>
            </div>
            <div region="south" border="false" style="text-align:right;padding:5px 0;">
                <a class="easyui-linkbutton" iconCls="icon-ok" href="javascript:void(0)" onclick="login()">login</a>
                <a class="easyui-linkbutton" iconCls="icon-cancel" href="javascript:void(0)" onclick="cleardata()">reset</a>
            </div>
    </div>
</div>
</body>
<script type="text/javascript">
document.onkeydown = function(e){
    var event = e || window.event;  
    var code = event.keyCode || event.which || event.charCode;
    if (code == 13) {
        login();
    }
}
$(function(){
    $("input[name='login']").focus();
});
function cleardata(){
    $('#loginForm').form('clear');
}
function login(){
     if($("input[name='login']").val()=="" || $("input[name='password']").val()==""){
         $("#showMsg").html("user name or password is null,try it again!");
         $("input[name='login']").focus();
    }else{
            //ajax异步提交  
           $.ajax({
                  type:"POST",   //post提交方式默认是get
                  url:"/checkins/login.do?method=login", 
                  data:$("#loginForm").serialize(),   //序列化               
                  error:function(request) {      // 设置表单提交出错                 
                      $("#showMsg").html(request);  //登录错误提示信息
                  },
                  success:function(data) {
                	  if(data > 0){
                          document.location = "/checkins/main.do?method=page";
                	  }else{
                		  $("#showMsg").html("user name or password error!");  //登录错误提示信息
                	  }
                  }
            });       
        } 
}
</script>
</html>