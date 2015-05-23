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

import java.util.LinkedList;
import java.util.List;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class HeaderSpanSizeLookupTest extends RobolectricTest {

  private static final int THREE_ROWS = 3;
  private static final int POSITION_ZERO = 0;
  private static final int POSITION_ONE = 1;

  @Test
  public void shouldReturnHeaderSpanSizeIfTheAdapterHasNoConfiguredAHeaderAndThePositionIsZero() {
    HeaderSpanSizeLookup headerSpanSizeLookup = new HeaderSpanSizeLookupBuilder().withAdapter(
        new HeaderRecyclerViewAdapterBuilder().withItems(givenSomeItems()).build())
        .withGridLayoutManager(new GridLayoutManagerBuilder().withSpanCount(THREE_ROWS).build())
        .build();

    assertEquals(1, headerSpanSizeLookup.getSpanSize(POSITION_ZERO));
  }

  @Test public void shouldReturnSpanCountIfThePositionIsZeroAndTheAdapterHasAHeaderConfigured() {
    HeaderSpanSizeLookup headerSpanSizeLookup = new HeaderSpanSizeLookupBuilder().withAdapter(
        new HeaderRecyclerViewAdapterBuilder().withHeader(giveAHeader()).build())
        .withGridLayoutManager(new GridLayoutManagerBuilder().withSpanCount(THREE_ROWS).build())
        .build();

    assertEquals(3, headerSpanSizeLookup.getSpanSize(POSITION_ZERO));
  }

  @Test public void shouldReturnHeaderSpanSizeIfThePositionIsZeroAndHasHeaderAndItemsConfigured() {
    HeaderSpanSizeLookup headerSpanSizeLookup = new HeaderSpanSizeLookupBuilder().withAdapter(
        new HeaderRecyclerViewAdapterBuilder().withHeader(giveAHeader())
            .withItems(givenSomeItems())
            .build())
        .withGridLayoutManager(new GridLayoutManagerBuilder().withSpanCount(THREE_ROWS).build())
        .build();

    assertEquals(3, headerSpanSizeLookup.getSpanSize(POSITION_ZERO));
  }

  @Test public void shouldReturnHeaderSpanSizeIfThePositionIsOneAndHasHeaderAndItemsConfigured() {
    HeaderSpanSizeLookup headerSpanSizeLookup = new HeaderSpanSizeLookupBuilder().withAdapter(
        new HeaderRecyclerViewAdapterBuilder().withHeader(giveAHeader())
            .withItems(givenSomeItems())
            .build())
        .withGridLayoutManager(new GridLayoutManagerBuilder().withSpanCount(THREE_ROWS).build())
        .build();

    assertEquals(1, headerSpanSizeLookup.getSpanSize(POSITION_ONE));
  }

  private Object giveAHeader() {
    return new Object();
  }

  private List<Object> givenSomeItems() {
    List<Object> items = new LinkedList<>();
    items.add(new Object());
    return items;
  }
}
