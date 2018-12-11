package android.sdei.com.basicappdagger.network

interface ServiceListener<T> {
    abstract fun getServerResponse(response: T, requestcode: Int)
    abstract fun getError(error: ErrorModel, requestcode: Int)
}