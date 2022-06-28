package com.flutter.moum.screenshot_callback

import android.os.Environment
import java.io.File


enum class Path(val path: String) {
    DCIM(
        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)
            .toString() + File.separator + "Screenshots" + File.separator
    ),
    PICTURES(
        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
            .toString() + File.separator + "Screenshots" + File.separator
    );

}