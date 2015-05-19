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
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

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
public abstract class HeaderRecyclerViewAdapterBaseTest<VH extends RecyclerView.ViewHolder, H, T>
    extends RobolectricTest {

    @Test
    public void shouldReturnZeroAsItemCountByDefault() {
        HeaderRecyclerViewAdapter<VH, H, T> defaultAdapter = getAdapterUnderTest();

        assertEquals(0, defaultAdapter.getItemCount());
    }

    @Test
    public void shouldReturnItemCountIfHasNoHeaderConfigured() {
        HeaderRecyclerViewAdapter<VH, H, T> adapterWithoutHeaderAndWithFiveItems = getAdapterUnderTest();
        List<T> items = givenAListWithFiveItems();
        adapterWithoutHeaderAndWithFiveItems.setItems(items);

        assertEquals(5, adapterWithoutHeaderAndWithFiveItems.getItemCount());
    }

    @Test
    public void shouldReturnItemCountPlusOneIfHasHeaderConfigured() {
        HeaderRecyclerViewAdapter<VH, H, T> adapterWithHeaderAndWithFiveItems = getAdapterUnderTest();
        List<T> items = givenAListWithFiveItems();
        H header = givenAHeader();
        adapterWithHeaderAndWithFiveItems.setHeader(header);
        adapterWithHeaderAndWithFiveItems.setItems(items);

        Assert.assertEquals(6, adapterWithHeaderAndWithFiveItems.getItemCount());
    }

    @Test
    public void shouldReturnOneAsItemCountIfThereAreNoItemsButTheAdapterHasOneHeaderConfigured() {
        HeaderRecyclerViewAdapter<VH, H, T> adapterWithHeaderAndNoItems = getAdapterUnderTest();
        H header = givenAHeader();
        adapterWithHeaderAndNoItems.setHeader(header);

        assertEquals(1, adapterWithHeaderAndNoItems.getItemCount());
    }

    @Test
    public void shouldReturnFalseIfPositionEqualsToZeroButThereIsNoHeaderConfigured() {
        HeaderRecyclerViewAdapter<VH, H, T> adapterWithoutHeaderAndWithoutItems = getAdapterUnderTest();
        int positionZero = 0;

        assertFalse(adapterWithoutHeaderAndWithoutItems.isHeaderPosition(positionZero));
    }

    @Test
    public void shouldReturnTrueIfPositionEqualsToZeroAndThereIsAHeaderConfigured() {
        HeaderRecyclerViewAdapter<VH, H, T> adapterWithHeaderAndNoItems = getAdapterUnderTest();
        H header = givenAHeader();
        adapterWithHeaderAndNoItems.setHeader(header);
        int positionZero = 0;

        assertTrue(adapterWithHeaderAndNoItems.isHeaderPosition(positionZero));
    }

    @Test
    public void shouldReturnNoHeaderTypeIfThereIsNoHeaderConfigured() {
        HeaderRecyclerViewAdapter<VH, H, T> adapterWithoutHeaderAndWithoutItems = getAdapterUnderTest();
        int anyPosition = 0;

        assertEquals(-1, adapterWithoutHeaderAndWithoutItems.getItemViewType(anyPosition));
    }

    @Test
    public void shouldReturnHeaderTypeIfThereIsAHeaderConfiguredAndThePositionIsZero() {
        HeaderRecyclerViewAdapter<VH, H, T> adapterWithHeaderAndNoItems = getAdapterUnderTest();
        H header = givenAHeader();
        adapterWithHeaderAndNoItems.setHeader(header);
        int positionZero = 0;

        assertEquals(-2, adapterWithHeaderAndNoItems.getItemViewType(positionZero));
    }

    @Test
    public void shouldReturnItemAsItemAtPositionZeroIfThereIsNoHeaderConfigured() {
        HeaderRecyclerViewAdapter<VH, H, T> adapterWithoutHeaderAndWithFiveItems = getAdapterUnderTest();
        List<T> items = givenAListWithFiveItems();
        adapterWithoutHeaderAndWithFiveItems.setItems(items);
        int positionZero = 0;

        T expectedItem = items.get(positionZero);
        assertEquals(expectedItem, adapterWithoutHeaderAndWithFiveItems.getItem(positionZero));
    }

    @Test
    public void shouldReturnFirstItemAsItemAtPositionOneIfHeaderWasConfiguredAndThereAreMoreItems() {
        HeaderRecyclerViewAdapter<VH, H, T> adapterWithHeaderAndWithFiveItems = getAdapterUnderTest();
        H header = givenAHeader();
        List<T> items = givenAListWithFiveItems();
        adapterWithHeaderAndWithFiveItems.setHeader(header);
        adapterWithHeaderAndWithFiveItems.setItems(items);
        int positionZero = 0;
        int positionOne = 1;

        T expectedItem = items.get(positionZero);
        assertEquals(expectedItem, adapterWithHeaderAndWithFiveItems.getItem(positionOne));
    }

    @Test
    public void shouldDelegateCallToOnCreateHeaderViewHolderIfViewTypeIsHeaderType() {
        HeaderRecyclerViewAdapter<VH, H, T> adapterWithHeaderAndSomeItems = spy(givenAnAdapterWithHeaderAndSomeItems());
        int typeHeader = -2;
        ViewGroup anyViewGroup = mock(ViewGroup.class);

        adapterWithHeaderAndSomeItems.onCreateViewHolder(anyViewGroup, typeHeader);

        verify(adapterWithHeaderAndSomeItems).onCreateHeaderViewHolder(anyViewGroup, typeHeader);
    }

    @Test
    public void shouldDelegateCallToOnCreateItemViewHolderIfViewTypeIsItemType() {
        HeaderRecyclerViewAdapter<VH, H, T> adapterWithHeaderAndSomeItems = spy(givenAnAdapterWithHeaderAndSomeItems());
        int typeItem = -1;
        ViewGroup anyViewGroup = mock(ViewGroup.class);

        adapterWithHeaderAndSomeItems.onCreateViewHolder(anyViewGroup, typeItem);

        verify(adapterWithHeaderAndSomeItems).onCreateItemViewHolder(anyViewGroup, typeItem);
    }

    @Test
    public void shouldDelegateCallToOnBindHeaderViewHolderIfViewTypeIsHeaderType() {
        HeaderRecyclerViewAdapter<VH, H, T> adapterWithHeaderAndSomeItems = spy(givenAnAdapterWithHeaderAndSomeItems());
        VH holder = givenAViewHolder();
        int headerPosition = 0;

        adapterWithHeaderAndSomeItems.onBindViewHolder(holder, headerPosition);

        verify(adapterWithHeaderAndSomeItems).onBindHeaderViewHolder(holder, headerPosition);
    }

    @Test
    public void shouldDelegateCallToOnBindItemViewHolderIfViewTypeIsItemType() {
        HeaderRecyclerViewAdapter<VH, H, T> adapterWithHeaderAndSomeItems = spy(givenAnAdapterWithHeaderAndSomeItems());
        VH holder = givenAViewHolder();
        int anyNonHeaderPosition = 1;

        adapterWithHeaderAndSomeItems.onBindViewHolder(holder, anyNonHeaderPosition);

        verify(adapterWithHeaderAndSomeItems).onBindItemViewHolder(holder, anyNonHeaderPosition);
    }

    private HeaderRecyclerViewAdapter<VH, H, T> givenAnAdapterWithHeaderAndSomeItems() {
        HeaderRecyclerViewAdapter<VH, H, T> adapter = getAdapterUnderTest();
        H header = givenAHeader();
        List<T> items = givenAListWithFiveItems();
        adapter.setHeader(header);
        adapter.setItems(items);
        return adapter;
    }

    protected abstract HeaderRecyclerViewAdapter<VH, H, T> getAdapterUnderTest();

    protected abstract VH givenAViewHolder();

    protected abstract H givenAHeader();

    protected abstract List<T> givenAListWithFiveItems();
}
