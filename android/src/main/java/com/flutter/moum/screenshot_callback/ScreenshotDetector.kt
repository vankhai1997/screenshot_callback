package com.flutter.moum.screenshot_callback

import android.content.ContentResolver
import android.content.Context
import android.database.ContentObserver
import android.icu.util.TimeUnit.values
import android.net.Uri
import android.os.Build
import android.os.FileObserver
import android.os.Handler
import android.os.Looper
import android.provider.MediaStore
import java.io.File

import java.time.chrono.JapaneseEra.values


class ScreenshotDetector(
    private val context: Context,
    private val callback: (name: String) -> Unit
) {
    private var handler: Handler? = null
    private var fileObserver: FileObserver? = null
    private var contentObserver: ContentObserver? = null

    fun start() {

        handler = Handler(Looper.getMainLooper())
        if (Build.VERSION.SDK_INT >= 29) {
            //Log.d(TAG, "android x");
            val files: MutableList<File> = ArrayList()
            for (path in Path.values()) {
                files.add(File(path.path))
            }
            fileObserver = object : FileObserver(files, CREATE) {
                override fun onEvent(event: Int, path: String?) {
                    //Log.d(TAG, "androidX onEvent");
                    if (event == CREATE) {
                        handler?.post(Runnable {
                            callback.invoke("callback")
                        })
                    }
                }
            }
            fileObserver?.startWatching()
        } else {
            //Log.d(TAG, "android others");
            for (path in Path.values()) {
                //Log.d(TAG, "onMethodCall: "+path.getPath());
                fileObserver = object : FileObserver(path.path, CREATE) {
                    override fun onEvent(event: Int, path: String?) {
                        //Log.d(TAG, "android others onEvent");
                        if (event == CREATE) {
                            callback.invoke("callback")
                        }
                    }
                }
                fileObserver?.startWatching()
            }
        }
    }

    fun stop() {
        fileObserver?.let {it.stopWatching() }
    }
}