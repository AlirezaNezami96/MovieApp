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

package alireza.nezami.database

import alireza.nezami.database.converter.GenreListTypeConverter
import alireza.nezami.database.converter.ProductionCompanyListTypeConverter
import alireza.nezami.database.converter.ProductionCountryListTypeConverter
import alireza.nezami.database.converter.SpokenLanguageListTypeConverter
import alireza.nezami.database.dao.FavoriteDao
import alireza.nezami.database.dao.GenreDao
import alireza.nezami.database.entity.genre.GenreEntity
import alireza.nezami.database.entity.movie.BelongsToCollectionEntity
import alireza.nezami.database.entity.movie.MovieDetailEntity
import alireza.nezami.database.entity.movie.ProductionCompanyEntity
import alireza.nezami.database.entity.movie.ProductionCountryEntity
import alireza.nezami.database.entity.movie.SpokenLanguageEntity
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [
        GenreEntity::class,
        BelongsToCollectionEntity::class,
        MovieDetailEntity::class,
        ProductionCompanyEntity::class,
        ProductionCountryEntity::class,
        SpokenLanguageEntity::class,
    ],
    version = 1,
    exportSchema = true,

    )
@TypeConverters(
    GenreListTypeConverter::class,
    ProductionCompanyListTypeConverter::class,
    ProductionCountryListTypeConverter::class,
    SpokenLanguageListTypeConverter::class,
)
abstract class MovieAppDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
    abstract fun genreDao(): GenreDao
}
