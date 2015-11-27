package in.co.sunrays.ocha.controller;


import in.co.sunrays.ocha.bean.BaseDTO;
import in.co.sunrays.ocha.bean.CollegeDTO;
import in.co.sunrays.ocha.bean.RoleDTO;
import in.co.sunrays.ocha.exception.ApplicationException;
import in.co.sunrays.ocha.model.CollegeModelInt;
import in.co.sunrays.ocha.model.ModelFactory;
import in.co.sunrays.util.DataUtility;
import in.co.sunrays.util.PropertyReader;
import in.co.sunrays.util.ServletUtility;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;


public class CollegeListCtl extends BaseCtl{

	 private static Logger log = Logger.getLogger(CollegeListCtl.class); 
	 
	    @Override 
	    protected BaseDTO populateDTO(HttpServletRequest request, BaseDTO CollListDTO) { 
	        CollegeDTO dto = (CollegeDTO)super.populateDTO(request, CollListDTO);
	 
	        dto.setName(DataUtility.getString(request.getParameter("name"))); 
	        dto.setCity(DataUtility.getString(request.getParameter("city"))); 
	 
	        return dto; 
	    } 
    
 

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("CollegeListCtl.doGet Start"); 
		 System.out.println("Start Do get Method");
		 
        List list = null; 
 
        int pageNo = DataUtility.getInt(request.getParameter("pageNo")); 
        int pageSize = DataUtility.getInt(request.getParameter("pageSize")); 
 
        pageNo = (pageNo == 0) ? 1 : pageNo; 
 
        pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader 
                .getValue("page.size")) : pageSize; 
 
        CollegeDTO dto = (CollegeDTO) populateDTO(request, getDTO()); 
 
        String op = DataUtility.getString(request.getParameter("operation")); 
 
        CollegeModelInt model = ModelFactory.getInstance().getCollegeModel(); 
 
        try { 
 
            if (OP_SEARCH.equalsIgnoreCase(op) || OP_NEXT.equalsIgnoreCase(op) 
                    || OP_PREVIOUS.equalsIgnoreCase(op)) { 
 
                if (OP_SEARCH.equalsIgnoreCase(op)) { 
                    pageNo = 1; 
                }
                
                else if (OP_NEXT.equalsIgnoreCase(op)) { 
                    pageNo++; 
                } else if (OP_PREVIOUS.equalsIgnoreCase(op) && pageNo > 1) { 
                    pageNo--; 
                }
                
                
 
            } else if (OP_DELETE.equalsIgnoreCase(op)) {
            	
            	System.out.println("inside delete");
				String check[] = request.getParameterValues("Delete");
				if (check != null && check.length > 0) {
					CollegeDTO dto1 = new CollegeDTO();
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
            ServletUtility.forwardView(ORSView.COLLEGE_LIST_VIEW, request, response); 
            
        } catch (ApplicationException e) { 
            log.error(e); 
            e.printStackTrace();
            ServletUtility.handleException(e, request, response); 
            return; 
        } 
        log.debug("CollegeListCtl doGet End"); 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}



	@Override
	protected String getView() {
		
		return ORSView.COLLEGE_LIST_VIEW;
	}



	@Override
	protected BaseDTO getDTO() {
		// TODO Auto-generated method stub
		return new CollegeDTO();
	}

}
