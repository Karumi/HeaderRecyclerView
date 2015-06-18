/*
 * Copyright (C) 2015 Karumi.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.karumi.headerrecyclerview;

import android.support.v7.widget.GridLayoutManager;

/**
 * GridLayoutManager.SpanSizeLookup implementation used to show a header in a RecyclerView when the
 * LayoutManager used is a GridLayoutManager.
 */
public class HeaderSpanSizeLookup extends GridLayoutManager.SpanSizeLookup {

  private final HeaderRecyclerViewAdapter adapter;
  private final GridLayoutManager layoutManager;

  public HeaderSpanSizeLookup(HeaderRecyclerViewAdapter adapter, GridLayoutManager layoutManager) {
    this.adapter = adapter;
    this.layoutManager = layoutManager;
  }

  @Override public int getSpanSize(int position) {
    boolean isHeaderOrFooter =
        adapter.isHeaderPosition(position) || adapter.isFooterPosition(position);
    return isHeaderOrFooter ? layoutManager.getSpanCount() : 1;
  }
}
