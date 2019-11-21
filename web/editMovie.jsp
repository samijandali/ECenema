<%@ page import="model.MovieService" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="model.Movie" %>
<%@ page import="java.util.ArrayList" %>
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
<!DOCTYPE html>
<html lang="en">
<!-- Mirrored from mono.flatheme.net/Shop/Other/Checkout.html by HTTrack Website Copier/3.x [XR&CO'2014], Mon, 30 Sep 2019 02:38:18 GMT -->
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="keywords" content="">
    <title>Manage Movies</title>
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
                    <a class="nav-link" href="./Logout">Logout</a>
                </li>
                <!-- dropdown link 8 -->
                <li class="nav-item">
                    <a class="nav-link" href="adminPage.jsp">Admin Page</a>
                </li>
            </ul>
        </div><!-- end container -->
    </nav>
</header>
<div class="section">
    <div class="container">
        <div class="row">
            <div class="col-12 col-lg-8">
                <%
                    MovieService movieService = new MovieService();
                    Movie movie = movieService.getByTitle((String)session.getAttribute("editMovie"));
                    session.setAttribute(movie.getSummary(), "currsummary");
                    %>

                <form action="./editMovieServ">
                    <h4 class="margin-bottom-20">Edit <% out.print(session.getAttribute("editMovie"));%></h4>
                    <input type="hidden" name="hidden" value="add">
                    <div class="form-row">
                        <div class="col">
                            <label>Title</label>
                            <input type="text" name="title" value="<% out.print(movie.getTitle());%>" >
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="col">
                            <label>Summary</label>
                            <input type="text" name="summary" value="<% out.print(movie.getSummary());%>" >
                        </div>
                        <div class="col">
                            <label>Genre</label>
                            <input type="text" name="genre" value="<% out.print(movie.getGenre());%>" >
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="col">
                            <label>Rating</label>
                            <input type="text" name="rating" value="<% out.print(movie.getRating());%>" >
                        </div>
                        <div class="col">
                            <label>Length</label>
                            <input type="text" name="length" value="<% out.print(movie.getLength());%>" >
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="col">
                            <label>Cast</label>
                            <input type="text" name="cast" value="<% out.print(movie.getCast());%>" >
                        </div>
                        <div class="col">
                            <label>Director</label>
                            <input type="text" name="director" value="<% out.print(movie.getDirector());%>" >
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="col">
                            <label>Producer</label>
                            <input type="text" name="producer" value="<% out.print(movie.getProducer());%>" >
                        </div>
                        <div class="col">
                            <label>Trailer Link</label>
                            <input type="text" name="link" value="<% out.print(movie.getLink());%>" >
                        </div>
                        <div class="col">
                            <label>Availability (0 for coming soon, 1 for available)</label>
                            <input type="number" min="0" max="1" step="1" name="available" value="<% out.print(movie.getAvailable());%>" >
                        </div>
                    </div>
                    <div class="form-row">
                        <input type = "file" name = "file" size = "50" />
                    </div>
                    <br>
                    <br>
                    <div>
                        <button class="button button-lg button-grey button-rounded">Submit</button>

                    </div>
                </form>
                <!-- Divider -->
                <hr class="bg-black-09">
                <br>
                <br>

                <!-- Divider -->
                <hr class="bg-black-09">
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