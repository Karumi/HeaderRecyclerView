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

import com.github.pedrovgs.nox.NoxItem;
import java.util.List;

/**
 * Presentation model created to contain all the information needed to draw the header of a list of
 * DragonBall characters.
 */
public class DragonBallHeader {

  private final List<NoxItem> dragonBallCharacters;

  public DragonBallHeader(List<NoxItem> dragonBallCharacters) {
    this.dragonBallCharacters = dragonBallCharacters;
  }

  public List<NoxItem> getDragonBallCharacters() {
    return dragonBallCharacters;
  }
}
