package android.sdei.com.basicappdagger.network

import android.util.MalformedJsonException
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.text.ParseException
import java.util.concurrent.TimeoutException

class NetworkManager {
    protected var compositeDisposable: Disposable?=null
  /**
     * Constructor
     *
     * @param contextObj                 The Context from where the method is called
     * @param successRedirectionListener The listener interface for receiving action events
     * @return none
     */


    fun <V> createApiRequest(observables: Observable<V>, callBack: ServiceListener<V>) {
        // progressShow(context, message)
        compositeDisposable = observables
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<V>() {
                    override fun onNext(@io.reactivex.annotations.NonNull s: V) {
                        //progressHide()
                        callBack.getServerResponse(s, 0)
                    }

                    override fun onError(@io.reactivex.annotations.NonNull e: Throwable) {
                        callBack.getError(setUpErrors(e), 0)

                    }

                    override fun onComplete() {}
                })
    }







    // Handling Java Exceptions
    private fun setUpErrors(t: Throwable): ErrorModel {
        val errorModel = ErrorModel()
        try {
            // Exception comes by Java
            if (t is SocketTimeoutException) {
                errorModel.error_code = ResponseCodes.INTERNET_NOT_AVAILABLE
                errorModel.error_message = ResponseCodes.logErrorMessage(errorModel.error_code)
            } else if (t is TimeoutException) {
                errorModel.error_code = ResponseCodes.URL_CONNECTION_ERROR
                errorModel.error_message = ResponseCodes.logErrorMessage(errorModel.error_code)
            } else if (t is ClassCastException) {
                errorModel.error_code = ResponseCodes.MODEL_TYPE_CAST_EXCEPTION
                errorModel.error_message = ResponseCodes.logErrorMessage(errorModel.error_code)
            } else if (t is MalformedJsonException) {
                errorModel.error_code = ResponseCodes.MODEL_TYPE_CAST_EXCEPTION
                errorModel.error_message = ResponseCodes.logErrorMessage(errorModel.error_code)
            } else if (t is ParseException) {
                errorModel.error_code = ResponseCodes.MODEL_TYPE_CAST_EXCEPTION
                errorModel.error_message = ResponseCodes.logErrorMessage(errorModel.error_code)
            } else if (t is UnknownHostException) {
                errorModel.error_code = ResponseCodes.INTERNET_NOT_AVAILABLE
                errorModel.error_message = ResponseCodes.logErrorMessage(errorModel.error_code)
            }else if (t is MalformedJsonException)
            {
                errorModel.error_code = ResponseCodes.MALFORMED_JSON
                errorModel.error_message = ResponseCodes.logErrorMessage(errorModel.error_code)
            }else if (t is ConnectException)
            {           errorModel.error_code = ResponseCodes.FAILED_TO_CONNECT
                errorModel.error_message = ResponseCodes.logErrorMessage(errorModel.error_code)

            }

            else  {
                val errorMessage = (t as HttpException).response().errorBody()!!.string()
                val responseCode = t.response().code()
                errorModel.error_code = responseCode
                errorModel.error_message = errorMessage
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
            errorModel.error_code = ResponseCodes.UNKNOWN_ERROR
            errorModel.error_message = ResponseCodes.logErrorMessage(errorModel.error_code)
        }
        return errorModel
    }




}