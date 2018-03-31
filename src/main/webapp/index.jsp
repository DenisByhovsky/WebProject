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

    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    < title>Pariroom </title>

    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom fonts for this template -->
    <link href="${pageContext.request.contextPath}/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic" rel="stylesheet" type="text/css">

    <!-- Plugin CSS -->
    <link href="${pageContext.request.contextPath}/vendor/magnific-popup/magnific-popup.css" rel="stylesheet" type="text/css">

    <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath}/css/freelancer.css" rel="stylesheet">

</head>

<body id="page-top">


<!-- Navigation -->
<nav class="navbar navbar-expand-lg bg-secondary fixed-top text-uppercase" id="mainNav">
    <div class="container">
        <a class="navbar-brand js-scroll-trigger" href="#page-top"><fmt:message key="site.name"/></a>
        <button class="navbar-toggler navbar-toggler-right text-uppercase bg-primary text-white rounded" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            Menu
            <i class="fa fa-bars"></i>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item mx-0 mx-lg-1">
                    <a class="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger" href="#portfolio"><fmt:message key="nav.sports"/></a>
                </li>
                <li class="nav-item mx-0 mx-lg-1">
                    <a class="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger" href="#about"><fmt:message key="nav.about"/></a>
                </li>

                <li class="nav-item mx-0 mx-lg-1">
                    <a class="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger" href="${pageContext.request.contextPath}/jsp/login.jsp"><fmt:message key="nav.login"/></a>
                </li>

                <li class="nav-item mx-0 mx-lg-1">
                    <a class="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger" href="${pageContext.request.contextPath}/jsp/register.jsp"><fmt:message key="nav.signup"/></a>
                </li>

                <li class="nav-item mx-0 mx-lg-1">
                        <form class="lang-select">
                        <select id="language" name="language" onchange="submit()" class="form-control">
                            <option value="ru" ${language == 'ru' ? 'selected' : ''}>RU </option>
                            <option value="en" ${language == 'en' ? 'selected' : ''}>EN </option>
                        </select>
                    </form>

                </li>
                <li class="nav-item mx-0 mx-lg-1">
                <div class="date-info"><ctg:info-date/></div>

                </li>
            </ul>
        </div>
    </div>
</nav>

<!-- Header -->
<header class="masthead bg-primary text-white text-center">
    <div class="container">
        <img class="img-fluid mb-5 d-block mx-auto" src="images/profile1.png" alt="">
        <h1 class="text-uppercase mb-0"><fmt:message key="site.name"/></h1>
        <hr class="star-light">
        <h2 class="font-weight-light mb-0"><fmt:message key="cont.description"/></h2>
    </div>
</header>


<!-- Portfolio Grid Section -->
<section class="portfolio" id="portfolio">

    <div class="container">
        <h2 class="text-center text-uppercase text-secondary mb-0"><fmt:message key="sport.types"/></h2>
        <hr class="star-dark mb-5">
        <div class="row">
            <div class="col-md-6 col-lg-4">
                <a class="portfolio-item d-block mx-auto" href="#portfolio-modal-1">
                    <div class="portfolio-item-caption d-flex position-absolute h-100 w-100">
                        <div class="portfolio-item-caption-content my-auto w-100 text-center text-white">
                            <i class="fa fa-search-plus fa-3x"></i>
                        </div>
                    </div>
                    <img class="img-fluid" src="images/portfolio/cabin.png" alt="">
                </a>
            </div>
            <div class="col-md-6 col-lg-4">
                <a class="portfolio-item d-block mx-auto" href="#portfolio-modal-2">
                    <div class="portfolio-item-caption d-flex position-absolute h-100 w-100">
                        <div class="portfolio-item-caption-content my-auto w-100 text-center text-white">
                            <i class="fa fa-search-plus1 fa-3x"></i>
                        </div>
                    </div>
                    <img class="img-fluid" src="images/portfolio/cake.png" alt="">
                </a>
            </div>
            <div class="col-md-6 col-lg-4">
                <a class="portfolio-item d-block mx-auto" href="#portfolio-modal-3">
                    <div class="portfolio-item-caption d-flex position-absolute h-100 w-100">
                        <div class="portfolio-item-caption-content my-auto w-100 text-center text-white">
                            <i class="fa fa-search-plus2 fa-3x"></i>
                        </div>
                    </div>
                    <img class="img-fluid" src="images/portfolio/circus.png" alt="">
                </a>
            </div>
            <div class="col-md-6 col-lg-4">
                <a class="portfolio-item d-block mx-auto" href="#portfolio-modal-4">
                    <div class="portfolio-item-caption d-flex position-absolute h-100 w-100">
                        <div class="portfolio-item-caption-content my-auto w-100 text-center text-white">
                            <i class="fa fa-search-plus3 fa-3x"></i>
                        </div>
                    </div>
                    <img class="img-fluid" src="images/portfolio/game.png" alt="">
                </a>
            </div>
            <div class="col-md-6 col-lg-4">
                <a class="portfolio-item d-block mx-auto" href="#portfolio-modal-5">
                    <div class="portfolio-item-caption d-flex position-absolute h-100 w-100">
                        <div class="portfolio-item-caption-content my-auto w-100 text-center text-white">
                            <i class="fa fa-search-plus4 fa-3x"></i>
                        </div>
                    </div>
                    <img class="img-fluid" src="images/portfolio/safe.png" alt="">
                </a>
            </div>
            <div class="col-md-6 col-lg-4">
                <a class="portfolio-item d-block mx-auto" href="#portfolio-modal-6">
                    <div class="portfolio-item-caption d-flex position-absolute h-100 w-100">
                        <div class="portfolio-item-caption-content my-auto w-100 text-center text-white">
                            <i class="fa fa-search-plus5 fa-3x"></i>
                        </div>
                    </div>
                    <img class="img-fluid" src="images/portfolio/submarine.png" alt="">
                </a>
            </div>
        </div>
    </div>
</section>

<!-- About Section -->
<section class="bg-primary text-white mb-0" id="about">
    <div class="container">
        <h2 class="text-center text-uppercase text-white"><fmt:message key="nav.about"/></h2>
        <hr class="star-light mb-5">
        <div class="row">
            <div class="col-lg-4 ml-auto">
                <p class="lead"><fmt:message key="about.description1"/></p>
            </div>
            <div class="col-lg-4 mr-auto">
                <p class="lead"><fmt:message key="about.description2"/></p>
            </div>
        </div>
        <div class="text-center mt-4">
            <a class="btn btn-xl btn-outline-light" href="${pageContext.request.contextPath}/feedback.jsp">
                <i class="fa fa-download mr-2"></i>
                <fmt:message key="feedback"/>!
            </a>
        </div>
    </div>
</section>

<!-- Footer -->
<footer class="footer text-center">
    <div class="container">
        <div class="row">
            <div class="col-md-4 mb-5 mb-lg-0">
                <h4 class="text-uppercase mb-4"><fmt:message key="location"/></h4>
                <p class="lead mb-0"><fmt:message key="location.descr"/></p>
            </div>


        </div>
    </div>
</footer>

<div class="copyright py-4 text-center text-white">
    <div class="container">
        <small> Pariroom 2018</small>
    </div>
</div>

<!-- Scroll to Top Button (Only visible on small and extra-small screen sizes) -->
<div class="scroll-to-top d-lg-none position-fixed ">
    <a class="js-scroll-trigger d-block text-center text-white rounded" href="#page-top">
        <i class="fa fa-chevron-up"></i>
    </a>
</div>

<!-- Portfolio Modals -->

<!-- Portfolio Modal 1 -->
<div class="portfolio-modal mfp-hide" id="portfolio-modal-1">
    <div class="portfolio-modal-dialog bg-white">
        <a class="close-button d-none d-md-block portfolio-modal-dismiss" href="${pageContext.request.contextPath}/jsp/login.jsp">
            <i class="fa fa-3x fa-times"></i>
        </a>
        <div class="container text-center">
            <div class="row">
                <div class="col-lg-8 mx-auto">
                    <h2 class="text-secondary text-uppercase mb-0"><fmt:message key="sport.football"/></h2>
                    <hr class="star-dark mb-5">
                    <img class="img-fluid mb-5" src="images/portfolio/cabin.png" alt="">
                    <p class="mb-5"><fmt:message key="sport.footdes"/></p>
                    <a class="1" href="${pageContext.request.contextPath}/jsp/login.jsp">
                        <fmt:message key="make.bet"/></a>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Portfolio Modal 2 -->
<div class="portfolio-modal mfp-hide" id="portfolio-modal-2">
    <div class="portfolio-modal-dialog bg-white">
        <a class="close-button d-none d-md-block portfolio-modal-dismiss" href="${pageContext.request.contextPath}/jsp/login.jsp">
            <i class="fa fa-3x fa-times"></i>
        </a>
        <div class="container text-center">
            <div class="row">
                <div class="col-lg-8 mx-auto">
                    <h2 class="text-secondary text-uppercase mb-0"><fmt:message key="sport.basketball"/></h2>
                    <hr class="star-dark mb-5">
                    <img class="img-fluid mb-5" src="images/portfolio/cake.png" alt="">
                    <p class="mb-5"><fmt:message key="sport.basketdes"/></p>
                    <a class="1" href="${pageContext.request.contextPath}/jsp/login.jsp">
                        <fmt:message key="make.bet"/></a>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Portfolio Modal 3 -->
<div class="portfolio-modal mfp-hide" id="portfolio-modal-3">
    <div class="portfolio-modal-dialog bg-white">
        <a class="close-button d-none d-md-block portfolio-modal-dismiss" href="${pageContext.request.contextPath}/jsp/login.jsp">
            <i class="fa fa-3x fa-times"></i>
        </a>
        <div class="container text-center">
            <div class="row">
                <div class="col-lg-8 mx-auto">
                    <h2 class="text-secondary text-uppercase mb-0"><fmt:message key="sport.hockey"/>
                    </h2>
                    <hr class="star-dark mb-5">
                    <img class="img-fluid mb-5" src="images/portfolio/circus.png" alt="">
                    <p class="mb-5"><fmt:message key="sport.hockeydes"/>
                    </p>
                    <a class="1" href="${pageContext.request.contextPath}/jsp/login.jsp">
                        <fmt:message key="make.bet"/>
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Portfolio Modal 4 -->
<div class="portfolio-modal mfp-hide" id="portfolio-modal-4">
    <div class="portfolio-modal-dialog bg-white">
        <a class="close-button d-none d-md-block portfolio-modal-dismiss" href="${pageContext.request.contextPath}/jsp/login.jsp">
            <i class="fa fa-3x fa-times"></i>
        </a>
        <div class="container text-center">
            <div class="row">
                <div class="col-lg-8 mx-auto">
                    <h2 class="text-secondary text-uppercase mb-0"><fmt:message key="sport.volley"/></h2>
                    <hr class="star-dark mb-5">
                    <img class="img-fluid mb-5" src="images/portfolio/game.png" alt="">
                    <p class="mb-5"><fmt:message key="sport.volleydes"/></p>
                    <a class="1" href="${pageContext.request.contextPath}/jsp/login.jsp">
                        <fmt:message key="make.bet"/>
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Portfolio Modal 5 -->
<div class="portfolio-modal mfp-hide" id="portfolio-modal-5">
    <div class="portfolio-modal-dialog bg-white">
        <a class="close-button d-none d-md-block portfolio-modal-dismiss" href="${pageContext.request.contextPath}/jsp/login.jsp">
            <i class="fa fa-3x fa-times"></i>
        </a>
        <div class="container text-center">
            <div class="row">
                <div class="col-lg-8 mx-auto">
                    <h2 class="text-secondary text-uppercase mb-0"><fmt:message key="sport.tennis"/></h2>
                    <hr class="star-dark mb-5">
                    <img class="img-fluid mb-5" src="images/portfolio/safe.png" alt="">
                    <p class="mb-5"><fmt:message key="sport.tennisdes"/></p>
                    <a class="1" href="${pageContext.request.contextPath}/jsp/login.jsp">
                        <fmt:message key="make.bet"/>
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Portfolio Modal 6 -->
<div class="portfolio-modal mfp-hide" id="portfolio-modal-6">
    <div class="portfolio-modal-dialog bg-white">
        <a class="close-button d-none d-md-block portfolio-modal-dismiss" href="${pageContext.request.contextPath}/jsp/login.jsp">
            <i class="fa fa-3x fa-times"></i>
        </a>
        <div class="container text-center">
            <div class="row">
                <div class="col-lg-8 mx-auto">
                    <h2 class="text-secondary text-uppercase mb-0"><fmt:message key="cybersport"/>
                    </h2>
                    <hr class="star-dark mb-5">
                    <img class="img-fluid mb-5" src="images/portfolio/submarine.png" alt="">
                    <p class="mb-5"><fmt:message key="m.cybersportdes"/>
                    </p>
                    <a class="1" href="${pageContext.request.contextPath}/jsp/login.jsp">
                        <fmt:message key="make.bet"/>
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap core JavaScript -->
<script src="${pageContext.request.contextPath}/vendor/jquery/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Plugin JavaScript -->
<script src="${pageContext.request.contextPath}/vendor/jquery-easing/jquery.easing.min.js"></script>
<script src="${pageContext.request.contextPath}/vendor/magnific-popup/jquery.magnific-popup.min.js"></script>

<!-- Contact Form JavaScript -->
<script src="${pageContext.request.contextPath}/js/jqBootstrapValidation.js"></script>
<script src="${pageContext.request.contextPath}/js/contact_me.js"></script>

<!-- Custom scripts for this template -->
<script src="${pageContext.request.contextPath}/js/freelancer.min.js"></script>

</body>

</html>
