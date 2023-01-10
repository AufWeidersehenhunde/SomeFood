package com.example.somefood.Orders

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.somefood.DBandProvider.UsersDb
import com.example.somefood.repository.RepositorySQL
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class OrdersViewModel (
    private val router: Router,
    private val repositorySQL: RepositorySQL
) : ViewModel() {
    private val _user = MutableStateFlow<UsersDb?>(null)



    fun checkStatus(uuid: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _user.value = repositorySQL.checkStatus(uuid)
        }
    }



}