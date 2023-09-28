package com.example.chatapplive

import android.app.Application
import com.example.chatapplive.component.DComponent
import com.example.chatapplive.component.DaggerDComponent

class MyApplication : Application() {
        lateinit var appComponent: DComponent

        override fun onCreate() {
            super.onCreate()

            appComponent = DaggerDComponent.builder()
                .application(this)
                .build()

        }
    }
