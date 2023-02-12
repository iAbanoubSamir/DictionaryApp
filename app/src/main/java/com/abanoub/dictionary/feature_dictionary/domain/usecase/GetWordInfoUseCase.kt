package com.abanoub.dictionary.feature_dictionary.domain.usecase

import com.abanoub.dictionary.core.util.Resource
import com.abanoub.dictionary.feature_dictionary.domain.model.WordInfo
import com.abanoub.dictionary.feature_dictionary.domain.repository.WordInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

class GetWordInfoUseCase(
    private val repository: WordInfoRepository
) {

    operator fun invoke(word: String): Flow<Resource<List<WordInfo>>> {
        if (word.isBlank()) {
            return emptyFlow()
        }
        return repository.getWordInfo(word)
    }
}