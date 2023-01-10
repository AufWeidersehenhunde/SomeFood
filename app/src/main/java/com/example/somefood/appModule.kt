package com.example.somefood

import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.somefood.AuthAndAuthorize.AuthAndRegViewModel
import com.example.somefood.AuthFragment.AuthViewModel
import com.example.somefood.AuthSuccessForNonCreator.NonCreatorListViewModel
import com.example.somefood.DBandProvider.DBprovider
import com.example.somefood.DBandProvider.FoodDb
import com.example.somefood.Dao.DaoUser
import com.example.somefood.FavoriteFragment.FavoriteViewModel
import com.example.somefood.MainActivity.MainActivityViewModel
import com.example.somefood.RegistrationFragment.RegistrationViewModel
import com.example.somefood.fragmentContainer.ContainerViewModel
import com.example.somefood.profileFragment.ProfileViewModel
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
    viewModel { ProfileViewModel(get(), get()) }
    viewModel { FavoriteViewModel(get(), get())}
    viewModel { ContainerViewModel(get(),get()) }
    viewModel{BottomSheetViewModel(get(), get())}
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
        FoodDb("1", "Vegetable Shepherd's Pie", "https://www.themealdb.com/images/media/meals/w8umt11583268117.jpg"),
        FoodDb("2", "Peanut Butter Cheesecake", "https://www.themealdb.com/images/media/meals/qtuuys1511387068.jpg"),
        FoodDb("3", "Christmas cake", "https://www.themealdb.com/images/media/meals/ldnrm91576791881.jpg"),
        FoodDb("4", "Rosół (Polish Chicken Soup)", "https://www.themealdb.com/images/media/meals/lx1kkj1593349302.jpg"),
        FoodDb("5", "Eton Mess", "https://www.themealdb.com/images/media/meals/uuxwvq1483907861.jpg"),
        FoodDb("6", "Japanese Katsudon", "https://www.themealdb.com/images/media/meals/d8f6qx1604182128.jpg"),
        FoodDb("7", "Chicken Karaage", "https://www.themealdb.com/images/media/meals/tyywsw1505930373.jpg"),
        FoodDb("8", "Beef Bourguignon", "https://www.themealdb.com/images/media/meals/vtqxtu1511784197.jpg"),
        FoodDb("9", "Laksa King Prawn Noodles", "https://www.themealdb.com/images/media/meals/rvypwy1503069308.jpg"),
        FoodDb("10", "Chicken Parmentier", "https://www.themealdb.com/images/media/meals/uwvxpv1511557015.jpg"),
        FoodDb("11", "Beef and Mustard Pie", "https://www.themealdb.com/images/media/meals/sytuqu1511553755.jpg"),
        FoodDb("12", "Corned Beef and Cabbage", "https://www.themealdb.com/images/media/meals/xb97a81583266727.jpg"),
    )
}