package com.example.somefood.AuthSuccess

import androidx.lifecycle.ViewModel
import com.example.somefood.DBandProvider.UsersDb
import com.example.somefood.repository.RepositorySQL
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.flow.MutableStateFlow

class ListViewModel (
    private val router: Router,
    private val repositorySQL: RepositorySQL
        ): ViewModel() {
    val check = MutableStateFlow<String>("")

            fun checkStatus(){
               check.value =  repositorySQL.checkAn("0")
            }
}