package in.co.sunrays.ocha.controller;

import in.co.sunrays.util.ServletUtility;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class VailidatorCtl implements Filter {

	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		HttpSession session = request.getSession(true);

		if (session.getAttribute("user") == null) {
			System.out.println("session is null");
			request.setAttribute("message","Your session has been expired. Please re-Login.");
			ServletUtility.forwardView(ORSView.LOGIN_VIEW, request, response);
			
		} else {
			chain.doFilter(req, resp);
		
	}
	}

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
