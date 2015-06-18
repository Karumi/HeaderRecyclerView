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
import java.util.List;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

/**
 * Base test class created to be extended by every HeaderRecyclerViewAdapter test case. Any
 * HeaderRecyclerViewAdapter extension have to match the contract described in this test.
 */
public abstract class HeaderRecyclerViewAdapterBaseTest<VH extends RecyclerView.ViewHolder, H, T, F>
    extends RobolectricTest {

  private static final int TYPE_ITEM = -1;
  private static final int TYPE_HEADER = -2;
  private static final int TYPE_FOOTER = -3;
  private static final ViewGroup ANY_VIEW_GROUP = mock(ViewGroup.class);
  private static final int HEADER_POSITION = 0;
  private static final int ITEM_POSITION = 1;
  private static final int FOOTER_POSITION = 5;

  protected HeaderRecyclerViewAdapter<VH, H, T, F> adapter;

  @Before public void setUp() {
    adapter = getAdapterUnderTest();
  }

  @Test public void shouldReturnZeroAsItemCountByDefault() {
    assertEquals(0, adapter.getItemCount());
  }

  @Test public void shouldReturnItemCountIfHasNoHeaderConfigured() {
    List<T> brands = givenAListWithFiveItems();

    adapter.setItems(brands);

    int fiveElements = brands.size();
    assertEquals(fiveElements, adapter.getItemCount());
  }

  @Test public void shouldReturnItemCountPlusOneIfHasHeaderConfigured() {
    List<T> items = givenAListWithFiveItems();
    H header = givenAHeader();

    adapter.setHeader(header);
    adapter.setItems(items);

    int expectedSize = items.size() + 1;
    assertEquals(expectedSize, adapter.getItemCount());
  }

  @Test public void shouldReturnItemCountPlusOneIfHasFooterConfigured() {
    List<T> items = givenAListWithFiveItems();
    F footer = givenAFooter();

    adapter.setFooter(footer);
    adapter.setItems(items);

    int expectedSize = items.size() + 1;
    assertEquals(expectedSize, adapter.getItemCount());
  }

  @Test public void shouldReturnItemCountPlusTwoIfHasHeaderAndFooterConfigured() {
    List<T> items = givenAListWithFiveItems();
    H header = givenAHeader();
    F footer = givenAFooter();

    adapter.setHeader(header);
    adapter.setFooter(footer);
    adapter.setItems(items);

    int expectedSize = items.size() + 2;
    assertEquals(expectedSize, adapter.getItemCount());
  }

  @Test
  public void shouldReturnOneAsItemCountIfThereAreNoItemsButTheAdapterHasOneHeaderConfigured() {
    H header = givenAHeader();

    adapter.setHeader(header);

    assertEquals(1, adapter.getItemCount());
  }

  @Test
  public void shouldReturnOneAsItemCountIfThereAreNoItemsButTheAdapterHasOneFooterConfigured() {
    F footer = givenAFooter();

    adapter.setFooter(footer);

    assertEquals(1, adapter.getItemCount());
  }

  @Test public void shouldReturnTwoAsItemCountIfThereAreNoItemsButTheAdapterHasFooterAndHeader() {
    H header = givenAHeader();
    F footer = givenAFooter();

    adapter.setHeader(header);
    adapter.setFooter(footer);

    assertEquals(2, adapter.getItemCount());
  }

  @Test public void shouldReturnFalseIfPositionEqualsToZeroButThereIsNoHeaderConfigured() {
    assertFalse(adapter.isHeaderPosition(0));
  }

  @Test public void shouldReturnFalseIfPositionEqualsToLastButThereIsNoFooterConfigured() {
    assertFalse(adapter.isFooterPosition(0));
  }

  @Test public void shouldReturnTrueIfPositionEqualsToZeroAndThereIsAHeaderConfigured() {
    H header = givenAHeader();

    adapter.setHeader(header);

    assertTrue(adapter.isHeaderPosition(0));
  }

  @Test public void shouldReturnTrueIfPositionEqualsToZeroAndThereIsAFooterConfigured() {
    F footer = givenAFooter();

    adapter.setFooter(footer);

    assertTrue(adapter.isFooterPosition(0));
  }

  @Test public void shouldReturnNoHeaderTypeIfThereIsNoHeaderConfigured() {
    int type = adapter.getItemViewType(0);
    assertEquals(TYPE_ITEM, type);
  }

  @Test public void shouldReturnHeaderTypeIfThereIsAHeaderConfiguredAndThePositionIsZero() {
    H header = givenAHeader();

    adapter.setHeader(header);

    assertEquals(TYPE_HEADER, adapter.getItemViewType(0));
  }

  @Test public void shouldReturnFooterTypeIfThereIsAFooterConfiguredAndThePositionIsZero() {
    F footer = givenAFooter();

    adapter.setFooter(footer);

    assertEquals(TYPE_FOOTER, adapter.getItemViewType(0));
  }

  @Test public void shouldReturnItemAsItemAtPositionZeroIfThereIsNoHeaderConfigured() {
    List<T> items = givenAListWithFiveItems();

    adapter.setItems(items);

    T expectedItem = items.get(0);
    assertEquals(expectedItem, adapter.getItem(0));
  }

  @Test
  public void shouldReturnFirstItemAsItemAtPositionOneIfHeaderWasConfiguredAndThereAreMoreItems() {
    H header = givenAHeader();
    List<T> items = givenAListWithFiveItems();

    adapter.setHeader(header);
    adapter.setItems(items);

    T expectedItem = items.get(0);
    assertEquals(expectedItem, adapter.getItem(1));
  }

  @Test public void shouldDelegateCallToOnCreateHeaderViewHolderIfViewTypeIsHeaderType() {
    HeaderRecyclerViewAdapter<VH, H, T, F> adapter = givenAnAdapterWithHeaderAndSomeItems();
    adapter = spy(adapter);

    adapter.onCreateViewHolder(ANY_VIEW_GROUP, TYPE_HEADER);

    verify(adapter).onCreateHeaderViewHolder(ANY_VIEW_GROUP, TYPE_HEADER);
  }

  @Test public void shouldDelegateCallToOnCreateFooterViewHolderIfViewTypeIsFooterType() {
    HeaderRecyclerViewAdapter<VH, H, T, F> adapter = givenAnAdapterWithFooterAndSomeItems();
    adapter = spy(adapter);

    adapter.onCreateViewHolder(ANY_VIEW_GROUP, TYPE_FOOTER);

    verify(adapter).onCreateFooterViewHolder(ANY_VIEW_GROUP, TYPE_FOOTER);
  }

  @Test public void shouldDelegateCallToOnCreateItemViewHolderIfViewTypeIsItemType() {
    HeaderRecyclerViewAdapter<VH, H, T, F> adapter = givenAnAdapterWithHeaderAndSomeItems();
    adapter = spy(adapter);

    adapter.onCreateViewHolder(ANY_VIEW_GROUP, TYPE_ITEM);

    verify(adapter).onCreateItemViewHolder(ANY_VIEW_GROUP, TYPE_ITEM);
  }

  @Test public void shouldDelegateCallToOnBindHeaderViewHolderIfViewTypeIsHeaderType() {
    HeaderRecyclerViewAdapter<VH, H, T, F> adapter = givenAnAdapterWithHeaderAndSomeItems();
    adapter = spy(adapter);
    VH holder = givenAViewHolder();

    adapter.onBindViewHolder(holder, HEADER_POSITION);

    verify(adapter).onBindHeaderViewHolder(holder, HEADER_POSITION);
  }

  @Test public void shouldDelegateCallToOnBindFooterViewHolderIfViewTypeIsFooterType() {
    HeaderRecyclerViewAdapter<VH, H, T, F> adapter = givenAnAdapterWithFooterAndSomeItems();
    adapter = spy(adapter);
    VH holder = givenAViewHolder();

    adapter.onBindViewHolder(holder, FOOTER_POSITION);

    verify(adapter).onBindFooterViewHolder(holder, FOOTER_POSITION);
  }

  @Test public void shouldDelegateCallToOnBindItemViewHolderIfViewTypeIsItemType() {
    HeaderRecyclerViewAdapter<VH, H, T, F> adapter = givenAnAdapterWithHeaderAndSomeItems();
    adapter = spy(adapter);
    VH holder = givenAViewHolder();

    adapter.onBindViewHolder(holder, ITEM_POSITION);

    verify(adapter).onBindItemViewHolder(holder, ITEM_POSITION);
  }

  private HeaderRecyclerViewAdapter<VH, H, T, F> givenAnAdapterWithHeaderAndSomeItems() {
    H header = givenAHeader();
    List<T> items = givenAListWithFiveItems();
    adapter.setHeader(header);
    adapter.setItems(items);
    return adapter;
  }

  private HeaderRecyclerViewAdapter<VH, H, T, F> givenAnAdapterWithFooterAndSomeItems() {
    F footer = givenAFooter();
    List<T> items = givenAListWithFiveItems();
    adapter.setFooter(footer);
    adapter.setItems(items);
    return adapter;
  }

  protected abstract HeaderRecyclerViewAdapter<VH, H, T, F> getAdapterUnderTest();

  protected abstract VH givenAViewHolder();

  protected abstract H givenAHeader();

  protected abstract F givenAFooter();

  protected abstract List<T> givenAListWithFiveItems();
}
