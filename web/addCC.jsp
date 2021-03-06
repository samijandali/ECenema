<%@ page import="model.UserService" %>
<%@ page import="model.User" %>
<!DOCTYPE html>
<html lang="en">
<!-- Mirrored from mono.flatheme.net/Shop/Other/Checkout.html by HTTrack Website Copier/3.x [XR&CO'2014], Mon, 30 Sep 2019 02:38:18 GMT -->
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="keywords" content="">
    <title>Edit Profile</title>
    <link href="https://mono.flatheme.net/assets/images/favicon.png" rel="shortcut icon">
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
    <nav class="navbar">
        <div class="container">
            <a class="navbar-brand" href="index.jsp">
                <h5>NotAMC Theatres</h5>
            </a>
            <ul class="list-horizontal-unstyled">
                <a style="white-space:pre" class="nav-link" href="index.jsp">Home</a>
                <li class="nav-item">
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="Logout.html">Logout</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="adminPage.jsp">Admin Page</a>
                </li>
            </ul>
        </div>
    </nav>
</header>
<div class="section">
    <div class="container">
        <div class="row">
            <div class="col-12 col-lg-8">
                <div class="return-login">
                    <div class="row">
                        <div class="col-12 col-sm-6">
                            <label>Email</label>
                            <input type="email" name="email">
                        </div>
                        <div class="col-12 col-sm-6">
                            <label>Password</label>
                            <input type="password" name="pw">
                        </div>
                    </div>
                    <button class="button button-lg button-dark">Login</button>
                </div>
                <h4 class="margin-bottom-20">Add Credit Card</h4>
                <%
                    UserService movieService = new UserService();
                    User user = movieService.getUser((String)session.getAttribute("user"));
                %>
                <br>
                <form action="./editoprofilo">
                    <h4 class="font-weight-light margin-bottom-30">Billing Information</h4>
                    <div>
                        <div class="form-row">
                            <div class="col">
                                <label>New Address</label>
                                <input type="text"
                                       id="caddress" name="caddress" class="form-control" /> <br />
                            </div>
                            <div class="col">
                                <label>State</label>
                                <input type="text"
                                       id="cstate" name="cstate" class="form-control" /> <br />
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="col">
                                <label>New Zip Code</label>
                                <input type="text"
                                       id="czipcode" name="czipcode" class="form-control" /> <br />
                            </div>
                            <div class="col">
                                <label>Country</label>
                                <input type="text"
                                       id="ccountry" name="ccountry" class="form-control" /> <br />
                            </div>

                        </div>
                        <br>

                        <div class="form-row">
                            <div class="col">
                                <label>New Credit Card Number</label>
                                <input type="number" name="cardno">
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="col">
                                <label>New CVV</label>
                                <input type="text" name="cvv">
                            </div>
                            <div class="col">
                                <label>Exp Date</label>
                                <input type="text" name="exp">
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="col">
                                <label>Un/Subscribe for promotions</label>
                            </div>
                            <div class="col">
                                <input class="form-check-input" type="checkbox" name="promo" id="promo" value="1">
                            </div>
                        </div>
                    </div>

                    <button type="submit" value="submit" class="button button-lg button-grey button-rounded">Submit</button>
                </form>
            </div>
        </div>
    </div>
</div>
<footer>
</footer>
<script src="https://mono.flatheme.net/assets/plugins/jquery.min.js"></script>
<script src="https://mono.flatheme.net/assets/plugins/bootstrap/popper.min.js"></script>
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
<script src="https://mono.flatheme.net/assets/js/functions.min.js"></script>
</body>
</html>
