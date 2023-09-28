package com.example.chatapplive.component

import com.example.chatapplive.LoginFragment
import com.example.chatapplive.MainActivity
import com.example.chatapplive.MyApplication
import com.example.chatapplive.modules.networkModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [networkModule::class])
interface DComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(loginFragment: LoginFragment)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: MyApplication): Builder
        fun build(): DComponent
    }
}