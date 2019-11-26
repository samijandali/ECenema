<%@ page import="model.User" %><%
    User user = new User();
    if (session.getAttribute("user") != null){
        user = (User) session.getAttribute("user");
    }
int admin = user.getAdmin();
if(admin == 0)
{
response.sendRedirect("index.jsp");
return; //necessary to make the redirect happen right now
} %>
<html lang="en">
<!-- Mirrored from mono.flatheme.net/Shop/Other/Checkout.html by HTTrack Website Copier/3.x [XR&CO'2014], Mon, 30 Sep 2019 02:38:18 GMT -->
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="keywords" content="">
    <title>Admin Page</title>
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
                <li class="nav-item">
                    <a class="nav-link" href="./Logout">Logout</a>
                </li>
                <!-- dropdown link 8 -->
                <li class="nav-item">
                    <a class="nav-link" href="about.html">about</a>
                </li>
            </ul>
            </ul>
        </div><!-- end container -->
    </nav>
    <!-- Page header -->
    <div class="section-sm bg-grey" style="padding: 10px 0">
        <div class="container">
            <div class="row align-items-center">
                <div class="col-12 col-md-12 text-right">
                    <nav aria-label="breadcrumb">
                        <ul class="breadcrumb">
                            <li class="breadcrumb-item"><a href="manageUsers.jsp">Manage Users</a></li>
                            <li class="breadcrumb-item"><a href="manageMovies.jsp">Manage Movies</a></li>
                            <li class="breadcrumb-item"><a href="addShowtime.jsp">Manage Showtimes</a></li>
                            <li class="breadcrumb-item"><a href="manageHalls.jsp">Manage Halls</a></li>
                            <li class="breadcrumb-item"><a href="managePromos.jsp">Manage Promotions</a></li>
                            <li class="breadcrumb-item"><a href="reports.html">View Reports</a></li>
                        </ul>
                    </nav>
                </div>
            </div><!-- end row -->
        </div><!-- end container -->
    </div>
</header>
<div class="section">
    <div class="container">
        <div class="row">
            <div class="col-12 col-lg-6">
                <!-- Billing details -->
                <h4 class="margin-bottom-20">Website Stats</h4>
                <form>
                    <div class="row">
                        <div class="col-12 col-sm-6">
                            <label>Movies Available</label>
                            <br>
                            <label>9</label>
                        </div>
                        <div class="col-12 col-sm-6">
                            <label>Running Promotions</label>
                            <br>
                            <label>0</label>
                        </div>
                        <div class="col-12 col-sm-6">
                            <label>Registered Users</label>
                            <br>
                            <label>37</label>
                        </div>
                    </div>
                </form>
            </div><!-- end row -->
        </div><!-- end container -->
    </div>
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
</div>
</body>
<!-- Mirrored from mono.flatheme.net/Shop/Other/Checkout.html by HTTrack Website Copier/3.x [XR&CO'2014], Mon, 30 Sep 2019 02:38:18 GMT -->
</html>