package io.fairboi.list.di

import android.content.Context
import io.fairboi.data.di.DataFactory

interface ListFeatureDependencies {
    val context: Context
    val dataFactory: DataFactory
}