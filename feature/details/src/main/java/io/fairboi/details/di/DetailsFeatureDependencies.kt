package io.fairboi.details.di

import android.content.Context
import io.fairboi.data.di.DataFactory

interface DetailsFeatureDependencies {
    val context: Context
    val dataFactory: DataFactory
}

