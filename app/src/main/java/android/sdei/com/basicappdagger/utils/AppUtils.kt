package android.sdei.com.basicappdagger.utils

import android.Manifest
import android.annotation.TargetApi
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

import java.io.File
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone
import java.util.concurrent.TimeUnit
import java.util.regex.Matcher
import java.util.regex.Pattern

import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody

/**
 * class defines common methods used in app
 */

class AppUtils(private val context: Context) {

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    fun checkPermission(): Boolean {

        val currentAPIVersion = Build.VERSION.SDK_INT
        if (currentAPIVersion >= android.os.Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(context as Activity, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    val alertBuilder = AlertDialog.Builder(context)
                    alertBuilder.setCancelable(true)
                    alertBuilder.setTitle("Permission necessary")
                    alertBuilder.setMessage("External storage permission is necessary")
                    alertBuilder.setPositiveButton(android.R.string.yes) { dialog, which -> ActivityCompat.requestPermissions(context, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA), Config.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE) }
                    val alert = alertBuilder.create()
                    alert.show()
                } else {
                    ActivityCompat.requestPermissions(context, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA), Config.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE)
                }
                return false
            } else {
                return true
            }
        } else {
            return true
        }
    }

    fun getrequestBody(request: String): RequestBody {
        return RequestBody.create(MediaType.parse("text/plain"), request)


    }

    fun getMultipartImagePath(
            key: String, file: File?): MultipartBody.Part? {
        if (file != null) {
            val reqFile = RequestBody.create(MediaType.parse("image/*"), file)
            return MultipartBody.Part.createFormData(key, file.name, reqFile)
        } else {
            return null
        }
    }

    companion object {
        internal var tz = Calendar.getInstance().timeZone
        private val TIMEZONE = "GMT-4"

        /**
         * method to check text with email pattern
         * @param email string
         * @return true if text matches else false
         */
        fun checkEmail(email: String?): Boolean {
            val pattern: Pattern
            val matcher: Matcher
            val EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
            pattern = Pattern.compile(EMAIL_PATTERN)
            matcher = pattern.matcher(email)
            return matcher.matches()
        }



        /**
         * method formats time
         * @param timeinSec epoch time
         * @return formatted time
         */

        fun getDate(timeinSec: Long): String {
            val ts = Timestamp(timeinSec)
            val date = Date(timeinSec * 1000)
            val today = Calendar.getInstance().time
            val diff = today.time - date.time
            val days = (diff / (1000 * 60 * 60 * 24)).toFloat()
            TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS)
            val dt = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
            dt.timeZone = TimeZone.getTimeZone(TIMEZONE)

            return dt.format(date)
        }

        fun getDateNormal(timeinSec: Long): String {
            val date = Date(timeinSec)
            val dt = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
            dt.timeZone = TimeZone.getTimeZone(TIMEZONE)
            return dt.format(date)
        }



        val endOfDay: Long
            get() {
                val calendar = Calendar.getInstance()
                calendar.set(Calendar.HOUR_OF_DAY, 23)
                calendar.set(Calendar.MINUTE, 59)
                calendar.set(Calendar.SECOND, 59)
                calendar.set(Calendar.MILLISECOND, 999)
                return calendar.timeInMillis
            }

        val startOfDay: Long
            get() {
                val calendar = Calendar.getInstance()
                calendar.set(Calendar.HOUR_OF_DAY, 0)
                calendar.set(Calendar.MINUTE, 0)
                calendar.set(Calendar.SECOND, 0)
                calendar.set(Calendar.MILLISECOND, 0)
                return calendar.timeInMillis
            }

        fun getFormattedDate(myCalendar: Calendar): String {
            val myFormat = "dd/MM/yyyy"
            val sdf = SimpleDateFormat(myFormat, Locale.US)

            return sdf.format(myCalendar.time)
        }

        fun getFormattedDateHistory(myCalendar: Calendar): String {
            val myFormat = "yyyyMMdd"
            val sdf = SimpleDateFormat(myFormat, Locale.US)

            return sdf.format(myCalendar.time)
        }
    }
}
