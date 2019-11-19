<%@ page import="model.User" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.Driver" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.*"%>
<%@ page import="model.MovieService" %>
<%@ page import="model.Movie" %>
<%@ page import="java.util.ArrayList" %>
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
                    <a style="white-space:pre" class="nav-link" href="/">Home</a>
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
                <div class="product-rating margin-bottom-20">
                    <i class="fas fa-star"></i>
                    <i class="fas fa-star"></i>
                    <i class="fas fa-star"></i>
                    <i class="fas fa-star"></i>
                    <i class="far fa-star"></i>
                </div>
                <!-- Product text -->

                <p><% out.print(movie.getSummary()); %>
                </p>

                <!-- Order -->
                <div class="margin-top-20 margin-bottom-20">
                    <div class="product-quantity margin-right-30">
                        <button class="button-circle button-circle-xs button-circle-grey dec"><i class="ti-minus"></i></button>
                        <input type="text" name="quantity" id="quantity" value="1">
                        <button class="button-circle button-circle-xs button-circle-grey inc"><i class="ti-plus"></i></button>
                    </div>
                    <a class="button button-lg button-dark" href="#">Add to cart</a>
                </div>
                <img src="assets/images/seating.png" usemap="#image-map">
            </div>
        </div><!-- end row -->
        <map name="image-map">
            <area target="" alt="" title="" href="" coords="190,109,161,84" shape="rect">
            <area target="" alt="" title="" href="" coords="197,87,224,108" shape="rect">
        </map>
        <br>
        <br>
        <div>
            <select class="custom-select" multiple>
                <option selected>Open this select menu</option>
                <option value="1">Tuesday 1:00 PM</option>
                <option value="2">Tuesday 4:00 PM</option>
                <option value="3">Wednesday 12:00 PM</option>
                <option value="3">Wednesday 1:00 PM</option>
                <option value="3">Thursday 5:00 PM</option>
                <option value="3">Thursday 8:00 PM</option>
                <option value="3">Thursday 9:00 PM</option>
            </select>
        </div>
    </div><!-- end container -->
</div>
<iframe width="1206" height="678" src="https://www.youtube.com/embed/TcMBFSGVi1c" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
<!-- end Product Content -->
<!-- Product Tab content -->
<div class="section no-padding-top">
    <div class="container">
        <div class="product-tab">
            <ul class="nav margin-bottom-20">
                <li class="nav-item">
                    <a class="nav-link active" data-toggle="tab" href="#tab-description"><h5 class="font-weight-light">Description</h5></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" data-toggle="tab" href="#tab-reviews"><h5 class="font-weight-light">Reviews <span>(3)</span></h5></a>
                </li>
            </ul>
            <div class="tab-content">
                <!-- Description tab content -->
                <div class="tab-pane fade show active" id="tab-description">
                    <div class="margin-bottom-20">
                        <h6 class="heading-uppercase">Product Material:</h6>
                        <p>Avengers: Endgame is a 2019 American superhero film based on the Marvel Comics superhero team the Avengers, produced by Marvel Studios and distributed by Walt Disney Studios Motion Pictures. It is the sequel to 2012's The Avengers, 2015's Avengers: Age of Ultron, and 2018's Avengers: Infinity War, and the twenty-second film in the Marvel Cinematic Universe (MCU). It was directed by Anthony and Joe Russo and written by Christopher Markus and Stephen McFeely, and features an ensemble cast including Robert Downey Jr., Chris Evans, Mark Ruffalo, Chris Hemsworth, Scarlett Johansson, Jeremy Renner, Don Cheadle, Paul Rudd, Brie Larson, Karen Gillan, Danai Gurira, Benedict Wong, Jon Favreau, Bradley Cooper, Gwyneth Paltrow, and Josh Brolin. In the film, the surviving members of the Avengers and their allies attempt to reverse the damage caused by Thanos in Infinity War.</p>
                    </div>
                    <div>
                        <h6 class="heading-uppercase">Cast:</h6>


                        <ul>
                            <li>Robert Downey Jr.</li>
                            <li>Chris Evans</li>
                            <li>Mark Ruffalo</li>
                            <li>Chris Hemsworth</li>
                            <li>Scarlett Johansson</li>
                            <li>Jeremy Renner</li>
                            <li>Don Cheadle</li>
                            <li>Paul Rudd</li>
                            <li>Brie Larson</li>
                            <li>Karen Gillan</li>
                            <li>Danai Gurira</li>
                            <li>Benedict Wong</li>
                            <li>Jon Favreau</li>
                            <li>Bradley Cooper</li>
                            <li>Gwyneth Paltrow</li>
                            <li>Josh Brolin</li>
                        </ul>
                    </div>
                </div>
                <!-- Reviews tab content -->
                <div class="tab-pane fade" id="tab-reviews">
                    <!-- Review box 1 -->
                    <div class="product-tab-review">
                        <div class="product-tab-review-user">
                            <i class="fa fa-user"></i>
                        </div>
                        <div class="product-tab-review-content">
                            <span class="product-tab-review-time">Feb 16, 2018</span>
                            <h6 class="heading-uppercase">Alexander Warren</h6>
                            <div class="product-rating">
                                <i class="fas fa-star"></i>
                                <i class="fas fa-star"></i>
                                <i class="fas fa-star"></i>
                                <i class="fas fa-star"></i>
                                <i class="fas fa-star"></i>
                            </div>
                            <p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.</p>
                        </div>
                    </div>
                    <!-- Review box 2 -->
                    <div class="product-tab-review">
                        <div class="product-tab-review-user">
                            <i class="fa fa-user"></i>
                        </div>
                        <div class="product-tab-review-content">
                            <span class="product-tab-review-time">Feb 14, 2018</span>
                            <h6 class="heading-uppercase">John Doe</h6>
                            <div class="product-rating">
                                <i class="fas fa-star"></i>
                                <i class="fas fa-star"></i>
                                <i class="fas fa-star"></i>
                                <i class="fas fa-star"></i>
                                <i class="far fa-star"></i>
                            </div>
                            <p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.</p>
                        </div>
                    </div>
                    <!-- Review box 3 -->
                    <div class="product-tab-review">
                        <div class="product-tab-review-user">
                            <i class="fa fa-user"></i>
                        </div>
                        <div class="product-tab-review-content">
                            <span class="product-tab-review-time">Feb 12, 2018</span>
                            <h6 class="heading-uppercase">Melissa Bakos</h6>
                            <div class="product-rating">
                                <i class="fas fa-star"></i>
                                <i class="fas fa-star"></i>
                                <i class="fas fa-star"></i>
                                <i class="fas fa-star"></i>
                                <i class="fas fa-star"></i>
                            </div>
                            <p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.</p>
                        </div>
                    </div>
                    <!-- end Review box 3 -->
                </div>
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