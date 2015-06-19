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
import android.view.View;
import android.widget.TextView;

/**
 * RecyclerView.ViewHolder extension created to render a DragonBallFooter instance.
 */
public class FooterViewHolder extends RecyclerView.ViewHolder {

  private final TextView loadMoreMessage;

  public FooterViewHolder(View itemView) {
    super(itemView);
    this.loadMoreMessage = (TextView) itemView.findViewById(R.id.tv_load_more);
  }

  public void render(DragonBallFooter footer) {
    String loadMoreText = footer.getLoadMoreMessage();
    loadMoreMessage.setText(loadMoreText);
  }
}
