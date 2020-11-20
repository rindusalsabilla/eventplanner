package id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.repository.LoginRepository

class CreateAccountViewModelFactory (private val loginRepository: LoginRepository)
    : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T: ViewModel?> create(modelClass: Class<T>): T {
        return CreateAccountViewModel(loginRepository) as T
    }
}