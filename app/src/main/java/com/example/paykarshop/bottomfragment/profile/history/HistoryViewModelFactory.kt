package com.example.paykarshop.bottomfragment.profile.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.paykarshop.bottomfragment.profile.LoginRepository
import com.example.paykarshop.bottomfragment.profile.LoginViewModel

class HistoryViewModelFactory constructor(private val historyRepository: HistoryRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(HistoryViewModel::class.java)) {
            HistoryViewModel(this.historyRepository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}