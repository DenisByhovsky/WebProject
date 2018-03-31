<!DOCTYPE html>
<%@ page pageEncoding="UTF-8" isELIgnored="false" contentType="text/html; UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>
<html lang="${language}">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <title>Pariroom</title>
    <!-- Favicon-->
    <link rel="icon" href="favicon.ico" type="image/x-icon">

    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css?family=Roboto:400,700&subset=latin,cyrillic-ext" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet" type="text/css">

    <!-- Bootstrap Core Css -->
    <link href="${pageContext.request.contextPath}/plugins/bootstrap/css/bootstrap.css" rel="stylesheet">

    <!-- Waves Effect Css -->
    <link href="${pageContext.request.contextPath}/plugins/node-waves/waves.css" rel="stylesheet" />

    <!-- Animation Css -->
    <link href="${pageContext.request.contextPath}/plugins/animate-css/animate.css" rel="stylesheet" />

    <!-- Morris Chart Css-->
    <link href="${pageContext.request.contextPath}/plugins/morrisjs/morris.css" rel="stylesheet" />

    <!-- Custom Css -->
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">

    <!-- AdminBSB Themes. You can choose a theme from css/themes instead of get all themes -->
    <link href="${pageContext.request.contextPath}/css/themes/all-themes.css" rel="stylesheet" />
</head>

<body class="theme-red">
<!-- Page Loader -->
<div class="page-loader-wrapper">
    <div class="loader">
        <div class="preloader">
            <div class="spinner-layer pl-red">
                <div class="circle-clipper left">
                    <div class="circle"></div>
                </div>
                <div class="circle-clipper right">
                    <div class="circle"></div>
                </div>
            </div>
        </div>
        <p>Please wait...</p>
    </div>
</div>
<!-- #END# Page Loader -->
<!-- Overlay For Sidebars -->
<div class="overlay"></div>
<!-- #END# Overlay For Sidebars -->
<!-- Search Bar -->
<div class="search-bar">
    <div class="search-icon">
        <i class="material-icons">search</i>
    </div>
    <input type="text" placeholder="START TYPING...">
    <div class="close-search">
        <i class="material-icons">close</i>
    </div>
</div>
<!-- #END# Search Bar -->
<!-- Top Bar -->
<nav class="navbar">
    <div class="container-fluid">
        <div class="navbar-header">
            <a href="javascript:void(0);" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar-collapse" aria-expanded="false"></a>
            <a href="javascript:void(0);" class="bars"></a>
            <a class="navbar-brand" href="${pageContext.request.contextPath}/index.jsp">PARIROOM - BOOMAKER CLUB</a>
        </div>
        <ul class="nav navbar-nav navbar-right">
            <!-- Call Search -->
            <li><form class="ch-lang">
                <select class="language-sel" name="language" onchange="submit()" >
                    <option value="ru" ${language == 'ru' ? 'selected' : ''}>RU </option>
                    <option value="en" ${language == 'en' ? 'selected' : ''}>EN </option>
                </select>
            </form></li>
            <!-- #END# Call Search -->
            <!-- Notifications -->
            <!-- #END# Notifications -->

            <li class="pull-right"><a href="javascript:void(0);" class="js-right-sidebar" data-close="true"><i class="material-icons">more_vert</i></a></li>
        </ul>
    </div>
</nav>
<!-- #Top Bar -->
<section>
    <!-- Left Sidebar -->
    <aside id="leftsidebar" class="sidebar">
        <!-- User Info -->
        <div class="user-info">
            <div class="image">
                <img src="${pageContext.request.contextPath}/images/user.png" width="48" height="48" alt="User" />
            </div>
            <div class="info-container">
                <div class="name" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Hello,${account.login}!</div>
                <div class="email"><label>${account.email}</label></div>
                <div class="btn-group user-helper-dropdown">
                    <i class="material-icons" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">keyboard_arrow_down</i>
                    <ul class="dropdown-menu pull-right">
                        <li><a href="main1.jsp"><i class="material-icons">person</i>Profile</a></li>

                        <li><a class="logout" href="/controller?command=logout"><i class="material-icons">input</i>Sign Out</a></li>
                    </ul>
                </div>
            </div>
        </div>
        <!-- #User Info -->
        <!-- #User Info -->
        <!-- Menu -->
        <div class="menu">
            <ul class="list">
                <li class="header">MAIN NAVIGATION</li>
                <li class="active">
                    <a href="${pageContext.request.contextPath}/jsp/admin/main.jsp">
                        <i class="material-icons">home</i>
                        <span><fmt:message key="cl.home"/></span>
                    </a>
                </li>

                <li>
                    <a href="/controller?command=put_result_page">
                        <i class="material-icons">menu</i>
                        <span><fmt:message key="ad.matches"/></span>
                    </a>
                </li>

                <li>
                    <a href="/controller?command=edit_event_page">
                        <i class="material-icons">menu</i>
                        <span><fmt:message key="ad.redact"/></span>
                    </a>
                </li>
                <li>
                    <a href="/controller?command=redirect_to_invoice_status">
                        <i class="material-icons">account_balance_wallet </i>
                        <span><fmt:message key="ad.invoice"/></span>
                    </a>
                </li>
                <li>

                    <a href="/controller?command=resultsinfo_page">
                        <i class="material-icons">archive</i>
                        <span><fmt:message key="ad.allres"/></span>
                    </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/jsp/admin/addEvent.jsp">
                        <i class="material-icons">add_circle</i>
                        <span><fmt:message key="ad.addmatch"/></span>
                    </a>
                </li>




                <a href="${pageContext.request.contextPath}/feedback.jsp">
                    <i class="material-icons">message</i>
                    <span><fmt:message key="cl.feedback"/></span>
                </a>
                </li>

            </ul>
        </div>
        <!-- #Menu -->
        <!-- Footer -->
        <div class="legal">
            <div class="copyright">
                &copy;2018 <a href="javascript:void(0);">AdminBSB Pariroom</a>.
            </div>

        </div>
        <!-- #Footer -->
    </aside>
    <!-- #END# Left Sidebar -->
    <!-- Right Sidebar -->

    <!-- #END# Right Sidebar -->
</section>

<section class="content">
    <div class="container-fluid">
        <div class="block-header">
            <h2>Your role, ${account.role}</h2>
        </div>




        <div class="row clearfix">
            <!-- Visitors -->

            <!-- #END# Visitors -->

            <!-- Answered Tickets -->

            <!-- #END# Answered Tickets -->


            <!-- Latest Social Trends -->
            <!-- #END# Latest Social Trends -->

        </div>

        <!-- Bordered Table -->
        <div class="row clearfix">
            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                <div class="card">
                    <div class="header">
                        <h2>
                            <fmt:message key="put.result"/>

                        </h2>

                    </div>
                    <div class="body table-responsive">
<c:forEach var="actionEvent" items="${actionEvents}">
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th><fmt:message key="bl.type"/>
                                </th>
                                <th><fmt:message key="bl.event"/>
                                </th>
                                <th><fmt:message key="bl.time"/>
                                </th>


                            </tr>

    <tr>
    <td class="thead-icon"><img src="images/${actionEvent.kindOfSport}.png"  width=30px alt="img"></td>
    <td class="match-name" colspan="5">Description: ${actionEvent.description}</td>
    </tr>
                            </thead>
                            <tbody>
    <c:forEach var="event" items="${actionEvent.events}">
        <tr>
        <td class="thead-icon">Event id: ${event.id}</td>
        <td class="match-name">${event.firstCompetitor} - ${event.secondCompetitor}</td>
        <td class="match-time">
        <fmt:formatDate value="${event.startDate}" type="date"/>
        <fmt:formatDate value="${event.startTime}" type="time" timeStyle="SHORT"/>
        </td>
                            </tr>
        <form role="form" method="get" action="/controller" id="${event.id}">
            <input name="command" value="put_results" hidden>
            <input name="eventID" value="${event.id}" hidden>
            <td class="score"><input class="score-input" name="score-first" type="number" placeholder="X1" min="0" step="1" required></td>
            <td class="score"><input class="score-input" name="score-second" type="number" placeholder="X2" min="0" step="1" required></td>
            <td class="coef">
                <a href="#" onclick="document.getElementById('${event.id}').submit()">save</a>
                <c:if test="${event.id eq eventID}">
                    &#10006;
                </c:if>
            </td>
        </form>
        </tr>
    </c:forEach>
                            </tbody>
                            </c:forEach>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <!-- #END# Bordered Table -->

        <div class="row clearfix">
            <!-- Task Info -->
            <!-- #END# Task Info -->
            <!-- Browser Usage -->
            <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
                <div class="card">
                    <div class="header">
                        <h2><fmt:message key="pay.info"/>
                        </h2>

                    </div>
                    <div class="body">
                        <img src="${pageContext.request.contextPath}/images/visa_mastercard.png" width="50px"/>
                        <img src="${pageContext.request.contextPath}/images/webmoney.png" width="115px"/>
                        <img src="${pageContext.request.contextPath}/images/qiwi.png" width="55px"/>
                    </div>
                </div>
            </div>
            <!-- #END# Browser Usage -->
        </div>
    </div>
</section>

<!-- Jquery Core Js -->
<script src="${pageContext.request.contextPath}/plugins/jquery/jquery.min.js"></script>

<!-- Bootstrap Core Js -->
<script src="${pageContext.request.contextPath}/plugins/bootstrap/js/bootstrap.js"></script>

<!-- Select Plugin Js -->
<script src="${pageContext.request.contextPath}/plugins/bootstrap-select/js/bootstrap-select.js"></script>

<!-- Slimscroll Plugin Js -->
<script src="${pageContext.request.contextPath}/plugins/jquery-slimscroll/jquery.slimscroll.js"></script>

<!-- Waves Effect Plugin Js -->
<script src="${pageContext.request.contextPath}/plugins/node-waves/waves.js"></script>

<!-- Jquery CountTo Plugin Js -->
<script src="${pageContext.request.contextPath}/plugins/jquery-countto/jquery.countTo.js"></script>

<!-- Morris Plugin Js -->
<script src="${pageContext.request.contextPath}/plugins/raphael/raphael.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/morrisjs/morris.js"></script>

<!-- ChartJs -->
<script src="${pageContext.request.contextPath}/plugins/chartjs/Chart.bundle.js"></script>

<!-- Flot Charts Plugin Js -->
<script src="${pageContext.request.contextPath}/plugins/flot-charts/jquery.flot.js"></script>
<script src="${pageContext.request.contextPath}/plugins/flot-charts/jquery.flot.resize.js"></script>
<script src="${pageContext.request.contextPath}/plugins/flot-charts/jquery.flot.pie.js"></script>
<script src="${pageContext.request.contextPath}/plugins/flot-charts/jquery.flot.categories.js"></script>
<script src="${pageContext.request.contextPath}/plugins/flot-charts/jquery.flot.time.js"></script>

<!-- Sparkline Chart Plugin Js -->
<script src="${pageContext.request.contextPath}/plugins/jquery-sparkline/jquery.sparkline.js"></script>

<!-- Custom Js -->
<script src="${pageContext.request.contextPath}/js/admin.js"></script>
<script src="${pageContext.request.contextPath}/js/pages/index.js"></script>

<!-- Demo Js -->
<script src="${pageContext.request.contextPath}/js/demo.js"></script>
</body>

</html>