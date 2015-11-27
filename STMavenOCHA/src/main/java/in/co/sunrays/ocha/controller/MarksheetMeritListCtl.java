package in.co.sunrays.ocha.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.sunrays.ocha.bean.BaseDTO;
import in.co.sunrays.ocha.bean.MarksheetDTO;
import in.co.sunrays.ocha.exception.ApplicationException;
import in.co.sunrays.ocha.model.MarksheetModelInt;
import in.co.sunrays.ocha.model.ModelFactory;
import in.co.sunrays.util.DataUtility;
import in.co.sunrays.util.PropertyReader;
import in.co.sunrays.util.ServletUtility;

import org.apache.log4j.Logger;

public class MarksheetMeritListCtl extends BaseCtl {

	public static final String OP_BACK = "Back";

	private static Logger log = Logger.getLogger(MarksheetMeritListCtl.class);

	@Override
	protected BaseDTO populateDTO(HttpServletRequest request, BaseDTO MarkMeritDTO) {
		MarksheetDTO dto = (MarksheetDTO)super.populateDTO(request, MarkMeritDTO);

		return dto;
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		log.debug("MarksheetMeritListCtl doGet Start");

		List list = null;

		int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
		int pageSize = DataUtility.getInt(request.getParameter("pageSize"));

		pageNo = (pageNo == 0) ? 1 : pageNo;

		pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader
				.getValue("page.size")) : pageSize;

		MarksheetDTO dto = (MarksheetDTO) populateDTO(request, getDTO());

		String op = DataUtility.getString(request.getParameter("operation"));

		MarksheetModelInt model = ModelFactory.getInstance()
				.getMarksheetModel();

		try {

			if (OP_BACK.equalsIgnoreCase(op)) {
				ServletUtility.redirect(ORSView.WELCOME_CTL, request, response);
				return;
			}

			list = model.getMeritList(pageNo, pageSize);
			ServletUtility.setList(list, request);
			if (list == null || list.size() == 0) {
				ServletUtility.setErrorMessage("No record found ", request);
			}
			ServletUtility.setList(list, request);

			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
			ServletUtility.forwardView(ORSView.MARKSHEET_MERIT_LIST_VIEW, request,
					response);

		} catch (ApplicationException e) {
			log.error(e);
			ServletUtility.handleException(e, request, response);
			return;
		}

		log.debug("MarksheetMeritListCtl doGet End");
	}

	@Override
	protected String getView() {
		return ORSView.MARKSHEET_MERIT_LIST_VIEW;
	}

	@Override
	protected BaseDTO getDTO() {
		// TODO Auto-generated method stub
		return new MarksheetDTO();
	}

}
