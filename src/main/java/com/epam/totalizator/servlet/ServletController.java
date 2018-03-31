package com.epam.totalizator.servlet;
import com.epam.totalizator.command.ActionCommand;

import com.epam.totalizator.command.CommandManager;
import com.epam.totalizator.pool.ConnectionPool;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/controller")
public class ServletController extends HttpServlet {

    private static final Logger LOGGER = LogManager.getLogger(ServletController.class.getName());
    static final String COMMAND = "command";

    public ServletController() {
        super();
    }

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
        LOGGER.info("GET");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
        LOGGER.info("POST");
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter(COMMAND);
        ActionCommand command = CommandManager.takeActionCommand(action);
        String page = command.execute(req);
        if(req.getAttribute(ActionCommand.DISPATCHER_TYPE) == ActionCommand.SEND_REDIRECT){
            resp.sendRedirect(page);
            LOGGER.log(Level.INFO, "Redirect" );
        }else {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            dispatcher.forward(req, resp);
            LOGGER.log(Level.INFO, "Forward" );

        }
        LOGGER.log(Level.INFO, "process request done" );
    }


    @Override
    public void destroy() {
        super.destroy();
        ConnectionPool.getInstance().destroyPool();
    }


}