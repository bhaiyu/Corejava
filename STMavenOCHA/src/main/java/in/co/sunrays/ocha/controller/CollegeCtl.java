package in.co.sunrays.ocha.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.sunrays.ocha.bean.BaseDTO;
import in.co.sunrays.ocha.bean.CollegeDTO;
import in.co.sunrays.ocha.exception.ApplicationException;
import in.co.sunrays.ocha.exception.DuplicateRecordException;
import in.co.sunrays.ocha.model.CollegeModelInt;
import in.co.sunrays.ocha.model.ModelFactory;
import in.co.sunrays.util.DataUtility;
import in.co.sunrays.util.DataValidator;
import in.co.sunrays.util.PropertyReader;
import in.co.sunrays.util.ServletUtility;

/**
 * College List functionality Controller. Performs operation for list, search
 * and delete operations of College
 * 
 */
public class CollegeCtl extends BaseCtl {

	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(CollegeCtl.class);

	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("CollegeCtl Method validate Started");

		boolean pass = true;
		String name = request.getParameter("name");

		if (DataValidator.isNull(request.getParameter("name"))) {
			request.setAttribute("name",
					PropertyReader.getValue("error.require", "Name"));
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("name"))) {
			request.setAttribute("name",
					PropertyReader.getValue("error.name", "Name"));
			pass = false;
		}
		if (DataValidator.isInteger(request.getParameter("name"))) {
			request.setAttribute("name",
					PropertyReader.getValue("error.require1", "Name"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("address"))) {
			request.setAttribute("address",
					PropertyReader.getValue("error.require", "Address"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("state"))) {
			request.setAttribute("state",
					PropertyReader.getValue("error.require", "State"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("city"))) {
			request.setAttribute("city",
					PropertyReader.getValue("error.require", "City"));
			pass = false;
		}
		String phoneNo = request.getParameter("phoneNo");
		if (DataValidator.isNull(request.getParameter("phoneNo"))) {
			request.setAttribute("phoneNo",
					PropertyReader.getValue("error.require", "Phone No"));
			pass = false;
		}

		else if (DataValidator.isNotNull(request.getParameter("phoneNo"))
				&& !DataValidator.isLong(request.getParameter("phoneNo"))) {
			request.setAttribute("phoneNo",
					PropertyReader.getValue("error.integer", "Phone No"));
			pass = false;
		} else if (phoneNo.length() < 10) {
			request.setAttribute("phoneNo", "Phone no Should be 10 Digits");
			pass = false;
		}

		log.debug("CollegeCtl Method validate Ended");

		return pass;
	}

	@Override
	protected BaseDTO populateDTO(HttpServletRequest request, BaseDTO CollDTO) {

		log.debug("CollegeCtl Method populateDTO Started");

		CollegeDTO dto = (CollegeDTO) super.populateDTO(request, CollDTO);

		dto.setId(DataUtility.getLong(request.getParameter("id")));

		dto.setName(DataUtility.getString(request.getParameter("name")));

		dto.setAddress(DataUtility.getString(request.getParameter("address")));

		dto.setState(DataUtility.getString(request.getParameter("state")));

		dto.setCity(DataUtility.getString(request.getParameter("city")));

		dto.setPhoneNo(DataUtility.getString(request.getParameter("phoneNo")));

		log.debug("CollegeCtl Method populateDTO Ended");

		return dto;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 * 
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		log.debug("CollegeCtl.doGet Started");
		System.out.println("In do get");

		CollegeModelInt model = ModelFactory.getInstance().getCollegeModel();

		long id = DataUtility.getLong(request.getParameter("id"));
		System.out.println("id = " + id);
		if (id > 0) {
			CollegeDTO dto;
			try {
				dto = model.findByPK(id);
				ServletUtility.setDto(dto, request);
			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}
			log.debug("CollegeCtl doGet End");
		}

		ServletUtility.forwardView(ORSView.COLLEGE_VIEW, request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		log.debug("CollegeCtl Method doGet Started");

		String op = DataUtility.getString(request.getParameter("operation"));

		// get model
		CollegeModelInt model = ModelFactory.getInstance().getCollegeModel();

		long id = DataUtility.getLong(request.getParameter("id"));

		if (op_SAVE.equalsIgnoreCase(op)) {

			CollegeDTO dto = (CollegeDTO) populateDTO(request, getDTO());

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
				e.printStackTrace();
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			} catch (DuplicateRecordException e) {
				ServletUtility.setDto(dto, request);
				ServletUtility.setErrorMessage("College Name already exists",
						request);
			}

		} else if (OP_DELETE.equalsIgnoreCase(op)) {

			CollegeDTO dto = (CollegeDTO) populateDTO(request, getDTO());
			try {
				model.delete(dto);
				ServletUtility.setSuccessMessage(
						"Data is successfully deleted", request);
				ServletUtility.forwardView(ORSView.COLLEGE_VIEW, request,
						response);
				return;

			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}

		} else if (OP_CANCEL.equalsIgnoreCase(op)) {

			ServletUtility
					.redirect(ORSView.COLLEGE_LIST_CTL, request, response);
			return;

		} else { // View page

			if (id > 0 || op != null) {
				CollegeDTO dto;
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

		ServletUtility.forwardView(ORSView.COLLEGE_VIEW, request, response);

		log.debug("CollegeCtl Method doGet Ended");
	}

	@Override
	protected String getView() {
		return ORSView.COLLEGE_VIEW;

	}

	@Override
	protected BaseDTO getDTO() {
		// TODO Auto-generated method stub
		return new CollegeDTO();
	}

}