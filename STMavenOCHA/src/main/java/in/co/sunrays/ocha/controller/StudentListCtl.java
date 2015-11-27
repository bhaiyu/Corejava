package in.co.sunrays.ocha.controller;

import in.co.sunrays.ocha.bean.BaseDTO;
import in.co.sunrays.ocha.bean.CollegeDTO;
import in.co.sunrays.ocha.bean.MarksheetDTO;
import in.co.sunrays.ocha.bean.StudentDTO;
import in.co.sunrays.ocha.exception.ApplicationException;
import in.co.sunrays.ocha.model.CollegeModelInt;
import in.co.sunrays.ocha.model.ModelFactory;
import in.co.sunrays.ocha.model.StudentModelInt;
import in.co.sunrays.util.DataUtility;
import in.co.sunrays.util.PropertyReader;
import in.co.sunrays.util.ServletUtility;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class StudentListCtl extends BaseCtl {

	private static Logger log = Logger.getLogger(StudentListCtl.class);

	@Override
	protected BaseDTO populateDTO(HttpServletRequest request, BaseDTO StuListDTO) {

		StudentDTO dto = (StudentDTO) super.populateDTO(request, StuListDTO);

		dto.setFirstName(DataUtility.getString(request
				.getParameter("firstName")));
		dto.setLastName(DataUtility.getString(request.getParameter("lastName")));
		dto.setEmail(DataUtility.getString(request.getParameter("email")));

		return dto;
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		log.debug("StudentListCtl doGet Start");
		System.out.println("In doget Method");
		List list = null;
		int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
		System.out.println(pageNo);
		int pageSize = DataUtility.getInt(request.getParameter("pageSize"));
		System.out.println(pageSize);
		pageNo = (pageNo == 0) ? 1 : pageNo;
		pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader
				.getValue("page.size")) : pageSize;
		StudentDTO dto = (StudentDTO) populateDTO(request, getDTO());
		String op = DataUtility.getString(request.getParameter("operation"));
		StudentModelInt model = ModelFactory.getInstance().getStudentModel();
		try {
			System.out.println("In Try Block");
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
					StudentDTO dto1 = new StudentDTO();
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
			ServletUtility.forwardView(ORSView.STUDENT_LIST_VIEW, request,
					response);

		} catch (ApplicationException e) {
			e.printStackTrace();
			log.error(e);
			ServletUtility.handleException(e, request, response);
			return;
		}
	}

	@Override
	protected String getView() {
		return ORSView.STUDENT_LIST_VIEW;
	}

	@Override
	protected BaseDTO getDTO() {
		// TODO Auto-generated method stub
		return new StudentDTO();
	}
}