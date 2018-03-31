package com.epam.totalizator.command.authorization;

import com.epam.totalizator.command.ActionCommand;
import com.epam.totalizator.manager.ManagerHandler;
import com.epam.totalizator.service.AccountService;
import com.epam.totalizator.exception.ServiceException;
import com.epam.totalizator.service.factory.ServiceFactory;
import com.epam.totalizator.service.impl.AccountServiceImpl;
import com.epam.totalizator.utill.Encryptor;
import com.epam.totalizator.utill.Validator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import java.sql.Date;

public class SignUpCommand implements ActionCommand {

    private static final Logger LOGGER = LogManager.getLogger(SignUpCommand.class.getName());

    static final String SUCCESS = "success";
    static final String PASSWORD = "password";
    static final String CONFIRM_PASSWORD = "confirm";
    static final String EMAIL = "email";
    static final String FIRST_NAME = "first-name";
    static final String LAST_NAME = "last-name";
    public static final String DOC_NUMB = "doc-numb";
    public static final String PHONE = "phone";
    public static final String DOC_TYPE = "doc-type";
    static final String SUCCESS_REGISTRATION = "reg.success";
    static final String EXISTED_LOGIN = "reg.exist";
    static final String PASSWORDS_NO_MATCH = "reg.pass.no.event";
    static final String TOO_SMALL = "reg.small";
    static final String LOGIN = "login";
    static final String FAIL = "fail";
    static final String CURRENCY = "currency";
    static final String BIRTHDAY = "birthday";

    public SignUpCommand(){ }

    /**
     * Command that registers new clients.
     * This command can be used by guests.
     * @author Denis Byhovsky
     */

    public String execute(HttpServletRequest request) {
        String page = ManagerHandler.getPage(ManagerHandler.SIGNUP_PAGE);
     try {
            String login = request.getParameter(LOGIN);
            String lastName = request.getParameter(LAST_NAME);
            String firstName = request.getParameter(FIRST_NAME);
            Date birthday = Date.valueOf(request.getParameter(BIRTHDAY));
             String documentType = request.getParameter(DOC_TYPE);
         String email = request.getParameter(EMAIL);
         String phone = request.getParameter(PHONE);
         String documentNumb = request.getParameter(DOC_NUMB);
            String curr = request.getParameter(CURRENCY);
            String pass = request.getParameter(PASSWORD);
            String confirmPassword = request.getParameter(CONFIRM_PASSWORD);
         //            //TODO
           String checkin = passCheckin(pass, confirmPassword, login, birthday , email );
              if (!checkin.isEmpty()){
                   request.setAttribute(FAIL, checkin);
                    return page;
                }
                if (Validator.checkNewAccount(login, pass, email, firstName, lastName)) {
                    pass = Encryptor.sha1Encrypt(pass);
                    ServiceFactory.getInstance().getAccountService().createNewAccount(login, pass, email, firstName, lastName, birthday, curr, phone, documentNumb, documentType);
                    request.setAttribute(SUCCESS, SUCCESS_REGISTRATION);
                    page = ManagerHandler.getPage(ManagerHandler.LOGIN_PAGE);
                }
            }catch (ServiceException e){
               LOGGER.error("Err in sign up command");
            }
        LOGGER.log(Level.INFO,"success signup command");
        return page;
    }

    /**
     * Method that check user information.
     * Personal information ckeck by the (@code Validator).
     * @author Denis Byhovsky
     */
    private String passCheckin(String password, String confirmPassword, String login, Date birthday ,String email) throws ServiceException{

        if (ServiceFactory.getInstance().getAccountService().checkAccountIsExist(login)){
            return EXISTED_LOGIN;
        }
        if (!Validator.checkAge(birthday)){
            return TOO_SMALL;
        }

        if (!ServiceFactory.getInstance().getAccountService().checkPasswords(password, confirmPassword)){
            return  PASSWORDS_NO_MATCH;
        }
        return "";
    }
}