package com.nouman.gittreadingrepo.di.viewmodeldi

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import com.nouman.gittreadingrepo.di.viewmodeldi.ViewModelFactory

/**
 * Module to provide [ViewModelProvider.Factory]
 */
@Module
interface ViewModelFactoryModule {

  @Binds fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
