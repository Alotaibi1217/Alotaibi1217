package com.lag.maldefender.activities

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.view.MenuItem
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.lag.maldefender.*
import net.gotev.uploadservice.data.UploadNotificationAction
import net.gotev.uploadservice.data.UploadNotificationConfig
import net.gotev.uploadservice.data.UploadNotificationStatusConfig
import net.gotev.uploadservice.extensions.getCancelUploadIntent
import java.util.ArrayList

open class BaseActivity() : AppCompatActivity() {
    override fun onPause() {
        super.onPause()

        // hide soft keyboard if shown
        val view = currentFocus
        if (view != null) {
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    private var mBackAction = false
    override fun attachBaseContext(base: Context) {
        // Ensure that the selected locale is used
        applyOverrideConfiguration(Utils.getLocalizedConfig(base))
        super.attachBaseContext(base)
    }

    protected fun displayBackAction() {
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            mBackAction = true
        }
    }

    protected fun getFragment(targetClass: Class<*>): Fragment? {
        val fragments = supportFragmentManager.fragments
        for (fragment in fragments) {
            if (targetClass.isInstance(fragment)) return fragment
        }
        return null
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (mBackAction && item.itemId == R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    protected fun getNotificationConfig(
        uploadId: String,
        @StringRes title: Int
    ): UploadNotificationConfig {
        val clickIntent = PendingIntent.getActivity(
            this, 1, Intent(this, MainActivity::class.java), PendingIntent.FLAG_UPDATE_CURRENT or  PendingIntent.FLAG_IMMUTABLE)

        val autoClear = false
        val largeIcon: Bitmap? = null
        val clearOnAction = true
        val ringToneEnabled = true

        val cancelAction = UploadNotificationAction(
            R.drawable.ic_cancelled,
            getString(R.string.cancel_upload),
            this.getCancelUploadIntent(uploadId)
        )

        val noActions = ArrayList<UploadNotificationAction>(1)
        val progressActions = ArrayList<UploadNotificationAction>(1)
        progressActions.add(cancelAction)

        val progress = UploadNotificationStatusConfig(
            getString(title) + ": " + CustomPlaceholdersProcessor.FILENAME_PLACEHOLDER,
            getString(R.string.uploading),
            R.drawable.ic_upload,
            Color.BLUE,
            largeIcon,
            clickIntent,
            progressActions,
            clearOnAction,
            autoClear
        )

        val success = UploadNotificationStatusConfig(
            getString(title) + ": " + CustomPlaceholdersProcessor.FILENAME_PLACEHOLDER,
            getString(R.string.upload_success),
            R.drawable.ic_upload_success,
            Color.GREEN,
            largeIcon,
            clickIntent,
            noActions,
            clearOnAction,
            autoClear
        )

        val error = UploadNotificationStatusConfig(
            getString(title) + ": " + CustomPlaceholdersProcessor.FILENAME_PLACEHOLDER,
            getString(R.string.upload_error),
            R.drawable.ic_upload_error,
            Color.RED,
            largeIcon,
            clickIntent,
            noActions,
            clearOnAction,
            autoClear
        )

        val cancelled = UploadNotificationStatusConfig(
            getString(title) + ": " + CustomPlaceholdersProcessor.FILENAME_PLACEHOLDER,
            getString(R.string.upload_cancelled),
            R.drawable.ic_cancelled,
            Color.YELLOW,
            largeIcon,
            clickIntent,
            noActions,
            clearOnAction
        )

        return UploadNotificationConfig(
            PCAPdroid.notificationChannelID,
            ringToneEnabled,
            progress,
            success,
            error,
            cancelled
        )
    }
//    private fun flagsCompat(flags: Int): Int {
//        if (Build.VERSION.SDK_INT > 30) {
//            return (flags or PendingIntent.FLAG_IMMUTABLE)
//        }
//
//        return flags
//    }


}