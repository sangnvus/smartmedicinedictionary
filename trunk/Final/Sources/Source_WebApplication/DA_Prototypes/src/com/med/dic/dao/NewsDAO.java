package com.med.dic.dao;

import java.util.List;

import com.med.dic.model.News;
import com.med.dic.pagination.Pagination;

public interface NewsDAO {

	List<News> newsList(News news, Pagination pagination);
	int countNews(News news);
	int addNews(News news);
	void updateNews(News news);
	void deleteNews(News news);
	News news(int Id);
	News selectTop();
	void displayNewsList(int newsId, List<News> topNewsList, List<News> otherNewsList);
	void displayNewsListInDetail(List<News> otherNewsList, int newsId);
}
