<%@page import = "com.shivi.model.DAO"%>
<%@page import="java.util.*"%>
<%
String adminName = (String)session.getAttribute("adminName");
if(adminName==null) {
	session.setAttribute("msg", "Please Login First!");
	response.sendRedirect("Admin.jsp");
} else {
%>

<!DOCTYPE html>
<html>

<head>
    <title>LegalCore</title>
    <link rel="icon" href="resource/legalcore-logo.png" />
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" />
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.min.js"></script>

    <!-- fontawesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/js/all.min.js"></script>
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
            <a class="navbar-brand" href="AdminHome.jsp">
                <img src="resource/legalcore-logo.png" alt=""> <span id="logo">LegalCore</span>
            </a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar" >
                <i class="fas fa-bars"></i>
            </button>
            <div class="collapse navbar-collapse" id="collapsibleNavbar">
                <div class="navbar-nav ml-auto">
                    <p>
                        <i class="fa-solid fa-mobile-screen-button"></i>
                        <a href="tel:98XXXXXXXX">98XXXXXXXX</a> ,
                        <a href="tel:98XXXXXXXX">98XXXXXXXX</a>
                    </p>
                </div>
            </div>
        </nav>
        <nav id="my-nav">
            <a href="AdminHome.jsp">Home</a>
            <a href="Logout">Logout</a>
            <span>Welcome: <b> <%=adminName %> </b> </span>
        </nav>
        <div data-aos="fade-right" data-aos-duration="1000">
            <h1>Admin Home</h1>
        </div>
    </header>
    <%DAO d = new DAO();%>
      <section class="container my-5">
    	<div class="row">
    		<div class="col">
    			<h3>Advocates</h3>
    			<%
    			d=new DAO();
	    		ArrayList<HashMap> advocates=d.getAdvocates();
	    		for(HashMap advocate:advocates){
	    			String status=(String)advocate.get("status");
		    	%>
		    	<div class="bg-warning p-3 my-2">
    				<img src="GetPhoto?email=<%=advocate.get("email")%>&type=advocate" alt="" height="100px" />
	    			<p> 
					Name: <b><%=advocate.get("name")%></b>
					Phone: <b><%=advocate.get("phone")%></b>
					</p>
					<p>
					Email: <b><%=advocate.get("email")%></b>
					Experience: <b><%=advocate.get("experience")%></b>
	   				</p>
	   				<p > 
	   				Address: <b><%=advocate.get("address")%></b>
	   				</p>
	   				<p > 
	   				Category: <b><%=advocate.get("category")%></b>
	   				Status: <b><%=status%></b>
	   			<%
   				if(status.equalsIgnoreCase("active")){
   				%>
   					<a class="btn btn-danger" href="UpdateStatus?email=<%=advocate.get("email")%>&status=deactive&type=advocate">Deactivate</a>
   				<%	
   				}else{
 				%>
 	   				<a class="btn btn-success" href="UpdateStatus?email=<%=advocate.get("email")%>&status=active&type=advocate">Activate</a>
 	   			<%	
   				}
	   			%>
	   				</p>	
    			</div>
		    	<%		
		    		}
		    	%>
    		</div>
    		<div class="col">
    			<h3>Users</h3>
    			<%
    			d=new DAO();
	    		ArrayList<HashMap> users=d.getUsers();
	    		for(HashMap user:users){
	    			String status=(String)user.get("status");
		    	%>
		    	<div class="bg-info p-3 my-2">
    				<img src="GetPhoto?email=<%=user.get("email")%>&type=user" alt="" height="100px" />
	    			<p> 
					Name: <b><%=user.get("name")%></b>
					Phone: <b><%=user.get("phone")%></b>
					</p>
					<p>
					Email: <b><%=user.get("email")%></b>
	   				</p>
	   				<p > 
	   				Address: <b><%=user.get("address")%></b>
	   				</p>
	   				<p > 
	   				Status: <b><%=status%></b>
	   			<%
   				if(status.equalsIgnoreCase("active")){
   				%>
   					<a class="btn btn-danger" href="UpdateStatus?email=<%=user.get("email")%>&status=deactive&type=user">Deactivate</a>
   				<%	
   				}else{
 				%>
 	   				<a class="btn btn-success" href="UpdateStatus?email=<%=user.get("email")%>&status=active&type=user">Activate</a>
 	   			<%	
   				}
	   			%>
	   				</p>	
    			</div>
		    	<%		
		    		}
		    	%>
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
<%
}
%>

