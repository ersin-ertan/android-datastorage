package com.nullcognition.storiopract00;
// ersin 12/11/15 Copyright (c) 2015+ All rights reserved.


import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nullcognition.storiopract00.model.Tweet;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TweetAdapter extends RecyclerView.Adapter<TweetAdapter.ViewHolder>{

	private List<Tweet> models;

	public void setTweets(@Nullable List<Tweet> models){
		this.models = models;
		notifyDataSetChanged();
	}

	@Override public int getItemCount(){
		return models == null ? 0 : models.size();
	}

	@Override public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
		return new ViewHolder(LayoutInflater.from(parent.getContext())
		                                    .inflate(R.layout.list_item, parent, false));
	}

	@SuppressLint("SetTextI18n")
	@Override public void onBindViewHolder(@NonNull ViewHolder holder, int position){
		final Tweet tweet = models.get(position);

		holder.contentTextView.setText(tweet.getContent());
		holder.authorTextView.setText(tweet.getAuthor());
	}

	static class ViewHolder extends RecyclerView.ViewHolder{

		@Bind(R.id.list_item_content)
		TextView contentTextView;

		@Bind(R.id.list_item_author)
		TextView authorTextView;

		public ViewHolder(@NonNull View itemView){
			super(itemView);
			ButterKnife.bind(this, itemView);
		}
	}
}
