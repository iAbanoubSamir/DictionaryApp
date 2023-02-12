package com.abanoub.dictionary.feature_dictionary.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.abanoub.dictionary.feature_dictionary.domain.model.Meaning
import com.abanoub.dictionary.feature_dictionary.domain.model.WordInfo

@Entity(tableName = "WordInfoTable")
data class WordInfoEntity(
    @PrimaryKey val id: Int? = null,
    val meanings: List<Meaning>,
    val origin: String,
    val phonetic: String,
    val word: String
) {
    fun toWordInfo(): WordInfo {
        return WordInfo(
            meanings = meanings,
            origin = origin,
            phonetic = phonetic,
            word = word
        )
    }
}