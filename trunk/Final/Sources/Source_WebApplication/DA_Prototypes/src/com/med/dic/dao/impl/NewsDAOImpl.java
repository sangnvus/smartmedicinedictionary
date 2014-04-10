package com.med.dic.dao.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.med.dic.base.dao.BaseDAO;
import com.med.dic.constant.DBNameCoreConstants;
import com.med.dic.dao.NewsDAO;
import com.med.dic.model.News;
import com.med.dic.pagination.Pagination;
import com.med.dic.validate.Validator;

public class NewsDAOImpl extends BaseDAO implements NewsDAO{

	public static final String SQL_ADMIN_SEARCH = " SELECT " + DBNameCoreConstants.ID + ", "
												+ DBNameCoreConstants.TITLE + ", "
												+ DBNameCoreConstants.CONTENT + ", "
												+ DBNameCoreConstants.AUTHOR + ", "
												+ DBNameCoreConstants.IMG_PATH +", "
												+ DBNameCoreConstants.REG_DATE + ", "
												+ DBNameCoreConstants.REG_USER + ", "
												+ " DATE_FORMAT( " + DBNameCoreConstants.REG_DATE + ", '%d%m%Y' ) AS DATE" + ", "
												+ DBNameCoreConstants.SUB_CONTENT
												+ " FROM " + DBNameCoreConstants.NEWS
												+ " WHERE "
												+ DBNameCoreConstants.TITLE + " like :title "
												+ " AND " + DBNameCoreConstants.CONTENT + " like :content "
												+ " AND " + DBNameCoreConstants.AUTHOR + " like :author "
												+ " AND " + DBNameCoreConstants.DELETE_FLAG + " = :deteleFlag ";
	public static final String SQL_DISPLAY_NEWS = " SELECT " + DBNameCoreConstants.ID + ", "
												+ DBNameCoreConstants.TITLE + ", "
												+ DBNameCoreConstants.CONTENT + ", "
												+ DBNameCoreConstants.AUTHOR + ", "
												+ DBNameCoreConstants.IMG_PATH +", "
												+ DBNameCoreConstants.REG_DATE + ", "
												+ DBNameCoreConstants.REG_USER + ", "
												+ DBNameCoreConstants.SUB_CONTENT + ", "
												+ DBNameCoreConstants.SEARCHED_TIME
												+ " FROM " + DBNameCoreConstants.NEWS
												+ " WHERE " + DBNameCoreConstants.DELETE_FLAG + " = 'N' ";
												
	
	@Override
	public List<News> newsList(News news, Pagination pagination) {
		List<News> newsList = new ArrayList<>();
		session = sessionFactory.openSession();
		String queryStr = SQL_ADMIN_SEARCH;
		if(!Validator.nullOrBlank(news.getSearchRedate())) {
			queryStr += " AND " + "DATE_FORMAT( " + DBNameCoreConstants.REG_DATE + ", '%d%m%Y' ) = :regDate ";
		}
		queryStr = pagination.getSQLQuery(queryStr, false);
		Query query = session.createSQLQuery(queryStr);
		if(!Validator.nullOrBlank(news.getSearchRedate())) {
			query.setParameter("regDate", news.getSearchRedate());
		}
		query.setParameter("title", "%" + news.getTitle() + "%");
		query.setParameter("content", "%" + news.getContent() + "%");
		query.setParameter("author","%" + news.getAuthor() + "%");
		query.setParameter("deteleFlag", news.getDeteleFlag());
		List result = query.list();
		for (Object obj : result) {
			Object[] element = (Object[])obj;
			News news1 = new News();
			news1.setId((int)element[0]);
			news1.setTitle((String)element[1]);
			news1.setContent((String)element[2]);
			news1.setAuthor((String)element[3]);
			news1.setImgPath((String)element[4]);
			try {
				Timestamp timeStmp = ((Timestamp)element[5]);
				Date date = new Date(timeStmp.getTime());
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
				news1.setSearchRedate(simpleDateFormat.format(date));
			} catch (Exception e) {
				e.printStackTrace();
			}
			news1.setRegUser((String)element[6]);
			news1.setSubContent((String)element[8]);
			newsList.add(news1);
		}
		return newsList;
	}

	@Override
	public int countNews(News news) {
		int count = 0;
		List<News> newsList = new ArrayList<>();
		session = sessionFactory.openSession();
		String queryStr = SQL_ADMIN_SEARCH; 
		if(!Validator.nullOrBlank(news.getSearchRedate())) {
			queryStr += " AND " + "DATE_FORMAT( " + DBNameCoreConstants.REG_DATE + ", '%d%m%Y' ) = :regDate ";
		}
		Query query = session.createSQLQuery(queryStr);
		if(!Validator.nullOrBlank(news.getSearchRedate())) {
			query.setParameter("regDate", news.getSearchRedate());
		}
		query.setParameter("title", "%" + news.getTitle() + "%");
		query.setParameter("content","%" + news.getContent() + "%");
		query.setParameter("author","%" + news.getAuthor() + "%");
		query.setParameter("deteleFlag", news.getDeteleFlag());
		newsList = query.list();
		count = newsList.size();
		return count;
	}

	@Override
	public int addNews(News news) {
		session = sessionFactory.openSession();
		Transaction tx = null;
		tx = session.beginTransaction();
		int newsId = (Integer)session.save(news);
		tx.commit();
		return newsId;
		
	}

	@Override
	public void updateNews(News news) {
		session = sessionFactory.openSession();
		Transaction tx = null;
		tx = session.beginTransaction();
		session.update(news);
		tx.commit();
		
	}

	@Override
	public News news(int Id) {
		session = sessionFactory.openSession();
		return (News)session.get(News.class, Id);
	}

	@Override
	public void deleteNews(News news) {
		session = sessionFactory.openSession();
		Transaction tx = null;
		tx = session.beginTransaction();
		session.update(news);
		tx.commit();
		
	}

	@Override
	public void displayNewsList(int newsId, List<News> topNewsList, List<News> otherNewsList) {
		int count = 0;
		session = sessionFactory.openSession();
		String queryStr = SQL_DISPLAY_NEWS;
		queryStr += " AND " + DBNameCoreConstants.ID + " != :newsId" + " ORDER BY " + DBNameCoreConstants.REG_DATE + " DESC LIMIT 0, 13";
		Query query = session.createSQLQuery(queryStr);
		query.setParameter("newsId", newsId);
		List result = query.list();
		for (Object obj : result) {
			Object[] element = (Object[])obj;
			News news1 = new News();
			news1.setId((int)element[0]);
			news1.setTitle((String)element[1]);
			news1.setContent((String)element[2]);
			news1.setAuthor((String)element[3]);
			news1.setImgPath((String)element[4]);
			try {
				Timestamp timeStmp = ((Timestamp)element[5]);
				Date date = new Date(timeStmp.getTime());
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
				news1.setSearchRedate(simpleDateFormat.format(date));
			} catch (Exception e) {
				e.printStackTrace();
			}
			news1.setRegUser((String)element[6]);
			news1.setSubContent((String)element[7]);
			news1.setSearchedTime((int)element[8]);
			if(count < 8 && count >= 0 ) {
				topNewsList.add(news1);
			} else if(count < 13 && count >= 8) {
				otherNewsList.add(news1);
			} else {
				break;
			}
			count++;
		}
	}
	
	@Override
	public News selectTop() {
		int count = 0;
		session = sessionFactory.openSession();
		String queryStr = SQL_DISPLAY_NEWS;
		queryStr += " ORDER BY " + DBNameCoreConstants.SEARCHED_TIME + " DESC "
				+ ", " + DBNameCoreConstants.REG_DATE + " DESC LIMIT 0, 1";
		Query query = session.createSQLQuery(queryStr);
		News news = new News();
		List result = query.list();
		for (Object obj : result) {
			if (count == 0) {
				Object[] element = (Object[]) obj;
				news.setId((int) element[0]);
				news.setTitle((String) element[1]);
				news.setContent((String) element[2]);
				news.setAuthor((String) element[3]);
				news.setImgPath((String) element[4]);
				try {
					Timestamp timeStmp = ((Timestamp) element[5]);
					Date date = new Date(timeStmp.getTime());
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
							"dd/MM/yyyy");
					news.setSearchRedate(simpleDateFormat.format(date));
				} catch (Exception e) {
					e.printStackTrace();
				}
				news.setRegUser((String) element[6]);
				news.setSubContent((String) element[7]);
				news.setSearchedTime((int) element[8]);
			}
		}
		return news;
	}

	@Override
	public void displayNewsListInDetail(List<News> otherNewsList, int newsId) {
		int count = 0;
		session = sessionFactory.openSession();
		String queryStr = SQL_DISPLAY_NEWS;
		queryStr += " AND " + DBNameCoreConstants.ID + " != :newsId" + " ORDER BY " + DBNameCoreConstants.REG_DATE + " DESC LIMIT 0, 12";
		Query query = session.createSQLQuery(queryStr);
		query.setParameter("newsId", newsId);
		List result = query.list();
		for (Object obj : result) {
			Object[] element = (Object[])obj;
			News news1 = new News();
			news1.setId((int)element[0]);
			news1.setTitle((String)element[1]);
			news1.setContent((String)element[2]);
			news1.setAuthor((String)element[3]);
			news1.setImgPath((String)element[4]);
			try {
				Timestamp timeStmp = ((Timestamp)element[5]);
				Date date = new Date(timeStmp.getTime());
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
				news1.setSearchRedate(simpleDateFormat.format(date));
			} catch (Exception e) {
				e.printStackTrace();
			}
			news1.setRegUser((String)element[6]);
			news1.setSubContent((String)element[7]);
			news1.setSearchedTime((int)element[8]);
			if(count < 12 && count >= 0) {
				otherNewsList.add(news1);
			} else {
				break;
			}
			count++;
		}
		
	}

}
