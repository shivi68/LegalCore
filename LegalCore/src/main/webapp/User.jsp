<!DOCTYPE html>
<html>

<head>
<title>LegalCore</title>
<link rel="icon" href="resource/legalcore-logo.png" />
<meta name="viewport" content="width=device-width, initial-scale=1">

<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" />
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.min.js"></script>

<!-- fontawesome -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/js/all.min.js"></script>
<!-- fontawesome END -->

<!-- AOS css and JS -->
<link rel="stylesheet" href="resource/aos/aos.css">
<script src="resource/aos/aos.js"></script>
<!-- AOS css and JS END-->

<!-- custom css-->
<link rel="stylesheet" href="resource/custom.css" />
<!-- custom css END-->

<meta name="author" content="Shivangi Gupta" />
<meta name="description" content="This is a website for Advocates" />
<meta name="keywords" content="best legal advice" />
</head>

<body>
	<header>
		<nav class="navbar navbar-expand-md">
			<a class="navbar-brand" href="index.jsp"> <img
				src="resource/legalcore-logo.png" alt=""> <span id="logo">LegalCore</span>
			</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#collapsibleNavbar">
				<i class="fas fa-bars"></i>
			</button>
			<div class="collapse navbar-collapse" id="collapsibleNavbar">
				<div class="navbar-nav ml-auto">
					<p>
						<i class="fa-solid fa-mobile-screen-button"></i> <a
							href="tel:98XXXXXXXX">98XXXXXXXX</a> , <a href="tel:98XXXXXXXX">98XXXXXXXX</a>
					</p>
				</div>
			</div>
		</nav>
		<nav id="my-nav">
			<a href="index.jsp">Home</a>
		</nav>
		<div data-aos="fade-right" data-aos-duration="1000">
			<h1>
				User Sign-In/Sign-Up</span>
			</h1>
		</div>
	</header>

	<section class="container my-5">
		<%
		String msg = (String) session.getAttribute("msg");
		if (msg != null) {
		%>
		<p style="color: red;">
			<%=msg%>
		</p>
		<%
		session.setAttribute("msg", null);
		}
		%>
		<div class="row">
			<div class="col">
				<h3>Sign-In</h3>
				<form action="UserLogin" method="post" >
					<div class="my-4 col">
						<div class="input-group">
							<div class="input-group-prepend">
								<span class="input-group-text"><i
									class="fa-solid fa-user"></i></span>
							</div>
							<input type="email" name="email" placeholder="Email Id"
								class="form-control" />
						</div>
					</div>
					<div class="my-4 col">
						<div class="input-group">
							<div class="input-group-prepend">
								<span class="input-group-text"><i
									class="fa-solid fa-star-of-life"></i></span>
							</div>
							<input type="password" name="password"
								placeholder="Password"  maxlength="20" class="form-control" />
						</div>
					</div>
					<div class="col-sm my-2">
						<button class="btn btn-primary" type="submit">Login</button>
					</div>
				</form>
				<a href="" data-toggle="modal" data-target="#exampleModal" >Forget Password</a>
				
				<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
				  <div class="modal-dialog">
				    <div class="modal-content">
				      <div class="modal-header">
				        <h5 class="modal-title" id="exampleModalLabel">Forget Password?</h5>
				        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
				          <span aria-hidden="true">&times;</span>
				        </button>
				      </div>
				      <div class="modal-body">
				        	<form action="SendMail" method="post">
				        		<div class="form-group">
				        			<input type="email" class="form-control"  name="email" placeholder="Enter your registered email id" required/>
				        		</div>
				        		<div class="form-group">
				        			<input type="hidden" name="type" value="user" />
				        			<button type="submit" class="btn btn-primary">Submit</button>
				        		</div>
				        	</form>
				      </div>
				    </div>
				  </div>
				</div>
			</div>
			<div class="col">
				<h3>Sign-Up</h3>
				<form action="UserRegister" method="post" enctype="multipart/form-data">
					<div class="my-4 col">
						<div class="input-group">
							<div class="input-group-prepend">
								<span class="input-group-text"><i
									class="fa-solid fa-user"></i></span>
							</div>
							<input type="email" name="email" placeholder="Email Id"
								class="form-control" required />
						</div>
					</div>
					<div class="my-4 col">
						<div class="input-group">
							<div class="input-group-prepend">
								<span class="input-group-text"><i
									class="fa-solid fa-user"></i></span>
							</div>
							<input type="text" name="name" pattern="[a-zA-Z ]+" placeholder="Full Name"
								class="form-control" required />
						</div>
					</div>
					<div class="my-4 col">
						<div class="input-group">
							<div class="input-group-prepend">
								<span class="input-group-text">
								<i class="fa-solid fa-phone"></i>
								</span>
							</div>
							<input type="tel" name="phone" pattern="[0-9]+" maxlength="10" placeholder="Phone No."
								class="form-control" required/>
						</div>
					</div>
					<div class="my-4 col">
						<div class="input-group">
							<div class="input-group-prepend">
								<span class="input-group-text"><i
									class="fa-solid fa-user"></i></span>
							</div>
							<textarea  cols="3" name="address" maxlength="500" placeholder="Address" 
								class="form-control" required></textarea>
						</div>
					</div>
					<div class="my-4 col">
						<div class="input-group">
							<div class="input-group-prepend">
								<span class="input-group-text"><i
									class="fa-solid fa-user"></i></span>
							</div>
							<input type="file" name="photo" accept=".png,.jpg,jpeg" class="form-control" />								
						</div>
					</div>
					<div class="my-4 col">
						<div class="input-group">
							<div class="input-group-prepend">
								<span class="input-group-text"><i
									class="fa-solid fa-star-of-life"></i></span>
							</div>
							<input type="password" name="password" maxlength="20"
								placeholder="Password" class="form-control" required/>
						</div>
					</div>
					<div class="col-sm my-2">
						<button class="btn btn-primary" type="submit">Sign Up</button>
					</div>
				</form>
			</div>
		</div>
	</section>
	<footer class="container-fluid bg-dark p-4 mt-5">
		<nav class="navbar text-white">
			<span>&copy; All rights reserved</span>
		</nav>
	</footer>
</body>
<script>
	AOS.init();
</script>
</html>



