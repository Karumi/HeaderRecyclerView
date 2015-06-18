package com.karumi.headerrecyclerview;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

public class HeaderSpanSizeLookupBuilder {

  HeaderRecyclerViewAdapter adapter = new HeaderRecyclerViewAdapter() {
    @Override
    protected RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent, int viewType) {
      return null;
    }

    @Override
    protected RecyclerView.ViewHolder onCreateItemViewHolder(ViewGroup parent, int viewType) {
      return null;
    }

    @Override
    protected RecyclerView.ViewHolder onCreateFooterViewHolder(ViewGroup parent, int viewType) {
      return null;
    }

    @Override protected void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override protected void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override protected void onBindFooterViewHolder(RecyclerView.ViewHolder holder, int position) {

    }
  };

  GridLayoutManager gridLayoutManager = new GridLayoutManagerBuilder().build();

  public HeaderSpanSizeLookupBuilder withAdapter(HeaderRecyclerViewAdapter adapter) {
    this.adapter = adapter;
    return this;
  }

  public HeaderSpanSizeLookupBuilder withGridLayoutManager(GridLayoutManager gridLayoutManager) {
    this.gridLayoutManager = gridLayoutManager;
    return this;
  }

  public HeaderSpanSizeLookup build() {
    return new HeaderSpanSizeLookup(adapter, gridLayoutManager);
  }
}
