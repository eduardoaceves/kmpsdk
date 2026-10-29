package com.kmp.module.data.local.datastore

/*
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import com.kmp.module.utils.animation.constants.Constants
import okio.Path.Companion.toPath
import org.koin.mp.KoinPlatform.getKoin

actual fun createDataStore(): DataStore<Preferences> =
    PreferenceDataStoreFactory.createWithPath(
        produceFile = {
            val context: Context = getKoin().get()
            context.filesDir.resolve(relative = "${Constants.DATASTORE_NAME}.preferences_pb").absolutePath.toPath()
        },
    )*/