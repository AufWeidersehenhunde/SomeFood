package com.example.somefood

import androidx.room.Room
import com.example.somefood.AuthAndAuthorize.AuthAndRegViewModel
import com.example.somefood.AuthFragment.AuthViewModel
import com.example.somefood.AuthSuccessForNonCreator.NonCreatorListViewModel
import com.example.somefood.DBandProvider.DBprovider
import com.example.somefood.MainActivity.MainActivityViewModel
import com.example.somefood.RegistrationFragment.RegistrationViewModel
import com.example.somefood.repository.RepositorySQL
import com.github.terrakok.cicerone.Cicerone
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

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
            "database"
        ).build()
    }
    single { get<DBprovider>().DaoUser() }
    single { RepositorySQL(get()) }
}