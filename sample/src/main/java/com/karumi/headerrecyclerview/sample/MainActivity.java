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
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.github.pedrovgs.nox.NoxItem;
import com.karumi.headerrecyclerview.HeaderRecyclerViewAdapter;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

  private RecyclerView recyclerView;
  private HeaderRecyclerViewAdapter adapter;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main_activity);
    initializeRecyclerView();
    fillRecyclerView();
  }

  private void initializeRecyclerView() {
    recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    recyclerView.setHasFixedSize(true);
    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
    recyclerView.setLayoutManager(layoutManager);
    adapter = new DragonBallAdapter();
  }

  private void fillRecyclerView() {
    List<DragonBallCharacter> characters = getDragonBallCharacters();
    DragonBallHeader header = getHeader(characters);
    adapter.setHeader(header);
    adapter.setItems(characters);
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
    characters.add(new DragonBallCharacter("Piccolo",
        "http://www.dragonballz.com/images/characters/content-keyart-characters-piccolo.jpg", 80));
    characters.add(new DragonBallCharacter("Broly", "http://vignette2.wikia.nocookie"
        + ".net/dragonball/images/f/ff/Lssj3-broly-vs-ssj3-goku-and-ulimate-gohan-9014"
        + ".jpg/revision/latest?cb=20110724000354&path-prefix=es", 99));
    characters.add(new DragonBallCharacter("Beerus",
        "http://www.dragonballz.com/images/characters/content-keyart-characters-beerus.jpg", 98));
    characters.add(new DragonBallCharacter("Gohan",
        "http://www.dragonballz.com/images/characters/content-keyart-characters-gohan.jpg", 70));
    characters.add(new DragonBallCharacter("Vegeta",
        "http://animepolls.com/wp-content/uploads/2015/03/Vegeta_by_aznfanaticwarrior.png", 90));
    characters.add(new DragonBallCharacter("Krillin",
        "http://www.dragonballz.com/images/characters/content-keyart-characters-krillin.jpg", 10));

    return characters;
  }

  private DragonBallHeader getHeader(List<DragonBallCharacter> characters) {
    List<NoxItem> noxItems = new ArrayList<NoxItem>();
    for (DragonBallCharacter character : characters) {
      noxItems.add(new NoxItem(character.getPhoto()));
    }
    return new DragonBallHeader(noxItems);
  }
}
