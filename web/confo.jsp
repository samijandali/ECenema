<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.util.Map" %>
<%@ page import="model.User" %>
<%@ page import="model.ShowtimeService" %>
<%@ page import="model.PriceService" %>
<%@ page import="model.Price" %>
<%@ page import="java.text.DecimalFormat" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="keywords" content="">
    <title>Order conformation</title>
    <link href="https://mono.flatheme.net/assets/plugins/bootstrap/bootstrap.min.css" rel="stylesheet">
    <link href="https://mono.flatheme.net/assets/plugins/owl-carousel/owl.carousel.min.css" rel="stylesheet">
    <link href="https://mono.flatheme.net/assets/plugins/owl-carousel/owl.theme.default.min.css" rel="stylesheet">
    <link href="https://mono.flatheme.net/assets/plugins/magnific-popup/magnific-popup.min.css" rel="stylesheet">
    <link href="https://mono.flatheme.net/assets/css/app.min.css" rel="stylesheet">
    <link href="https://mono.flatheme.net/assets/plugins/font-awesome/css/all.css" rel="stylesheet">
    <link href="https://mono.flatheme.net/assets/plugins/themify/themify-icons.min.css" rel="stylesheet">
</head>
<body data-preloader="2">
<header>
    <nav class="navbar navbar-absolute">
        <div class="container">
            <a class="navbar-brand" href="index.jsp">
                <h5>NotAMC Theatre</h5>
            </a>
            <nav class="nav">
                <div class="container">
                    <ul class="nav m-auto dropdown-transparent-dark">
                        <!-- dropdown link 1 -->
                        <li class="nav-item">
                            <a style="white-space:pre" class="nav-link" href="index.jsp">Home</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="Login.html">Login</a>
                        </li>
                        <!-- dropdown link 8 -->
                        <li class="nav-item">
                            <a class="nav-link" href="about.html">About</a>
                        </li>
                    </ul>
                    <!-- Nav Toggle button -->
                    <button class="nav-toggle-btn">
                        <span class="lines"></span>
                    </button><!-- toggle button will show when screen resolution is less than 992px -->
                </div><!-- end container -->
            </nav>
            <!-- Nav Toggle button -->
            <button class="nav-toggle-btn">
                <span class="lines"></span>
            </button><!-- toggle button will show when screen resolution is less than 992px -->
        </div><!-- end container -->
    </nav>
</header>
<div class="section-fullscreen bg-image">
    <div class="bg-black-04">
        <div class="container text-center">
            <div class="position-middle">
                <div class="row">
                    <div class="col-12 col-sm-8 offset-sm-2 col-md-6 offset-md-3 col-lg-4 offset-lg-4">
                        <h4 class="font-weight-light margin-bottom-30">Order Completed</h4>
                        <h3>Your order has been completed, check below for details</h3>
                            <h6>Order Details:</h6>
                        <%
                            int showtimeID = (Integer) session.getAttribute("showtimeID");
                            ShowtimeService showtimeService = new ShowtimeService();
                            DecimalFormat df = new DecimalFormat("#.##");
                            String movie = showtimeService.getMovie(showtimeID);
                            String[] seatNbs = (String[]) session.getAttribute("seatNbs");
                            String[] seatLoc = (String[]) session.getAttribute("seatLoc");
                            String seatnabs = String.join(", ", seatNbs);
                            String seatlocos = String.join(", ", seatLoc);
                            Map<Integer, Integer> seatFre = (Map) session.getAttribute("seatFre");
                            double total = Double.parseDouble(request.getParameter("total"));
                            double discount = (Double) session.getAttribute("discount");
                        %>
                        <h5>
                            Movie: <% out.print(movie);%>
                            <br>
                            Seats Numbers:
                            <%
                               out.print(seatlocos);
                            %>
                            <br>
                            Seat Type and Count:
                            <%
                                PriceService priceService = new PriceService();
                                for (Map.Entry<Integer,Integer> entry : seatFre.entrySet()) {
                                    %>
                            <br>
                            <span class="payment-detail-product-quantity">
                            <% out.print(priceService.getByID(entry.getKey()));%> x <span class="payment-detail-product-quantity"> <% out.print(entry.getValue());%> </span><span>
                            <%
                                } %>
                            <br>
                            Total Before Discount: $<% out.print(total); %>
                                <br>
                            Discount : <% out.print(100 * discount); %>%
                                <br>
                            Total After Discount: $<% out.print(df.format(total * (1.0 - discount))); %>
                        </h5>
                    </div>
                </div><!-- end row -->
            </div><!-- end position-middle -->
        </div><!-- end container -->
    </div>
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
</body>
<!-- Mirrored from mono.flatheme.net/Pages/Account/.html by HTTrack Website Copier/3.x [XR&CO'2014], Sun, 29 Sep 2019 23:11:31 GMT -->
</html>