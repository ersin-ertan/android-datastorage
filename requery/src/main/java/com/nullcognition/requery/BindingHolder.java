package com.nullcognition.requery;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

/**
 * Created by mms on 11/6/16.
 */

public class BindingHolder<B extends ViewDataBinding> extends RecyclerView.ViewHolder {

  protected final B binding;

  public BindingHolder(B binding) {
    super(binding.getRoot());
    this.binding = binding;
  }
}
