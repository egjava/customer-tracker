package com.eliz.Testdb;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;
/**
 * Servlet implementation class TestDbServlet
 */
@WebServlet("/TestDbServlet")
public class TestDbServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public TestDbServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String user= "springstudent";
		String pwd="Admin@123";
		
		String jdbcurl = "jdbc:mysql://localhost:3306/web_customer_tracker?serverTimezone=UTC&useSSL=false";
		String driver = "com.mysql.jdbc.Driver";
		try{
			PrintWriter out = response.getWriter();
			out.println("Connecting to Database:"+jdbcurl);
			Class.forName(driver);
			Connection con= DriverManager.getConnection(jdbcurl,user, pwd);
			out.println("Success!!!");
			con.close();
			}
		catch(Exception e){
			e.printStackTrace();
			throw new ServletException(e);
			}
		}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
