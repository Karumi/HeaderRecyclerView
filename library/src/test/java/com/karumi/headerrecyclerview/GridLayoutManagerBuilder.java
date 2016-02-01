package com.karumi.headerrecyclerview;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;

import org.robolectric.RuntimeEnvironment;

public class GridLayoutManagerBuilder {

  Context context = RuntimeEnvironment.application;
  int spanCount = 1;

  public GridLayoutManagerBuilder withContext(Context context) {
    this.context = context;
    return this;
  }

  public GridLayoutManagerBuilder withSpanCount(int spanCount) {
    this.spanCount = spanCount;
    return this;
  }

  public GridLayoutManager build() {
    return new GridLayoutManager(context, spanCount);
  }
}
