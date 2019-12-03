<%@ page import="model.User" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Order" %>
<%@ page import="model.OrderService" %>
<%
    User user = new User();
    if (session.getAttribute("user") != null){
        user = (User) session.getAttribute("user");
    }else{
        response.sendRedirect("index.jsp");
        return;
    } %>
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
            <a class="navbar-brand" href="index.jsp">
                <h5>NotAMC Theatres</h5>
            </a>
            <ul class="list-horizontal-unstyled">
                <li class="nav-item nav-dropdown">
                    <a style="white-space:pre" class="nav-link" href="index.jsp">Home</a>
                </li>
                <% if (1 == user.getAdmin()) { %>
                <li class="nav-item nav-dropdown">
                    <a class="nav-link" href="adminPage.jsp">Admin Page</a>
                </li>
                <% } %>
                <li class="nav-item nav-dropdown">
                    <a class="nav-link" href="./Logout">Logout</a>
                </li>
                <li class="nav-item nav-dropdown" >
                    <a class="nav-link" href = "profile.jsp" > Profile </a >
                </li>
            </ul>
        </div>
    </nav>
</header>

<div class="section">
    <br>
    <div class="container">
    <div class="col-12 col-lg-8">
        <h4 class="margin-bottom-20">Your Profile</h4>
    <h4 class="font-weight-light margin-bottom-30">Personal Information</h4>
    <div class="form-row">
        <div class="col">
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
            <br>
            <br>
            <hr class="bg-black-09">
            <br>
            <br>
            <a class="button button-lg button-grey button-rounded" href="editProfile.html">Edit Profile</a>
            <br>
            <br>
            <hr class="bg-black-09">
            <br>
            <br>
        </div>
        <div>
            <table class="table">
                <thead class="thead-dark">
                <tr>
                    <th scope="col">Movie</th>
                    <th scope="col">Total</th>
                    <th scope="col">Number of Tickets</th>
                    <th scope="col">Refund</th>
                </tr>
                </thead>
                <tbody>

                <%
                    OrderService orderService = new OrderService();
                    ArrayList<String[]> orders = orderService.getUserOrders(user.getId());
                    if(orders.size() > 0){
                        for(int q=0; q<orders.size(); q++){%>
                <tr>
                    <th scope="row"><%out.print(orders.get(q)[0]);%></th>
                    <td>$<%out.print(orders.get(q)[1]);%></td>
                    <td><%out.print(orders.get(q)[2]);%></td>
                    <td><a href="./Refund?orderid=<%out.print(orders.get(q)[3]);%>" name="email" class="text-blue">
                        Refund
                    </a></td>
                </tr>
                <%
                }}%>
                </tbody>
            </table>
        </div>

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