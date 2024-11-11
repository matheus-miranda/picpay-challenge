package com.picpay.desafio.android.core.database.di

import android.content.Context
import androidx.room.Room
import com.picpay.desafio.android.core.database.PicPayDatabase
import com.picpay.desafio.android.core.database.contacts.di.contactsDaoModule
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val coreDatabaseModule = module {
    singleOf(::provideDatabase)
    includes(contactsDaoModule)
}

internal fun provideDatabase(context: Context): PicPayDatabase = Room.databaseBuilder(
    context, PicPayDatabase::class.java, "app.dp"
).fallbackToDestructiveMigration().build()
