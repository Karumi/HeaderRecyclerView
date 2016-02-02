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

import com.karumi.headerrecyclerview.testBaseClass.PowermockTest;
import com.karumi.headerrecyclerview.testBaseClass.RobolectricTestWithMockito;

import junit.framework.Assert;

import org.junit.Test;
import org.powermock.core.classloader.annotations.PrepareForTest;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.powermock.api.mockito.PowerMockito.*;
import static org.mockito.Mockito.verify;

/**
 * Base test class created to be extended by every HeaderRecyclerViewAdapter test case. Any
 * HeaderRecyclerViewAdapter extension have to match the contract described in this test.
 */
@PrepareForTest(RecyclerView.ViewHolder.class)
public abstract class HeaderRecyclerViewAdapterBaseTest<VH extends RecyclerView.ViewHolder, H, T, F>
        extends PowermockTest {

    private static final int TYPE_ITEM = -1;
    private static final int TYPE_HEADER = -2;
    private static final int TYPE_FOOTER = -3;
    private static final int POSITION_ZERO = 0;
    private static final int ANY_POSITION = 3;
    private static final int POSITION_ONE = 1;
    private static final int HEADER_POSITION = 0;
    private static final int ANY_NON_HEADER_POSITION = 4;
    private static final int FOOTER_POSITION = 5;

    @Test
    public void shouldReturnZeroAsItemCountByDefault() {
        HeaderRecyclerViewAdapter<VH, H, T, F> defaultAdapter =
                new HeaderRecyclerViewAdapterBuilder().build();

        assertEquals(0, defaultAdapter.getItemCount());
    }

    @Test
    public void shouldReturnItemCountIfHasNoHeaderConfigured() {
        HeaderRecyclerViewAdapter<VH, H, T, F> adapterWithoutHeaderAndWithFiveItems =
                new HeaderRecyclerViewAdapterBuilder().withItems(givenAListWithFiveItems()).build();

        assertEquals(5, adapterWithoutHeaderAndWithFiveItems.getItemCount());
    }

    @Test
    public void shouldReturnItemCountPlusOneIfHasHeaderConfigured() {
        HeaderRecyclerViewAdapter<VH, H, T, F> adapterWithHeaderAndWithFiveItems =
                new HeaderRecyclerViewAdapterBuilder().withHeader(givenAHeader())
                        .withItems(givenAListWithFiveItems())
                        .build();

        assertEquals(6, adapterWithHeaderAndWithFiveItems.getItemCount());
    }

    @Test
    public void shouldReturnItemCountPlusOneIfHasFooterConfigured() {
        HeaderRecyclerViewAdapter<VH, H, T, F> adapterWithFooterAndWithFiveItems =
                new HeaderRecyclerViewAdapterBuilder().withFooter(givenAHeader())
                        .withItems(givenAListWithFiveItems())
                        .build();

        assertEquals(6, adapterWithFooterAndWithFiveItems.getItemCount());
    }

    @Test
    public void shouldReturnItemCountPlusTwoIfHasHeaderAndFooterConfigured() {
        HeaderRecyclerViewAdapter<VH, H, T, F> adapterWithFooterAndWithFiveItems =
                new HeaderRecyclerViewAdapterBuilder().withHeader(givenAHeader())
                        .withFooter(givenAHeader())
                        .withItems(givenAListWithFiveItems())
                        .build();

        assertEquals(7, adapterWithFooterAndWithFiveItems.getItemCount());
    }

    @Test
    public void shouldReturnOneAsItemCountIfThereAreNoItemsButTheAdapterHasOneHeaderConfigured() {
        HeaderRecyclerViewAdapter<VH, H, T, F> adapterWithHeaderAndNoItems =
                new HeaderRecyclerViewAdapterBuilder().withHeader(givenAHeader()).build();

        assertEquals(1, adapterWithHeaderAndNoItems.getItemCount());
    }

    @Test
    public void shouldReturnOneAsItemCountIfThereAreNoItemsButTheAdapterHasOneFooterConfigured() {
        HeaderRecyclerViewAdapter<VH, H, T, F> adapterWithHeaderAndNoItems =
                new HeaderRecyclerViewAdapterBuilder().withFooter(givenAFooter()).build();

        assertEquals(1, adapterWithHeaderAndNoItems.getItemCount());
    }

    @Test
    public void shouldReturnTwoAsItemCountIfThereAreNoItemsButTheAdapterHasFooterAndHeader() {
        HeaderRecyclerViewAdapter<VH, H, T, F> adapterWithHeaderAndNoItems =
                new HeaderRecyclerViewAdapterBuilder().withFooter(givenAFooter())
                        .withHeader(givenAHeader())
                        .build();

        assertEquals(2, adapterWithHeaderAndNoItems.getItemCount());
    }

    @Test
    public void shouldReturnFalseIfPositionEqualsToZeroButThereIsNoHeaderConfigured() {
        HeaderRecyclerViewAdapter<VH, H, T, F> adapterWithoutHeaderAndWithoutItems =
                new HeaderRecyclerViewAdapterBuilder().build();

        assertFalse(adapterWithoutHeaderAndWithoutItems.isHeaderPosition(POSITION_ZERO));
    }

    @Test
    public void shouldReturnFalseIfPositionEqualsToLastButThereIsNoFooterConfigured() {
        HeaderRecyclerViewAdapter<VH, H, T, F> adapterWithoutHeaderAndWithoutItems =
                new HeaderRecyclerViewAdapterBuilder().build();

        assertFalse(adapterWithoutHeaderAndWithoutItems.isFooterPosition(POSITION_ZERO));
    }

    @Test
    public void shouldReturnTrueIfPositionEqualsToZeroAndThereIsAHeaderConfigured() {
        HeaderRecyclerViewAdapter<VH, H, T, F> adapterWithHeaderAndNoItems =
                new HeaderRecyclerViewAdapterBuilder().withHeader(givenAHeader()).build();

        assertTrue(adapterWithHeaderAndNoItems.isHeaderPosition(POSITION_ZERO));
    }

    @Test
    public void shouldReturnTrueIfPositionEqualsToZeroAndThereIsAFooterConfigured() {
        HeaderRecyclerViewAdapter<VH, H, T, F> adapterWithHeaderAndNoItems =
                new HeaderRecyclerViewAdapterBuilder().withFooter(givenAFooter()).build();

        assertTrue(adapterWithHeaderAndNoItems.isFooterPosition(POSITION_ZERO));
    }

    @Test
    public void shouldReturnNoHeaderTypeIfThereIsNoHeaderConfigured() {
        HeaderRecyclerViewAdapter<VH, H, T, F> adapterWithoutHeaderAndWithoutItems =
                new HeaderRecyclerViewAdapterBuilder().build();

        assertEquals(TYPE_ITEM, adapterWithoutHeaderAndWithoutItems.getItemViewType(ANY_POSITION));
    }

    @Test
    public void shouldReturnHeaderTypeIfThereIsAHeaderConfiguredAndThePositionIsZero() {
        HeaderRecyclerViewAdapter<VH, H, T, F> adapterWithHeaderAndNoItems =
                new HeaderRecyclerViewAdapterBuilder().withHeader(givenAHeader()).build();

        assertEquals(TYPE_HEADER, adapterWithHeaderAndNoItems.getItemViewType(POSITION_ZERO));
    }

    @Test
    public void shouldReturnFooterTypeIfThereIsAFooterConfiguredAndThePositionIsZero() {
        HeaderRecyclerViewAdapter<VH, H, T, F> adapterWithHeaderAndNoItems =
                new HeaderRecyclerViewAdapterBuilder().withFooter(givenAFooter()).build();

        assertEquals(TYPE_FOOTER, adapterWithHeaderAndNoItems.getItemViewType(POSITION_ZERO));
    }

    @Test
    public void shouldReturnItemAsItemAtPositionZeroIfThereIsNoHeaderConfigured() {
        List<T> fiveItems = givenAListWithFiveItems();
        HeaderRecyclerViewAdapter<VH, H, T, F> adapterWithoutHeaderAndWithFiveItems =
                new HeaderRecyclerViewAdapterBuilder().withItems(fiveItems).build();

        T positionZeroItem = fiveItems.get(POSITION_ZERO);
        assertEquals(positionZeroItem, adapterWithoutHeaderAndWithFiveItems.getItem(POSITION_ZERO));
    }

    @Test
    public void shouldReturnFirstItemAsItemAtPositionOneIfHeaderWasConfiguredAndThereAreMoreItems() {
        List<T> fiveItems = givenAListWithFiveItems();
        HeaderRecyclerViewAdapter<VH, H, T, F> adapterWithHeaderAndWithFiveItems =
                new HeaderRecyclerViewAdapterBuilder().withHeader(givenAHeader())
                        .withItems(fiveItems)
                        .build();

        T positionZeroItem = fiveItems.get(POSITION_ZERO);
        assertEquals(positionZeroItem, adapterWithHeaderAndWithFiveItems.getItem(POSITION_ONE));
    }

    @Test
    public void shouldDelegateCallToOnCreateHeaderViewHolderIfViewTypeIsHeaderType() {
        HeaderRecyclerViewAdapter<VH, H, T, F> adapter =
                spy(new HeaderRecyclerViewAdapterBuilder().withHeader(givenAHeader())
                        .withItems(givenAListWithFiveItems())
                        .build());
        ViewGroup anyViewGroup = mock(ViewGroup.class);

        adapter.onCreateViewHolder(anyViewGroup, TYPE_HEADER);

        verify(adapter).onCreateHeaderViewHolder(anyViewGroup, TYPE_HEADER);
    }

    @Test
    public void shouldDelegateCallToOnCreateFooterViewHolderIfViewTypeIsFooterType() {
        HeaderRecyclerViewAdapter<VH, H, T, F> adapter =
                spy(new HeaderRecyclerViewAdapterBuilder().withFooter(givenAFooter())
                        .withItems(givenAListWithFiveItems())
                        .build());
        ViewGroup anyViewGroup = mock(ViewGroup.class);

        adapter.onCreateViewHolder(anyViewGroup, TYPE_FOOTER);

        verify(adapter).onCreateFooterViewHolder(anyViewGroup, TYPE_FOOTER);
    }

    @Test
    public void shouldDelegateCallToOnCreateItemViewHolderIfViewTypeIsItemType() {
        HeaderRecyclerViewAdapter<VH, H, T, F> adapter =
                spy(new HeaderRecyclerViewAdapterBuilder().withHeader(givenAHeader())
                        .withItems(givenAListWithFiveItems())
                        .build());
        ViewGroup anyViewGroup = mock(ViewGroup.class);

        adapter.onCreateViewHolder(anyViewGroup, TYPE_ITEM);

        verify(adapter).onCreateItemViewHolder(anyViewGroup, TYPE_ITEM);
    }

    @Test
    public void shouldDelegateCallToOnBindHeaderViewHolderIfViewTypeIsHeaderType() {
        HeaderRecyclerViewAdapter<VH, H, T, F> adapterWithHeaderAndSomeItems =
                spy(new HeaderRecyclerViewAdapterBuilder().withHeader(givenAHeader())
                        .withItems(givenAListWithFiveItems())
                        .build());
        VH holder = givenAViewHolder();

        adapterWithHeaderAndSomeItems.onBindViewHolder(holder, HEADER_POSITION);

        verify(adapterWithHeaderAndSomeItems).onBindHeaderViewHolder(holder, HEADER_POSITION);
    }

    @Test
    public void shouldDelegateCallToOnBindFooterViewHolderIfViewTypeIsFooterType() {
        HeaderRecyclerViewAdapter<VH, H, T, F> adapterWithHeaderAndSomeItems =
                spy(new HeaderRecyclerViewAdapterBuilder().withFooter(givenAFooter())
                        .withItems(givenAListWithFiveItems())
                        .build());
        VH holder = givenAViewHolder();

        adapterWithHeaderAndSomeItems.onBindViewHolder(holder, FOOTER_POSITION);

        verify(adapterWithHeaderAndSomeItems).onBindFooterViewHolder(holder, FOOTER_POSITION);
    }

    @Test
    public void shouldDelegateCallToOnBindItemViewHolderIfViewTypeIsItemType() {
        HeaderRecyclerViewAdapter<VH, H, T, F> adapterWithHeaderAndSomeItems =
                spy(new HeaderRecyclerViewAdapterBuilder().withHeader(givenAHeader())
                        .withItems(givenAListWithFiveItems())
                        .build());
        VH holder = givenAViewHolder();

        adapterWithHeaderAndSomeItems.onBindViewHolder(holder, ANY_NON_HEADER_POSITION);

        verify(adapterWithHeaderAndSomeItems).onBindItemViewHolder(holder, ANY_NON_HEADER_POSITION);
    }

    @Test
    public void shouldDelegateCallToOnFailedToRecycleHeaderViewIfViewTypeIsHeaderType() {
        HeaderRecyclerViewAdapter<VH, H, T, F> adapter =
                spy(new HeaderRecyclerViewAdapterBuilder().withHeader(givenAHeader())
                        .withItems(givenAListWithFiveItems())
                        .build());
        VH viewHolder = givenAMockViewHolder();
        when(viewHolder.getAdapterPosition()).thenReturn(0);

        boolean ret = adapter.onFailedToRecycleView(viewHolder);
        assertFalse(ret);

        verify(adapter).onFailedToRecycleHeaderView(viewHolder);
        verify(adapter, never()).onFailedToRecycleItemView(viewHolder);
        verify(adapter, never()).onFailedToRecycleFooterView(viewHolder);
    }

    @Test
    public void shouldDelegateCallToOnFailedToRecycleItemViewIfViewTypeIsItemTypeAndHaveHeader() {
        HeaderRecyclerViewAdapter<VH, H, T, F> adapter =
                spy(new HeaderRecyclerViewAdapterBuilder().withHeader(givenAHeader())
                        .withItems(givenAListWithFiveItems())
                        .build());
        VH viewHolder = givenAMockViewHolder();
        when(viewHolder.getAdapterPosition()).thenReturn(1);

        boolean ret = adapter.onFailedToRecycleView(viewHolder);
        assertFalse(ret);

        verify(adapter, never()).onFailedToRecycleHeaderView(viewHolder);
        verify(adapter).onFailedToRecycleItemView(viewHolder);
        verify(adapter, never()).onFailedToRecycleFooterView(viewHolder);
    }


    @Test
    public void shouldDelegateCallToOnFailedToRecycleItemViewIfViewTypeIsItemTypeAndNotHaveHeader() {
        HeaderRecyclerViewAdapter<VH, H, T, F> adapter =
                spy(new HeaderRecyclerViewAdapterBuilder()
                        .withItems(givenAListWithFiveItems())
                        .build());
        VH viewHolder = givenAMockViewHolder();
        when(viewHolder.getAdapterPosition()).thenReturn(0);

        boolean ret = adapter.onFailedToRecycleView(viewHolder);
        assertFalse(ret);

        verify(adapter, never()).onFailedToRecycleHeaderView(viewHolder);
        verify(adapter).onFailedToRecycleItemView(viewHolder);
        verify(adapter, never()).onFailedToRecycleFooterView(viewHolder);
    }

    @Test
    public void shouldDelegateCallToOnFailedToRecycleItemViewIfViewTypeIsItemTypeAndHaveFooter() {
        HeaderRecyclerViewAdapter<VH, H, T, F> adapter =
                spy(new HeaderRecyclerViewAdapterBuilder()
                        .withFooter(givenAFooter())
                        .withItems(givenAListWithFiveItems())
                        .build());
        VH viewHolder = givenAMockViewHolder();
        when(viewHolder.getAdapterPosition()).thenReturn(4);

        boolean ret = adapter.onFailedToRecycleView(viewHolder);
        assertFalse(ret);

        verify(adapter, never()).onFailedToRecycleHeaderView(viewHolder);
        verify(adapter).onFailedToRecycleItemView(viewHolder);
        verify(adapter, never()).onFailedToRecycleFooterView(viewHolder);
    }

    @Test
    public void shouldDelegateCallToOnFailedToRecycleItemViewIfViewTypeIsItemTypeAndNotHaveFooter() {
        HeaderRecyclerViewAdapter<VH, H, T, F> adapter =
                spy(new HeaderRecyclerViewAdapterBuilder()
                        .withItems(givenAListWithFiveItems())
                        .build());
        VH viewHolder = givenAMockViewHolder();
        when(viewHolder.getAdapterPosition()).thenReturn(4);

        boolean ret = adapter.onFailedToRecycleView(viewHolder);
        assertFalse(ret);

        verify(adapter, never()).onFailedToRecycleHeaderView(viewHolder);
        verify(adapter).onFailedToRecycleItemView(viewHolder);
        verify(adapter, never()).onFailedToRecycleFooterView(viewHolder);
    }

    @Test
    public void shouldDelegateCallToOnFailedToRecycleFooterViewIfViewTypeIsFooterType() {
        HeaderRecyclerViewAdapter<VH, H, T, F> adapter =
                spy(new HeaderRecyclerViewAdapterBuilder()
                        .withHeader(givenAHeader())
                        .withFooter(givenAFooter())
                        .withItems(givenAListWithFiveItems())
                        .build());
        VH viewHolder = givenAMockViewHolder();
        when(viewHolder.getAdapterPosition()).thenReturn(6);

        boolean ret = adapter.onFailedToRecycleView(viewHolder);
        assertFalse(ret);

        verify(adapter, never()).onFailedToRecycleHeaderView(viewHolder);
        verify(adapter, never()).onFailedToRecycleItemView(viewHolder);
        verify(adapter).onFailedToRecycleFooterView(viewHolder);
    }

    @Test
    public void shouldDelegateCallToOnHeaderViewAttachedToWindowIfViewTypeIsHeaderType() {
        HeaderRecyclerViewAdapter<VH, H, T, F> adapter =
                spy(new HeaderRecyclerViewAdapterBuilder().withHeader(givenAHeader())
                        .withItems(givenAListWithFiveItems())
                        .build());
        VH viewHolder = givenAMockViewHolder();
        when(viewHolder.getAdapterPosition()).thenReturn(0);

        adapter.onViewAttachedToWindow(viewHolder);

        verify(adapter).onHeaderViewAttachedToWindow(viewHolder);
        verify(adapter, never()).onItemViewAttachedToWindow(viewHolder);
        verify(adapter, never()).onFooterViewAttachedToWindow(viewHolder);
    }

    @Test
    public void shouldDelegateCallToOnItemViewAttachedToWindowIfViewTypeIsItemTypeAndHaveHeader() {
        HeaderRecyclerViewAdapter<VH, H, T, F> adapter =
                spy(new HeaderRecyclerViewAdapterBuilder().withHeader(givenAHeader())
                        .withItems(givenAListWithFiveItems())
                        .build());
        VH viewHolder = givenAMockViewHolder();
        when(viewHolder.getAdapterPosition()).thenReturn(1);

        adapter.onViewAttachedToWindow(viewHolder);

        verify(adapter, never()).onHeaderViewAttachedToWindow(viewHolder);
        verify(adapter).onItemViewAttachedToWindow(viewHolder);
        verify(adapter, never()).onFooterViewAttachedToWindow(viewHolder);
    }


    @Test
    public void shouldDelegateCallToOnItemViewAttachedToWindowIfViewTypeIsItemTypeAndNotHaveHeader() {
        HeaderRecyclerViewAdapter<VH, H, T, F> adapter =
                spy(new HeaderRecyclerViewAdapterBuilder()
                        .withItems(givenAListWithFiveItems())
                        .build());
        VH viewHolder = givenAMockViewHolder();
        when(viewHolder.getAdapterPosition()).thenReturn(0);

        adapter.onViewAttachedToWindow(viewHolder);

        verify(adapter, never()).onHeaderViewAttachedToWindow(viewHolder);
        verify(adapter).onItemViewAttachedToWindow(viewHolder);
        verify(adapter, never()).onFooterViewAttachedToWindow(viewHolder);
    }

    @Test
    public void shouldDelegateCallToOnItemViewAttachedToWindowIfViewTypeIsItemTypeAndHaveFooter() {
        HeaderRecyclerViewAdapter<VH, H, T, F> adapter =
                spy(new HeaderRecyclerViewAdapterBuilder()
                        .withFooter(givenAFooter())
                        .withItems(givenAListWithFiveItems())
                        .build());
        VH viewHolder = givenAMockViewHolder();
        when(viewHolder.getAdapterPosition()).thenReturn(4);

        adapter.onViewAttachedToWindow(viewHolder);

        verify(adapter, never()).onHeaderViewAttachedToWindow(viewHolder);
        verify(adapter).onItemViewAttachedToWindow(viewHolder);
        verify(adapter, never()).onFooterViewAttachedToWindow(viewHolder);
    }

    @Test
    public void shouldDelegateCallToOnItemViewAttachedToWindowIfViewTypeIsItemTypeAndNotHaveFooter() {
        HeaderRecyclerViewAdapter<VH, H, T, F> adapter =
                spy(new HeaderRecyclerViewAdapterBuilder()
                        .withItems(givenAListWithFiveItems())
                        .build());
        VH viewHolder = givenAMockViewHolder();
        when(viewHolder.getAdapterPosition()).thenReturn(4);

        adapter.onViewAttachedToWindow(viewHolder);

        verify(adapter, never()).onHeaderViewAttachedToWindow(viewHolder);
        verify(adapter).onItemViewAttachedToWindow(viewHolder);
        verify(adapter, never()).onFooterViewAttachedToWindow(viewHolder);
    }

    @Test
    public void shouldDelegateCallToOnFooterViewAttachedToWindowIfViewTypeIsFooterType() {
        HeaderRecyclerViewAdapter<VH, H, T, F> adapter =
                spy(new HeaderRecyclerViewAdapterBuilder()
                        .withHeader(givenAHeader())
                        .withFooter(givenAFooter())
                        .withItems(givenAListWithFiveItems())
                        .build());
        VH viewHolder = givenAMockViewHolder();
        when(viewHolder.getAdapterPosition()).thenReturn(6);

        adapter.onViewAttachedToWindow(viewHolder);

        verify(adapter, never()).onHeaderViewAttachedToWindow(viewHolder);
        verify(adapter, never()).onItemViewAttachedToWindow(viewHolder);
        verify(adapter).onFooterViewAttachedToWindow(viewHolder);
    }


    @Test
    public void shouldDelegateCallToOnHeaderViewDetachedFromWindowIfViewTypeIsHeaderType() {
        HeaderRecyclerViewAdapter<VH, H, T, F> adapter =
                spy(new HeaderRecyclerViewAdapterBuilder().withHeader(givenAHeader())
                        .withItems(givenAListWithFiveItems())
                        .build());
        VH viewHolder = givenAMockViewHolder();
        when(viewHolder.getAdapterPosition()).thenReturn(0);

        adapter.onViewDetachedFromWindow(viewHolder);

        verify(adapter).onHeaderViewDetachedFromWindow(viewHolder);
        verify(adapter, never()).onItemViewDetachedFromWindow(viewHolder);
        verify(adapter, never()).onFooterViewDetachedFromWindow(viewHolder);
    }

    @Test
    public void shouldDelegateCallToOnItemViewDetachedFromWindowIfViewTypeIsItemTypeAndHaveHeader() {
        HeaderRecyclerViewAdapter<VH, H, T, F> adapter =
                spy(new HeaderRecyclerViewAdapterBuilder().withHeader(givenAHeader())
                        .withItems(givenAListWithFiveItems())
                        .build());
        VH viewHolder = givenAMockViewHolder();
        when(viewHolder.getAdapterPosition()).thenReturn(1);

        adapter.onViewDetachedFromWindow(viewHolder);

        verify(adapter,never()).onHeaderViewDetachedFromWindow(viewHolder);
        verify(adapter).onItemViewDetachedFromWindow(viewHolder);
        verify(adapter, never()).onFooterViewDetachedFromWindow(viewHolder);
    }


    @Test
    public void shouldDelegateCallToOnItemViewDetachedFromWindowIfViewTypeIsItemTypeAndNotHaveHeader() {
        HeaderRecyclerViewAdapter<VH, H, T, F> adapter =
                spy(new HeaderRecyclerViewAdapterBuilder()
                        .withItems(givenAListWithFiveItems())
                        .build());
        VH viewHolder = givenAMockViewHolder();
        when(viewHolder.getAdapterPosition()).thenReturn(0);

        adapter.onViewDetachedFromWindow(viewHolder);

        verify(adapter,never()).onHeaderViewDetachedFromWindow(viewHolder);
        verify(adapter).onItemViewDetachedFromWindow(viewHolder);
        verify(adapter, never()).onFooterViewDetachedFromWindow(viewHolder);
    }

    @Test
    public void shouldDelegateCallToOnItemViewDetachedFromWindowIfViewTypeIsItemTypeAndHaveFooter() {
        HeaderRecyclerViewAdapter<VH, H, T, F> adapter =
                spy(new HeaderRecyclerViewAdapterBuilder()
                        .withFooter(givenAFooter())
                        .withItems(givenAListWithFiveItems())
                        .build());
        VH viewHolder = givenAMockViewHolder();
        when(viewHolder.getAdapterPosition()).thenReturn(4);

        adapter.onViewDetachedFromWindow(viewHolder);

        verify(adapter,never()).onHeaderViewDetachedFromWindow(viewHolder);
        verify(adapter).onItemViewDetachedFromWindow(viewHolder);
        verify(adapter, never()).onFooterViewDetachedFromWindow(viewHolder);
    }

    @Test
    public void shouldDelegateCallToOnItemViewDetachedFromWindowIfViewTypeIsItemTypeAndNotHaveFooter() {
        HeaderRecyclerViewAdapter<VH, H, T, F> adapter =
                spy(new HeaderRecyclerViewAdapterBuilder()
                        .withItems(givenAListWithFiveItems())
                        .build());
        VH viewHolder = givenAMockViewHolder();
        when(viewHolder.getAdapterPosition()).thenReturn(4);

        adapter.onViewDetachedFromWindow(viewHolder);

        verify(adapter,never()).onHeaderViewDetachedFromWindow(viewHolder);
        verify(adapter).onItemViewDetachedFromWindow(viewHolder);
        verify(adapter, never()).onFooterViewDetachedFromWindow(viewHolder);
    }

    @Test
    public void shouldDelegateCallToOnFooterViewDetachedFromWindowIfViewTypeIsFooterType() {
        HeaderRecyclerViewAdapter<VH, H, T, F> adapter =
                spy(new HeaderRecyclerViewAdapterBuilder()
                        .withHeader(givenAHeader())
                        .withFooter(givenAFooter())
                        .withItems(givenAListWithFiveItems())
                        .build());
        VH viewHolder = givenAMockViewHolder();
        when(viewHolder.getAdapterPosition()).thenReturn(6);

        adapter.onViewDetachedFromWindow(viewHolder);

        verify(adapter,never()).onHeaderViewDetachedFromWindow(viewHolder);
        verify(adapter,never()).onItemViewDetachedFromWindow(viewHolder);
        verify(adapter).onFooterViewDetachedFromWindow(viewHolder);
    }

    protected abstract VH givenAViewHolder();

    protected abstract H givenAHeader();

    protected abstract F givenAFooter();

    protected abstract List<T> givenAListWithFiveItems();

    protected abstract VH givenAMockViewHolder();
}
