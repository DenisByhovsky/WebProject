
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
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <title>Pariroom</title>
    <!-- Favicon-->
    <link rel="icon" href="favicon.ico" type="image/x-icon">

    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css?family=Roboto:400,700&subset=latin,cyrillic-ext" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet" type="text/css"/>
    <!-- Bootstrap Core Css -->
    <link href="${pageContext.request.contextPath}/plugins/bootstrap/css/bootstrap.css" rel="stylesheet"/>

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

<c:set var="page" value="/controller?command=redirect_to_sport_page" scope="session"/>
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
            <a class="navbar-brand" href="index.html">PARIROOM - BOOMAKER CLUB</a>
        </div>
        <div class="collapse navbar-collapse" id="navbar-collapse">
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

            </ul>
        </div>
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
                        <li><a href="main.jsp"><i class="material-icons">person</i>Profile</a></li>

                        <li><a class="logout" href="/controller?command=logout"><i class="material-icons">input</i>Sign Out</a></li>
                    </ul>
                </div>
            </div>
        </div>
        <!-- #User Info -->
        <!-- Menu -->
        <div class="menu">
            <ul class="list">
                <li class="header">MAIN NAVIGATION</li>
                <li class="active">
                    <a href="${pageContext.request.contextPath}/jsp/client/main.jsp">
                        <i class="material-icons">home</i>
                        <span><fmt:message key="cl.home"/>
</span>
                    </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/jsp/client/bets.jsp">
                        <i class="material-icons">menu</i>
                        <span><fmt:message key="cl.bets"/>
</span>
                    </a>
                </li>

                <li>
                    <a href="${pageContext.request.contextPath}/jsp/client/cash.jsp">
                        <i class="material-icons">account_balance_wallet</i>
                        <span><fmt:message key="cl.ballance"/>
</span>
                    </a>
                </li>

                <li>
                    <a href="/controller?command=redirect_to_sport_page">
                        <i class="material-icons">list</i>
                        <span><fmt:message key="cl.sports"/>
</span>
                    </a>
                </li>


                <li>
                    <a href="/controller?command=redirect_to_cybersport_page">
                        <i class="material-icons">list</i>
                        <span><fmt:message key="cl.cybersport"/>
</span>
                    </a>
                </li>


                <li>
                    <a href="${pageContext.request.contextPath}/jsp/client/settings.jsp">
                        <i class="material-icons">settings</i>
                        <span><fmt:message key="cl.settings"/>
</span>
                    </a>
                </li>


                <li>
                    <a href="${pageContext.request.contextPath}/jsp/client/cashIn.jsp">
                        <i class="material-icons">monetization_on</i>
                        <span><fmt:message key="cl.deposit"/>
</span>
                    </a>
                </li>


                <li>
                    <a href="${pageContext.request.contextPath}/jsp/client/cashOut.jsp">
                        <i class="material-icons">money_off</i>
                        <span><fmt:message key="cl.draw"/>
</span>
                    </a>
                </li>

                <a href="${pageContext.request.contextPath}/feedback.jsp">
                    <i class="material-icons">message</i>
                    <span><fmt:message key="cl.feedback"/>
</span>    </a>
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

        <!-- Widgets -->

        <div class="row clearfix">
            <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
                <div class="info-box bg-pink hover-expand-effect">
                    <a class="icon" href="/controller?command=redirect_to_sport_page">
                        <i class="material-icons">playlist_add_check</i>
                    </a>
                    <div class="content">
                        <div class="text">SPORTS</div>
                        <div class="number count-to" data-from="0" data-to="243" data-speed="1000" data-fresh-interval="20"></div>
                    </div>
                    </a>
                </div>
            </div>
            <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
                <div class="info-box bg-cyan hover-expand-effect">
                    <a class="icon" href="/controller?command=redirect_to_cybersport_page">
                        <i class="material-icons">playlist_add_check</i>
                    </a>

                    <div class="content">
                        <div class="text">CYBERSPORT</div>
                        <div class="number count-to" data-from="0" data-to="257" data-speed="1000" data-fresh-interval="20"></div>
                    </div>
                </div>
            </div>



            <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
                <div class="info-box bg-orange hover-expand-effect">
                    <a class="icon" href="${pageContext.request.contextPath}/jsp/client/cash.jsp">
                        <i class="material-icons">playlist_add_check</i>
                    </a>
                    <div class="content">
                        <div class="text">INVOICE</div>

                    </div>
                </div>
            </div>
        </div>

        <!-- #END# Widgets -->

        <div class="row clearfix">
            <!-- Visitors -->

            <!-- #END# Visitors -->

            <!-- Latest Social Trends -->
            <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
                <div class="card">
                    <div class="body bg-cyan">
                        <div class="m-b--35 font-bold"><p><br></p></div>
                        <div class="bets-block">
                            <div class="no-bets-block" id="no-bet"><p><fmt:message key="sp.noact"/>
                            </p></div>
                            <c:forEach var="bet" items="${account.bets}">
                                <script type="text/javascript">
                                    document.getElementById("no-bet").classList.add("hidden");
                                </script>
                                <div id="bet-info-block">
                                    <label>match:</label><span>${bet.event.firstCompetitor} - ${bet.event.secondCompetitor}</span><br>
                                    <label>type:</label><span>${bet.typeBet}</span><br>
                                    <label>bet:</label><span>${bet.amountCount} ${account.invoice.currency}</span><br>
                                    <label>date:</label>
                                    <span>
                <fmt:formatDate value="${bet.event.startDate}" type="date"/>
                <fmt:formatDate value="${bet.event.startTime}" type="time" timeStyle="SHORT"/>
            </span><br>
                                   <span>${bet.expectedWin}  ${account.invoice.currency}</span>
                                    <span class="span-coef">${bet.coefficient}</span><br>
                                    <form role="form" action="/controller" method="POST">
                                        <input type="hidden" name="command" value="cancel_bet">
                                        <input type="hidden" name="bet" value="${bet.id}">
                                        <input type="hidden" name="amount" value="${bet.amountCount}">
                                        <input type="submit" id="cancel-btn" value="Cansel">
                                    </form>
                                </div><br>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
            <!-- #END# Latest Social Trends -->


            <!-- #END# Answered Tickets -->
            <!-- Answered Tickets -->







            <!-- Hover Rows -->
            <div class="row clearfix">
                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                    <div class="card">
                        <div class="header">

                        </div>
                        <div class="body table-responsive">
                            <table class="table table-hover">
                                <c:forEach var="actionEvent" items="${actionEvents}">
                                <script>
                                    document.getElementById("info-text").classList.add("hidden");</script>
                                <thead>
                                <tr>
                                    <th class="thead-icon"><img src="images/${actionEvent.kindOfSport}.png" alt="img" width="30px"></th>
                                    <th class="match-name" colspan="3">${actionEvent.description}</th>

                                    <th>Date </th>
                                    <th>1 </th>
                                    <th>X </th>
                                    <th>2 </th>
                                    <th>X1 </th>
                                    <th>12 </th>
                                    <th>X2 </th>
                                </tr>
                                </thead>
                                    <tbody class="tbody">
                                    <c:forEach var="event" items="${actionEvent.events}">
                                        <tr>
                                            <td class="thead-icon">${event.id}</td>
                                            <td class="match-name">${event.firstCompetitor} - ${event.secondCompetitor}</td>
                                            <td class="match-time">
                                                <fmt:formatDate value="${event.startDate}" type="date"/>
                                                <fmt:formatDate value="${event.startTime}" type="time" timeStyle="SHORT"/>
                                            </td>
                                            <td class="coef">
                                                <a href="/controller?command=open_bet&event=${event.id}&coef=${event.coefficient.winFirst}&betType=First victory">
                                                   ${event.coefficient.winFirst}
                                            </td>
                                            <td class="coef">
                                                <a href="/controller?command=open_bet&event=${event.id}&coef=${event.coefficient.nobody}&betType=Dead heat">
                                                   ${event.coefficient.nobody}
                                            </td>
                                            <td class="coef">
                                                <a href="/controller?command=open_bet&event=${event.id}&coef=${event.coefficient.winSecond}&betType=Second victory">
                                                    ${event.coefficient.winSecond}
                                            </td>
                                            <td class="coef">
                                                <a href="/controller?command=open_bet&event=${event.id}&coef=${event.coefficient.firstOrNobody}&betType=First victory or dead heat">
                                                     ${event.coefficient.firstOrNobody}
                                            </td>
                                            <td class="coef">
                                                <a href="/controller?command=open_bet&event=${event.id}&coef=${event.coefficient.firstOrSecond}&betType=First or Second victory">
                                                    ${event.coefficient.firstOrSecond}
                                            </td>
                                            <td class="coef">
                                                <a href="/controller?command=open_bet&event=${event.id}&coef=${event.coefficient.secondOrNobody}&betType=Second victory or dead heat">
                                                 ${event.coefficient.secondOrNobody}
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </c:forEach>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
            <!-- #END# Hover Rows -->









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