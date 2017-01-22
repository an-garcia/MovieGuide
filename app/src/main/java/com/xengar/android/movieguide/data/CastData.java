/*
 * Copyright (C) 2017 Angel Garcia
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.xengar.android.movieguide.data;

/**
 * CastData
 */
public class CastData {
    private final String actorName;
    private final String profilePath;
    private final String character;
    private final int personId;
    private final int castOrder;

    // Constructor
    public CastData(String actorName, String profilePath, String character, int personId,
                    int castOrder) {
        this.actorName = actorName;
        this.profilePath = profilePath;
        this.character = character;
        this.personId = personId;
        this.castOrder = castOrder;
    }

    // Getters
    public String getCastName() {
        return actorName;
    }

    public String getCastImagePath() {
        return profilePath;
    }

    public String getCharacter() {
        return character;
    }

    public int getPersonId() {
        return personId;
    }

    public int getCastOrder() {
        return castOrder;
    }
}
