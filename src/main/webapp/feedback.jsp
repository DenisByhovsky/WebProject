
<%@ page pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />

<c:set var="page" value="${pageContext.request.contextPath}/controller?command=redirect_to_main_page" scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>
<html lang="${language}">
<head>
    <title>Contact</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!--===============================================================================================-->
    <link rel="icon" type="image/png" href="images/icons/favicon.ico"/>
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="vendor/bootstrap/css/bootstrap.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="vendor/animate/animate.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="vendor/css-hamburgers/hamburgers.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="vendor/select2/select2.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="css/util.css">
    <link rel="stylesheet" type="text/css" href="css/main.css">
    <!--===============================================================================================-->
</head>
<body>

<div class="bg-contact2" style="background-image: url('images/send1.jpg');">
    <div class="container-contact2">
        <li class="nav-item mx-0 mx-lg-1">


        </li>
        <div class="wrap-contact2">
            <form class="lang-select">
                <select id="language" name="language" onchange="submit()" class="form-control">
                    <option value="ru" ${language == 'ru' ? 'selected' : ''}>RU </option>
                    <option value="en" ${language == 'en' ? 'selected' : ''}>EN </option>
                </select>
            </form>
            <form class="contact2-form validate-form" action="MailServlet" method="POST">

					<span class="contact2-form-title">


						<fmt:message key="mess.title"/> <br>
                        <fmt:message key="mess.title2"/>
					</span>


                <div class="wrap-input2 validate-input" data-validate = "Valid email is required: ex@abc.xyz">
                    <input class="input2" type="email" multiple name="subject">
                    <span class="focus-input2" data-placeholder="EMAIL"></span>
                </div>

                <div class="wrap-input2 validate-input" data-validate = "Message is required">
                    <input class="input2" type="text" multiple name="name">
                    <span class="focus-input2" data-placeholder="NAME"></span>
                </div>

                <div class="wrap-input2 validate-input" data-validate = "Message is required">
                    <textarea class="input2" name="body"></textarea>
                    <span class="focus-input2" data-placeholder="MESSAGE"></span>
                </div>

                <div class="container-contact2-form-btn">
                    <div class="wrap-contact2-form-btn">
                        <div class="contact2-form-bgbtn"></div>
                        <button class="contact2-form-btn">
                           Send message
                        </button>

                    </div>
                </div>

            </form>

            <button class="contact2-form-btn">
                <a class="btn btn-xl btn-outline-light" href="${pageContext.request.contextPath}/index.jsp"></a>
            </button>
        </div>
    </div>
</div>




<!--===============================================================================================-->
<script src="vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
<script src="vendor/bootstrap/js/popper.js"></script>
<script src="vendor/bootstrap/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
<script src="vendor/select2/select2.min.js"></script>
<!--===============================================================================================-->
<script src="js/main.js"></script>

<!-- Global site tag (gtag.js) - Google Analytics -->
<script async src="https://www.googletagmanager.com/gtag/js?id=UA-23581568-13"></script>
<script>
    window.dataLayer = window.dataLayer || [];
    function gtag(){dataLayer.push(arguments);}
    gtag('js', new Date());

    gtag('config', 'UA-23581568-13');
</script>

</body>
</html>
