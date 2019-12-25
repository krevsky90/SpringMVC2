<html>
<head>
    <title>KREV users</title>
</head>
<body>
<ul>
    <#list usersVar as tempUser>
        <li>${tempUser.name} ${tempUser.surname} ${tempUser.email}</li>
    </#list>
</ul>

</body>
</html>
