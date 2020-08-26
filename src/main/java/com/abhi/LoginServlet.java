package com.abhi;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LoginServlet",
            urlPatterns = {"/LoginServlet"},
            initParams = { @WebInitParam(name = "user", value = "abhinav"), @WebInitParam(name = "password", value = "admin") })
public class LoginServlet extends HttpServlet {

    public void init() throws ServletException {
        System.out.println("login servlet init is called");
        if (getServletContext().getInitParameter("dbURL").equals("jdbc:mysql://localhost/abhidb")
            && getServletContext().getInitParameter("dbUser").equals("root")
            && getServletContext().getInitParameter("dbUserPwd").equals("admin")) {

            getServletContext().setAttribute("dbSuccess", true);
        } else {
            throw new ServletException("DB Connection error");
        }
    }

    // session management using cookie
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        if (getServletConfig().getInitParameter("user").equals(request.getParameter("user"))
//            && getServletConfig().getInitParameter("password").equals(request.getParameter("pwd"))) {
//
//            Cookie loginCookie = new Cookie("user", request.getParameter("user"));
//            loginCookie.setMaxAge(10);
//            response.addCookie(loginCookie);
//            response.sendRedirect("loginSuccess.jsp");
//        } else {
//            RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
//            PrintWriter out = response.getWriter();
//            out.println("<font color=red>Either user name or password is wrong.</font>");
//            rd.include(request, response);
//
//        }
//    }

    // session management using httpsession
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (getServletConfig().getInitParameter("user").equals(request.getParameter("user"))
                && getServletConfig().getInitParameter("password").equals(request.getParameter("pwd"))) {

            HttpSession session = request.getSession(true);
            session.setAttribute("user", "Abhinav");
            session.setMaxInactiveInterval(100);

            Cookie userName = new Cookie("user", "Banti_Singh");
            response.addCookie(userName);
            response.sendRedirect("loginSuccess1.jsp");

        } else {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
            PrintWriter out = response.getWriter();
            out.println("<font color=red>Either user name or password is wrong.</font>");
            rd.include(request, response);

        }
    }
}
