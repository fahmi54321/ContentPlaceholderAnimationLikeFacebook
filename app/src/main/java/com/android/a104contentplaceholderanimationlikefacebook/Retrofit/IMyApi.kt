package com.android.a104contentplaceholderanimationlikefacebook.Retrofit

import com.android.a104contentplaceholderanimationlikefacebook.Model.Item
import io.reactivex.Flowable
import io.reactivex.Observable
import retrofit2.http.GET
import java.util.*

interface IMyApi {
    @get:GET("photos")
    var getData:Observable<List<Item>>
}