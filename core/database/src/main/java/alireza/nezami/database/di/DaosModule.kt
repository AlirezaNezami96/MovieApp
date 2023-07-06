/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package alireza.nezami.database.di

import alireza.nezami.database.MovieAppDatabase
import alireza.nezami.database.dao.FavoriteDao
import alireza.nezami.database.dao.GenreDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DaosModule {
    @Provides
    fun providesFavoriteDao(
        database: MovieAppDatabase,
    ): FavoriteDao = database.favoriteDao()

    @Provides
    fun providesGenreDao(
        database: MovieAppDatabase,
    ): GenreDao = database.genreDao()

}
