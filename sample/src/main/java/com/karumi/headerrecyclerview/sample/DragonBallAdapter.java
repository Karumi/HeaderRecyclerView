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

package com.karumi.headerrecyclerview.sample;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.karumi.headerrecyclerview.HeaderRecyclerViewAdapter;

/**
 * HeaderRecyclerViewAdapter extension created to show how to use the library using DragonBall
 * characters.
 */
public class DragonBallAdapter extends
    HeaderRecyclerViewAdapter<RecyclerView.ViewHolder, DragonBallHeader, DragonBallCharacter> {

  @Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
    RecyclerView.ViewHolder viewHolder;
    LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
    if (isHeaderType(viewType)) {
      View headerView = inflater.inflate(R.layout.row_dragon_ball_header, viewGroup, false);
      viewHolder = new HeaderViewHolder(headerView);
    } else {
      View characterView = inflater.inflate(R.layout.row_dragon_ball_character, viewGroup, false);
      viewHolder = new CharacterViewHolder(characterView);
    }
    return viewHolder;
  }

  @Override public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
    if (isHeaderPosition(position)) {
      DragonBallHeader header = getHeader();
      HeaderViewHolder headerViewHolder = (HeaderViewHolder) viewHolder;
      headerViewHolder.render(header);
    } else {
      DragonBallCharacter character = getItem(position);
      CharacterViewHolder characterViewHolder = (CharacterViewHolder) viewHolder;
      characterViewHolder.render(character);
    }
  }
}
