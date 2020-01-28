<html>
<head>
    <title>KREV users</title>
</head>
<body>
<#if usersVar?has_content>
    <ul>
        <#list usersVar as tempUser>
            <li>${tempUser.name} ${tempUser.surname} ${tempUser.email}</li>
        </#list>
    </ul>
<#else>
    <p>No users yet</p>
    <a href="/logout">Logout</a>
</#if>

</body>
</html>
