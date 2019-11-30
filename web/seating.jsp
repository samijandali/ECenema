<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.Driver" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.*"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.*" %>
<%@ page import="java.util.Arrays" %>
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
                    <div id="seat-map">
                        <div class="front-indicator">Front</div>
                    </div>
                    <div class="booking-details">
                        <h2>Booking Details</h2>
                        <h3> Selected Seats (<span id="counter">0</span>):</h3>
                        <ul id="selected-seats">
                        </ul>
                        Total: <b>$<span id="total">0</span></b>
                        <button class="checkout-button">Checkout &raquo;</button>
                        <div id="legend"></div>
                    </div>
                    <script>
                        var firstSeatLabel = 1;

                        $(document).ready(function() {
                            var $cart = $('#selected-seats'),
                                $counter = $('#counter'),
                                $total = $('#total'),
                                sc = $('#seat-map').seatCharts({
                                    map: [
                                        'ff_ff',
                                        'ff_ff',
                                        'ee_ee',
                                        'ee_ee',
                                        'ee___',
                                        'ee_ee',
                                        'ee_ee',
                                        'ee_ee',
                                        'eeeee',
                                    ],
                                    seats: {
                                        f: {
                                            price   : 100,
                                            classes : 'first-class', //your custom CSS class
                                            category: 'First Class'
                                        },
                                        e: {
                                            price   : 40,
                                            classes : 'economy-class', //your custom CSS class
                                            category: 'Economy Class'
                                        }

                                    },
                                    naming : {
                                        top : false,
                                        getLabel : function (character, row, column) {
                                            return firstSeatLabel++;
                                        },
                                    },
                                    legend : {
                                        node : $('#legend'),
                                        items : [
                                            [ 'f', 'available',   'First Class' ],
                                            [ 'e', 'available',   'Economy Class'],
                                            [ 'f', 'unavailable', 'Already Booked']
                                        ]
                                    },
                                    click: function () {
                                        if (this.status() == 'available') {
                                            //let's create a new <li> which we'll add to the cart items
                                            $('<li>'+this.data().category+' Seat # '+this.settings.label+': <b>$'+this.data().price+'</b> <a href="#" class="cancel-cart-item">[cancel]</a></li>')
                                                .attr('id', 'cart-item-'+this.settings.id)
                                                .data('seatId', this.settings.id)
                                                .appendTo($cart);

                                            /*
                                             * Lets up<a href="https://www.jqueryscript.net/time-clock/">date</a> the counter and total
                                             *
                                             * .find function will not find the current seat, because it will change its stauts only after return
                                             * 'selected'. This is why we have to add 1 to the length and the current seat price to the total.
                                             */
                                            $counter.text(sc.find('selected').length+1);
                                            $total.text(recalculateTotal(sc)+this.data().price);

                                            return 'selected';
                                        } else if (this.status() == 'selected') {
                                            //update the counter
                                            $counter.text(sc.find('selected').length-1);
                                            //and total
                                            $total.text(recalculateTotal(sc)-this.data().price);

                                            //remove the item from our cart
                                            $('#cart-item-'+this.settings.id).remove();

                                            //seat has been vacated
                                            return 'available';
                                        } else if (this.status() == 'unavailable') {
                                            //seat has been already booked
                                            return 'unavailable';
                                        } else {
                                            return this.style();
                                        }
                                    }
                                });

                            //this will handle "[cancel]" link clicks
                            $('#selected-seats').on('click', '.cancel-cart-item', function () {
                                //let's just trigger Click event on the appropriate seat, so we don't have to repeat the logic here
                                sc.get($(this).parents('li:first').data('seatId')).click();
                            });

                            //let's pretend some seats have already been booked
                            sc.get(['1_2', '4_1', '7_1', '7_2']).status('unavailable');

                        });

                        function recalculateTotal(sc) {
                            var total = 0;

                            //basically find every selected seat and sum its price
                            sc.find('selected').each(function () {
                                total += this.data().price;
                            });

                            return total;
                        }

                    </script>
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