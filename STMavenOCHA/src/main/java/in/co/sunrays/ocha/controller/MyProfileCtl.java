package in.co.sunrays.ocha.controller;

import in.co.sunrays.ocha.bean.BaseDTO;
import in.co.sunrays.ocha.bean.UserDTO;
import in.co.sunrays.ocha.exception.ApplicationException;
import in.co.sunrays.ocha.exception.DuplicateRecordException;
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
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

@SuppressWarnings("serial")
public class MyProfileCtl extends BaseCtl {
	public static final String OP_CHANGE_MY_PASSWORD = "ChangePassword";
	private static Logger log = Logger.getLogger(MyProfileCtl.class);

	protected boolean validate(HttpServletRequest request) {

		log.debug("MyProfileCtl Method validate Started");

		boolean pass = true;

		String op = DataUtility.getString(request.getParameter("operation"));

		if (OP_CHANGE_MY_PASSWORD.equalsIgnoreCase(op) || op == null) {

			return pass;
		}

		if (DataValidator.isNull(request.getParameter("firstName"))) {
			System.out.println("firstName" + request.getParameter("firstName"));
			request.setAttribute("firstName",
					PropertyReader.getValue("error.require", "First Name"));
			pass = false;
		}
		if (DataValidator.isInteger(request.getParameter("firstName"))) {
			request.setAttribute("firstName",
					PropertyReader.getValue("error.require1", "First Name"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("lastName"))) {
			// System.out.println("lastName"+request.getParameter("lastName"));
			request.setAttribute("lastName",
					PropertyReader.getValue("error.require", "Last Name"));
			pass = false;

		}
		if (DataValidator.isInteger(request.getParameter("lastName"))) {
			request.setAttribute("lastName", PropertyReader.getValue(
					"error.require1", "l" + "Last Name"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("gender"))) {
			// System.out.println("gender"+request.getParameter("gender"));
			request.setAttribute("gender",
					PropertyReader.getValue("error.require", "Gender"));
			pass = false;

		}
		String phoneNo = request.getParameter("mobileNo");
		if (DataValidator.isNull(request.getParameter("mobileNo"))) {
			request.setAttribute("mobileNo",
					PropertyReader.getValue("error.require", "Phone No"));
			pass = false;
		}

		else if (DataValidator.isNotNull(request.getParameter("mobileNo"))
				&& !DataValidator.isLong(request.getParameter("mobileNo"))) {
			request.setAttribute("mobileNo",
					PropertyReader.getValue("error.integer", "Phone No"));
			pass = false;
		} else if (phoneNo.length() < 10) {
			request.setAttribute("mobileNo", "Phone no Should be 10 Digits");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("dob"))) {
			request.setAttribute("dob",
					PropertyReader.getValue("error.require", "Date of Birth"));
			pass = false;
		}
		log.debug("MyProfileCtl Method Validate Ended");

		return pass;

	}

	protected BaseDTO populateDTO(HttpServletRequest request, BaseDTO MyrofDTO) {

		log.debug("MyProfileCtl Method populateDTO Started");

		UserDTO dto = (UserDTO)super.populateDTO(request, MyrofDTO);

		dto.setId(DataUtility.getLong(request.getParameter("id")));

		dto.setLogin(DataUtility.getString(request.getParameter("login")));

		dto.setFirstName(DataUtility.getString(request
				.getParameter("firstName")));

		dto.setLastName(DataUtility.getString(request.getParameter("lastName")));

		dto.setMobileNo(DataUtility.getString(request.getParameter("mobileNo")));

		dto.setGender(DataUtility.getString(request.getParameter("gender")));

		dto.setDob(DataUtility.getDate(request.getParameter("dob")));

		return dto;

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		log.debug("MyprofileCtl Method doGet Started");
		UserDTO userdto = (UserDTO) session.getAttribute("user");
		long id = userdto.getId();
		String op = DataUtility.getString(request.getParameter("operation"));

		UserModelInt model = ModelFactory.getInstance().getUserModel();

		if (op_SAVE.equalsIgnoreCase(op)) {
			UserDTO dto = (UserDTO) populateDTO(request, getDTO());
			try {
				if (id > 0) {
					System.out.println("in if block");
					userdto.setFirstName(dto.getFirstName());
					userdto.setLastName(dto.getLastName());
					userdto.setGender(dto.getGender());
					userdto.setMobileNo(dto.getMobileNo());
					userdto.setDob(dto.getDob());

					model.update(userdto);

				}
				ServletUtility.setDto(dto, request);
				ServletUtility.setSuccessMessage(
						"Profile has been updated successfully ", request);
			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			} catch (DuplicateRecordException e) {
				ServletUtility.setDto(dto, request);
				ServletUtility.setErrorMessage("Login id already exists",
						request);
			}
		} else if (OP_CHANGE_MY_PASSWORD.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.CHANGE_PASSWORD_CTL, request,
					response);
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

		ServletUtility.forwardView(ORSView.MY_PROFILE_VIEW, request, response);

		log.debug("MyProfileCtl Method doGet Ended");

	}

	@Override
	protected String getView() {

		return ORSView.MY_PROFILE_VIEW;
	}

	@Override
	protected BaseDTO getDTO() {
		// TODO Auto-generated method stub
		return new UserDTO();
	}
}