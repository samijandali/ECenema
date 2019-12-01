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
    <title><% out.print(movie.getTitle());%></title>
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
    <!-- Fonts/Icons -->
    <link href="https://mono.flatheme.net/assets/plugins/font-awesome/css/all.css" rel="stylesheet">
    <link href="https://mono.flatheme.net/assets/plugins/themify/themify-icons.min.css" rel="stylesheet">
</head>
<body data-preloader="2">
<header>
    <nav class="navbar">
        <div class="container">
            <a class="navbar-brand" href="https://mono.flatheme.net/">
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
                <li class="nav-item">
                    <a class="nav-link" href="cart.html">Cart</a>
                </li>
            </ul>
            </ul>
        </div><!-- end container -->
    </nav>
    <!-- Page header -->
</header>
<!-- Product Content -->

<div class="section">
    <div class="container">
        <div class="row align-items-center col-spacing-40">
            <div class="col-12 col-lg-6 product-single">
                 <img src="assets/images/<% out.print(movie.getTitle());%>.jpg">
            </div>
            <div class="col-12 col-lg-6">
                <!-- Product Price -->
                <div class="product-price">
                    <h5 class="font-weight-light">$14</h5>
                </div>
                <!-- Product Title -->

                <h4><% out.print(movie.getTitle()); %></h4>


                <!-- Product Rating -->
                <div>
                   <h6>Rating: <% out.print(movie.getRating());%> </h6>
                </div>
                <form action="./PassShowtime">
                <!-- Order -->
                <div>
                    <label for="showtime">Showtimes</label><select style="width: 220px" class="custom-select" id="showtime" name="showtime">
                    <%
                        ShowtimeService showtimeService = new ShowtimeService();
                        ArrayList<String[]> showtimes = showtimeService.getAllShowtimes(movie.getTitle());
                        ArrayList<Integer> ids = showtimeService.getAllIDs(movie.getTitle());
                        assert showtimes != null;
                        for (int i = 0; i<ids.size(); i++) {
                            String temp = "";
                            temp = Arrays.toString(showtimes.get(i));
                            temp = temp.replace("[", "");
                            temp = temp.replace("]", "");
                            temp = temp.replace(",", "");
                    %>
                    <option name ="movie" value="<%out.print(ids.get(i));%>"><% out.print(temp);%></option>
                    <%
                        }%>
                </select>
                </div>
                <div class="margin-top-20 margin-bottom-20">
                    <button class="button button-lg button-dark">Book a seat</button>
                </div>
                </form>
            </div>
        </div><!-- end row -->
        <br>
        <br>
    </div><!-- end container -->
</div>
<iframe width="1206" height="678" src="<% out.print(movie.getLink()); %>" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
<!-- end Product Content -->
<!-- Product Tab content -->
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
                    <div class="margin-bottom-20">
                        <h6 class="heading-uppercase">Summary:</h6>
                        <p><% out.print(movie.getSummary());%></p>
                    </div>
                    <div class="margin-bottom-20">
                        <h6 class="heading-uppercase">Genre:</h6>
                        <p><% out.print(movie.getGenre());%></p>
                    </div>
                    <div>
                        <h6 class="heading-uppercase">Cast:</h6>
                        <p><% out.print(movie.getCast());%></p>
                    </div>
                    <br>
                    <div>
                        <h6 class="heading-uppercase">Producer:</h6>
                        <p><% out.print(movie.getProducer());%></p>
                    </div>
                    <br>
                    <div>
                        <h6 class="heading-uppercase">Director:</h6>
                        <p><% out.print(movie.getDirector());%></p>
                    </div>
                    <br>
                    <div>
                        <h6 class="heading-uppercase">Rating:</h6>
                        <p><% out.print(movie.getRating());%></p>
                    </div>
                    <br>
                    <div>
                        <h6 class="heading-uppercase">Review:</h6>
                        <p><% out.print(movie.getReview1());%></p>
                    </div>
                    <br>
                    <div>
                        <h6 class="heading-uppercase">Review:</h6>
                        <p><% out.print(movie.getReview2());%></p>
                    </div>
                    <br>
                    <div>
                        <h6 class="heading-uppercase">Review:</h6>
                        <p><% out.print(movie.getReview3());%></p>
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