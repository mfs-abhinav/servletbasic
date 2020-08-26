package com.abhi;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LogoutServlet",
            urlPatterns = {"/LogoutServlet"})
public class LogoutServlet extends HttpServlet {

    // session management using cookie
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.setContentType("text/html");
//        Cookie loginCookie = null;
//        Cookie[] cookies = request.getCookies();
//        if (cookies != null) {
//            for (Cookie cookie: cookies) {
//                if (cookie.getName().equals("user")) {
//                    loginCookie = cookie;
//                }
//            }
//        }
//        if (loginCookie != null) {
//            loginCookie.setMaxAge(0);
//            response.addCookie(loginCookie);
//        }
//        response.sendRedirect("login.html");
//    }

    // session management using httpsession
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("JSESSIONID")){
                    System.out.println("JSESSIONID="+cookie.getValue());
                    break;
                }
            }
        }
        //invalidate the session if exists
        HttpSession session = request.getSession(false);
        System.out.println("User="+session.getAttribute("user"));
        if(session != null){
            session.invalidate();
        }
        response.sendRedirect("login.html");
    }

}
