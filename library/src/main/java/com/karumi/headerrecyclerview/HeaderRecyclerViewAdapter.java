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
import java.util.Collections;
import java.util.List;

/**
 * RecyclerView.Adapter extension created to add header capability support and a generic List of
 * items really useful most of the cases. You should extend from this class and override
 * onCreateViewHolder to create your ViewHolder instances and onBindViewHolder methods to draw your
 * user interface as you wish.
 *
 * The usage of List<T> items member is not mandatory. If you are going to provide your custom
 * implementation remember to override getItemCount method.
 */
public abstract class HeaderRecyclerViewAdapter<VH extends RecyclerView.ViewHolder, H, T>
    extends RecyclerView.Adapter<VH> {

  protected static final int TYPE_HEADER = -2;
  protected static final int TYPE_NO_HEADER = -1;

  private H header;
  private List<T> items = Collections.EMPTY_LIST;

  /**
   * Returns the type associated to an item given a position passed as arguments. If the position
   * is related to a header item returns the constant TYPE_HEADER, if not, returns TYPE_NO_HEADER.
   * If your application has to support different types override this method and provide your
   * implementation. Remember that TYPE_HEADER and TYPE_NO_HEADER are internal constants can be
   * used to identify an item given a position, try to use different values in your application.
   */
  @Override public int getItemViewType(int position) {
    int typeHeader = TYPE_NO_HEADER;
    if (isHeaderPosition(position)) {
      typeHeader = TYPE_HEADER;
    }
    return typeHeader;
  }

  /**
   * Returns the items list size if there is no a header configured or the size plus one if there
   * is a header already configured.
   */
  @Override public int getItemCount() {
    return hasHeader() ? items.size() + 1 : items.size();
  }

  public H getHeader() {
    return header;
  }

  public T getItem(int position) {
    if (hasHeader() && hasItems()) {
      --position;
    }
    return items.get(position);
  }

  public void setHeader(H header) {
    this.header = header;
  }

  public void setItems(List<T> items) {
    validateItems(items);
    this.items = items;
  }

  /**
   * Returns true if the view type parameter passed as argument is equals to TYPE_HEADER.
   */
  protected boolean isHeaderType(int viewType) {
    return viewType == TYPE_HEADER;
  }

  /**
   * Returns true if the position type parameter passed as argument is equals to 0 and the adapter
   * has a not null header already configured.
   */
  protected boolean isHeaderPosition(int position) {
    return hasHeader() && position == 0;
  }

  /**
   * Returns true if the header configured is not null.
   */
  protected boolean hasHeader() {
    return getHeader() != null;
  }

  private boolean hasItems() {
    return items.size() > 0;
  }

  private void validateItems(List<T> items) {
    if (items == null) {
      throw new IllegalArgumentException("You can't use a null List<Item> instance.");
    }
  }
}
