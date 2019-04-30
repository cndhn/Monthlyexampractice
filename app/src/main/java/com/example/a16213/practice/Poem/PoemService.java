package com.example.a16213.practice.Poem;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface PoemService {

    @FormUrlEncoded
    @POST("getTangPoetry")
    Call<PoemBean> getPeom(@Field("page")String page, @Field("count")String count);


}
