<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Map" %>
<%@ page import="model.*" %>
<% Movie movie = (Movie) session.getAttribute("movie");
   User user = (User) session.getAttribute("user");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="keywords" content="">
    <title>Checkout</title>
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
            <button class="nav-toggle-btn position-center">
                <span class="lines"></span>
            </button>
            <ul class="list-horizontal-unstyled">
                <li class="nav-item">
                    <a style="white-space:pre" class="nav-link" href="index.jsp">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="Login.html">Login</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="profile.jsp">Profile</a>
                </li>
            </ul>
            </ul>
        </div>
    </nav>
</header>
<div class="search-wrapper search-style-2">
    <div class="container">
        <div class="row">
            <div class="col-12 col-sm-10 offset-sm-1 col-md-8 offset-md-2 col-lg-6 offset-lg-3 text-center">
                <form class="form-style-5">
                    <input class="font-large" type="text" placeholder="Search.." name="search" required>
                    <button><i class="fa fa-search"></i></button>
                </form>
            </div>
        </div>
    </div>
</div>
<form action="./PlaceOrder">
<div class="section">
    <div class="container">
        <div class="row">
            <div class="col-12 col-lg-8">
                <div class="text-center margin-bottom-30">
                    <p>Returning Customer? <a class="return-login-btn" href="Login.html">Login</a></p>
                </div>
                <h4 class="margin-bottom-20">Billing Details</h4>
                <label for="payment">Select payment</label>
                <br><select style="width: 220px" class="custom-select" id="payment" name="payment">
                <%
                    PaymentService paymentService = new PaymentService();
                    ArrayList<String[]> payments = paymentService.getAll(user.getUsername());
                    assert payments != null;
                    for (String[] payment : payments) {
                        String temp = "";
                        temp = payment[1].substring(payment[1].length() - 4);
                %>
                <option name ="payment" value="<%out.print(payment[0]);%>">**** **** **** <% out.print(temp);%></option>
                <%
                    }%>
            </select>
<%--                <form>--%>
<%--                <div class="row">--%>
<%--                    <div class="col-12 col-sm-6">--%>
<%--                        <label class="required">First name</label>--%>
<%--                        <input type="text" name="fname" required>--%>
<%--                    </div>--%>
<%--                    <div class="col-12 col-sm-6">--%>
<%--                        <label class="required">Last name</label>--%>
<%--                        <input type="text" name="lname" required>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--                <div>--%>
<%--                    <label>Company name</label>--%>
<%--                    <input type="text" name="cname">--%>
<%--                </div>--%>
<%--                <div>--%>
<%--                    <label class="required">Country</label>--%>
<%--                    <select class="custom-select custom-select-lg select-fullwidth margin-bottom-10">--%>
<%--                        <option selected>Select your country</option>--%>
<%--                        <option value="1">Australia</option>--%>
<%--                        <option value="2">Brazil</option>--%>
<%--                        <option value="3">Canada</option>--%>
<%--                        <option value="4">France</option>--%>
<%--                        <option value="5">German</option>--%>
<%--                        <option value="6">India</option>--%>
<%--                        <option value="7">Italy</option>--%>
<%--                        <option value="8">Netherland</option>--%>
<%--                        <option value="9">Mongolia</option>--%>
<%--                        <option value="10">Turkey</option>--%>
<%--                        <option value="11">Switzerland</option>--%>
<%--                        <option value="12">United States of America</option>--%>
<%--                    </select>--%>
<%--                </div>--%>
<%--                <div class="row">--%>
<%--                    <div class="col-12 col-sm-6">--%>
<%--                        <label class="required">City</label>--%>
<%--                        <input type="text" name="city" required>--%>
<%--                    </div>--%>
<%--                    <div class="col-12 col-sm-6">--%>
<%--                        <label>State</label>--%>
<%--                        <input type="text" name="state">--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--                <div>--%>
<%--                    <label>Street address</label>--%>
<%--                    <input type="text" name="street1">--%>
<%--                    <input type="text" name="street2">--%>
<%--                </div>--%>
<%--                <div>--%>
<%--                    <label class="required">Postcode / ZIP</label>--%>
<%--                    <input type="tel" name="postcode">--%>
<%--                </div>--%>
<%--                <div class="row">--%>
<%--                    <div class="col-12 col-sm-6">--%>
<%--                        <label class="required">Phone</label>--%>
<%--                        <input type="tel" name="phone" required>--%>
<%--                    </div>--%>
<%--                    <div class="col-12 col-sm-6">--%>
<%--                        <label class="required">Email</label>--%>
<%--                        <input type="email" name="email" required>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--                <div>--%>
<%--                    <label>Order notes(optional)</label>--%>
<%--                    <textarea name="note"></textarea>--%>
<%--                </div>--%>
<%--                <div class="custom-control custom-checkbox create-account-toggle">--%>
<%--                    <input type="checkbox" class="custom-control-input" id="customCheck1">--%>
<%--                    <label class="custom-control-label" for="customCheck1">Create an account</label>--%>
<%--                </div>--%>
<%--                <div class="create-account-box">--%>
<%--                    <div>--%>
<%--                        <label class="required">Create account password</label>--%>
<%--                        <input type="password" name="pw">--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </form>--%>
            </div>
            <%
                PriceService priceService = new PriceService();
                Map<Integer, Integer> seatMap = (Map) session.getAttribute("seatFre");
            %>

            <div class="col-12 col-lg-4">
                <div class="payment-detail-box">

                    <h5 class="margin-bottom-20">Your Order</h5>
                    <h6 class="heading-uppercase"><% out.print(movie.getTitle());%>:</h6>
                    <ul class="payment-detail-product-list">
                        <%
                            int total = 0;
                            for (Map.Entry<Integer,Integer> entry : seatMap.entrySet()) {
                        %>
                        <li> <% out.print(priceService.getByID(entry.getKey()));%> <span class="payment-detail-product-quantity"> <% out.print(entry.getValue());%> </span><span> </span></li>
                        <%
                                total += priceService.getPrice(entry.getKey()) * entry.getValue();
                            } %>
                    </ul>
                    <ul>
                        <li><h6 class="heading-uppercase">Subtotal:</h6><span>$<% out.print(total);%></span></li>
                        <input type="hidden" id="total" name="total" value="<% out.print(total);%>">
                    </ul>
                </div>
                <button class="button button-xl button-dark button-fullwidth">Place Order</button>
            </div>

        </div>
    </div>
</div>
</form>
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