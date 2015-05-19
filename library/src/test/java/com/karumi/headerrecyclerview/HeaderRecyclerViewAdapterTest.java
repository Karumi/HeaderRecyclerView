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

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;

public class HeaderRecyclerViewAdapterTest extends HeaderRecyclerViewAdapterBaseTest {

  @Override
  protected HeaderRecyclerViewAdapter<RecyclerView.ViewHolder, Object, Object>
  getAdapterUnderTest() {
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

  @Override protected RecyclerView.ViewHolder givenAViewHolder() {
    return new FakeViewHolder(mock(View.class));
  }

  @Override protected Object givenAHeader() {
    return new Object();
  }

  @Override protected List givenAListWithFiveItems() {
    List<Object> items = new ArrayList<>();
    for (int i = 0; i < 5; i++) {
      items.add(new Object());
    }
    return items;
  }

  private class FakeViewHolder extends RecyclerView.ViewHolder {

    public FakeViewHolder(View itemView) {
      super(itemView);
    }
  }
}
