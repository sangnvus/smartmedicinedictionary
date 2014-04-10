package com.med.dic.action;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;

import com.med.dic.constant.MedicineDictionaryConstants;
import com.med.dic.form.NewsForm;
import com.med.dic.model.SMDUser;
import com.med.dic.pagination.Pagination;
import com.med.dic.sercurity.CheckSMDUser;
import com.med.dic.utility.AuthenticationUtility;
import com.med.dic.utility.CheckVisitTime;
import com.med.dic.validate.Validator;
import com.opensymphony.xwork2.ActionContext;

public class NewsAction extends NewsForm{

	public String execute() {
		String actionName = ActionContext.getContext().getName();
		//CheckVisitTime.checkVisitor(this.session, this.servletRequest, this.visitTimeDAO);
		CheckSMDUser.checkSMDUser(session, servletRequest);
		SMDUser smdUser = new SMDUser();
		smdUser.setPreviousUser(this.previousUser);
		smdUser.setEmail(this.smdEmail);
		String user = AuthenticationUtility.checkSmdUser(this.session, smdUser);
		if(MedicineDictionaryConstants.userGroup1.equals(user) && smdUser.getPreviousUser()) {
			if("tim-kiem-tin-tuc-action".equals(actionName)) {
				resetPaging();
				return searchNewsByAdmin();
			} else if("tim-kiem-tin-tuc".equals(actionName)) {
				return searchNewsByAdmin();
			} else if("chi-tiet-ve-tin-tuc".equals(actionName)) {
				return newsDetail();
			} else if("xoa-tin-tuc".equals(actionName)) {
				return deleteNews();
			} else if("chinh-sua-tin-tuc".equals(actionName)) {
				return updateNews();
			} else if("huy-bo-chinh-sua".equals(actionName)) {
				return cancelUpdateNews();
			} else if("them-moi-tin-tuc".equals(actionName)) {
				return addNews();
			} else if("ban-sao-moi".equals(actionName)) {
				return copyNews();
			} else if("khoi-phuc-tin-tuc".equals(actionName)) {
				return restoreNews();
			}
			return SUCCESS;
		} else {
			return user;
		}
	}
	
	public void resetPaging() {
		pagination = new Pagination(10, 1);
		this.countPage = 0;
	}
	
	public String searchNewsByAdmin() {
		this.copyNews = false;
		try {
			if(!Validator.nullOrBlank(this.searchRegDate)) {
				if(this.searchRegDate.startsWith("0")) {
					//this.searchRegDate = this.searchRegDate.
				}
			}
			String regDate = this.searchRegDate.replace("-", "");
			regDate = this.searchRegDate.replace("/", "");
			news.setTitle(this.searchTitle);
			news.setSearchRedate(regDate);
			news.setContent(this.searchContent);
			news.setAuthor(this.searchAuthor);
			if(deleteFlagBoolean) {
				news.setDeteleFlag("Y");
			} else {
				news.setDeteleFlag("N");
			}
			if(countPage > 0) {
				pagination.setPreperties(this.countPage);
			} else {
				int count = newsDAO.countNews(news);
				this.countPage = count;
				pagination.setPreperties(count);
			}
			newsList = newsDAO.newsList(news, pagination);
			pagination.setPage_records(newsList.size());
			this.searched = true;
			this.detailed = false;
			this.actionSuccess = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String newsDetail() {
		this.copyNews = false;
		this.detailed = true;
		this.searched = false;
		this.actionSuccess = false;
		this.countPage = 0;
		newsDetail = newsDAO.news(this.newsDetailId);
		newsDetail.setRegDateStr(new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(newsDetail.getRegDate()));
		newsDetail.setModDateStr(new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(newsDetail.getModDate()));
		return SUCCESS;
	}
	
	public String copyNews() {
		this.copyNews = true;
		this.detailed = false;
		this.searched = false;
		this.actionSuccess = false;
		newsDetail = newsDAO.news(this.newsDetailId);
		newsDetail.setRegDateStr(new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(newsDetail.getRegDate()));
		newsDetail.setModDateStr(new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(newsDetail.getModDate()));
		return SUCCESS;
	}
	
	public String restoreNews() {
		this.copyNews = false;
		this.detailed = false;
		this.searched = false;
		this.countPage = 0;
		news = newsDAO.news(this.newsDetailId);
		news.setDeteleFlag("N");
		newsDAO.updateNews(news);
		this.actionSuccess = true;
		this.message = "Bạn đã khôi phục tin tức thành công!";
		return SUCCESS;
	}
	
	public String deleteNews() {
		this.detailed = false;
		this.searched = false;
		this.copyNews = false;
		this.countPage = 0;
		news = newsDAO.news(this.newsDetailId);
		news.setDeteleFlag("Y");
		newsDAO.deleteNews(news);
		this.actionSuccess = true;
		this.message = "Bạn đã xóa tin tức thành công!";
		return SUCCESS;
	}
	
	public String updateNews() {
		this.detailed = false;
		this.searched = false;
		this.copyNews = false;
		this.countPage = 0;
		news = newsDAO.news(this.newsDetailId);
		news.setModDate(new Date());
		String modUser = (String)session.get("regUser");
		if(this.imgPath != null) {
			String timestamp = new SimpleDateFormat("yyyyMMddHHmmss")
			.format(new Date());
			String path = servletRequest.getSession().getServletContext()
					.getRealPath("/");
			try {
				String filePathImage = path + "/News_Images/";
				File image = imgPath;
				imgPathFileName = timestamp + imgPathFileName;
				File destFile = new File(filePathImage
						+ imgPathFileName);
				news.setImgPath(imgPathFileName);
				FileUtils.copyFile(image, destFile);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		news.setModUser(modUser);
		news.setContent(this.content);
		news.setTitle(this.title);
		news.setAuthor(this.author);
		news.setSubContent(this.subContent);
		newsDAO.updateNews(news);
		this.actionSuccess = true;
		this.message = "Bạn đã cập nhật tin tức thành công!";
		return SUCCESS;
	}
	
	public String addNews() {
		this.detailed = false;
		this.searched = false;
		this.copyNews = false;
		this.countPage = 0;
		news.setRegDate(new Date());
		news.setModDate(new Date());
		news.setDeteleFlag("N");
		String regUser = (String)session.get("regUser");
		news.setRegUser(regUser);
		news.setModUser(regUser);
		news.setTitle(this.title);
		news.setContent(this.content);
		news.setAuthor(this.author);
		news.setSubContent(this.subContent);
		if(!Validator.nullOrBlank(imgDesc)) {
			news.setImgDesc(this.imgDesc);
		}
		this.content.replace("\r", "");
		String[] contentList = this.content.split("\n");

		if(this.imgPath != null) {
			String timestamp = new SimpleDateFormat("yyyyMMddHHmmss")
			.format(new Date());
			String path = servletRequest.getSession().getServletContext()
					.getRealPath("/");
			try {
				String filePathImage = path + "/News_Images/";
				File image = imgPath;
				imgPathFileName = timestamp + imgPathFileName;
				File destFile = new File(filePathImage
						+ imgPathFileName);
				news.setImgPath(imgPathFileName);
				FileUtils.copyFile(image, destFile);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		newsDetailId = newsDAO.addNews(news);
		for (String string : contentList) {
			if(!Validator.nullOrBlank(string)) {
				news.arrayContent.add(string);
			}
		}
		news.setRegDateStr(new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(news.getRegDate()));
		news.setModDateStr(new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(news.getModDate()));
		this.actionSuccess = true;
		this.message = "Bạn đã thêm mới tin tức thành công!";
		return SUCCESS;
	}
	
	public String cancelUpdateNews() {
		this.searched = false;
		this.detailed = false;
		this.actionSuccess = false;
		this.countPage = 0;
		return SUCCESS;
	}
}
