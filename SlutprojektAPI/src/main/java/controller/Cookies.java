package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.GetWeather;
import model.WeatherBean;


/**
 * Servlet implementation class Cookies
 */
@WebServlet("/Cookies")
public class Cookies extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Cookies() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Cookie cookie = null;
		Cookie[] cookies = null; // T
		
		cookies = request.getCookies(); // adding cookie in response
		
		String city = "";
		
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				cookie = cookies[i];
			       if(cookie.getName().equals("city")){
			    	   city = cookie.getValue();
			       }
		}
		
			WeatherBean wBean = new WeatherBean(city, "");

			GetWeather.getTheWeather(wBean);

			request.setAttribute("wBean", wBean);
			

			RequestDispatcher rd = request.getRequestDispatcher("showWeather.jsp");
			rd.forward(request, response);		  
		 }

	
}
}