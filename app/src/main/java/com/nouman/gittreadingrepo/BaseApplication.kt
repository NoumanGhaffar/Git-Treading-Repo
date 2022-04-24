package com.nouman.gittreadingrepo

import android.content.Context
import com.nouman.gittreadingrepo.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

/**
 * Application class. [DaggerApplication] injects classes behind scene.
 */
open class BaseApplication : DaggerApplication() {

  private var context: Context? = null
    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        context = applicationContext;
    }

  open fun getAppContext(): Context? {
    return context
  }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }
}
