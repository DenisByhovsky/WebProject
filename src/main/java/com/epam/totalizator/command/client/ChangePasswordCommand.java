package com.epam.totalizator.command.client;

import com.epam.totalizator.command.ActionCommand;
import com.epam.totalizator.entity.Account;
import com.epam.totalizator.entity.AccountRole;
import com.epam.totalizator.exception.ServiceException;
import com.epam.totalizator.service.factory.ServiceFactory;
import com.epam.totalizator.utill.Encryptor;
import com.epam.totalizator.utill.Validator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ChangePasswordCommand implements ActionCommand {

    private static final Logger LOGGER = LogManager.getLogger(ChangePasswordCommand.class.getName());

    static final String FAIL = "fail";
    static final String SUCCESS = "success";
    static final String CONFIRM_PASSWORD = "confirm";
    static final String OLD_PASSWORD = "oldPass";
    static final String NEW_PASSWORD = "newPass";
    static final String PASSWORD_FAIL = "bad.password";
    static final String PASSWORD_CONFIRMED = "pass.confirmed";
    static final String PASSWORD_INCORRECT = "pass.incorrect";
    static final String PASSWORDS_NO_MATCH = "regpass.nomatch";

    public ChangePasswordCommand(){ }

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute(ACCOUNT);
        String page = session.getAttribute(PAGE).toString();

        if (account.getRole() == AccountRole.CLIENT) {
            try {
                String oldPassword = request.getParameter(OLD_PASSWORD);
                String newPassword = request.getParameter(NEW_PASSWORD);
                String confirmPassword = request.getParameter(CONFIRM_PASSWORD);
                String barrier = passCheckCorrect(oldPassword, newPassword, confirmPassword, account.getPassword());
                if(barrier.isEmpty()) {
                    newPassword = Encryptor.sha1Encrypt(newPassword);
                    ServiceFactory.getInstance().getAccountService().updatePassword(newPassword, account.getLogin());
                    request.setAttribute(SUCCESS, PASSWORD_CONFIRMED);
                    account.setPassword(newPassword);
                }else {
                    request.setAttribute(FAIL, barrier);
                }
            } catch (ServiceException e) {
                LOGGER.error("Error in change pass comm"+e);
            }
        }else {
            page = CONTROLLER_COMMAND_REDIRECT_TO_MAIN_PAGE;
        }
        LOGGER.log(Level.INFO,"Success pass changed comm");
        return page;
    }

    private String passCheckCorrect(String old, String updated, String confirm, String password){
        old = Encryptor.sha1Encrypt(old);
        LOGGER.log(Level.INFO,"Old"+ old);
        if (!old.equals(password)){
            return PASSWORD_INCORRECT;
        }
        if (!updated.equals(confirm)){
            return PASSWORDS_NO_MATCH;
        }
        if (!Validator.checkPass(updated)){
            return PASSWORD_FAIL;
        }
        return "";
    }
}