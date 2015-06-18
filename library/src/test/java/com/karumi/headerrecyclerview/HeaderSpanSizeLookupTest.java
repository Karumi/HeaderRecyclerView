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
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import java.util.LinkedList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.robolectric.Robolectric;

import static junit.framework.Assert.assertEquals;

public class HeaderSpanSizeLookupTest extends RobolectricTest {

  private static final int ANY_SPAN_COUNT = 3;
  private static final int HEADER_SPAN_SIZE = 1;
  private static final int FOOTER_SPAN_SIZE = 1;

  private HeaderSpanSizeLookup headerSpanSizeLookup;
  private HeaderRecyclerViewAdapter<RecyclerView.ViewHolder, Object, Object, Object> adapter;

  @Before public void setUp() {
    GridLayoutManager gridLayoutManager =
        new GridLayoutManager(Robolectric.application, ANY_SPAN_COUNT);
    adapter = getHeaderRecyclerAdapter();
    headerSpanSizeLookup = new HeaderSpanSizeLookup(adapter, gridLayoutManager);
  }

  @Test
  public void shouldReturnHeaderSpanSizeIfTheAdapterHasNoConfiguredAHeaderAndThePositionIsZero() {
    List<Object> items = givenSomeItems();
    adapter.setItems(items);

    int spanSize = headerSpanSizeLookup.getSpanSize(0);

    assertEquals(HEADER_SPAN_SIZE, spanSize);
  }

  @Test
  public void shouldReturnFooterSpanSizeIfTheAdapterHasNoConfiguredAFooterAndThePositionIsTheLast() {
    List<Object> items = givenSomeItems();
    adapter.setItems(items);

    int spanSize = headerSpanSizeLookup.getSpanSize(items.size());

    assertEquals(FOOTER_SPAN_SIZE, spanSize);
  }

  @Test public void shouldReturnSpanCountIfThePositionIsZeroAndTheAdapterHasAHeaderConfigured() {
    Object header = giveAHeader();
    adapter.setHeader(header);

    int spanSize = headerSpanSizeLookup.getSpanSize(0);

    assertEquals(ANY_SPAN_COUNT, spanSize);
  }

  @Test public void shouldReturnHeaderSpanSizeIfThePositionIsZeroAndHasHeaderAndItemsConfigured() {
    Object header = giveAHeader();
    List<Object> items = givenSomeItems();
    adapter.setHeader(header);
    adapter.setItems(items);

    int spanSize = headerSpanSizeLookup.getSpanSize(0);

    assertEquals(ANY_SPAN_COUNT, spanSize);
  }

  @Test public void shouldReturnHeaderSpanSizeIfThePositionIsOneAndHasHeaderAndItemsConfigured() {
    Object header = giveAHeader();
    List<Object> items = givenSomeItems();
    adapter.setHeader(header);
    adapter.setItems(items);

    int spanSize = headerSpanSizeLookup.getSpanSize(1);

    assertEquals(HEADER_SPAN_SIZE, spanSize);
  }

  private Object giveAHeader() {
    return new Object();
  }

  private List<Object> givenSomeItems() {
    List<Object> items = new LinkedList<>();
    items.add(new Object());
    return items;
  }

  private HeaderRecyclerViewAdapter<RecyclerView.ViewHolder, Object, Object, Object>
  getHeaderRecyclerAdapter() {
    return new HeaderRecyclerViewAdapter<RecyclerView.ViewHolder, Object, Object, Object>() {

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

      @Override
      protected void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {

      }

      @Override protected void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {

      }

      @Override
      protected void onBindFooterViewHolder(RecyclerView.ViewHolder holder, int position) {

      }
    };
  }
}
