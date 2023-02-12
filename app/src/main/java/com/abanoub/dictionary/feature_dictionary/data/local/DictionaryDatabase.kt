package com.abanoub.dictionary.feature_dictionary.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.abanoub.dictionary.feature_dictionary.data.local.entity.WordInfoEntity

@Database(
    entities = [WordInfoEntity::class],
    version = 1
)

@TypeConverters(Converters::class)
abstract class DictionaryDatabase : RoomDatabase() {

    abstract val wordInfoDao: WordInfoDao

}