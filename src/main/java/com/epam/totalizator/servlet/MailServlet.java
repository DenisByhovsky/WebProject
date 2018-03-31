package com.epam.totalizator.servlet;

import com.epam.totalizator.mail.MailThread;

import java.io.IOException;
import java.util.Properties;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;

/**
 * Mail servlet.
 */
@WebServlet("/MailServlet")
public class MailServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Properties properties = new Properties();
        ServletContext context = getServletContext();
        String filename = context.getInitParameter("mail");
        properties.load(context.getResourceAsStream(filename));
        MailThread mailOperator =
                new MailThread(request.getParameter("to"), request.getParameter("subject"), request.getParameter("name"), request.getParameter("body"), properties);

// start mail thread
        mailOperator.start();
        request.getRequestDispatcher("send.jsp").forward(request, response);
    }
}

