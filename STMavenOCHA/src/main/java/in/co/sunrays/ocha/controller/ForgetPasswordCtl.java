package in.co.sunrays.ocha.controller;

import in.co.sunrays.ocha.bean.BaseDTO; 
import in.co.sunrays.ocha.bean.UserDTO; 
import in.co.sunrays.ocha.exception.ApplicationException; 
import in.co.sunrays.ocha.exception.RecordNotFoundException; 
import in.co.sunrays.ocha.model.ModelFactory; 
import in.co.sunrays.ocha.model.UserModelInt; 
import in.co.sunrays.util.DataUtility; 
import in.co.sunrays.util.DataValidator; 
import in.co.sunrays.util.PropertyReader; 
import in.co.sunrays.util.ServletUtility; 


import java.io.IOException; 

import javax.servlet.ServletException; 
import javax.servlet.http.HttpServlet; 
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse; 
 
import org.apache.log4j.Logger; 

public class ForgetPasswordCtl  extends BaseCtl
{

	private static Logger log = Logger.getLogger(ForgetPasswordCtl.class); 
	 
    @Override 
    protected boolean validate(HttpServletRequest request) { 
 
        log.debug("ForgetPasswordCtl Method validate Started"); 
 
        boolean pass = true; 
         
        String login = request.getParameter("login"); 
         
        if (DataValidator.isNull(login)) { 
            request.setAttribute("login", 
                    PropertyReader.getValue("error.require", "Email Id")); 
            pass = false; 
        }else if (!DataValidator.isEmail(login)) { 
            request.setAttribute("login", 
                    PropertyReader.getValue("error.email", "Login ")); 
            pass = false; 
        } 
        log.debug("ForgetPasswordCtl Method validate Ended"); 
 
        return pass; 
    } 
 
    @Override 
    protected BaseDTO populateDTO(HttpServletRequest request, BaseDTO ForDTO) { 
 
        log.debug("ForgetPasswordCtl Method populateDTO Started"); 
 
        UserDTO dto = (UserDTO)super.populateDTO(request, ForDTO);
 
        dto.setLogin(DataUtility.getString(request.getParameter("login"))); 
 
        log.debug("ForgetPasswordCtl Method populateDTO Ended"); 
 
        return dto; 
    } 
 
    /** 
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse 
     *      response) 
     */ 
    protected void doGet(HttpServletRequest request, 
            HttpServletResponse response) throws ServletException, IOException { 
        log.debug("ForgetPasswordCtl Method doGet Started"); 
 
        String op = DataUtility.getString(request.getParameter("operation")); 
 
        UserDTO dto = (UserDTO) populateDTO(request, getDTO()); 
 
        // get model 
        UserModelInt model = ModelFactory.getInstance().getUserModel(); 
 
        if (OP_GO.equalsIgnoreCase(op)) { 
 
            try { 
                model.forgetPassword(dto.getLogin()); 
                ServletUtility.setSuccessMessage( 
                        "Password has been sent to your email id", 
                        request); 
            } catch (RecordNotFoundException e) { 
                ServletUtility.setErrorMessage(e.getMessage(), request); 
                log.error(e); 
            } catch (ApplicationException e) { 
                log.error(e); 
                ServletUtility.handleException(e, request, response); 
                return; 
            } 
                ServletUtility.forwardView(ORSView.FORGET_PASSWORD_VIEW, request, 
                        response); 
        } else { 
            ServletUtility.forwardView(ORSView.FORGET_PASSWORD_VIEW, request, 
                    response); 
        } 
 
        log.debug("ForgetPasswordCtl Method doGet Ended"); 
    } 
 
    @Override 
    protected String getView() { 
        return ORSView.FORGET_PASSWORD_VIEW; 
    }

	@Override
	protected BaseDTO getDTO() {
		// TODO Auto-generated method stub
		return new UserDTO();
	} 
	
}
