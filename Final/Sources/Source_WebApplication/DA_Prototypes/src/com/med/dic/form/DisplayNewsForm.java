package com.med.dic.form;

import java.util.ArrayList;
import java.util.List;

import com.med.dic.base.action.BaseAction;
import com.med.dic.dao.AdvertiseDAO;
import com.med.dic.dao.NewsDAO;
import com.med.dic.model.Advertise;
import com.med.dic.model.News;

public class DisplayNewsForm extends BaseAction{

	public NewsDAO newsDAO;
	public News news = new News();
	public News topNews = new News();
	public List<News> topNewsList = new ArrayList<>();
	public List<News> otherNewsList = new ArrayList<>();
	public int newsId;
	public String newsDate;
	
	public int count;
	public AdvertiseDAO advertiseDAO;
	public List<Advertise> adLineOne = new ArrayList<>();
	public List<Advertise> adLineTwo = new ArrayList<>();
	
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
	 * @return the topNews
	 */
	public News getTopNews() {
		return topNews;
	}
	/**
	 * @param topNews the topNews to set
	 */
	public void setTopNews(News topNews) {
		this.topNews = topNews;
	}
	/**
	 * @return the topNewsList
	 */
	public List<News> getTopNewsList() {
		return topNewsList;
	}
	/**
	 * @param topNewsList the topNewsList to set
	 */
	public void setTopNewsList(List<News> topNewsList) {
		this.topNewsList = topNewsList;
	}
	/**
	 * @return the otherNewsList
	 */
	public List<News> getOtherNewsList() {
		return otherNewsList;
	}
	/**
	 * @param otherNewsList the otherNewsList to set
	 */
	public void setOtherNewsList(List<News> otherNewsList) {
		this.otherNewsList = otherNewsList;
	}
	/**
	 * @return the newsId
	 */
	public int getNewsId() {
		return newsId;
	}
	/**
	 * @param newsId the newsId to set
	 */
	public void setNewsId(int newsId) {
		this.newsId = newsId;
	}
	/**
	 * @return the newsDate
	 */
	public String getNewsDate() {
		return newsDate;
	}
	/**
	 * @param newsDate the newsDate to set
	 */
	public void setNewsDate(String newsDate) {
		this.newsDate = newsDate;
	}
	/**
	 * @return the advertiseDAO
	 */
	public AdvertiseDAO getAdvertiseDAO() {
		return advertiseDAO;
	}
	/**
	 * @param advertiseDAO the advertiseDAO to set
	 */
	public void setAdvertiseDAO(AdvertiseDAO advertiseDAO) {
		this.advertiseDAO = advertiseDAO;
	}
	/**
	 * @return the adLineOne
	 */
	public List<Advertise> getAdLineOne() {
		return adLineOne;
	}
	/**
	 * @param adLineOne the adLineOne to set
	 */
	public void setAdLineOne(List<Advertise> adLineOne) {
		this.adLineOne = adLineOne;
	}
	/**
	 * @return the adLineTwo
	 */
	public List<Advertise> getAdLineTwo() {
		return adLineTwo;
	}
	/**
	 * @param adLineTwo the adLineTwo to set
	 */
	public void setAdLineTwo(List<Advertise> adLineTwo) {
		this.adLineTwo = adLineTwo;
	}
	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}
	/**
	 * @param count the count to set
	 */
	public void setCount(int count) {
		this.count = count;
	}
}
