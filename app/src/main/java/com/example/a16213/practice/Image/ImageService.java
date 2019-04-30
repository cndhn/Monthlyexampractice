package com.example.a16213.practice.Image;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ImageService {


    @Multipart
    @POST("api/upload")
    @Headers({"User-Agent: Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:66.0) Gecko/20100101 Firefox/66.0", "Connection: keep-alive"})
    Call<ResponseBody> postImg(@Part MultipartBody.Part body);

}
