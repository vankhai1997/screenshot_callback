package com.flutter.moum.screenshot_callback

import android.os.Handler
import android.os.Looper
import io.flutter.plugin.common.EventChannel


class EventCallbackHandler : EventChannel.StreamHandler {

    private var eventSink: EventChannel.EventSink? = null

    override fun onListen(arguments: Any?, sink: EventChannel.EventSink) {
        eventSink = sink
    }

    fun send(event: String) {

        Handler(Looper.getMainLooper()).post {
            eventSink?.success(event)
        }
    }

    override fun onCancel(arguments: Any?) {
        eventSink = null
    }
}
