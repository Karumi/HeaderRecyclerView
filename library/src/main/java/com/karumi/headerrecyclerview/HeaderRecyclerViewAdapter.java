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
import android.view.ViewGroup;
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
public abstract class HeaderRecyclerViewAdapter<VH extends RecyclerView.ViewHolder, H, T, F>
    extends RecyclerView.Adapter<VH> {

  protected static final int TYPE_HEADER = -2;
  protected static final int TYPE_ITEM = -1;
  protected static final int TYPE_FOOTER = -3;

  private H header;
  private List<T> items = Collections.EMPTY_LIST;
  private F footer;
  private boolean showFooter = true;

  /**
   * Invokes onCreateHeaderViewHolder, onCreateItemViewHolder or onCreateFooterViewHolder methods
   * based on the view type param.
   */
  @Override public VH onCreateViewHolder(ViewGroup parent, int viewType) {
    VH viewHolder;
    if (isHeaderType(viewType)) {
      viewHolder = onCreateHeaderViewHolder(parent, viewType);
    } else if (isFooterType(viewType)) {
      viewHolder = onCreateFooterViewHolder(parent, viewType);
    } else {
      viewHolder = onCreateItemViewHolder(parent, viewType);
    }
    return viewHolder;
  }

  /**
   * Invokes onBindHeaderViewHolder, onBindItemViewHolder or onBindFooterViewHOlder methods based
   * on
   * the position param.
   */
  @Override public void onBindViewHolder(VH holder, int position) {
    if (isHeaderPosition(position)) {
      onBindHeaderViewHolder(holder, position);
    } else if (isFooterPosition(position)) {
      onBindFooterViewHolder(holder, position);
    } else {
      onBindItemViewHolder(holder, position);
    }
  }

  /**
   * Returns the type associated to an item given a position passed as arguments. If the position
   * is related to a header item returns the constant TYPE_HEADER or TYPE_FOOTER if the position is
   * related to the footer, if not, returns TYPE_ITEM.
   *
   * If your application has to support different types override this method and provide your
   * implementation. Remember that TYPE_HEADER, TYPE_ITEM and TYPE_FOOTER are internal constants
   * can be used to identify an item given a position, try to use different values in your
   * application.
   */
  @Override public int getItemViewType(int position) {
    int viewType = TYPE_ITEM;
    if (isHeaderPosition(position)) {
      viewType = TYPE_HEADER;
    } else if (isFooterPosition(position)) {
      viewType = TYPE_FOOTER;
    }
    return viewType;
  }

  /**
   * Returns the items list size if there is no a header configured or the size taking into account
   * that if a header or a footer is configured the number of items returned is going to include
   * this elements.
   */
  @Override public int getItemCount() {
    int size = items.size();
    if (hasHeader()) {
      size++;
    }
    if (hasFooter()) {
      size++;
    }
    return size;
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

  public F getFooter() {
    return footer;
  }

  public void setHeader(H header) {
    this.header = header;
  }

  public void setItems(List<T> items) {
    validateItems(items);
    this.items = items;
  }

  public void setFooter(F footer) {
    this.footer = footer;
  }

  public void showFooter() {
    this.showFooter = true;
  }

  public void hideFooter() {
    this.showFooter = false;
  }

  protected abstract VH onCreateHeaderViewHolder(ViewGroup parent, int viewType);

  protected abstract VH onCreateItemViewHolder(ViewGroup parent, int viewType);

  protected abstract VH onCreateFooterViewHolder(ViewGroup parent, int viewType);

  protected abstract void onBindHeaderViewHolder(VH holder, int position);

  protected abstract void onBindItemViewHolder(VH holder, int position);

  protected abstract void onBindFooterViewHolder(VH holder, int position);

  /**
   * Returns true if the position type parameter passed as argument is equals to 0 and the adapter
   * has a not null header already configured.
   */
  public boolean isHeaderPosition(int position) {
    return hasHeader() && position == 0;
  }

  public boolean isFooterPosition(int position) {
    int lastPosition = getItemCount() - 1;
    return hasFooter() && position == lastPosition;
  }

  /**
   * Returns true if the view type parameter passed as argument is equals to TYPE_HEADER.
   */
  protected boolean isHeaderType(int viewType) {
    return viewType == TYPE_HEADER;
  }

  protected boolean isFooterType(int viewType) {
    return viewType == TYPE_FOOTER;
  }

  /**
   * Returns true if the header configured is not null.
   */
  protected boolean hasHeader() {
    return getHeader() != null;
  }

  /**
   * Returns true if the footer configured is not null.
   */
  protected boolean hasFooter() {
    return getFooter() != null && showFooter;
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
