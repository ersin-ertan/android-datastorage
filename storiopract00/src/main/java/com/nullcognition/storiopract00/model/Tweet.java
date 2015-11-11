package com.nullcognition.storiopract00.model;
// ersin 11/11/15 Copyright (c) 2015+ All rights reserved.

import com.nullcognition.storiopract00.table.TweetTable;
import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteColumn;
import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteType;

@StorIOSQLiteType(table = TweetTable.TABLE_NAME)
public class Tweet{

	@StorIOSQLiteColumn(name = TweetTable.COLUMN_ID, key = true)
	Long id; // is there any reason as to why this is a class instead of a primitive
	// it could be because a null value is accepted in the static constructor

	@StorIOSQLiteColumn(name = TweetTable.COLUMN_AUTHOR)
	String author;

	@StorIOSQLiteColumn(name = TweetTable.COLUMN_CONTENT)
	String content;

	Tweet(){}

	private Tweet(Long id, String author, String content){
		this.id = id;
		this.author = author;
		this.content = content;
	}

	public static Tweet newTweet(Long id, String author, String content){
		return new Tweet(id, author, content);
	}

	public static Tweet newTweet(String author, String content){
		return new Tweet(null, author, content);
	}

	public Long getId(){ return id; }

	public String getAuthor(){ return author; }

	public String getContent(){ return content; }

	@Override
	public boolean equals(Object o){
		if(this == o){ return true; }
		if(o == null || getClass() != o.getClass()){ return false; }

		Tweet tweet = (Tweet) o;

		if(id != null ? !id.equals(tweet.id) : tweet.id != null){ return false; }
		if(!author.equals(tweet.author)){ return false; }
		return content.equals(tweet.content);
	}

	@Override
	public int hashCode(){
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + author.hashCode();
		result = 31 * result + content.hashCode();
		return result;
	}

	@Override
	public String toString(){
		return "Tweet{" +
				"id=" + id +
				", author='" + author + '\'' +
				", content='" + content + '\'' +
				'}';
	}

}
