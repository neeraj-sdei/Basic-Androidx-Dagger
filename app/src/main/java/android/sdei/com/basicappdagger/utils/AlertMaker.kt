package android.sdei.com.basicappdagger.utils

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface


object AlertMaker {


    /**
     * method creates alert dialog
     * @param context app context
     * @param title string for alert title
     * @param message string message
     * @param pos_btn_name string positive button text
     */
    fun showAndroidPopup(context: Context, title: String,
                         message: String, pos_btn_name: String?) {
        var pos_btn_name = pos_btn_name


        var builder: AlertDialog.Builder? = null
    /*    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //builder = AlertDialog.Builder(context, R.style.MyDialogs)
        } else {
            builder = AlertDialog.Builder(context)
        }*/
        builder = AlertDialog.Builder(context)

        val alertDialog = builder.create()



        if (!title.equals("", ignoreCase = true)) {
            alertDialog.setTitle(title)
        }
        if (pos_btn_name == null || pos_btn_name.equals("", ignoreCase = true)) {
            pos_btn_name = "OK"
        }

        alertDialog.setMessage(message)
        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, pos_btn_name) { dialog, which -> alertDialog.dismiss() }

        alertDialog.show()


    }


    fun showAndroidActionPopup(context: Context?, title: String,
                               message: String, pos_btn_name: String, alertInterface: AlertWithOneButtonInterface) {


        var builder: AlertDialog.Builder? = null
      /*  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = AlertDialog.Builder(context, R.style.MyDialogs)
        } else {
            builder = AlertDialog.Builder(context)
        }*/
        builder = AlertDialog.Builder(context)

        val alertDialog = builder.create()



        if (!title.equals("", ignoreCase = true)) {
            alertDialog.setTitle(title)
        }

        alertDialog.setMessage(message)
        alertDialog.setCancelable(false)
        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, pos_btn_name) { dialog, which ->
            alertDialog.dismiss()
            alertInterface.buttonPositive()
        }

        alertDialog.show()

    }

    /**
     * method creates alert dialog with 2 buttons
     * @param context app context
     * @param title string for alert title
     * @param message string message
     * @param pos_btn_name string positive button text
     * @param neg_btn_name string negative button text
     * @param alertInterface callback for click on buttons
     */
    fun showAndroidActionPopupWith2Buttons(context: Context?, title: String,
                                           message: String, pos_btn_name: String, neg_btn_name: String, alertInterface: AlertWithTwoButtonInterface) {


        var builder: AlertDialog.Builder? = null
    /*    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = AlertDialog.Builder(context, R.style.MyDialogs)
        } else {
            builder = AlertDialog.Builder(context)
        }*/
        builder = AlertDialog.Builder(context)

        val alertDialog = builder.create()



        if (!title.equals("", ignoreCase = true)) {
            alertDialog.setTitle(title)
        }

        alertDialog.setMessage(message)
        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, pos_btn_name) { dialog, which ->
            alertDialog.dismiss()
            alertInterface.buttonPositive("")
        }

        alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, neg_btn_name) { dialog, which ->
            alertDialog.dismiss()
            alertInterface.buttonNegative()
        }

        alertDialog.show()
        alertDialog.setCancelable(false)

    }


}
