package com.med.dic.action;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.apache.commons.io.FileUtils;

import com.med.dic.dao.RepresentativeDAO;
import com.med.dic.dao.SMDUserDAO;
import com.med.dic.model.SMDUser;
import com.med.dic.sercurity.EncryptPassword;
import com.med.dic.utility.EmailUtility;
import com.med.dic.validate.Validator;

public class SendEmailAction {

	// SMTP properties - fetched from struts.xml
		private String host;
		private String port;
		private String userName;
		private String password;

		// file upload properties - fetched by interceptor fileUpload
		private File fileUpload;
		private String fileUploadFileName;
		private String fileUploadContentType;

		// e-mail fields - fetched from EmailForm.jsp
		private String recipient;
		private String subject;
		private String message;
		private SMDUser smdUser;
		private SMDUserDAO smdUserDAO;
		private RepresentativeDAO repDAO;
		private String msg;
		private SMDUser user = new SMDUser();
		public boolean success = false;
		
		public String doSendEmail() throws IOException, AddressException, MessagingException {
			if (!Validator.nullOrBlank(this.recipient)) {
//				smdUser = new SMDUser();
//				smdUser.setEmail(this.recipient);
//				if (isValidUser(smdUser)) {
//					String timestamp = new SimpleDateFormat("dd/MM/yyyy")
//							.format(new Date());
//					Random randomGenerator = new Random();
//					int randomInt = randomGenerator.nextInt(1000000000);
//					smdUser.setPassword(EncryptPassword.md5(String
//							.valueOf(randomInt)));
////					smdUserDAO.updateSMDUser(smdUser);
//					String repName = repDAO.getRepName(this.recipient);
					this.subject = "[SMD - Smart Medicine Dictionary] Reset password";
//					this.msg = "Xin chào bạn " + repName + ",";
					this.msg += "\n";
//					this.msg += "Bạn đã gửi yêu cầu cấp lại mật khẩu vào ngày "
//							+ timestamp + ".\n";
//					this.msg += "Mật khẩu mới của bạn là " + randomInt + ".\n";
					this.msg += "Bạn nên đăng nhập để thay đổi mật khẩu của mình.\n";
					this.msg += "Cám ơn bạn đã sử dụng dịch vụ của chúng tôi.\n";
//				}
			}
			File saveFile = null;
			/*String tempPath = System.getProperty("java.io.tmpdir");
			saveFile = new File(tempPath + File.separator + fileUploadFileName);
			FileUtils.copyFile(fileUpload, saveFile);*/

			EmailUtility.sendEmail(host, port, userName, password, recipient,
					subject, msg, saveFile);

			/*if (saveFile != null) {
				saveFile.delete();
			}*/
			
			return "success";
		}

		public boolean isValidUser(SMDUser smduser) {
			user = smdUserDAO.getSMDUser(this.recipient);
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
		
		public String getHost() {
			return host;
		}

		public void setHost(String host) {
			this.host = host;
		}

		public String getPort() {
			return port;
		}

		public void setPort(String port) {
			this.port = port;
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}


		public File getFileUpload() {
			return fileUpload;
		}

		public void setFileUpload(File fileUpload) {
			this.fileUpload = fileUpload;
		}

		public String getFileUploadFileName() {
			return fileUploadFileName;
		}

		public void setFileUploadFileName(String fileUploadFileName) {
			this.fileUploadFileName = fileUploadFileName;
		}

		public String getFileUploadContentType() {
			return fileUploadContentType;
		}

		public void setFileUploadContentType(String fileUploadContentType) {
			this.fileUploadContentType = fileUploadContentType;
		}

		public String getRecipient() {
			return recipient;
		}

		public void setRecipient(String recipient) {
			this.recipient = recipient;
		}

		public String getSubject() {
			return subject;
		}

		public void setSubject(String subject) {
			this.subject = subject;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		/**
		 * @return the smdUser
		 */
		public SMDUser getSmdUser() {
			return smdUser;
		}

		/**
		 * @param smdUser the smdUser to set
		 */
		public void setSmdUser(SMDUser smdUser) {
			this.smdUser = smdUser;
		}

		/**
		 * @return the smdUserDAO
		 */
		public SMDUserDAO getSmdUserDAO() {
			return smdUserDAO;
		}

		/**
		 * @param smdUserDAO the smdUserDAO to set
		 */
		public void setSmdUserDAO(SMDUserDAO smdUserDAO) {
			this.smdUserDAO = smdUserDAO;
		}

		/**
		 * @return the repDAO
		 */
		public RepresentativeDAO getRepDAO() {
			return repDAO;
		}

		/**
		 * @param repDAO the repDAO to set
		 */
		public void setRepDAO(RepresentativeDAO repDAO) {
			this.repDAO = repDAO;
		}

		/**
		 * @return the success
		 */
		public boolean getSuccess() {
			return success;
		}

		/**
		 * @param success the success to set
		 */
		public void setSuccess(boolean success) {
			this.success = success;
		}
}
