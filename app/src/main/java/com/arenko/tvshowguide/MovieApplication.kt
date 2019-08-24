package com.arenko.tvshowguide

import android.app.Application
import android.content.Context
import com.arenko.tvshowguide.di.AppComponent
import com.arenko.tvshowguide.di.AppModule
import com.arenko.tvshowguide.di.ContextModule
import com.arenko.tvshowguide.di.DaggerAppComponent


class MovieApplication : Application() {
    lateinit var appComponent: AppComponent
        internal set
    lateinit var context: Context

    override fun onCreate() {
        super.onCreate()
        context = this
        appComponent =
            DaggerAppComponent.builder().contextModule(ContextModule(this)).appModule(AppModule())
                .build()
    }

    override fun attachBaseContext(context: Context) {
        super.attachBaseContext(context)
    }
}
