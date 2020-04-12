<%--
  Created by JamesChang
  Date/檔案建立日期: 2020/4/12
  Time/檔案建立時間: 下午 02:03
  File Description/檔案描述:
--%>
<html>
<head>
    <meta name="layout" content="${gspLayout ?: 'main'}"/>
    <title>${g.message(code: "bs.security.register.role.label")}</title>
</head>

<body>
<div class="row">
    <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
        <div class="card card-signin my-5">
            <div class="card-body">
                <h5 class="card-title text-center">${g.message(code: "bs.security.register.role.label")}</h5>
                <g:if test='${flash.message}'>
                    <div class="alert alert-danger" role="alert">${flash.message}</div>
                </g:if>
                <form class="form-signin" action="register" method="POST" id="loginForm" autocomplete="off">
                    <div class="form-group">
                        <label for="role.id">${g.message(code: "role.authority.label")}</label>
                        <g:select class="form-control" name="role.id"
                                  from="${bs.security.Role.list()}"
                                  optionKey="id" optionValue="authorityName"
                        />
                    </div>

                    <div class="form-group">
                        <label for="username">${g.message(code: "user.username.label")}</label>
                        <input type="text" placeholder="${g.message(code: "user.username.placeholder.label")}" class="form-control" name="username" id="username" autocapitalize="none"/>
                    </div>

                    <div class="form-group">
                        <label for="password">${g.message(code: "user.password.label")}</label>
                        <input type="password" placeholder="${g.message(code: "user.password.placeholder.label")}" class="form-control" name="password" id="password"/>
                    </div>

                    <div class="form-group">
                        <label for="password">${g.message(code: "user.reEnterPassword.label")}</label>
                        <input type="password" placeholder="${g.message(code: "user.reEnterPassword.placeholder.label")}" class="form-control" name="repassword" id="repassword"/>
                    </div>

                    <div class="form-group">
                        <label for="username">${g.message(code: "user.fullname.label")}</label>
                        <input type="text" placeholder="${g.message(code: "user.reEnterPassword.placeholder.label")}" class="form-control" name="fullname" id="fullname" autocapitalize="none"/>
                    </div>

                    <button id="submit" class="btn btn-lg btn-primary btn-block text-uppercase" type="submit">${g.message(code: "bs.security.register.label")}</button>
                    <hr class="my-4">
                    <p>${g.message(code: "bs.security.hadAccount.label")} <g:link controller="login" action="auth">${g.message(code: "bs.security.login.label")}</g:link></p>
                </form>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    document.addEventListener("DOMContentLoaded", function(event) {
        document.forms['loginForm'].elements['username'].focus();
    });
</script>
</body>
</html>