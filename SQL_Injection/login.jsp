<%@page import ="java.sql.*" %>
<html>
<head>
<link rel="stylesheet" type="text/css" href="login_css.css">

<h1>WELCOME</h1>
</head>

<body>


<%!
    public void printResult(ResultSet rs, JspWriter out) throws Exception
    {

       String dbuserid = null, dbpassword = null;
	   String a="this.parentElement.style.display='none';>";
	   
	   //a="<div class='alert'> <span class='closebtn' onclick='this.parentElement.style.display="none";'>&times;</span> <strong>Invalid UserName or password</strong> </div>";

     out.println("<table>");
      out.println("<tr>");
      out.println("<th>Firstname</th>");
      out.println("<th>Lastname</th>");
      out.println("<th>User Name</th>");
      out.println("<th>Gender</th>");
      out.println("<th>Date of birth</th>");
      out.println("<th>Country</th>");
      out.println("<th>Email id</th>");
      out.println("</tr>");

        while(rs.next())
          {
            dbuserid = rs.getString("userid");
            dbpassword = rs.getString("password");

          //  out.println(rs.getString("fname"));
           // out.println(rs.getString("lname"));
				out.println("<tr>");
				out.println("<td>"+rs.getString("fname")+"</td>");
				out.println("<td>"+rs.getString("lname")+"</td>");
				out.println("<td>"+rs.getString("userid")+"</td>");
				out.println("<td>"+rs.getString("gender")+"</td>");
				out.println("<td>"+rs.getString("dtob")+"</td>");
				out.println("<td>"+rs.getString("country")+"</td>");
				out.println("<td>"+rs.getString("emailid")+"</td>");
				out.println("</tr>");
        
        }
        out.println("</table>");
          if(dbuserid == null && dbpassword == null)
            out.println("<div class='alert'> <span class='closebtn' onclick="+a+"&times;</span> <strong>Invalid User Name or Password</strong> </div>");
			
		    
    }
%>

<%
try
{
    Class.forName("com.mysql.jdbc.Driver");

    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/SQL_Injection","root","SQL_!nject");

    if(request.getParameter("submit_btn")!=null)
    {
        String userid, password , injectionType;

        userid = request.getParameter("UserName");
        password = request.getParameter("Password");
        injectionType = request.getParameter("injection_type");


        if(injectionType.equals("with_protection"))
        {    
          PreparedStatement pstmt=null; //create statement
  
          pstmt=con.prepareStatement("select * from user_details where userid=? AND password=?"); //sql select query 
          pstmt.setString(1,userid);
          pstmt.setString(2,password);
  
          ResultSet rs=pstmt.executeQuery();

          printResult(rs, out);

        }
        else if(injectionType.equals("without_protection"))
         {
            String sql = null;
            Statement stmt = con.createStatement();

            sql = "select * from user_details where userid = '"+ userid +"' and password = '"+password+"';"; 

            ResultSet rs = stmt.executeQuery(sql);
			
            printResult(rs, out);
         }     
        con.close();
    }

}
catch(Exception e)
{
    out.println(e);
}
%>
<br>
<button class="btn" onclick="window.location.href = 'logout.jsp';" style="margin: 0 auto;" align="center">Logout</button>

</body>
</html>
