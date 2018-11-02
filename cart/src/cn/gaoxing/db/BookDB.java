package cn.gaoxing.db;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import cn.gaoxing.domain.Book;

public class BookDB {
	private static Map<String,Book> books=new LinkedHashMap<String,Book>();
	static {
		books.put("1", new Book("1", "javaweb开发",50.5));
		books.put("2", new Book("2", "数据结构",40.5));
		books.put("3", new Book("3", "计算机组成原理",60.5));
		books.put("4", new Book("4", "c语言开发",35.5));
		books.put("5", new Book("5", "c#开发",45.5));
	}
	public static Collection<Book> getAll(){
		return books.values();
	}
	public static Book getBook(String id){
		return books.get(id);
	}
}
