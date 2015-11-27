package in.co.sunrays.ocha.controller;

import java.io.IOException;

import in.co.sunrays.ocha.bean.BaseDTO;
import in.co.sunrays.ocha.bean.MarksheetDTO;
import in.co.sunrays.ocha.exception.ApplicationException;
import in.co.sunrays.ocha.model.MarksheetModelInt;
import in.co.sunrays.ocha.model.ModelFactory;
import in.co.sunrays.util.DataUtility;
import in.co.sunrays.util.DataValidator;
import in.co.sunrays.util.PropertyReader;
import in.co.sunrays.util.ServletUtility;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class GetMarksheetCtl extends BaseCtl {

	private static Logger log = Logger.getLogger(GetMarksheetCtl.class);

	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("GetMarksheetCTL Method validate Started");

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("rollNo"))) {
			request.setAttribute("rollNo",
					PropertyReader.getValue("error.require", "Roll Number"));
			pass = false;
		}

		log.debug("GetMarksheetCTL Method validate Ended");

		return pass;
	}

	@Override
	protected BaseDTO populateDTO(HttpServletRequest request, BaseDTO GetMarkDTO) {

		log.debug("GetMarksheetCtl Method populateDTO Started");

		MarksheetDTO dto = (MarksheetDTO)super.populateDTO(request, GetMarkDTO);

		dto.setId(DataUtility.getLong(request.getParameter("id")));

		dto.setRollNo(DataUtility.getString(request.getParameter("rollNo")));

		dto.setName(DataUtility.getString(request.getParameter("name")));

		dto.setPhysics(DataUtility.getInt(request.getParameter("physics")));

		dto.setChemistry(DataUtility.getInt(request.getParameter("chemistry")));

		dto.setMaths(DataUtility.getInt(request.getParameter("maths")));

		log.debug("GetMarksheetCtl Method populateDTO Ended");

		return dto;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		log.debug("GetMarksheetCtl Method doGet Started");

		ServletUtility.forwardView(ORSView.GET_MARKSHEET_VIEW, request, response);

		log.debug("MarksheetCtl Method doGet Ended");
	}

	@Override
	protected String getView() {
		return ORSView.GET_MARKSHEET_VIEW;
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		log.debug("GetMarksheetCtl Method doGet Started");

		String op = DataUtility.getString(request.getParameter("operation"));

		// get model
		MarksheetModelInt model = ModelFactory.getInstance()
				.getMarksheetModel();

		MarksheetDTO dto = (MarksheetDTO) populateDTO(request, getDTO());

		long id = DataUtility.getLong(request.getParameter("id"));

		if (OP_GO.equalsIgnoreCase(op)) {

			try {
				dto = model.findByRollNo(dto.getRollNo());
				if (dto != null) {
					ServletUtility.setDto(dto, request);
				} else {
					ServletUtility.setErrorMessage("RollNo Does Not exists",
							request);
				}
			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.setDto(dto, request);
				ServletUtility.handleException(e, request, response);
				return;
			}

		}

		ServletUtility.forwardView(ORSView.GET_MARKSHEET_VIEW, request, response);

		log.debug("MarksheetCtl Method doGet Ended");
	}

	@Override
	protected BaseDTO getDTO() {
		// TODO Auto-generated method stub
		return new MarksheetDTO();
	}

}
