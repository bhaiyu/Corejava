package in.co.sunrays.ocha.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.sunrays.ocha.bean.BaseDTO;
import in.co.sunrays.ocha.bean.CollegeDTO;
import in.co.sunrays.ocha.bean.RoleDTO;
import in.co.sunrays.ocha.exception.ApplicationException;
import in.co.sunrays.ocha.model.ModelFactory;
import in.co.sunrays.ocha.model.RoleModelInt;
import in.co.sunrays.util.DataUtility;
import in.co.sunrays.util.PropertyReader;
import in.co.sunrays.util.ServletUtility;

import org.apache.log4j.Logger;

/* 
 * Role List functionality Controller. Performs operation for list, 
 * search operations of Role
 */ 

public class RoleListCtl extends BaseCtl {
	private static Logger log = Logger.getLogger(RoleListCtl.class);
																																							
	@Override
	protected BaseDTO populateDTO(HttpServletRequest request, BaseDTO RolDTO) {
														
		RoleDTO dto = (RoleDTO)super.populateDTO(request, RolDTO);
		dto.setName(DataUtility.getString(request.getParameter("name")));

		return dto;
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		log.debug("RoleListCtl doGet Start");

		List list = null;

		int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
		int pageSize = DataUtility.getInt(request.getParameter("pageSize"));

		pageNo = (pageNo == 0) ? 1 : pageNo;

		pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader
				.getValue("page.size")) : pageSize;

		RoleDTO dto = (RoleDTO) populateDTO(request, getDTO());

		String op = DataUtility.getString(request.getParameter("operation"));

		RoleModelInt model = ModelFactory.getInstance().getRoleModel();

		try {

			if (OP_SEARCH.equalsIgnoreCase(op) || "Next".equalsIgnoreCase(op)
					|| "Previous".equalsIgnoreCase(op)) {

				if (OP_SEARCH.equalsIgnoreCase(op)) {
					pageNo = 1;
				} else if (OP_NEXT.equalsIgnoreCase(op)) {
					pageNo++;
				} else if (OP_PREVIOUS.equalsIgnoreCase(op) && pageNo > 1) {
					pageNo--;
				}

			} else if (OP_DELETE.equalsIgnoreCase(op)) {

            	System.out.println("inside delete");
				String check[] = request.getParameterValues("Delete");
				if (check != null && check.length > 0) {
					RoleDTO dto1 = new RoleDTO();
					for (String id : check) {
						dto1.setId(DataUtility.getLong(id));
						model.delete(dto1);
						ServletUtility.setDto(dto1, request); 
		                ServletUtility.setSuccessMessage("Record is successfully Deleted", 
		                        request); 
					}
				}

			}
			list = model.search(dto, pageNo, pageSize);
			ServletUtility.setList(list, request);
			if (list == null || list.size() == 0) {
				ServletUtility.setErrorMessage("No record found ", request);
			}
			ServletUtility.setList(list, request);

			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
			ServletUtility.forwardView(ORSView.ROLE_LIST_VIEW, request, response);

		} catch (ApplicationException e) {
			log.error(e);
			ServletUtility.handleException(e, request, response);
			return;
		}
		log.debug("RoleListCtl doGet End");
	}

	@Override
	protected String getView() {
		return ORSView.ROLE_LIST_VIEW;
	}

	@Override
	protected BaseDTO getDTO() {
		// TODO Auto-generated method stub
		return new RoleDTO();
	}

}
