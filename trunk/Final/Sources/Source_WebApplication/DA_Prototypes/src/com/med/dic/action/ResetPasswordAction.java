package com.med.dic.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.struts2.interceptor.SessionAware;

import com.med.dic.base.action.BaseAction;
import com.med.dic.dao.RepresentativeDAO;
import com.med.dic.dao.SMDUserDAO;
import com.med.dic.email.config.LoadEmailConfiguration;
import com.med.dic.form.EmailForm;
import com.med.dic.model.SMDUser;
import com.med.dic.sercurity.EncryptPassword;
import com.med.dic.utility.CheckVisitTime;
import com.med.dic.utility.EmailUtility;
import com.med.dic.validate.Validator;
import com.opensymphony.xwork2.ActionSupport;

public class ResetPasswordAction extends BaseAction implements SessionAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// SMTP properties - fetched from struts.xml
	private String host;
	private String port;
	private String userName = "xuanlt01913@fpt.edu.vn";
	private String password = "cryonmyshoulder";
	
	private RepresentativeDAO repDAO;
	private SMDUserDAO smdUserDAO;
	private String receiption;
	private String msg;
	private String subject;
	private SMDUser smdUser;
	private SMDUser user = new SMDUser();
	public boolean success = false;
	private String ret;
	static Properties properties = new Properties();
	static {
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.socketFactory.port", "465");
		properties.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.port", "465");
	}

	public String doResetPassword() throws IOException, AddressException,
			MessagingException {
		CheckVisitTime.checkVisitor(session, servletRequest, visitTimeDAO);
		ret = SUCCESS;
		if (!Validator.nullOrBlank(this.receiption)) {
			smdUser = new SMDUser();
			smdUser.setEmail(this.receiption);
			if (isValidUser(smdUser)) {
				String timestamp = new SimpleDateFormat("dd/MM/yyyy")
						.format(new Date());
				Random randomGenerator = new Random();
				int randomInt = randomGenerator.nextInt(1000000000);
				smdUser.setPassword(EncryptPassword.md5(String
						.valueOf(randomInt)));
				smdUserDAO.updateSMDUser(smdUser);
				String repName = repDAO.getRepName(this.receiption);
				this.subject = "[SMD - Smart Medicine Dictionary] Reset password " + timestamp;
				this.msg = "Xin chào bạn " + repName + ",";
				this.msg += "\n";
				this.msg += "Bạn đã gửi yêu cầu cấp lại mật khẩu vào ngày "
						+ timestamp + ".\n";
				this.msg += "Mật khẩu mới của bạn là " + randomInt + ".\n";
				this.msg += "Bạn nên đăng nhập để thay đổi mật khẩu của mình.\n";
				this.msg += "Cám ơn bạn đã sử dụng dịch vụ của chúng tôi.\n";
				this.msg += "Vào đây để đăng nhập ==> ";
				this.msg += "http://localhost:8080/MedicineDictionaryWebApp/Login.\n";
				session.remove("userGroup2");
				session.remove("userGroup1");
				session.remove("rep");
				session.remove("pharmacyList");
				session.remove("smdEmail");
				SendEmail();
				success = true;
			} else {
				addFieldError("wrongEmail", "Email của bạn không tồn tại trong hệ thống của chúng tôi!");
			}
		}
		return ret;
	}

	public void SendEmail() {
		try {
			Session session = Session.getDefaultInstance(properties,
					new javax.mail.Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(userName, password);
						}
					});

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(userName));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(this.receiption));
			message.setSubject(this.subject);
			message.setText(this.msg);
			Transport.send(message);
		} catch (Exception e) {
			ret = ERROR;
			e.printStackTrace();
		}
	}
	public boolean isValidUser(SMDUser smduser) {
		user = smdUserDAO.getSMDUser(this.receiption);
		if (user == null) {
			return false;
		} else {
			if (user.getEmail().equals(smduser.getEmail())) {
				this.smdUser = user;
				return true;
			} else {
				return false;
			}
		}
	}

	/**
	 * @return the receiption
	 */
	public String getReceiption() {
		return receiption;
	}

	/**
	 * @param receiption
	 *            the receiption to set
	 */
	public void setReceiption(String receiption) {
		this.receiption = receiption;
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
	 * @return the success
	 */
	public boolean getSuccess() {
		return success;
	}

	/**
	 * @param success
	 *            the success to set
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}

	/**
	 * @return the host
	 */
	public String getHost() {
		return host;
	}

	/**
	 * @param host the host to set
	 */
	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * @return the port
	 */
	public String getPort() {
		return port;
	}

	/**
	 * @param port the port to set
	 */
	public void setPort(String port) {
		this.port = port;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
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
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
}
