package com.med.dic.action;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.med.dic.constant.MedicineDictionaryConstants;
import com.med.dic.form.DisplayNewsForm;
import com.med.dic.model.Advertise;
import com.med.dic.utility.CheckVisitTime;
import com.opensymphony.xwork2.ActionContext;

public class DisplayNewsAction extends DisplayNewsForm{
	
	public static String[] engDay = {MedicineDictionaryConstants.MONDAY, MedicineDictionaryConstants.TUESDAY, 
		MedicineDictionaryConstants.WEDNESDAY, MedicineDictionaryConstants.THURSDAY, 
		MedicineDictionaryConstants.FRIDAY, MedicineDictionaryConstants.SATURDAY,
		MedicineDictionaryConstants.SUNDAY};
	public static String[] vietDay = {MedicineDictionaryConstants.T2, MedicineDictionaryConstants.T3,
		MedicineDictionaryConstants.T4, MedicineDictionaryConstants.T5,
		MedicineDictionaryConstants.T6, MedicineDictionaryConstants.T7,
		MedicineDictionaryConstants.CN};
	public String execute() {
		String actionName = ActionContext.getContext().getName();
		CheckVisitTime.checkVisitor(session, servletRequest, visitTimeDAO);
		if("news".equals(actionName)) {
			return displayNews();
		} else if("tin-tuc-chi-tiet".equals(actionName)) {
			return newsDetailed();
		}
		return SUCCESS;
	}
	
	
	public String displayNews() {
		topNews  = newsDAO.selectTop();
		newsDAO.displayNewsList(topNews.getId(), this.topNewsList, this.otherNewsList);
		/*if(topNewsList.size() > 0) {
			topNews = topNewsList.get(0);
			topNewsList.remove(0);
		}*/
		if(topNews != null) {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
			Date date = new Date();
			Calendar calendar = Calendar.getInstance();
			topNews.setRegDateStr(simpleDateFormat.format(date));
			calendar.setTime(date);
			this.newsDate = new DateFormatSymbols().getWeekdays()[calendar.get(Calendar.DAY_OF_WEEK)];
			for (int i = 0; i < engDay.length; i++) {
				if(newsDate.toUpperCase().equals(engDay[i])) {
					newsDate = vietDay[i];
					break;
				}
			}
			
			/// display advertising
			List<Advertise> adList = new ArrayList<>();
			adList = advertiseDAO.advertiseList();
			count = 0;
			for (Advertise advertise : adList) {
				if(count < 8) {
					if(count < 4) {
						adLineOne.add(advertise);
					} else {
						adLineTwo.add(advertise);
					}
				}
				count++;
			}
		}
		return SUCCESS;
	}
	
	public String newsDetailed() {
		news = newsDAO.news(this.newsId);
		String content = news.getContent().replace("\r", "");
		String[] arrayContent = content.split("\n");
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		Date date = news.getRegDate();
		news.setRegDateStr(simpleDateFormat.format(date));
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		String dayName = new DateFormatSymbols().getWeekdays()[calendar.get(Calendar.DAY_OF_WEEK)];
		for (int i = 0; i < engDay.length; i++) {
			if(dayName.toUpperCase().equals(engDay[i])) {
				dayName = vietDay[i];
				break;
			}
		}
		news.setDayName(dayName);
		for (String string : arrayContent) {
			news.arrayContent.add(string);
		}
		news.setSearchedTime(news.getSearchedTime() + 1);
		newsDAO.updateNews(news);
		newsDAO.displayNewsListInDetail(this.otherNewsList, this.newsId);
		
		
		/// display advertising
		List<Advertise> adList = new ArrayList<>();
		adList = advertiseDAO.advertiseList();
		count = 0;
		for (Advertise advertise : adList) {
			if(count < 8) {
				if(count < 4) {
					adLineOne.add(advertise);
				} else {
					adLineTwo.add(advertise);
				}
			}
			count++;
		}
		return SUCCESS;
	}
}
