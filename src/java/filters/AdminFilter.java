/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author 810783
 */
public class AdminFilter implements Filter
{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException
    {
        //
    }

     public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException 
    {   
        // this code will execute before HomeServlet and UsersServlet
        HttpServletRequest r = (HttpServletRequest)request;
        HttpSession session = r.getSession();
        HttpServletResponse resp = (HttpServletResponse)response;
        
        if(session.getAttribute("username") != null) 
        {
            String username = (String)session.getAttribute("username");
            
            if(username.contains("admin"))
            {
                chain.doFilter(request, response);
            }
            else
            {
                resp.sendRedirect("home");
            }
        } 
        else 
        {
            resp.sendRedirect("login");
        }

        // this code will execute after HomeServlet and UsersServlet
    }

    @Override
    public void destroy()
    {
        //
    }
    
}
