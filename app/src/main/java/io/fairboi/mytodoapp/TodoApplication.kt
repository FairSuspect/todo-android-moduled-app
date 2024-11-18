package io.fairboi.mytodoapp

import android.app.Application
import io.fairboi.mytodoapp.di.DaggerAppComponent

class TodoApplication : Application() {
    internal val appComponent by lazy {
        DaggerAppComponent
            .factory()
            .create(applicationContext)
    }

    override fun onCreate() {
        super.onCreate()

        appComponent.inject(this)

    }
}