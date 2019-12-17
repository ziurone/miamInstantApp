package com.example.miaminstantapp.injection.modules

import android.content.Context
import androidx.room.Room
import com.example.miaminstantapp.injection.qualifiers.AppContext
import com.example.miaminstantapp.persistence.MiamDataBase
import com.example.miaminstantapp.persistence.UserIngredientDao
import com.example.miaminstantapp.persistence.VolumeUnitDao
import com.example.miaminstantapp.scopes.PerApplication
import dagger.Module
import dagger.Provides

@Module
class RoomModule {

    @Provides
    @PerApplication
    fun provideRoomDB(@AppContext context: Context): MiamDataBase = Room.databaseBuilder(context, MiamDataBase::class.java, MiamDataBase.NAME).build()

    @Provides
    @PerApplication
    fun provideUserIngredientDao(miamDataBase: MiamDataBase): UserIngredientDao = miamDataBase.userIngredientDao()

    @Provides
    @PerApplication
    fun provideVolumeUnitDao(miamDataBase: MiamDataBase): VolumeUnitDao = miamDataBase.volumeUnitDao()
}