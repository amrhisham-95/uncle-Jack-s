package com.example.unclejacks.di

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.example.unclejacks.models.CartProductsFinal
import com.example.unclejacks.models.FavoriteProducts
import com.example.unclejacks.models.Fruits
import com.example.unclejacks.models.Juices
import com.example.unclejacks.roomDatabase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLiveDataVariableFruits(appDB: RoomDatabaseClassFruits) : LiveData<List<Fruits>> = appDB.dataDao().readAllDataFruits()

    @Provides
    @Singleton
    fun provideGetDaoFruits(appDB: RoomDatabaseClassFruits): DaoFruit {
        return appDB.dataDao()
    }

    @Provides
    @Singleton
    fun provideRoomDatabaseFruits(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,RoomDatabaseClassFruits::class.java,"fruit_table"
    ).build()


    /****************************************************************************/

    @Provides
    @Singleton
    fun provideLiveDataVariableJuices(appDB: RoomDatabaseClassJuices) : LiveData<List<Juices>> = appDB.dataDao().readAllDataJuices()

    @Provides
    @Singleton
    fun provideGetDaoJuices(appDB: RoomDatabaseClassJuices): DaoJuice {
        return appDB.dataDao()
    }

    @Provides
    @Singleton
    fun provideRoomDatabaseJuices(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,RoomDatabaseClassJuices::class.java,"juice_table"
    ).build()

    /****************************************************************************/

    @Provides
    @Singleton
    fun provideLiveDataVariableFinal(appDB: RoomDatabaseFinal) : LiveData<List<CartProductsFinal>> = appDB.dataDao().readAllDataFinal()


    @Provides
    @Singleton
    fun provideGetDaoFinal(appDB: RoomDatabaseFinal): DaoFinalBuy {
        return appDB.dataDao()
    }

    @Provides
    @Singleton
    fun provideRoomDatabaseFinal(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,RoomDatabaseFinal::class.java,"final_table"
    ).build()


    /****************************************************************************/

    @Provides
    @Singleton
    fun provideLiveDataVariableFavorite(appDB: RoomDatabaseFavorite) : LiveData<List<FavoriteProducts>> = appDB.dataDao().readAllDataFavorite()


    @Provides
    @Singleton
    fun provideGetDaoFavorite(appDB: RoomDatabaseFavorite): DaoFavoriteBuy {
        return appDB.dataDao()
    }

    @Provides
    @Singleton
    fun provideRoomDatabaseFavorite(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,RoomDatabaseFavorite::class.java,"favorite_table"
    ).build()





}