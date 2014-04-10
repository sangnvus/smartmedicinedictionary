package com.med.dic.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.interceptor.SessionAware;

import com.med.dic.base.action.BaseAction;
import com.med.dic.dao.PharmacyDAO;
import com.med.dic.dao.RepresentativeDAO;
import com.med.dic.dao.SMDUserDAO;
import com.med.dic.model.Pharmacy;
import com.med.dic.model.Representative;
import com.med.dic.model.SMDUser;
import com.med.dic.utility.CheckVisitTime;

public class LoginAction extends BaseAction implements SessionAware {

	/**
	 * serial version UID
	 */
	private static final long serialVersionUID = 3339799710760487086L;
	private String userName;
	private String password;
	private SMDUserDAO smdUserDAO;
	private SMDUser user = new SMDUser();
	private RepresentativeDAO repDAO;
	public Representative rep = new Representative();
	public List<Pharmacy> pharmacy = new ArrayList<>();
	private PharmacyDAO pharmacyDAO;

	@Override
	public String execute() {
		SMDUser smdUser = (SMDUser) session.get("user");
		CheckVisitTime.checkVisitor(session, servletRequest, visitTimeDAO);
		// logged status
		if (smdUser != null) {
			return SUCCESS;
		} else {
			this.userName = this.userName.toLowerCase();
			SMDUser u = new SMDUser(this.userName, this.password);
			if (isValidUser(u)) {
				if (user.getUserGroup() == 2) {
					rep = repDAO.getRepByEmail(this.userName);
					pharmacy = pharmacyDAO.repsPharmacy(rep.getRepId());
					if(pharmacy.size() > 0) {
						session.put("userName", user.getName());
						session.put("smdEmail", user.getEmail());
						session.put("userGroup2", user);
						session.remove("userGroup1");
						session.put("rep", rep);
						session.put("pharmacyList", pharmacy);
						return "userGroup2";
					} else {
						addFieldError("loginError",
								"Tài khoản của bạn đang chờ kiểm duyệt, xin vui lòng quay lại sau?");
						return INPUT;
					}
					
				} else {
					session.remove("userGroup2");
					session.put("userName", user.getName());
					session.put("smdEmail", user.getEmail());
					session.put("userGroup1", user);
					return "userGroup1";
				}
			}
			addFieldError("loginError",
					"Tên người dùng hoặc mật khẩu bạn đã nhập không chính xác?");
			return INPUT;
		}
	}

	public boolean isValidUser(SMDUser smduser) {
		user = smdUserDAO.getSMDUser(this.userName);
		if (user == null) {
			return false;
		} else {
			if (user.getEmail().equals(smduser.getEmail())
					&& user.getPassword().equals(smduser.getPassword())) {
				return true;
			} else {
				return false;
			}
		}
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the smdUserDAO
	 */
	public SMDUserDAO getSmdUserDAO() {
		return smdUserDAO;
	}

	/**
	 * @param smdUserDAO
	 *            the smdUserDAO to set
	 */
	public void setSmdUserDAO(SMDUserDAO smdUserDAO) {
		this.smdUserDAO = smdUserDAO;
	}

	/**
	 * @return the user
	 */
	public SMDUser getUser() {
		return user;
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
	 * @return the pharmacy
	 */
	public List<Pharmacy> getPharmacy() {
		return pharmacy;
	}

	/**
	 * @param pharmacy
	 *            the pharmacy to set
	 */
	public void setPharmacy(List<Pharmacy> pharmacy) {
		this.pharmacy = pharmacy;
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

}
