<%@ page pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>
<html lang="${language}">
    <head>
    <title>Pariroom</title>
    <link href="${pageContext.request.contextPath}/jsp/css/aut.css" rel="stylesheet">

</head>
<body>
<c:set var="page" value="/controller?command=redirect_to_main_page" scope="session"/>

<div class="form_cont">
    <div class="login_form">
        <h3><fmt:message key="login.title"/></h3>
        <form role="form" method="post" action="/controller">
            <input type="hidden" name="command" value="login">
            <ul>
                <li>
                    <table class="F-Table">
                        <tbody>
                        <tr>

                            <td class="F-Lcol"><fmt:message key="login.nick"/></td>
                            <td class="F-Val"> <input  class="login"  type="text" name="login" size="30" required pattern="[A-z0-9_-]{3,16}" ></td>
                        </tr>
                        </tbody>
                    </table>
                </li>
                <li>
                    <table class="F-Table">
                        <tbody>
                        <tr>
                            <td class="F-Lcol"><fmt:message key="login.pass"/></td>
                            <td class="F-Val"> <input  class="pass" type="password" name="password" size="30"  required pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}$"></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td class="buttons">


                                <button class="btn-orange-cancel"  type="submit"><fmt:message key="login.btn"/></button>
                                <button class="btn-orange-cancel" type="reset" onclick=""><fmt:message key="login.clear"/></button>

                            </td>
                        </tr>
                        </tbody>
                    </table>
                </li>
            </ul>
        </form>
    </div>
</div>
    <blockquote>
        <p><fmt:message key="login.bloq"/>
            <a href="${pageContext.request.contextPath}/jsp/register.jsp"><fmt:message key="login.go"/>
            </a></p>

    </blockquote>

    <button class="but_new_big" name="op" onclick="history.back()" ><fmt:message key="reg.back"/></button>



</body>
</html>