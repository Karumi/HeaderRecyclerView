package com.karumi.headerrecyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import java.util.List;

public class HeaderRecyclerViewAdapterBuilder<VH extends RecyclerView.ViewHolder, H, T, F> {

  HeaderRecyclerViewAdapter<VH, H, T, F> adapter = new HeaderRecyclerViewAdapter<VH, H, T, F>() {

    @Override protected VH onCreateHeaderViewHolder(ViewGroup parent, int viewType) {
      return null;
    }

    @Override protected VH onCreateItemViewHolder(ViewGroup parent, int viewType) {
      return null;
    }

    @Override protected VH onCreateFooterViewHolder(ViewGroup parent, int viewType) {
      return null;
    }

    @Override protected void onBindHeaderViewHolder(VH holder, int position) {

    }

    @Override protected void onBindItemViewHolder(VH holder, int position) {

    }

    @Override protected void onBindFooterViewHolder(VH holder, int position) {

    }
  };

  public HeaderRecyclerViewAdapterBuilder withHeader(H header) {
    adapter.setHeader(header);
    return this;
  }

  public HeaderRecyclerViewAdapterBuilder withItems(List<T> items) {
    adapter.setItems(items);
    return this;
  }

  public HeaderRecyclerViewAdapterBuilder withFooter(F footer) {
    adapter.setFooter(footer);
    return this;
  }

  public HeaderRecyclerViewAdapter<VH, H, T, F> build() {
    return adapter;
  }
}
