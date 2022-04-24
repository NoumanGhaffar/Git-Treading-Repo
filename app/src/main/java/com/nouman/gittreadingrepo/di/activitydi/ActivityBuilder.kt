package com.nouman.gittreadingrepo.di.activitydi

import androidx.lifecycle.ViewModel
import com.nouman.gittreadingrepo.di.viewmodeldi.ViewModelKey
import com.nouman.gittreadingrepo.ui.MainActivity
import com.nouman.gittreadingrepo.ui.RepoDetailsActivity
import com.nouman.gittreadingrepo.viewmodel.RepoDetailsViewModel
import com.nouman.gittreadingrepo.viewmodel.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

/*
 */
@Module
interface ActivityBuilder {

    @ActivityScope
    @ContributesAndroidInjector(
        modules = [
            ActivityModule::class
        ]
    )
    fun contributeMainActivity(): MainActivity

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindMainViewModel(viewModel: MainViewModel): ViewModel

    @ActivityScope
    @ContributesAndroidInjector(
        modules = [
            ActivityModule::class
        ]
    )

    fun contributeRepoDetailsActivity(): RepoDetailsActivity

    @Binds
    @IntoMap
    @ViewModelKey(RepoDetailsViewModel::class)
    fun bindDetailsViewModel(viewModel: RepoDetailsViewModel): ViewModel

}
