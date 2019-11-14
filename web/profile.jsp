<%@ page import="model.User" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="keywords" content="">
    <title>Account Information</title>
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
            <a class="navbar-brand" href="https://mono.flatheme.net/">
                <h5>NotAMC Theatres</h5>
            </a>
            <ul class="list-horizontal-unstyled">
                <li class="nav-item">
                    <a style="white-space:pre" class="nav-link" href="userHome.html">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="Logout.html">Logout</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="./ProfileServ">Profile</a>
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
    <h4 class="margin-bottom-20">Your Profile</h4>
    <br>
    <h4 class="font-weight-light margin-bottom-30">Personal Information</h4>
    <div class="form-row">
        <div class="col">
            <% User user = (User) session.getAttribute("user"); %>
            <label>Username: </label>
            <% out.print(user.getUsername()); %>
        </div>
    </div>
    <div class="form-row">
        <div class="col">
            <label>First Name: </label>
            <% out.print(user.getFname()); %>
        </div>
        <div class="col">
            <label>Last Name: </label>
            <% out.print(user.getLname()); %>
        </div>
    </div>

    <div>
        <div class="form-row">
            <div class="col">
                <label>Address: </label>
                <% out.print(user.getAddress()); %>
            </div>
            <div class="col">
                <label>State: </label>
                <% out.print(user.getState()); %>
            </div>
        </div>

        <div class="form-row">
            <div class="col">
                <label>Zip Code: </label>
                <% out.print(user.getZipCode()); %>
            </div>
            <div class="col">
                <label>Country: </label>
                <% out.print(user.getCountry()); %>
            </div>

        </div>
        <div>
            <label>Phone Number: </label>
            <% out.print(user.getPNumber()); %>
        </div>
        <br>
        <h4 class="font-weight-light margin-bottom-30">Billing Information</h4>
        <div>
            <div class="form-row">
                <div class="col">
                    <label>Address: </label>
                </div>
                <div class="col">
                    <label>State: </label>
                </div>
            </div>

            <div class="form-row">
                <div class="col">
                    <label>Zip Code:</label>
                </div>
                <div class="col">
                    <label>Country: </label>
                </div>

            </div>
            <br></br>

            <a href= "editProfile.html"> Edit Profile</a>

        </div>
        <div>
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