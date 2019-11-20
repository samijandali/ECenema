<%@ page import="model.MovieService" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="model.Movie" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html lang="en">
<!-- Mirrored from mono.flatheme.net/Shop/Other/Checkout.html by HTTrack Website Copier/3.x [XR&CO'2014], Mon, 30 Sep 2019 02:38:18 GMT -->
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="keywords" content="">
    <title>Add Showtime</title>
    <!-- Favicon -->
    <link href="https://mono.flatheme.net/assets/images/favicon.png" rel="shortcut icon">
    <!-- CSS -->
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
            <a class="navbar-brand" href="/">
                <h5>NotAMC Theatres</h5>
            </a>
            <ul class="list-horizontal-unstyled">
                <li class="nav-item">
                    <a style="white-space:pre" class="nav-link" href="/">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="login.html">Login</a>
                </li>
                <!-- dropdown link 8 -->
                <li class="nav-item">
                    <a class="nav-link" href="About.html">About</a>
                </li>
            </ul>
        </div><!-- end container -->
    </nav>
</header>
<div class="section">
    <div class="container">
        <div class="row">
            <div class="col-12 col-lg-8">
                <!-- Returning customer login -->
                <div class="return-login">
                    <form>
                        <input type="hidden" id="add" name="add" value="add">
                        <div class="row">
                            <div class="col-12 col-sm-6">
                                <label class="required">Email</label>
                                <input type="email" name="email" required>
                            </div>
                            <div class="col-12 col-sm-6">
                                <label class="required">Password</label>
                                <input type="password" name="pw" required>
                            </div>
                        </div>
                        <button class="button button-lg button-dark">Login</button>
                    </form>
                </div>
                <form action="./addShowtime">
                    <h4 class="margin-bottom-20">Add New Showtime</h4>
                    <input type="hidden" name="hidden" value="add">
                    <div class="form-row">

                        <div class="col">
                            <label>Movie Title</label>
                            <input type="text" name="title" required>
                        </div>
                        <div class="col">
                            <label>Showroom Name</label>
                            <select class="form-control" name="showroom">
                                <option>1</option>
                                <% // TODO: Add showRoom.getName()%>
                            </select>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="col">
                            <label>Day</label>
                            <input type="text" name="day" required>
                        </div>
                        <div class="col">
                            <label>Time Slot</label>
                            <select class="form-control" name="time">
                                <option>1</option>
                                <% // TODO: Add showRoom.getName()%>
                            </select>
                        </div>
                    </div>
                    <br>
                    <br>
                    <div>
                        <button class="button button-lg button-grey button-rounded">Submit</button>
                    </div>
                </form>
                    <br>
                    <br>
                    <!-- checkbox -->
                    <div class="create-account-box">
                        <div>
                            <label class="required">Create account password</label>
                            <input type="password" name="pw">
                        </div>
                    </div>
            </div>
        </div><!-- end row -->
    </div><!-- end container -->
</div>
<footer>
    <!-- end footer -->
</footer>
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
<!-- Mirrored from mono.flatheme.net/Shop/Other/Checkout.html by HTTrack Website Copier/3.x [XR&CO'2014], Mon, 30 Sep 2019 02:38:18 GMT -->
</html>