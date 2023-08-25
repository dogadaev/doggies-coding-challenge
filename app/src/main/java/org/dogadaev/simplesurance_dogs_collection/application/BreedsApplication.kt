package org.dogadaev.simplesurance_dogs_collection.application

import android.app.Application
import org.dogadaev.simplesurance_dogs_collection.data.DataGraph
import org.dogadaev.simplesurance_dogs_collection.presentation.PresentationGraph
import org.dogadaev.simplesurance_dogs_collection.ui.UiGraph
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BreedsApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@BreedsApplication)
            modules(
                AppGraph.module,
                DataGraph.module,
                PresentationGraph.module,
                UiGraph.module,
            )
        }
    }
}