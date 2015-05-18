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
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Base test class created to be extended by every HeaderRecyclerViewAdapter test case. Any
 * HeaderRecyclerViewAdapter extension have to match the contract described in this test.
 */
public abstract class HeaderRecyclerViewAdapterBaseTest<VH extends RecyclerView.ViewHolder, H, T>
    extends RobolectricTest {

  private static final int NO_HEADER_TYPE = -1;
  private static final int TYPE_HEADER = -2;

  protected HeaderRecyclerViewAdapter<VH, H, T> adapter;

  @Before public void setUp() {
    adapter = getAdapterUnderTest();
  }

  protected abstract HeaderRecyclerViewAdapter<VH, H, T> getAdapterUnderTest();

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
    Assert.assertEquals(expectedSize, adapter.getItemCount());
  }

  @Test
  public void shouldReturnOneAsItemCountIfThereAreNoItemsButTheAdapterHasOneHeaderConfigured() {
    H header = givenAHeader();

    adapter.setHeader(header);

    assertEquals(1, adapter.getItemCount());
  }

  @Test public void shouldReturnFalseIfPositionEqualsToZeroButThereIsNoHeaderConfigured() {
    assertFalse(adapter.isHeaderPosition(0));
  }

  @Test public void shouldReturnTrueIfPositionEqualsToZeroAndThereIsAHeaderConfigured() {
    H header = givenAHeader();

    adapter.setHeader(header);

    assertTrue(adapter.isHeaderPosition(0));
  }

  @Test public void shouldReturnNoHeaderTypeIfThereIsNoHeaderConfigured() {
    int type = adapter.getItemViewType(0);
    assertEquals(NO_HEADER_TYPE, type);
  }

  @Test public void shouldReturnHeaderTypeIfThereIsAHeaderConfiguredAndThePositionIsZero() {
    H header = givenAHeader();

    adapter.setHeader(header);

    assertEquals(TYPE_HEADER, adapter.getItemViewType(0));
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

  protected abstract H givenAHeader();

  protected abstract List<T> givenAListWithFiveItems();
}
