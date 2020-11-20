package id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.utils

import android.content.Context
import id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.data.event.EventDb
import id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.data.login.LoginDb
import id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.data.profile.ProfileDb
import id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.repository.EventRepository
import id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.repository.LoginRepository
import id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.repository.ProfileRepository
import id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.ui.event.EventViewModelFactory
import id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.ui.auth.CreateAccountViewModelFactory
import id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.ui.auth.LoginViewModelFactory
import id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.ui.profile.ProfileViewModelFactory

object InjectorUtils {
    fun provideEventViewModelFactory(context: Context): EventViewModelFactory {

        val eventRepository = EventRepository.getInstance(EventDb.getInstance(context).eventDao())
        return EventViewModelFactory(eventRepository)
    }

    fun provideLoginViewModelFactory(context: Context): LoginViewModelFactory {

        val loginRepository = LoginRepository.getInstance(LoginDb.getInstance(context).loginDao())
        return LoginViewModelFactory(loginRepository)
    }

    fun provideRegisterViewModelFactory(context: Context): CreateAccountViewModelFactory {

        val registerRepository = LoginRepository.getInstance(LoginDb.getInstance(context).loginDao())
        return CreateAccountViewModelFactory(registerRepository)
    }

    fun provideProfileViewModelFactory(context: Context): ProfileViewModelFactory {

        val profileRepository = ProfileRepository.getInstance(ProfileDb.getInstance(context).profileDao())
        return ProfileViewModelFactory(profileRepository)
    }
}