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

/**
 * Presentation model created to contain all the information needed to draw a DragonBall character.
 */
public class DragonBallCharacter {

  private final String name;
  private final String photo;
  private final int level;

  public DragonBallCharacter(String name, String photo, int level) {
    this.name = name;
    this.photo = photo;
    this.level = level;
  }

  public String getName() {
    return name;
  }

  public String getPhoto() {
    return photo;
  }

  public int getLevel() {
    return level;
  }
}
