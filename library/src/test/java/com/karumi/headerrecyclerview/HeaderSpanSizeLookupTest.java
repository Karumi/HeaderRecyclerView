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

  @Test
  public void shouldReturnHeaderSpanSizeIfTheAdapterHasNoConfiguredAHeaderAndThePositionIsZero() {
    int spanCount = 3;
    HeaderSpanSizeLookup headerSpanSizeLookup = new HeaderSpanSizeLookupBuilder().withAdapter(
        new HeaderRecyclerViewAdapterBuilder().withItems(givenSomeItems()).build())
        .withGridLayoutManager(new GridLayoutManagerBuilder().withSpanCount(spanCount).build())
        .build();
    int positionZero = 0;

    assertEquals(1, headerSpanSizeLookup.getSpanSize(positionZero));
  }

  @Test public void shouldReturnSpanCountIfThePositionIsZeroAndTheAdapterHasAHeaderConfigured() {
    int spanCount = 3;
    HeaderSpanSizeLookup headerSpanSizeLookup = new HeaderSpanSizeLookupBuilder().withAdapter(
        new HeaderRecyclerViewAdapterBuilder().withHeader(giveAHeader()).build())
        .withGridLayoutManager(new GridLayoutManagerBuilder().withSpanCount(spanCount).build())
        .build();
    int positionZero = 0;

    assertEquals(3, headerSpanSizeLookup.getSpanSize(positionZero));
  }

  @Test public void shouldReturnHeaderSpanSizeIfThePositionIsZeroAndHasHeaderAndItemsConfigured() {
    int spanCount = 3;
    HeaderSpanSizeLookup headerSpanSizeLookup = new HeaderSpanSizeLookupBuilder().withAdapter(
        new HeaderRecyclerViewAdapterBuilder().withHeader(giveAHeader())
            .withItems(givenSomeItems())
            .build())
        .withGridLayoutManager(new GridLayoutManagerBuilder().withSpanCount(spanCount).build())
        .build();
    int positionZero = 0;

    assertEquals(3, headerSpanSizeLookup.getSpanSize(positionZero));
  }

  @Test public void shouldReturnHeaderSpanSizeIfThePositionIsOneAndHasHeaderAndItemsConfigured() {
    int spanCount = 3;
    HeaderSpanSizeLookup headerSpanSizeLookup = new HeaderSpanSizeLookupBuilder().withAdapter(
        new HeaderRecyclerViewAdapterBuilder().withHeader(giveAHeader())
            .withItems(givenSomeItems())
            .build())
        .withGridLayoutManager(new GridLayoutManagerBuilder().withSpanCount(spanCount).build())
        .build();
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
}
