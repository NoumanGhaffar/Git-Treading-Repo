package com.nouman.gittreadingrepo.di

import android.app.Application
import com.nouman.gittreadingrepo.BaseApplication
import com.nouman.gittreadingrepo.di.activitydi.ActivityBuilder
import com.nouman.gittreadingrepo.di.viewmodeldi.ViewModelFactoryModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

/**
 * The main component of the app.
 * Add all application scoped modules.
 */
@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        DatabaseModule::class,
        NetworkModule::class,
        ActivityBuilder::class,
        ViewModelFactoryModule::class,
    ]
)
interface AppComponent : AndroidInjector<BaseApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun networkModule(networkModule: NetworkModule): Builder

        fun build(): AppComponent
    }

    override fun inject(app: BaseApplication)
}
