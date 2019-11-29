<%@ page import="model.UserService" %>
<%@ page import="model.User" %>
<%@ page import="model.PaymentService" %>
<%@ page import="java.util.ArrayList" %>
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
			<a class="navbar-brand" href="https://mono.flatheme.net/">
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
					<a class="nav-link" href="profile.jsp">Profile</a>
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
				<h4 class="margin-bottom-20">Edit Profile</h4>
				<%
					UserService userService = new UserService();
					User user = (User)session.getAttribute("user");
				%>
				<br></br>
				<h4 class="font-weight-light margin-bottom-30">Personal Information</h4>
				<form action="./editoprofilo">
					<div class="form-row">
						<div class="col">
							<label>New First Name</label>
							<input type="text" id="fname" name ="fname" value="<% out.print(user.getFname());%>">
						</div>
						<div class="col">
							<label>New Last Name</label>
							<input type="text" id= "lname" name="lname" value="<% out.print(user.getLname());%>">
						</div>
					</div>
					<br>

					<div class="form-row">
						<div class="col">
							<label>Address</label>
							<input type="text"
								   id="address" name="address" class="form-control" value="<% out.print(user.getAddress());%>"/> <br />
						</div>
						<div class="col">
							<label>State</label>
							<input type="text"
								   id="state" name="state" class="form-control" value="<% out.print(user.getState());%>"/> <br />
						</div>
					</div>
					<div class="form-row">
						<div class="col">
							<label>New Zip Code</label>
							<input type="text"
								   id="zipcode" name="zipcode" class="form-control" value="<% out.print(user.getZipCode());%>"/> <br />
						</div>
						<div class="col">
							<label>Country</label>
							<input type="text"
								   id="country" name="country" class="form-control" value="<% out.print(user.getCountry());%>"/> <br />
						</div>

					</div>
					<div>
						<label>Phone Number (Format: +9999999999)</label>
						<input type="tele" name="pnumber" id="pnumber" value="<% out.print(user.getPNumber());%>" pattern='^\+?\d{0,10}'>
					</div>
					<br></br>
					<h4 class="font-weight-light margin-bottom-30">Default Payment</h4>
					<div>

						<label for="paydef">Select default payment</label>
						<br><select style="width: 220px" class="custom-select" id="paydef" name="paydef">
						<%
							PaymentService paymentService = new PaymentService();
							ArrayList<Long> payments = paymentService.getAllPayments(user.getUsername());
							assert payments != null;
							for (long payment : payments) {
								String temp = "";
								temp = Long.toString(payment);
								temp = temp.substring(temp.length() - 4);
						%>
						<option name ="paydef" value="<%out.print(Long.toString(payment));%>">**** **** **** <% out.print(temp);%></option>
						<%
							}%>
					</select>
					</div>

					<button type="submit" value="submit" class="button button-lg button-grey button-rounded">Submit</button>
				</form>
				<form action="./editPay">
				<!-- Divider -->
				<hr class="bg-black-09">
				<br>
				<br>
				<h4 class="font-weight-light margin-bottom-30">Edit Billing Information</h4>
				<div>

					<label for="payedit">Edit payment</label>
					<br><select style="width: 220px" class="custom-select" id="payedit" name="payedit">
					<%
						assert payments != null;
						for (long payment : payments) {
							String temp = "";
							temp = Long.toString(payment);
							temp = temp.substring(temp.length() - 4);
					%>
					<option name ="movie" value="<%out.print(Long.toString(payment));%>">**** **** **** <% out.print(temp);%></option>
					<%
						}%>
				</select>
				</div>
					<button type="submit" value="submit" class="button button-lg button-grey button-rounded">Submit</button>
				</form>
				<br>

				<hr class="bg-black-09">
				<br>
				<br>

				<form action="./addcard">
					<div class="form-row">
						<div class="col">
							<label>Card Number (No spaces/Special Characters)</label>
							<input type="text" id="newcard" name ="newcard">
						</div>
					</div>
					<br>
					<div class="form-row">
						<div class="col">
							<label>CVV</label>
							<input type="text" id="cvv" name ="cvv">
						</div>
						<div class="col">
							<label>Expiration Date (MM/YY)</label>
							<input type="text" id="exp" name ="exp">
						</div>
					</div>
					<br>
					<div class="form-row">
						<div class="col">
							<label>Address</label>
							<input type="text"
								   id="newadd" name="newadd" class="form-control"/> <br />
						</div>
						<div class="col">
							<label>State</label>
							<input type="text"
								   id="newstate" name="newstate" class="form-control"/> <br />
						</div>
					</div>
					<div class="form-row">
						<div class="col">
							<label>New Zip Code</label>
							<input type="text"
								   id="newzip" name="newzip" class="form-control"/> <br />
						</div>
						<div class="col">
							<label>Country</label>
							<input type="text"
								   id="newcoun" name="newcoun" class="form-control"/> <br />
						</div>
					</div>

					<button type="submit" value="submit" class="button button-lg button-grey button-rounded">Submit</button>
				</form>
				<hr class="bg-black-09">
				<br>
				<br>
				<a class="button button-lg button-dark" href="resetPassword.jsp">Reset Password</a>
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
