package ru.pharus.socnetwork.controllers.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 *  Security authorization filter
 *
 */

@WebFilter({"/user", "/friends", "/edit", "/feed", "/messages", "/im"})
public class AuthorizationFilter implements Filter {
    private static final Logger log = LoggerFactory.getLogger(AuthorizationFilter.class);

    /**
     * Method checking <code>user_id</code> attribute in session scope
     * redirect to main if attribute invalid
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.trace("Enter to AuthorizationFilter");
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;


        //boolean allowedRequest = false;
        String url = httpRequest.getServletPath();
        if(url!=null) {
            log.trace("user request url mapped to " + url);
        }

        HttpSession session = httpRequest.getSession(true);
        if (session.getAttribute("user_id") == null) {
            log.info(String.format("Session %s not authorized, redirect to /", session.getId()));
            httpResponse.sendRedirect("/");
            return;
        }

        chain.doFilter(request,response);
        log.trace("Quit from AuthorizationFilter");
    }
}
