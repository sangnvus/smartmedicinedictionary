package com.med.dic.form;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.med.dic.base.action.BaseAction;
import com.med.dic.dao.NewsDAO;
import com.med.dic.model.News;
import com.med.dic.pagination.Pagination;

public class NewsForm extends BaseAction{
	
	public List<News> newsList = new ArrayList<>();
	public NewsDAO newsDAO;
	public News news = new News();
	public String searchTitle = "";
	public String searchRegDate = "";
	public String searchAuthor = "";
	public String searchContent = "";
	public String imgDesc = "";
	
	public boolean searched = false;
	public boolean detailed = false;
	public boolean actionSuccess = false;
	public boolean copyNews = false;
	public boolean deleteFlagBoolean = false;
	public String page;
	
	// detail
	public int newsDetailId;
	public News newsDetail = new News();
	public String message;
	
	// ADD
	public File imgPath;
	public String imgPathFileName;
	public String imgPathContentType;
	public String author;
	public String title;
	public String subContent;
	public String content;
	
	public Pagination pagination = new Pagination(10, 1);
	public int countPage;

	/**
	 * @return the newsDAO
	 */
	public NewsDAO getNewsDAO() {
		return newsDAO;
	}

	/**
	 * @param newsDAO the newsDAO to set
	 */
	public void setNewsDAO(NewsDAO newsDAO) {
		this.newsDAO = newsDAO;
	}

	/**
	 * @return the searchTitle
	 */
	public String getSearchTitle() {
		return searchTitle;
	}

	/**
	 * @param searchTitle the searchTitle to set
	 */
	public void setSearchTitle(String searchTitle) {
		this.searchTitle = searchTitle;
	}

	/**
	 * @return the searchRegDate
	 */
	public String getSearchRegDate() {
		return searchRegDate;
	}

	/**
	 * @param searchRegDate the searchRegDate to set
	 */
	public void setSearchRegDate(String searchRegDate) {
		this.searchRegDate = searchRegDate;
	}

	/**
	 * @return the news
	 */
	public News getNews() {
		return news;
	}

	/**
	 * @param news the news to set
	 */
	public void setNews(News news) {
		this.news = news;
	}

	/**
	 * @return the newsList
	 */
	public List<News> getNewsList() {
		return newsList;
	}

	/**
	 * @param newsList the newsList to set
	 */
	public void setNewsList(List<News> newsList) {
		this.newsList = newsList;
	}

	/**
	 * @return the pagination
	 */
	public Pagination getPagination() {
		return pagination;
	}

	/**
	 * @param pagination the pagination to set
	 */
	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	/**
	 * @return the searchAuthor
	 */
	public String getSearchAuthor() {
		return searchAuthor;
	}

	/**
	 * @param searchAuthor the searchAuthor to set
	 */
	public void setSearchAuthor(String searchAuthor) {
		this.searchAuthor = searchAuthor;
	}

	/**
	 * @return the searched
	 */
	public boolean getSearched() {
		return searched;
	}

	/**
	 * @param searched the searched to set
	 */
	public void setSearched(boolean searched) {
		this.searched = searched;
	}

	/**
	 * @return the detailed
	 */
	public boolean getDetailed() {
		return detailed;
	}

	/**
	 * @param detailed the detailed to set
	 */
	public void setDetailed(boolean detailed) {
		this.detailed = detailed;
	}

	/**
	 * @return the searchContent
	 */
	public String getSearchContent() {
		return searchContent;
	}

	/**
	 * @param searchContent the searchContent to set
	 */
	public void setSearchContent(String searchContent) {
		this.searchContent = searchContent;
	}

	/**
	 * @return the page
	 */
	public String getPage() {
		return page;
	}

	/**
	 * @param page the page to set
	 */
	public void setPage(String page) {
		this.page = page;
	}

	/**
	 * @return the newDetailId
	 */
	public int getNewsDetailId() {
		return newsDetailId;
	}

	/**
	 * @param newDetailId the newDetailId to set
	 */
	public void setNewsDetailId(int newsDetailId) {
		this.newsDetailId = newsDetailId;
	}

	/**
	 * @return the newsDetail
	 */
	public News getNewsDetail() {
		return newsDetail;
	}

	/**
	 * @return the actionSuccess
	 */
	public boolean getActionSuccess() {
		return actionSuccess;
	}

	/**
	 * @param actionSuccess the actionSuccess to set
	 */
	public void setActionSuccess(boolean actionSuccess) {
		this.actionSuccess = actionSuccess;
	}

	/**
	 * @param newsDetail the newsDetail to set
	 */
	public void setNewsDetail(News newsDetail) {
		this.newsDetail = newsDetail;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the imgPath
	 */
	public File getImgPath() {
		return imgPath;
	}

	/**
	 * @param imgPath the imgPath to set
	 */
	public void setImgPath(File imgPath) {
		this.imgPath = imgPath;
	}

	/**
	 * @return the imgPathFileName
	 */
	public String getImgPathFileName() {
		return imgPathFileName;
	}

	/**
	 * @param imgPathFileName the imgPathFileName to set
	 */
	public void setImgPathFileName(String imgPathFileName) {
		this.imgPathFileName = imgPathFileName;
	}

	/**
	 * @return the imgPathContentType
	 */
	public String getImgPathContentType() {
		return imgPathContentType;
	}

	/**
	 * @param imgPathContentType the imgPathContentType to set
	 */
	public void setImgPathContentType(String imgPathContentType) {
		this.imgPathContentType = imgPathContentType;
	}

	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * @param author the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the subContent
	 */
	public String getSubContent() {
		return subContent;
	}

	/**
	 * @param subContent the subContent to set
	 */
	public void setSubContent(String subContent) {
		this.subContent = subContent;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the copyNews
	 */
	public boolean getCopyNews() {
		return copyNews;
	}

	/**
	 * @param copyNews the copyNews to set
	 */
	public void setCopyNews(boolean copyNews) {
		this.copyNews = copyNews;
	}

	/**
	 * @return the deleteFlagBoolean
	 */
	public boolean getDeleteFlagBoolean() {
		return deleteFlagBoolean;
	}

	/**
	 * @param deleteFlagBoolean the deleteFlagBoolean to set
	 */
	public void setDeleteFlagBoolean(boolean deleteFlagBoolean) {
		this.deleteFlagBoolean = deleteFlagBoolean;
	}

	/**
	 * @return the countPage
	 */
	public int getCountPage() {
		return countPage;
	}

	/**
	 * @param countPage the countPage to set
	 */
	public void setCountPage(int countPage) {
		this.countPage = countPage;
	}

	/**
	 * @return the imgDesc
	 */
	public String getImgDesc() {
		return imgDesc;
	}

	/**
	 * @param imgDesc the imgDesc to set
	 */
	public void setImgDesc(String imgDesc) {
		this.imgDesc = imgDesc;
	}

}
