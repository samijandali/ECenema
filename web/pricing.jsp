<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.Driver" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.*"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.*" %>
<%@ page import="java.util.Arrays" %>
<% Movie movie = (Movie) session.getAttribute("movie"); %>
<!DOCTYPE html>
<html lang="en">
<!-- Mirrored from mono.flatheme.net/Shop/Other/Product-Single.html by HTTrack Website Copier/3.x [XR&CO'2014], Sun, 29 Sep 2019 23:57:47 GMT -->
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="keywords" content="">
    <title>Select Seats</title>
    <!-- CSS -->
    <link href="https://mono.flatheme.net/assets/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://mono.flatheme.net/assets/css/owl.carousel.min.css" rel="stylesheet">
    <link href="https://mono.flatheme.net/assets/css/owl.theme.default.min.css" rel="stylesheet">
    <link href="https://mono.flatheme.net/assets/css/magnific-popup.min.css" rel="stylesheet">
    <link href="https://mono.flatheme.net/assets/css/app.min.css" rel="stylesheet">
    <link href="https://mono.flatheme.net/assets/plugins/bootstrap/bootstrap.min.css" rel="stylesheet">
    <link href="https://mono.flatheme.net/assets/plugins/owl-carousel/owl.carousel.min.css" rel="stylesheet">
    <link href="https://mono.flatheme.net/assets/plugins/owl-carousel/owl.theme.default.min.css" rel="stylesheet">
    <link href="https://mono.flatheme.net/assets/plugins/magnific-popup/magnific-popup.min.css" rel="stylesheet">
    <link href="https://mono.flatheme.net/assets/css/app.min.css" rel="stylesheet">
    <link rel="stylesheet" href="jquery.seat-charts.css">
    <script src="//code.jquery.com/jquery.min.js"></script>
    <script src="jquery.seat-charts.js"></script>
    <!-- Fonts/Icons -->
    <link href="https://mono.flatheme.net/assets/plugins/font-awesome/css/all.css" rel="stylesheet">
    <link href="https://mono.flatheme.net/assets/plugins/themify/themify-icons.min.css" rel="stylesheet">
</head>
<body data-preloader="2">
<header>
    <nav class="navbar">
        <div class="container">
            <a class="navbar-brand" href="index.jsp">
                <h5>NotAMC Theatres</h5>
            </a>
            <button class="nav-toggle-btn position-center">
                <span class="lines"></span>
            </button><!-- toggle button will show when screen resolution is less than 992px -->
            <ul class="list-horizontal-unstyled">
                <li class="nav-item">
                    <a style="white-space:pre" class="nav-link" href="index.jsp">Home</a>
                </li>
                <%
                    if (session.getAttribute("user") != null){
                        User user = (User) session.getAttribute("user");
                        if (1 == user.getAdmin()) { %>
                <li class="nav-item nav-dropdown">
                    <a class="nav-link" href="adminPage.jsp">Admin Page</a>
                </li>
                <% } %>
                <li class="nav-item nav-dropdown">
                    <a class="nav-link" href="./Logout">Logout</a>
                </li>
                <li class="nav-item nav-dropdown" >
                    <a class="nav-link" href = "profile.jsp" > Profile </a >
                </li >
                <% } else{%>
                <li class="nav-item nav-dropdown">
                    <a class="nav-link" href="Login.html">Login</a>
                </li>
                <% } %>
            </ul>
            </ul>
        </div><!-- end container -->
    </nav>
    <!-- Page header -->
</header>
<!-- Product Content -->

<!-- end Product Content -->
<!-- Product Tab content -->

<div class = "seating-checkout__header">
    <div class="container">
        <div class="seating-checkout u-cf media">
            <div aria-hidden="true" class="media-left media-top">
                <picture class="Picture--rounded">
                    <style>
                        #circle {
                            border-radius: 50%;
                            width:100px;
                            height:100px;
                        }
                    </style>
                    <img src="assets/images/<% out.print(movie.getTitle());%>.jpg" alt="" id="circle">
                </picture>
            </div>
            <div class="media-body">
                <a class="MovieTitleHeader-title" href="http://localhost:8080/ECenema_war_exploded/BookServ?movie=<% out.print(movie.getTitle());%>#">
                    <h2 class="MovieTitleHeader-title--shortened-title"><% out.print(movie.getTitle());%></h2>
                </a>
            </div>
        </div>
    </div>
</div>
<hr class="bg-black-09">
<br>

<div class="section no-padding-top">
    <div class="container">
        <div class="product-tab">
            <ul class="nav margin-bottom-20">
                <li class="nav-item">
                    <a class="nav-link active" data-toggle="tab" href="#tab-description"><h5 class="font-weight-light">Description</h5></a>
                </li>
            </ul>
            <div class="tab-content">
                <!-- Description tab content -->
                <div class="tab-pane fade show active" id="tab-description">
                    <form action="./PassPrice">
                    <div id="seat-map">

                    </div>
                    <%
                        String[] seatNbs = (String[]) session.getAttribute("seatNbs");
                        PriceService priceService = new PriceService();
                        ArrayList<Price> prices = priceService.getPrices();
                    %>
                    <div class="booking-details">
                        <h2>Select Ticket Type</h2>

                        <%
                            for(String seat: seatNbs){

                        %><br>Seat
                        <% out.print(seat);%>
                        :
                        <select name="priceSelect">
                            <% for(Price price: prices){ %>
                            <option name='priceSelect' value="<% out.print(price.getId());%>"><% out.print(price.getType());%></option>
                            <% } %>
                        </select>
                        <% }%>
                        <br>
                        <br>
                        <button class="button button-lg button-dark">Checkout &raquo;</button>
                        </form>
                    </div>
                </div>
                <!-- Reviews tab content -->
            </div>
        </div>
    </div><!-- end container -->
</div>
<!-- end Product Tab content -->
<!-- Products Slider -->
<!-- end Products Slider -->
<!-- ***** JAVASCRIPTS ***** -->
<!-- Libraries -->
<script src="https://mono.flatheme.net/assets/plugins/jquery.min.js"></script>
<script src="https://mono.flatheme.net/assets/plugins/bootstrap/popper.min.js"></script>
<!-- Plugins -->
<script src="https://mono.flatheme.net/assets/plugins/bootstrap/bootstrap.min.js"></script>
<script src="https://mono.flatheme.net/assets/plugins/appear.min.js"></script>
<script src="https://mono.flatheme.net/assets/plugins/easing.min.js"></script>
<script src="https://mono.flatheme.net/assets/plugins/retina.min.js"></script>
<script src="https://mono.flatheme.net/assets/plugins/countdown.min.js"></script>
<script src="https://mono.flatheme.net/assets/plugins/imagesloaded.pkgd.min.js"></script>
<script src="https://mono.flatheme.net/assets/plugins/isotope.pkgd.min.js"></script>
<script src="https://mono.flatheme.net/assets/plugins/jarallax/jarallax.min.js"></script>
<script src="https://mono.flatheme.net/assets/plugins/jarallax/jarallax-video.min.js"></script>
<script src="https://mono.flatheme.net/assets/plugins/magnific-popup/magnific-popup.min.js"></script>
<script src="https://mono.flatheme.net/assets/plugins/owl-carousel/owl.carousel.min.js"></script>
<script src="https://mono.flatheme.net/assets/plugins/jquery.easypiechart.min.js"></script>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBUma4oJ7_6VbfGNdUYdv6VQ0Ph07Fz0k8"></script>
<script src="https://mono.flatheme.net/assets/plugins/gmaps.min.js"></script>
<!-- Scripts -->
<script src="https://mono.flatheme.net/assets/js/functions.min.js"></script>
</body>
<!-- Mirrored from mono.flatheme.net/Shop/Other/Product-Single.html by HTTrack Website Copier/3.x [XR&CO'2014], Sun, 29 Sep 2019 23:57:50 GMT -->
</html>