<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>index</title>
</head>
<body>
<h4>当前登录用户：${sysUser.userName!""}</h4>
<h4>当前用户用如下权限：</h4>
<#if isSysPermissionList>
    <#list sysPermissionList as sysPermission>
    <a href="${sysPermission.url!''}">${sysPermission.permissionName!''}</a>
    <br/>
    </#list>
</#if>

<a href="/user/loginout"><input type="button" value="退出"></a>
</body>
</html>