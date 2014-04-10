package com.med.dic.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.med.dic.dao.PharmacyDAO;
import com.med.dic.dao.RepresentativeDAO;
import com.med.dic.dao.VisitTimeDAO;
import com.med.dic.model.Pharmacy;
import com.med.dic.model.Representative;
import com.med.dic.model.SMDUser;
import com.med.dic.utility.CheckVisitTime;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class AuthenticationInterceptor extends ActionSupport implements
		Interceptor, ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5529965130724380222L;
	private HttpServletRequest servletRequest;
	private RepresentativeDAO repDAO;
	public Representative rep = new Representative();
	private PharmacyDAO pharmacyDAO;
	private VisitTimeDAO visitTimeDAO;
	public Pharmacy pharmacy;

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public String intercept(ActionInvocation actionInvocation) throws Exception {
		Map<String, Object> session = actionInvocation.getInvocationContext()
				.getSession();
		CheckVisitTime.checkVisitor(session, servletRequest, visitTimeDAO);
		SMDUser user1 = (SMDUser) session.get("userGroup1");
		SMDUser user2 = (SMDUser) session.get("userGroup2");
		if (user2 == null && user1 == null) {
			return LOGIN;
		} else {
			if (user1 != null) {
				return "userGroup1";
			} else {
				session.put(
						"pharmacyList",
						pharmacyDAO.repsPharmacy(repDAO.getRepByEmail(
								user2.getEmail()).getRepId()));
				return "userGroup2";
			}
		}
		// return actionInvocation.invoke();
	}

	/**
	 * @return the repDAO
	 */
	public RepresentativeDAO getRepDAO() {
		return repDAO;
	}

	/**
	 * @param repDAO
	 *            the repDAO to set
	 */
	public void setRepDAO(RepresentativeDAO repDAO) {
		this.repDAO = repDAO;
	}

	/**
	 * @return the rep
	 */
	public Representative getRep() {
		return rep;
	}

	/**
	 * @param rep
	 *            the rep to set
	 */
	public void setRep(Representative rep) {
		this.rep = rep;
	}

	/**
	 * @return the pharmacyDAO
	 */
	public PharmacyDAO getPharmacyDAO() {
		return pharmacyDAO;
	}

	/**
	 * @param pharmacyDAO
	 *            the pharmacyDAO to set
	 */
	public void setPharmacyDAO(PharmacyDAO pharmacyDAO) {
		this.pharmacyDAO = pharmacyDAO;
	}

	/**
	 * @return the pharmacy
	 */
	public Pharmacy getPharmacy() {
		return pharmacy;
	}

	/**
	 * @param pharmacy
	 *            the pharmacy to set
	 */
	public void setPharmacy(Pharmacy pharmacy) {
		this.pharmacy = pharmacy;
	}

	@Override
	public void setServletRequest(HttpServletRequest servletRequest) {
		this.servletRequest = servletRequest;

	}

	/**
	 * @return the visitTimeDAO
	 */
	public VisitTimeDAO getVisitTimeDAO() {
		return visitTimeDAO;
	}

	/**
	 * @param visitTimeDAO
	 *            the visitTimeDAO to set
	 */
	public void setVisitTimeDAO(VisitTimeDAO visitTimeDAO) {
		this.visitTimeDAO = visitTimeDAO;
	}
}
