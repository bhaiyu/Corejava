package in.co.sunrays.ocha.controller;

import in.co.sunrays.ocha.bean.BaseDTO;
import in.co.sunrays.util.ServletUtility;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class WelcomeCtl extends BaseCtl {

	private static Logger log = Logger.getLogger(WelcomeCtl.class);

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		log.debug("WelcomeCtl Method doGet Started");

		ServletUtility.forwardView(ORSView.WELCOME_VIEW, request, response);

		log.debug("WelcomeCtl Method doGet Ended");
	}

	@Override
	protected String getView() {
		return ORSView.WELCOME_VIEW;
	}

	@Override
	protected BaseDTO getDTO() {
		// TODO Auto-generated method stub
		return null;
	}



	

}
