package com.abanoub.dictionary.feature_dictionary.di

import android.app.Application
import androidx.room.Room
import com.abanoub.dictionary.feature_dictionary.data.local.DictionaryDatabase
import com.abanoub.dictionary.feature_dictionary.data.remote.DictionaryApi
import com.abanoub.dictionary.feature_dictionary.data.repository.WordInfoRepositoryImpl
import com.abanoub.dictionary.feature_dictionary.data.util.GsonParser
import com.abanoub.dictionary.feature_dictionary.domain.repository.WordInfoRepository
import com.abanoub.dictionary.feature_dictionary.domain.usecase.GetWordInfoUseCase
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideGetWordInfoUseCase(repository: WordInfoRepository): GetWordInfoUseCase {
        return GetWordInfoUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideWordInfoRepository(
        api: DictionaryApi,
        db: DictionaryDatabase
    ): WordInfoRepository {
        return WordInfoRepositoryImpl(api, db.wordInfoDao)
    }

    @Provides
    @Singleton
    fun provideDictionaryDatabase(app: Application): DictionaryDatabase {
        return Room.databaseBuilder(
            app,
            DictionaryDatabase::class.java,
            "dictionary_db"
        ).addTypeConverter(GsonParser(Gson()))
            .build()
    }

    @Provides
    @Singleton
    fun provideDictionaryApi(): DictionaryApi {
        return Retrofit.Builder()
            .baseUrl(DictionaryApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DictionaryApi::class.java)
    }
}