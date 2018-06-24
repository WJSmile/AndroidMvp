package com.zwj.mvp.lib.http;

import io.reactivex.Observable;
import retrofit2.http.POST;

public interface RetrofitService {

    @POST("test")
    Observable<TestBase> getData();
}
