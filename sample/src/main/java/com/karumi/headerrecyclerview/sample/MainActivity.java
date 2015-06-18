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

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.github.pedrovgs.nox.NoxItem;
import com.karumi.headerrecyclerview.HeaderRecyclerViewAdapter;
import com.karumi.headerrecyclerview.HeaderSpanSizeLookup;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

  private static final int NUMBER_OF_COLUMNS = 2;
  private static final int FIVE_SECONDS = 5000;

  private RecyclerView recyclerView;
  private HeaderRecyclerViewAdapter adapter;
  private boolean loading;
  private boolean hasMore = true;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main_activity);
    initializeRecyclerView();
    fillRecyclerView();
    hookLoadMoreListener();
  }

  private void hookLoadMoreListener() {
    recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
      @Override public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        int visibleItemCount = recyclerView.getChildCount();
        GridLayoutManager layoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
        int totalItemCount = layoutManager.getItemCount();
        int firstVisibleItem = layoutManager.findFirstVisibleItemPosition();
        int visibleThreshold = 5;
        boolean isCloseToTheEnd =
            (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold);
        if (!loading && isCloseToTheEnd && hasMore) {
          loadMore();
        }
      }
    });
  }

  private void loadMore() {
    loading = true;
    new Handler().postDelayed(new Runnable() {
      @Override public void run() {
        adapter.hideFooter();
        List<DragonBallCharacter> originalCharacters = getDragonBallCharacters();
        List<DragonBallCharacter> characters =
            new ArrayList<DragonBallCharacter>(originalCharacters);
        characters.addAll(originalCharacters);
        adapter.setItems(characters);
        adapter.notifyDataSetChanged();
        loading = false;
        hasMore = false;
      }
    }, FIVE_SECONDS);
  }

  private void initializeRecyclerView() {
    adapter = new DragonBallAdapter();
    recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    recyclerView.setHasFixedSize(true);
    GridLayoutManager layoutManager = new GridLayoutManager(this, NUMBER_OF_COLUMNS);
    HeaderSpanSizeLookup headerSpanSizeLookup = new HeaderSpanSizeLookup(adapter, layoutManager);
    layoutManager.setSpanSizeLookup(headerSpanSizeLookup);
    recyclerView.setLayoutManager(layoutManager);
    recyclerView.setAdapter(adapter);
  }

  private void fillRecyclerView() {
    List<DragonBallCharacter> characters = getDragonBallCharacters();
    DragonBallHeader header = getHeader(characters);
    DragonBallFooter footer = getFooter();
    adapter.setHeader(header);
    adapter.setItems(characters);
    adapter.setFooter(footer);
    adapter.notifyDataSetChanged();
  }

  private List<DragonBallCharacter> getDragonBallCharacters() {
    List<DragonBallCharacter> characters = new ArrayList<>();
    characters.add(new DragonBallCharacter("Goku",
        "http://www.dragonballz.com/images/characters/content-keyart-characters-goku.jpg", 100));
    characters.add(new DragonBallCharacter("Freezer",
        "http://www.dragonballz.com/images/characters/content-keyart-characters-frieza.jpg", 82));
    characters.add(new DragonBallCharacter("Buu",
        "http://www.dragonballz.com/images/characters/content-keyart-characters-buu.jpg", 88));
    characters.add(new DragonBallCharacter("Master Roshi",
        "http://www.dragonballz.com/images/characters/content-keyart-characters-master-roshi.jpg",
        8));
    characters.add(new DragonBallCharacter("Cell",
        "http://www.dragonballz.com/images/characters/content-keyart-characters-cell.jpg", 84));
    characters.add(new DragonBallCharacter("Tien & Chiaotzu",
        "http://www.dragonballz.com/images/characters/content-keyart-characters-tien-chiaotzu.jpg",
        45));
    characters.add(new DragonBallCharacter("Piccolo",
        "http://www.dragonballz.com/images/characters/content-keyart-characters-piccolo.jpg", 80));
    characters.add(new DragonBallCharacter("Raditz",
        "http://www.dragonballz.com/images/characters/content-keyart-characters-raditz.jpg", 35));
    characters.add(new DragonBallCharacter("Trunks",
        "http://www.dragonballz.com/images/characters/content-keyart-characters-trunks.jpg", 85));
    characters.add(new DragonBallCharacter("Beerus",
        "http://www.dragonballz.com/images/characters/content-keyart-characters-beerus.jpg", 98));
    characters.add(new DragonBallCharacter("Babidi",
        "http://www.dragonballz.com/images/characters/content-keyart-characters-babidi.jpg", 30));
    characters.add(new DragonBallCharacter("Gohan",
        "http://www.dragonballz.com/images/characters/content-keyart-characters-gohan.jpg", 70));
    characters.add(new DragonBallCharacter("Vegeta",
        "http://animepolls.com/wp-content/uploads/2015/03/Vegeta_by_aznfanaticwarrior.png", 90));
    characters.add(new DragonBallCharacter("Krillin",
        "http://www.dragonballz.com/images/characters/content-keyart-characters-krillin.jpg", 10));
    characters.add(new DragonBallCharacter("Nappa",
        "http://www.dragonballz.com/images/characters/content-keyart-characters-nappa.jpg", 45));
    characters.add(new DragonBallCharacter("King Kai",
        "http://www.dragonballz.com/images/characters/content-keyart-characters-king-kai.jpg", 20));
    return characters;
  }

  private DragonBallHeader getHeader(List<DragonBallCharacter> characters) {
    List<NoxItem> noxItems = new ArrayList<NoxItem>();
    for (int i = 0; i < 3; i++) {
      for (DragonBallCharacter character : characters) {
        noxItems.add(new NoxItem(character.getPhoto()));
      }
    }
    return new DragonBallHeader(noxItems);
  }

  public DragonBallFooter getFooter() {
    String loadMoreMessage = getString(R.string.load_more_message);
    return new DragonBallFooter(loadMoreMessage);
  }
}
