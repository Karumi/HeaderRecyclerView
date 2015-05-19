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

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

/**
 * RecyclerView.ViewHolder extension which renders a DragonBallCharacter instance into a view.
 */
public class CharacterViewHolder extends RecyclerView.ViewHolder {

  private final ImageView photoImageView;
  private final TextView nameTextView;
  private final TextView levelTextView;
  private final Context context;

  public CharacterViewHolder(View itemView) {
    super(itemView);
    this.context = itemView.getContext();
    this.photoImageView = (ImageView) itemView.findViewById(R.id.iv_photo);
    this.nameTextView = (TextView) itemView.findViewById(R.id.tv_name);
    this.levelTextView = (TextView) itemView.findViewById(R.id.tv_level);
  }

  public void render(DragonBallCharacter character) {
    String photo = character.getPhoto();
    String name = character.getName();
    String level = "Level :" + character.getLevel();

    Picasso.with(context).load(photo).into(photoImageView);
    nameTextView.setText(name);
    levelTextView.setText(level);
  }
}
