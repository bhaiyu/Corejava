package in.co.sunrays.ocha.controller;

import java.io.IOException;

import in.co.sunrays.ocha.bean.BaseDTO;
import in.co.sunrays.ocha.bean.RoleDTO;
import in.co.sunrays.ocha.bean.UserDTO;
import in.co.sunrays.ocha.exception.ApplicationException;
import in.co.sunrays.ocha.model.ModelFactory;
import in.co.sunrays.ocha.model.RoleModelInt;
import in.co.sunrays.ocha.model.UserModelInt;
import in.co.sunrays.util.DataUtility;
import in.co.sunrays.util.DataValidator;
import in.co.sunrays.util.PropertyReader;
import in.co.sunrays.util.ServletUtility;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

public class LoginCtl extends BaseCtl {

	private static final long serialVersionUID = 1L;
	public static final String OP_REGISTER = "Register";
	public static final String OP_SIGN_IN = "SignIn";
	public static final String OP_SIGN_UP = "SignUp";
	public static final String OP_LOG_OUT = "logout";
	private static Logger log = Logger.getLogger(LoginCtl.class);

	@Override
	protected boolean validate(HttpServletRequest request) {
		log.debug("LoginCtl method validate started");
		boolean pass = true;
		String op = request.getParameter("operation");
		if (OP_SIGN_UP.equals(op) || OP_LOG_OUT.equals(op)) {
			return pass;
		}
		String login = request.getParameter("login");
		if (DataValidator.isNull(login)) {
			request.setAttribute("login",
					PropertyReader.getValue("error.require", "Login Id"));
			pass = false;
		} else if (!DataValidator.isEmail(login)) {
			request.setAttribute("login",
					PropertyReader.getValue("error.email", "Login"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("password"))) {
			request.setAttribute("password",
					PropertyReader.getValue("error.require", "Password"));
			pass = false;
		}
		log.debug("LoginCtl Method validate Ended");

		return pass;

	}

	@Override
	protected BaseDTO populateDTO(HttpServletRequest request, BaseDTO loginDTO) {

		log.debug("LoginCtl Method populateDTO Started");

		UserDTO dto = (UserDTO) super.populateDTO(request, loginDTO);
		dto.setId(DataUtility.getLong(request.getParameter("id")));
		dto.setLogin(DataUtility.getString(request.getParameter("login")));
		dto.setPassword(DataUtility.getString(request.getParameter("password")));
		dto.setCreatedBy(DataUtility.getString(request
				.getParameter("createdBy")));

		log.debug("LoginCtl Method populateDTO Ended");

		return dto;
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		log.debug(" Method doGet Started");

		String op = DataUtility.getString(request.getParameter("operation"));
		// get model
		UserModelInt model = ModelFactory.getInstance().getUserModel();
		RoleModelInt role = ModelFactory.getInstance().getRoleModel();
		if (OP_LOG_OUT.equals(op)) {

			session = request.getSession();

			session.invalidate();

			ServletUtility.redirect(ORSView.LOGIN_CTL, request, response);

			return;

		}

		long id = DataUtility.getLong(request.getParameter("id"));
		if (id > 0 || op != null) {
			UserDTO userDTO;
			try {
				userDTO = model.findByPK(id);
				ServletUtility.setDto(userDTO, request);
			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}
		}

		ServletUtility.forwardView(ORSView.LOGIN_VIEW, request, response);
		log.debug("UserCtl Method doGet Ended");
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		log.debug(" Method doGet Started");

		String op = DataUtility.getString(request.getParameter("operation"));
		// get model
		UserModelInt model = ModelFactory.getInstance().getUserModel();
		RoleModelInt role = ModelFactory.getInstance().getRoleModel();

		long id = DataUtility.getLong(request.getParameter("id"));
		if (OP_SIGN_IN.equalsIgnoreCase(op)) {

			UserDTO dto = (UserDTO) populateDTO(request, getDTO());

			try {
				dto = model.authenticate(dto.getLogin(), dto.getPassword());

				if (dto != null) {
					session.setAttribute("user", dto);
					long rollId = dto.getRoleId();

					RoleDTO roleDTO = role.findByPK(rollId);

					if (roleDTO != null) {
						session.setAttribute("role", roleDTO.getName());
					}

					ServletUtility.forwardView(ORSView.WELCOME_VIEW, request,
							response);
					return;

				} else {
					dto = (UserDTO) populateDTO(request, getDTO());
					ServletUtility.setDto(dto, request);
					ServletUtility.setErrorMessage(
							"Invalid LoginId And Password", request);
				}
			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}

		} else if (OP_LOG_OUT.equals(op)) {

			session = request.getSession();

			session.invalidate();

			ServletUtility.redirect(ORSView.LOGIN_CTL, request, response);

			return;

		} else if (OP_SIGN_UP.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.USER_REGISTRATION_CTL, request,
					response);
			return;

		}

		else { // View page

			if (id > 0 || op != null) {
				UserDTO userDTO;
				try {
					userDTO = model.findByPK(id);
					ServletUtility.setDto(userDTO, request);
				} catch (ApplicationException e) {
					log.error(e);
					ServletUtility.handleException(e, request, response);
					return;
				}
			}
		}

		ServletUtility.forwardView(getView(), request, response);

		log.debug("UserCtl Method doGet Ended");
	}

	@Override
	protected String getView() {
		return ORSView.LOGIN_VIEW;
	}

	@Override
	protected BaseDTO getDTO() {
		// TODO Auto-generated method stub
		return new UserDTO();
	}

}
