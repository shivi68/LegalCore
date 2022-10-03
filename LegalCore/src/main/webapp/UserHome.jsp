<%@page import="java.util.*"%>
<%@page import = "com.shivi.model.DAO"%>
<%
String name = (String)session.getAttribute("name");
String email = (String)session.getAttribute("email");
if(email==null) {
	session.setAttribute("msg", "Please Login First!");
	response.sendRedirect("User.jsp");
} else {
%>

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
			<a href="UserHome.jsp">Home</a> <a href="Logout">Logout</a> <span>Welcome:
				<b> <%=name %>
			</b>
			</span> <img src="GetPhoto?email=<%=email%>&type=user" alt="" height="50px" />
		</nav>
		<div data-aos="fade-right" data-aos-duration="1000">
			<h1>User Home</h1>
		</div>
	</header>

  <section class="container my-5">
    	<%
			DAO d=new DAO();
    		HashSet<String> advocates=d.getAdvocatesByUser(email);
			for(String a_email:advocates){
		%>
				<p>Advocate Email:<b style="background-color:brown;color:white;"><%=a_email%></b> </p>
		<%		
				d=new DAO();
				ArrayList<HashMap> advices2=d.getAdviceByUserAdvocate(email, a_email);
				for(HashMap advice2:advices2){
		%>
					<div class="bg-warning p-3">
						<p> 
    					Query: <b><%=advice2.get("query")%></b>
    					Date: <b><%=advice2.get("q_date")%></b>
	    				</p>
	    				<%
	    					if(advice2.get("reply")!=null){
    					%>
	    					<p> 
	    					Reply: <b><%=advice2.get("reply")%></b>
	    					Date: <b><%=advice2.get("r_date")%></b>
		    				</p>	
	    				<%		
	    					}else{
    					%>
	    					<p> 
	    					Reply: <b style="color:red;">Pending</b>
		    				</p>	
	    				<%			
	    					}
	    				%>
	    				
					</div>
			<%		
				}
			}
			%>
		
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
<%
}
%>

