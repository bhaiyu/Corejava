package in.co.sunrays.ocha.controller;

import java.io.IOException;


import java.sql.Timestamp;
import java.util.Date;

import in.co.sunrays.ocha.bean.BaseDTO;
import in.co.sunrays.ocha.bean.UserDTO;
import in.co.sunrays.util.DataUtility;
import in.co.sunrays.util.DataValidator;
import in.co.sunrays.util.ServletUtility;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public abstract class BaseCtl extends HttpServlet {
	public static final String op_SAVE = "Save";
	public static final String OP_CANCEL = "Cancel";
	public static final String OP_DELETE = "Delete";
	public static final String OP_LIST = "List";
	public static final String OP_SEARCH = "Search";
	public static final String OP_VIEW = "View";
	public static final String OP_NEXT = "Next";
	public static final String OP_PREVIOUS = "Previous";
	public static final String OP_NEW = "New";
	public static final String OP_GO = "Go";
	public static final String MSG_SUCCESS = "success";
	public static final String MSG_ERROR = "error";

	protected boolean validate(HttpServletRequest request) {
		return true;
	}

	protected void preload(HttpServletRequest request) {
	}

	protected BaseDTO populateDTO(HttpServletRequest request, BaseDTO dto) {
		HttpSession session = request.getSession(true);
		UserDTO user = (UserDTO) session.getAttribute("user");
		String createdBy = DataUtility.getStringData(request
				.getParameter("createdBy"));
		String modifiedBy = DataUtility.getStringData(request
				.getParameter("modifiedBy"));
		Timestamp createdDateTime = DataUtility.getTime(request
				.getParameter("createdDateTime"));
		Timestamp modifiedDateTime = DataUtility.getTime(request
				.getParameter("modifiedDateTime"));

		if (user != null) {
			modifiedBy = user.getLogin();
			modifiedDateTime = new Timestamp(new Date().getTime());
			System.out.println("created by in baseCTL " + createdBy);
			if (createdBy.equals("null")) {
				System.out.println("in baseCtl if createdBy null");
				createdBy = modifiedBy;
				createdDateTime = modifiedDateTime;
			}
		}

		dto.setCreatedBy(createdBy);
		dto.setModifiedBy(modifiedBy);
		dto.setCreatedDatetime(createdDateTime);
		dto.setModifiedDatetime(modifiedDateTime);
		return dto;
	}
	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		preload(request);

		String op = DataUtility.getString(request.getParameter("operation"));

		if (DataValidator.isNotNull(op) && !OP_CANCEL.equalsIgnoreCase(op)
				&& !OP_VIEW.equalsIgnoreCase(op)
				&& !OP_DELETE.equalsIgnoreCase(op)) {
			if (!validate(request)) {
				BaseDTO dto = (BaseDTO) populateDTO(request, getDTO());
				ServletUtility.setDto(dto, request);
				ServletUtility.forwardView(getView(), request, response);
				return;
			}
		}
		super.service(request, response);

	}
	protected abstract BaseDTO getDTO();

	protected abstract String getView();

}
