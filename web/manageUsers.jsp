<%@ page import="model.User" %>
<%@ page import="java.sql.*" %>
<%@ page import="model.UserService" %>
<%@ page import="java.util.ArrayList" %>

<%
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
<!DOCTYPE html>
<html lang="en">
<!-- Mirrored from mono.flatheme.net/Shop/Other/Checkout.html by HTTrack Website Copier/3.x [XR&CO'2014], Mon, 30 Sep 2019 02:38:18 GMT -->
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="keywords" content="">
    <title>Manage Users</title>
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
            <a class="navbar-brand" href="index.jsp">
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
                    <a class="nav-link" href="./adminServ">Admin Page</a>
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
                <form action ="./newAdminServ">
                    <h4 class="margin-bottom-20">Add New Admin User</h4>
                    <div>
                        <label class="required">Username</label>
                        <input type="text" name="username">
                    </div>
                    <div>
                        <label class="required">Password</label>
                        <input type="text" name="password">
                    </div>
                    <div>
                        <label class="required">Email</label>
                        <input type="email" name="email">
                    </div>
                    <!-- Submit button for new Username -->
                    <button class="button button-lg button-grey button-rounded">Submit</button>
                </form>



                <!-- Divider -->
                <hr class="bg-black-09">
                <h4 class="margin-bottom-20">Current Users</h4>
                <form action="./suspendServ">
                <table class="table">
                    <thead class="thead-dark">
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">First</th>
                        <th scope="col">Last</th>
                        <th scope="col">Email</th>
                        <th scope="col">Manage</th>
                    </tr>
                    </thead>
                    <tbody>

                    <%
                        ArrayList<User> userlist = (ArrayList<User>) session.getAttribute("userlist");
                        int count = 0;
                        if(userlist.size() > 0){
                            for(int q=0; q<userlist.size(); q++){%>
                            <tr>
                                <th scope="row"><%out.print(count);%></th>
                                <td><%out.print(userlist.get(q).getFname());%></td>
                                <td><%out.print(userlist.get(q).getLname());%></td>
                                <td><%out.print(userlist.get(q).getEmail());%></td>
                                <td><a href="./suspendServ?email=<%out.print(userlist.get(q).getEmail());%>" name="email" class="text-blue">
                                <%if(userlist.get(q).getSuspended() == 0){
                                    out.print("Suspend");
                                    }else{
                                    out.print("Unsuspend");
                                }%>
                                </a></td>
                            </tr>
                            <%count++;
                            }}%>
                    </tbody>
                </table>
                </form>





                <!-- checkbox -->
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