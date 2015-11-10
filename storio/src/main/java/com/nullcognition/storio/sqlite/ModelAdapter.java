package com.nullcognition.storio.sqlite;
// ersin 10/11/15 Copyright (c) 2015+ All rights reserved.


import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nullcognition.storio.R;
import com.nullcognition.storio.sqlite.entity.Model;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ModelAdapter extends RecyclerView.Adapter<ModelAdapter.ViewHolder>{

	private List<Model> models;

	public void setModels(@Nullable List<Model> models){
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
		final Model tweet = models.get(position);

		holder.authorTextView.setText("@" + tweet.author());
		holder.contentTextView.setText(tweet.content());
	}

	static class ViewHolder extends RecyclerView.ViewHolder{

		@Bind(R.id.list_item_author)
		TextView authorTextView;

		@Bind(R.id.list_item_content)
		TextView contentTextView;

		public ViewHolder(@NonNull View itemView){
			super(itemView);
			ButterKnife.bind(this, itemView);
		}
	}
}

