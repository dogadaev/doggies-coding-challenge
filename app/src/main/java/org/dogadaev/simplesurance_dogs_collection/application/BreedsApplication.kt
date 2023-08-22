package org.dogadaev.simplesurance_dogs_collection.application

import android.app.Application
import org.dogadaev.simplesurance_dogs_collection.data.DataGraph
import org.dogadaev.simplesurance_dogs_collection.presentation.PresentationGraph
import org.dogadaev.simplesurance_dogs_collection.ui.UiGraph
import org.koin.dsl.koinApplication

class BreedsApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        koinApplication {
            modules(
                AppGraph.module,
                DataGraph.module,
                PresentationGraph.module,
                UiGraph.module,
            )
        }
    }
}