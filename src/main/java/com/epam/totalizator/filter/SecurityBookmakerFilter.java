package com.epam.totalizator.filter;

import com.epam.totalizator.entity.Account;
import com.epam.totalizator.entity.AccountRole;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = { "/jsp/bookmaker/*"}, initParams = {
                @WebInitParam(name = "MAIN_PAGE", value = "/controller?command=redirect_to_main_page")})
public class SecurityBookmakerFilter implements Filter {
    private String redirect;

    @Override
    public void init(FilterConfig filterConfig)  {
        redirect = filterConfig.getInitParameter("MAIN_PAGE");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chainFilter) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        Account account = (Account) request.getSession().getAttribute("account");
        if (account == null || account.getRole()!= AccountRole.BOOKMAKER){
            response.sendRedirect(redirect);
        }
        chainFilter.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        redirect = null;
    }
}