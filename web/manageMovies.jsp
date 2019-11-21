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
                    <a style="white-space:pre" class="nav-link" href="index.jsp">Home</a>
                </li>
                <li class="nav-item nav-dropdown">
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
                <form action="./movieServlet">
                    <h4 class="margin-bottom-20">Add New Movie</h4>
                    <input type="hidden" name="hidden" value="add">
                    <div class="form-row">
                        <div class="col">
                            <label>Title</label>
                            <input type="text" name="title" required>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="col">
                            <label>Summary</label>
                            <input type="text" name="summary" required>
                        </div>
                        <div class="col">
                            <label>Genre</label>
                            <input type="text" name="genre" required>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="col">
                            <label>Rating</label>
                            <input type="text" name="rating" required>
                        </div>
                        <div class="col">
                            <label>Length</label>
                            <input type="text" name="length" required>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="col">
                            <label>Cast</label>
                            <input type="text" name="cast" required>
                        </div>
                        <div class="col">
                            <label>Director</label>
                            <input type="text" name="director" required>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="col">
                            <label>Review 1</label>
                            <input type="text" name="review1" required>
                        </div>
                        <div class="col">
                            <label>Review 2</label>
                            <input type="text" name="review2" required>
                        </div>
                        <div class="col">
                            <label>Review 3</label>
                            <input type="text" name="review3" required>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="col">
                            <label>Producer</label>
                            <input type="text" name="producer" required>
                        </div>
                        <div class="col">
                        <label>Trailer Link</label>
                        <input type="text" name="link" required>
                        </div>
                        <div class="col">
                            <label>Availability (0 for coming soon, 1 for available)</label>
                            <input type="text" name="available" required>
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
                    <h4 class="margin-bottom-20">Delete Movie</h4>
                <form action="./movieServlet">
                    <input type="hidden" name="hidden" value="delete">
                    <div>

                        <label for="isTitles"></label><select class="custom-select" id="isTitles" name="isTitles" multiple>
                            <%
                                MovieService movieService = new MovieService();
                                ArrayList<Movie> movies = null;
                                try {
                                    movies = movieService.getAllMovies();
                                } catch (ClassNotFoundException | SQLException e) {
                                    e.printStackTrace();
                                }
                                assert movies != null;
                                for (Movie movie : movies) {
                            %>
                            <option name ="movie" value="<%out.print(movie.getTitle());%>"><% out.print(movie.getTitle());%></option>
                            <%
                                    }%>
                        </select>

                    </div>
                    <div>
                        <button class="button button-lg button-grey button-rounded">Submit</button>
                    </div>
                    </form>
                    <hr class="bg-black-09">
                    <br>
                    <br>
                <h4 class="margin-bottom-20">Edit Movie</h4>
                    <form action="./PassMovie">
                        <label for="ttitle"></label><select class="custom-select" id="ttitle" name="ttitle">
                        <%
                            MovieService movieService1 = new MovieService();
                            ArrayList<Movie> movies1 = null;
                            try {
                                movies1 = movieService1.getAllMovies();
                            } catch (ClassNotFoundException | SQLException e) {
                                e.printStackTrace();
                            }
                            assert movies1 != null;
                            for (Movie movie : movies1) {
                        %>
                        <option name ="movie" value="<%out.print(movie.getTitle());%>"><% out.print(movie.getTitle());%></option>
                        <%
                            }%>
                    </select>




                    <div>
                        <button class="button button-lg button-grey button-rounded">Edit Movie</button>
                    </div>
                </form>
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