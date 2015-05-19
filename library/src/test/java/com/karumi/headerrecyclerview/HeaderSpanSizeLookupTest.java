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
import org.junit.Test;
import org.robolectric.Robolectric;

import static junit.framework.Assert.assertEquals;

public class HeaderSpanSizeLookupTest extends RobolectricTest {

  @Test
  public void shouldReturnHeaderSpanSizeIfTheAdapterHasNoConfiguredAHeaderAndThePositionIsZero() {
    List<Object> items = givenSomeItems();
    HeaderRecyclerViewAdapter<RecyclerView.ViewHolder, Object, Object>
        adapterWithoutHeaderAndWithItems = getHeaderRecyclerAdapter();
    int spanCount = 3;
    GridLayoutManager gridLayoutManager = new GridLayoutManager(Robolectric.application, spanCount);
    HeaderSpanSizeLookup headerSpanSizeLookup =
        new HeaderSpanSizeLookup(adapterWithoutHeaderAndWithItems, gridLayoutManager);
    adapterWithoutHeaderAndWithItems.setItems(items);
    int positionZero = 0;

    assertEquals(1, headerSpanSizeLookup.getSpanSize(positionZero));
  }

  @Test public void shouldReturnSpanCountIfThePositionIsZeroAndTheAdapterHasAHeaderConfigured() {
    Object header = giveAHeader();
    HeaderRecyclerViewAdapter<RecyclerView.ViewHolder, Object, Object> adapterWithHeaderAndNoItems =
        getHeaderRecyclerAdapter();
    int spanCount = 3;
    GridLayoutManager gridLayoutManager = new GridLayoutManager(Robolectric.application, spanCount);
    HeaderSpanSizeLookup headerSpanSizeLookup =
        new HeaderSpanSizeLookup(adapterWithHeaderAndNoItems, gridLayoutManager);
    adapterWithHeaderAndNoItems.setHeader(header);
    int positionZero = 0;

    assertEquals(3, headerSpanSizeLookup.getSpanSize(positionZero));
  }

  @Test public void shouldReturnHeaderSpanSizeIfThePositionIsZeroAndHasHeaderAndItemsConfigured() {
    Object header = giveAHeader();
    List<Object> items = givenSomeItems();
    HeaderRecyclerViewAdapter<RecyclerView.ViewHolder, Object, Object> adapterWithHeaderAndItems =
        getHeaderRecyclerAdapter();
    int spanCount = 3;
    GridLayoutManager gridLayoutManager = new GridLayoutManager(Robolectric.application, spanCount);
    HeaderSpanSizeLookup headerSpanSizeLookup =
        new HeaderSpanSizeLookup(adapterWithHeaderAndItems, gridLayoutManager);
    adapterWithHeaderAndItems.setHeader(header);
    adapterWithHeaderAndItems.setItems(items);
    int positionZero = 0;

    assertEquals(3, headerSpanSizeLookup.getSpanSize(positionZero));
  }

  @Test public void shouldReturnHeaderSpanSizeIfThePositionIsOneAndHasHeaderAndItemsConfigured() {
    Object header = giveAHeader();
    List<Object> items = givenSomeItems();
    HeaderRecyclerViewAdapter<RecyclerView.ViewHolder, Object, Object> adapterWithHeaderAndItems =
        getHeaderRecyclerAdapter();
    int spanCount = 3;
    GridLayoutManager gridLayoutManager = new GridLayoutManager(Robolectric.application, spanCount);
    HeaderSpanSizeLookup headerSpanSizeLookup =
        new HeaderSpanSizeLookup(adapterWithHeaderAndItems, gridLayoutManager);
    adapterWithHeaderAndItems.setHeader(header);
    adapterWithHeaderAndItems.setItems(items);
    int positionOne = 1;

    assertEquals(1, headerSpanSizeLookup.getSpanSize(positionOne));
  }

  private Object giveAHeader() {
    return new Object();
  }

  private List<Object> givenSomeItems() {
    List<Object> items = new LinkedList<>();
    items.add(new Object());
    return items;
  }

  private HeaderRecyclerViewAdapter<RecyclerView.ViewHolder, Object, Object> getHeaderRecyclerAdapter() {
    return new HeaderRecyclerViewAdapter<RecyclerView.ViewHolder, Object, Object>() {

      @Override
      protected RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent, int viewType) {
        return null;
      }

      @Override
      protected RecyclerView.ViewHolder onCreateItemViewHolder(ViewGroup parent, int viewType) {
        return null;
      }

      @Override
      protected void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {

      }

      @Override protected void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {

      }
    };
  }
}
