package com.miftahulhudaf.sahabatbencana

import android.app.Application
import com.miftahulhudaf.sahabatbencana.data.di.dataModule
import com.miftahulhudaf.sahabatbencana.data.di.repositoryModule
import com.miftahulhudaf.sahabatbencana.di.preferenceModule
import com.miftahulhudaf.sahabatbencana.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(
                listOf(
                    dataModule,
                    repositoryModule,
                    preferenceModule,
                    viewModelModule
                )
            )
        }
    }
}