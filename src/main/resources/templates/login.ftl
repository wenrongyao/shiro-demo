<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>login</title>

<#include  "./common/common.ftl">
</head>

<body>
<form action="user/login" method="post">
    username: <input type="text" id="userName" name="userName">
    <br><br>
    password: <input type="password" id="password" name="password">
    <br><br>
    <input type="button" id="submit" value="submit">
</form>


<script>
    $(function () {
        $("#submit").click(function () {
            var userName = $("#userName").val();
            var password = $("#password").val();
            $.post("/user/tologin",
                    {
                        userName: userName,
                        password: password
                    },
                    function (data) {
//                        var res = JSON.parse(data);
                        if (data.code === -1) {
                            return layer.msg(res.msg);
                        } else {
                            window.location.href = "/user/index";
                        }
                    });
        });
    });

</script>
</body>
</html>