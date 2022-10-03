package com.shivi.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.io.InputStream;

public class DAO {
	private Connection c;
	//constructor 
	public DAO() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
	    c = DriverManager.getConnection("jdbc:mysql://localhost:3306/legalcoreweb", "root", "saloni");
	}
	public ArrayList<HashMap> getAdvocatesByCategory(String category) throws Exception {
		PreparedStatement p=c.prepareStatement("select * from advocates where category=? and status='active'");
		p.setString(1, category);
		ResultSet rs=p.executeQuery();
		ArrayList<HashMap> advocates=new ArrayList<HashMap>();
		while(rs.next()) {
			HashMap advocate=new HashMap();
			advocate.put("email", rs.getString("email"));
			advocate.put("name", rs.getString("name"));
			advocate.put("phone", rs.getString("phone"));
			advocate.put("address", rs.getString("address"));
			advocate.put("experience", rs.getInt("experience"));
			//advocate.put("category", rs.getString("category"));
			advocates.add(advocate);
		}
		c.close();
		return advocates;
	}
	
	public ArrayList<HashMap> getAdvocates() throws Exception {
		PreparedStatement p=c.prepareStatement("select * from advocates");
		ResultSet rs=p.executeQuery();
		ArrayList<HashMap> advocates=new ArrayList<HashMap>();
		while(rs.next()) {
			HashMap advocate=new HashMap();
			advocate.put("email", rs.getString("email"));
			advocate.put("name", rs.getString("name"));
			advocate.put("phone", rs.getString("phone"));
			advocate.put("address", rs.getString("address"));
			advocate.put("experience", rs.getInt("experience"));
			advocate.put("category", rs.getString("category"));
			advocate.put("status", rs.getString("status"));
			advocates.add(advocate);
		}
		c.close();
		return advocates;
	}
	
	public ArrayList<HashMap> getUsers() throws Exception {
		PreparedStatement p=c.prepareStatement("select * from users");
		ResultSet rs=p.executeQuery();
		ArrayList<HashMap> users=new ArrayList<HashMap>();
		while(rs.next()) {
			HashMap user=new HashMap();
			user.put("email", rs.getString("email"));
			user.put("name", rs.getString("name"));
			user.put("phone", rs.getString("phone"));
			user.put("address", rs.getString("address"));
			user.put("status", rs.getString("status"));
			users.add(user);
		}
		c.close();
		return users;
	}
	
	public ArrayList<HashMap> getAdviceByUserAdvocate(String u_email,String a_email) throws Exception {
		PreparedStatement p=c.prepareStatement("select * from advices where u_email=? and a_email=?");
		p.setString(1, u_email);
		p.setString(2, a_email);
		ResultSet rs=p.executeQuery();
		ArrayList<HashMap> advices=new ArrayList<HashMap>();
		while(rs.next()) {
			HashMap advice=new HashMap();
			advice.put("id", rs.getInt("id"));
			advice.put("query", rs.getString("query"));
			advice.put("q_date", rs.getString("q_date"));
			advice.put("reply", rs.getString("reply"));
			advice.put("r_date", rs.getString("r_date"));
			advices.add(advice);
		}
		c.close();
		return advices;
	}
	public HashSet<String> getAdvocatesByUser(String u_email) throws Exception {
		PreparedStatement p=c.prepareStatement("select distinct a_email from advices where u_email=? order by a_email");
		p.setString(1, u_email);
		ResultSet rs=p.executeQuery();
		HashSet<String> advocates=new HashSet<String>();
		while(rs.next()) {
			advocates.add(rs.getString("a_email"));
		}
		c.close();
		return advocates;
	}
	public HashSet<String> getUsersByAdvocate(String a_email) throws Exception {
		PreparedStatement p=c.prepareStatement("select distinct u_email from advices where a_email=? order by u_email");
		p.setString(1, a_email);
		ResultSet rs=p.executeQuery();
		HashSet<String> users=new HashSet<String>();
		while(rs.next()) {
			users.add(rs.getString("u_email"));
		}
		c.close();
		return users;
	}
	public HashMap<String,String> getUserByEmail(String u_email) throws Exception {
		PreparedStatement p=c.prepareStatement("select * from users where email=?");
		p.setString(1, u_email);
		ResultSet rs=p.executeQuery();
		HashMap<String,String> user=null;
		if(rs.next()) {
			user=new HashMap<>();
			user.put("name", rs.getString("name"));
			user.put("phone", rs.getString("phone"));
			user.put("address", rs.getString("address"));
		}
		c.close();
		return user;
	}
	public String checkAdminLogin(String id,String password) throws Exception {
		PreparedStatement p=c.prepareStatement("select * from admin where id=? and password=?");
		p.setString(1, id);
		p.setString(2, password);
		ResultSet rs=p.executeQuery();
		if(rs.next()) {
			String n=rs.getString("name");
			c.close();
			return n;
		}else {
			return null;
		}
	}
	public String checkUserLogin(String email,String password) throws Exception {
		PreparedStatement p=c.prepareStatement("select * from users where email=? and password=?");
		p.setString(1, email);
		p.setString(2, password);
		ResultSet rs=p.executeQuery();
		if(rs.next()) {
			String n=rs.getString("name");
			c.close();
			return n;
		}else {
			return null;
		}
	}
	public String checkAdvocateLogin(String email,String password) throws Exception {
		PreparedStatement p=c.prepareStatement("select * from advocates where email=? and password=?");
		p.setString(1, email);
		p.setString(2, password);
		ResultSet rs=p.executeQuery();
		if(rs.next()) {
			String n=rs.getString("name");
			c.close();
			return n;
		}else {
			return null;
		}
	}
	public String registerUser(HashMap user) throws Exception {
		PreparedStatement p=c.prepareStatement("insert into users (email,name,phone,address,photo,password,status) values (?,?,?,?,?,?,'active')");
		p.setString(1, (String)user.get("email"));
		p.setString(2, (String)user.get("name"));
		p.setString(3, (String)user.get("phone"));
		p.setString(4, (String)user.get("address"));
		p.setBinaryStream(5, (InputStream)user.get("photo"));
		p.setString(6, (String)user.get("password"));
		try {
			p.executeUpdate();
		}catch (SQLIntegrityConstraintViolationException e) {
			return "already";
		}
		c.close();
		return "success";
	}

	public String addAdvice(String a_email,String u_email,String query) throws Exception {
		PreparedStatement p=c.prepareStatement("insert into advices (a_email,u_email,query,q_date) values (?,?,?,CURRENT_DATE)");
		p.setString(1, a_email);
		p.setString(2, u_email);
		p.setString(3, query);
		p.executeUpdate();
		c.close();
		return "success";
	}
	public void replyAdvice(int id,String reply) throws Exception {
		PreparedStatement p=c.prepareStatement("update advices set reply=?,r_date=CURRENT_DATE where id=?");
		p.setString(1, reply);
		p.setInt(2, id);
		p.executeUpdate();
		c.close();
	}
	public void updateStatus(String email,String status,String type) throws Exception {
		PreparedStatement p;
		if(type.equalsIgnoreCase("advocate")) {
			p=c.prepareStatement("update advocates set status=? where email=?");
			
		}else {
			p=c.prepareStatement("update users set status=? where email=?");
		}
		p.setString(1, status);
		p.setString(2, email);
		p.executeUpdate();
	}
	public String registerAdvocate(HashMap advocate) throws Exception {
		PreparedStatement p=c.prepareStatement("insert into advocates (email,name,phone,address,photo,experience,password,category,status) values (?,?,?,?,?,?,?,?,'active')");
		p.setString(1, (String)advocate.get("email"));
		p.setString(2, (String)advocate.get("name"));
		p.setString(3, (String)advocate.get("phone"));
		p.setString(4, (String)advocate.get("address"));
		p.setBinaryStream(5, (InputStream)advocate.get("photo"));
		p.setInt(6, (int)advocate.get("experience"));
		p.setString(7, (String)advocate.get("password"));
		p.setString(8, (String)advocate.get("category"));
		try {
			p.executeUpdate();
		}catch (SQLIntegrityConstraintViolationException e) {
			return "already";
		}
		c.close();
		return "success";
	}
	public byte[] getPhoto(String email,String type) throws Exception {
		PreparedStatement p;
		if(type.equalsIgnoreCase("advocate")) {
			p=c.prepareStatement("select photo from advocates where email=?");
			
		}else {
			p=c.prepareStatement("select photo from users where email=?");
		}
		p.setString(1, email);
		ResultSet rs=p.executeQuery();
		if(rs.next()) {
			byte[] b=rs.getBytes("photo");
			c.close();
			return b;
		}else {
			return null;
		}
	}
	public String getPassword(String email,String type) throws Exception {
		PreparedStatement p;
		if(type.equalsIgnoreCase("advocate")) {
			p=c.prepareStatement("select password from advocates where email=?");
			
		}else {
			p=c.prepareStatement("select password from users where email=?");
		}
		p.setString(1, email);
		ResultSet rs=p.executeQuery();
		if(rs.next()) {
			String pass=rs.getString("password");
			c.close();
			return pass;
		}else {
			return null;
		}
	}
}
