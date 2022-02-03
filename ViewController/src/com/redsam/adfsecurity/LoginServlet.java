package com.redsam.adfsecurity;

import java.io.IOException;

import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

public class LoginServlet extends HttpServlet {
    public LoginServlet() {
        super();
    }
    
    
    @Override
    public void init() throws ServletException {
        System.out.println("HEREEEE");
    }


    @Override
    protected void doGet(HttpServletRequest httpServletRequest,
                         HttpServletResponse httpServletResponse) throws ServletException,
                                                                         IOException {
        
        System.out.println("LoginServlet get");

        httpServletResponse.setContentType("text/html");
        PrintWriter out = httpServletResponse.getWriter();
        out.println("<p>Hello World!</p>");
        super.doGet(httpServletRequest, httpServletResponse);
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest,
                          HttpServletResponse httpServletResponse) throws ServletException,
                                                                          IOException {
     
        String username = httpServletRequest.getParameter("username");
        String password = httpServletRequest.getParameter("password");
        Object principal = SecurityContextHolder. getContext(). getAuthentication(). getPrincipal();
        System.err.println("login before:  " + principal);
        
        //replace this with proper security check against the DB or LDAP
        if("redsam".equals(username) && "redsam".equals(password)) {
            Authentication auth = 
              new UsernamePasswordAuthenticationToken(username, password, getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(auth);
            principal = SecurityContextHolder. getContext(). getAuthentication(). getPrincipal();
            System.err.println("login after:  " + principal);
            httpServletResponse.sendRedirect("faces/home.jspx");
        } else {
            httpServletResponse.sendRedirect("login.html?error=true");
        }
        
    
        
        
    }
    
    public Collection<GrantedAuthority> getAuthorities() {
            //make everyone ROLE_USER
            Collection<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
            GrantedAuthority grantedAuthority = new GrantedAuthority() {
                //anonymous inner type
                public String getAuthority() {
                    return "ROLE_USER";
                }
            }; 
            grantedAuthorities.add(grantedAuthority);
            return grantedAuthorities;
    }
}
