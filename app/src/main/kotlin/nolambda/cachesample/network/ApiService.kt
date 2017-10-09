package nolambda.cachesample.network

import io.reactivex.Single
import nolambda.cachesample.data.UserAgent
import retrofit2.http.GET

interface ApiService {

    @GET("user-agent")
    fun getUserAgent(): Single<UserAgent>

}
