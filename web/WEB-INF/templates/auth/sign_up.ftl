<#assign sf=JspTaglibs["http://www.springframework.org/tags/form"]>

<html>
<head>
    <title>Sign up</title>
</head>
<body>
<@sf.form action="/sign_up" method="post" modelAttribute="user">
    <div>
        <!-- just name of current field -->
        <@sf.label path="name">Name</@sf.label>
        <@sf.input path="name"/>
        <!-- place for output of error -->
        <@sf.errors path="name"/>
    </div>
    <div>
        <@sf.label path="surname">Surname</@sf.label>
        <@sf.input path="surname"/>
        <@sf.errors path="surname"/>
    </div>
    <div>
        <@sf.label path="email">Email</@sf.label>
        <@sf.input path="email" type="email"/>
        <@sf.errors path="email"/>
    </div>
    <div>
        <@sf.label path="password">Password</@sf.label>
        <@sf.input path="password" type="password"/>
        <@sf.errors path="password"/>
    </div>
    <input type="submit">
</@sf.form>

<!-- <form action="/users/new" method="post">
    <input name="name" type="text" placeholder="name">
    <input name="surname" type="text" placeholder="surname">
    <input name="email" type="email" placeholder="email">
    <input type="submit"> -->
</form>
</body>
</html>
