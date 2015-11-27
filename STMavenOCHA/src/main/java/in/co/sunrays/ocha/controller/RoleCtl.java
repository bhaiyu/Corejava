package in.co.sunrays.ocha.controller;

import in.co.sunrays.ocha.bean.BaseDTO;
import in.co.sunrays.ocha.bean.RoleDTO;
import in.co.sunrays.ocha.exception.ApplicationException;
import in.co.sunrays.ocha.exception.DuplicateRecordException;
import in.co.sunrays.ocha.model.ModelFactory;
import in.co.sunrays.ocha.model.RoleModelInt;
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



public class RoleCtl extends BaseCtl {

	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(RoleCtl.class);

	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("RoleCtl Method validate Started");

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("name"))) {
			request.setAttribute("name",
					PropertyReader.getValue("error.require", "Name"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("description"))) {
			request.setAttribute("description",
					PropertyReader.getValue("error.require", "Description"));
			pass = false;
		}
		
		if(DataValidator.isInteger(request.getParameter("name")))
		{
			request.setAttribute("name", PropertyReader.getValue("error.require1", "Name"));
			pass=false;
		}
		
		if(DataValidator.isInteger(request.getParameter("description")))
		{
			request.setAttribute("description", PropertyReader.getValue("error.require1", "Description"));
			pass=false;
		}

		log.debug("RoleCtl Method validate Ended");

		return pass;
	}

	@Override
	protected BaseDTO populateDTO(HttpServletRequest request, BaseDTO RolDto) {

		log.debug("RoleCtl Method populateDTO Started");

		RoleDTO dto = (RoleDTO)super.populateDTO(request, RolDto);

		dto.setId(DataUtility.getLong(request.getParameter("id")));

		dto.setName(DataUtility.getString(request.getParameter("name")));

		dto.setDescription(DataUtility.getString(request
				.getParameter("description")));

		log.debug("RoleCtl Method populateDTO Ended");

		return dto;
	}

	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		log.debug("RoleCtl Method doGet Started");

		System.out.println("In do get");

		String op = DataUtility.getString(request.getParameter("operation"));

		// get model
		RoleModelInt model = ModelFactory.getInstance().getRoleModel();

		long id = DataUtility.getLong(request.getParameter("id"));

		if (op_SAVE.equalsIgnoreCase(op)) {
			RoleDTO dto = (RoleDTO) populateDTO(request, getDTO());
			try {
				if (id > 0) {
					model.update(dto);
					ServletUtility.setSuccessMessage("Data is successfully updated", request);
					
					
				} else {
					System.out.println("@@@@@@@@@@@@@@@ in ctl add");
					long pk = model.add(dto);
					//dto.setId(pk);
					
					ServletUtility.setSuccessMessage("Data is successfully inserted",request);
					//ServletUtility.forward(ORSView.ROLE_VIEW, request, response);
				}

				
				//ServletUtility.setDto(dto, request);
				

			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			} catch (DuplicateRecordException e) {
				ServletUtility.setDto(dto, request);
				ServletUtility.setErrorMessage("Role already exists", request);
			}

		} else if (OP_DELETE.equalsIgnoreCase(op)) {

			RoleDTO dto = (RoleDTO) populateDTO(request, getDTO());
			try {
				model.delete(dto);
				ServletUtility.redirect(ORSView.ROLE_LIST_CTL, request,
						response);
				return;
			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}

		} else if (OP_CANCEL.equalsIgnoreCase(op)) {

			RoleDTO dto = (RoleDTO) populateDTO(request, getDTO());
			
			if(dto.getId() == 0)
			{
			ServletUtility.forwardView(ORSView.ROLE_VIEW, request, response);
			}else
			{
				ServletUtility.redirect(ORSView.ROLE_LIST_CTL, request, response);
			}
	return;

		} else { // View page

			if (id > 0 || op != null) {
				RoleDTO dto;
				try {
					dto = model.findByPK(id);
					ServletUtility.setDto(dto, request);
				} catch (ApplicationException e) {
					log.error(e);
					System.out.println("Error in RoleCTl======="
							+ e.getMessage());
					ServletUtility.handleException(e, request, response);
					return;
				}
			}
		}

		ServletUtility.forwardView(ORSView.ROLE_VIEW, request, response);

		log.debug("RoleCtl Method doGet Ended");
	}

	@Override
	
	protected String getView() {
		return ORSView.ROLE_VIEW;
	}

	@Override
	protected BaseDTO getDTO() {
		// TODO Auto-generated method stub
		return new RoleDTO();
	}

}