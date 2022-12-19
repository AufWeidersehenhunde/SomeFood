package com.example.somefood

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.somefood.AuthAndAuthorize.AuthAndRegViewModel
import com.example.somefood.AuthFragment.AuthViewModel
import com.example.somefood.AuthSuccessForNonCreator.NonCreatorListViewModel
import com.example.somefood.DBandProvider.DBprovider
import com.example.somefood.DBandProvider.DataGenerator
import com.example.somefood.MainActivity.MainActivityViewModel
import com.example.somefood.RegistrationFragment.RegistrationViewModel
import com.example.somefood.repository.RepositorySQL
import com.github.terrakok.cicerone.Cicerone
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import java.util.concurrent.Executors


val appModule = module {
    val cicerone = Cicerone.create()
    single { cicerone.router }
    single { cicerone.getNavigatorHolder() }
    viewModel { MainActivityViewModel(get()) }
    viewModel { RegistrationViewModel(get(),get()) }
    viewModel { AuthAndRegViewModel(get()) }
    viewModel { AuthViewModel(get(), get()) }
    viewModel { NonCreatorListViewModel(get(),get()) }
    single {
        Room.databaseBuilder(
            androidApplication().applicationContext,
            DBprovider::class.java,
            "database")
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        //pre-populate data
                        Executors.newSingleThreadExecutor().execute {
                            DBprovider.instance?.let {
                                it.DaoUser().insertFoods(DataGenerator.getFoods())
                            }
                        }
                    }
                }).build()
        }


    single { get<DBprovider>().DaoUser() }
    single { RepositorySQL(get()) }
}