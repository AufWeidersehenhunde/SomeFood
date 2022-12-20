package com.example.somefood

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.somefood.AuthAndAuthorize.AuthAndRegViewModel
import com.example.somefood.AuthFragment.AuthViewModel
import com.example.somefood.AuthSuccessForNonCreator.NonCreatorListViewModel
import com.example.somefood.DBandProvider.DBprovider
import com.example.somefood.DBandProvider.FoodDb
import com.example.somefood.Dao.DaoUser
import com.example.somefood.MainActivity.MainActivityViewModel
import com.example.somefood.RegistrationFragment.RegistrationViewModel
import com.example.somefood.repository.RepositorySQL
import com.github.terrakok.cicerone.Cicerone
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import java.io.File
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
//                        db.execSQL(/* sql = */ "INSERT INTO food VALUES (230, 1, 222);")
//                        db.execSQL(/* sql = */ "INSERT INTO food VALUES (231, 2, 222);")
//                        db.execSQL(/* sql = */ "INSERT INTO food VALUES (232, 3, 222);")
//                        db.execSQL(/* sql = */ "INSERT INTO food VALUES (234, 5, 222);")
//                        db.execSQL(/* sql = */ "INSERT INTO food VALUES (233, 4, 222);")
                        Executors.newSingleThreadExecutor().execute {
                            get<DaoUser>().insertFoods(getFoods())
                        }
                    }
                }).build()


        }


    single { get<DBprovider>().DaoUser() }
    single { RepositorySQL(get()) }


}

fun getFoods(): List<FoodDb>{
    return listOf(
        FoodDb("1", "Noma0n", "https://avatars.githubusercontent.com/u/1?v=4"),
        FoodDb("2", "Noma1n", "https://rickandmortyapi.com/api/character/avatar/202.jpeg"),
        FoodDb("3", "Noma2n", "https://rickandmortyapi.com/api/character/avatar/202.jpeg")
    )
}