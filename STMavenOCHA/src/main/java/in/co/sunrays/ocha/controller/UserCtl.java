package in.co.sunrays.ocha.controller;

import in.co.sunrays.ocha.bean.BaseDTO;
import in.co.sunrays.ocha.bean.UserDTO;
import in.co.sunrays.ocha.exception.ApplicationException;
import in.co.sunrays.ocha.exception.DuplicateRecordException;
import in.co.sunrays.ocha.model.ModelFactory;
import in.co.sunrays.ocha.model.RoleModelInt;
import in.co.sunrays.ocha.model.UserModelInt;
import in.co.sunrays.util.DataUtility;
import in.co.sunrays.util.DataValidator;
import in.co.sunrays.util.PropertyReader;
import in.co.sunrays.util.ServletUtility;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class UserCtl extends BaseCtl {

	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(UserCtl.class);

	@Override
	protected void preload(HttpServletRequest request) {
		RoleModelInt model = ModelFactory.getInstance().getRoleModel();
		try {
			List l = model.list();
			request.setAttribute("roleList", l);
		} catch (ApplicationException e) {
			log.error(e);
		}

	}

	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("UserCtl Method validate Started");

		boolean pass = true;

		String login = request.getParameter("login");
		String dob = request.getParameter("dob");

		if (DataValidator.isNull(request.getParameter("firstName"))) {
			request.setAttribute("firstName",
					PropertyReader.getValue("error.require", "First Name"));
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("firstName"))) {
			request.setAttribute("firstName",
					PropertyReader.getValue("error.name", "First Name"));
			pass = false;
		}
		if (DataValidator.isInteger(request.getParameter("firstName"))) {
			request.setAttribute("firstName",
					PropertyReader.getValue("error.require1", "First Name"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("lastName"))) {
			request.setAttribute("lastName", in.co.sunrays.util.PropertyReader
					.getValue("error.require", "Last Name"));
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("lastName"))) {
			request.setAttribute("lastName",
					PropertyReader.getValue("error.name", "Last Name"));
			pass = false;
		}
		if (DataValidator.isInteger(request.getParameter("lastName"))) {
			request.setAttribute("lastName", in.co.sunrays.util.PropertyReader
					.getValue("error.require1", "Last Name"));
			pass = false;
		}

		if (in.co.sunrays.util.DataValidator.isNull(login)) {
			request.setAttribute("login", in.co.sunrays.util.PropertyReader
					.getValue("error.require", "Login Id"));
			pass = false;
		} else if (!in.co.sunrays.util.DataValidator.isEmail(login)) {
			request.setAttribute("login", in.co.sunrays.util.PropertyReader
					.getValue("error.email", "Login "));
			pass = false;
		}

		if (in.co.sunrays.util.DataValidator.isNull(request
				.getParameter("password"))) {
			request.setAttribute("password", in.co.sunrays.util.PropertyReader
					.getValue("error.require", "Password"));
			pass = false;
		}

		if (in.co.sunrays.util.DataValidator.isNull(request
				.getParameter("confirmPassword"))) {
			request.setAttribute("confirmPassword",
					in.co.sunrays.util.PropertyReader.getValue("error.require",
							"Confirm Password"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("gender"))) {
			request.setAttribute("gender", in.co.sunrays.util.PropertyReader
					.getValue("error.require", "Gender"));
			pass = false;
		}

		if (DataValidator.isNull(dob)) {
			request.setAttribute("dob", in.co.sunrays.util.PropertyReader
					.getValue("error.require", "Date Of Birth"));
			pass = false;
		
		} else if (!in.co.sunrays.util.DataValidator.isDate(dob)) {
			request.setAttribute("dob", in.co.sunrays.util.PropertyReader
					.getValue("error.date", "Date Of Birth"));
			pass = false;
		}

		if (!request.getParameter("password").equals(
				request.getParameter("confirmPassword"))
				&& !"".equals(request.getParameter("confirmPassword"))) {
			ServletUtility.setErrorMessage(
					"Confirm  Password  should not be matched.", request);
			pass = false;
		}

		log.debug("UserCtl Method validate Ended");

		return pass;
	}

	@Override
	protected BaseDTO populateDTO(HttpServletRequest request, BaseDTO User1DTO) {

		log.debug("UserCtl Method populateDTO Started");

		UserDTO dto = (UserDTO) super.populateDTO(request, User1DTO);

		dto.setId(in.co.sunrays.util.DataUtility.getLong(request
				.getParameter("id")));

		dto.setRoleId(in.co.sunrays.util.DataUtility.getLong(request
				.getParameter("roleId")));

		dto.setFirstName(in.co.sunrays.util.DataUtility.getString(request
				.getParameter("firstName")));

		dto.setLastName(in.co.sunrays.util.DataUtility.getString(request
				.getParameter("lastName")));

		dto.setLogin(in.co.sunrays.util.DataUtility.getString(request
				.getParameter("login")));

		dto.setPassword(in.co.sunrays.util.DataUtility.getString(request
				.getParameter("password")));

		dto.setConfirmPassword(in.co.sunrays.util.DataUtility.getString(request
				.getParameter("confirmPassword")));

		dto.setGender(in.co.sunrays.util.DataUtility.getString(request
				.getParameter("gender")));

		dto.setDob(in.co.sunrays.util.DataUtility.getDate(request
				.getParameter("dob")));

		log.debug("UserCtl Method populateDTO Ended");

		return dto;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		log.debug("UserCtl Method doGet Started");
		String op = in.co.sunrays.util.DataUtility.getString(request
				.getParameter("operation"));

		// get model
		UserModelInt model = ModelFactory.getInstance().getUserModel();

		long id = in.co.sunrays.util.DataUtility.getLong(request
				.getParameter("id"));

		if (op_SAVE.equalsIgnoreCase(op)) {
			UserDTO dto = (UserDTO) populateDTO(request, getDTO());

			try {
				if (id > 0) {
					model.update(dto);
				} else {
					long pk = model.add(dto);
					dto.setId(pk);
				}
				ServletUtility.setDto(dto, request);
				ServletUtility.setSuccessMessage("Data is successfully saved",
						request);

			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			} catch (DuplicateRecordException e) {
				ServletUtility.setDto(dto, request);
				ServletUtility.setErrorMessage("Login id already exists",
						request);
			}

		} else if (OP_DELETE.equalsIgnoreCase(op)) {

			UserDTO dto = (UserDTO) populateDTO(request, getDTO());
			try {
				model.delete(dto);
				ServletUtility.redirect(ORSView.USER_LIST_CTL, request,
						response);
				return;
			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}

		} else if (OP_CANCEL.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.USER_LIST_CTL, request, response);
			return;

		} else { // View page

			if (id > 0 || op != null) {
				System.out.println("in id > 0  condition");
				UserDTO dto;
				try {
					dto = model.findByPK(id);
					ServletUtility.setDto(dto, request);
				} catch (ApplicationException e) {
					log.error(e);
					ServletUtility.handleException(e, request, response);
					return;
				}
			}
		}

		ServletUtility.forwardView(ORSView.USER_VIEW, request, response);

		log.debug("UserCtl Method doGet Ended");
	}

	@Override
	protected String getView() {
		return ORSView.USER_VIEW;
	}

	@Override
	protected BaseDTO getDTO() {
		// TODO Auto-generated method stub
		return new UserDTO();
	}

}
