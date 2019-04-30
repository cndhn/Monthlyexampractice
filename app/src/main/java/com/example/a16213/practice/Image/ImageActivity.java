package com.example.a16213.practice.Image;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.a16213.practice.R;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.Multipart;

public class ImageActivity extends AppCompatActivity implements View.OnClickListener {

    private Button startImage;
    private SimpleDraweeView simple;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_image);

        initView();

    }

    private void initView() {
        startImage = (Button) findViewById(R.id.startImage);

        startImage.setOnClickListener(this);
        simple = (SimpleDraweeView) findViewById(R.id.simple);
        simple.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.startImage:
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 200);
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 200) {
            Uri uri = data.getData();
            simple.setImageURI(uri);
            ContentResolver contentResolver = this.getContentResolver();
            Cursor cursor = contentResolver.query(uri, null, null, null, null);
            if (cursor != null) {
                cursor.moveToFirst();
                String path = cursor.getString(cursor.getColumnIndex("_data"));
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://sm.ms/")
                        .build();
                ImageService imageService = retrofit.create(ImageService.class);
                File file1 = new File(path);
                MediaType mediaType = MediaType.parse("application/otcet-stream");
                final RequestBody requestBody = RequestBody.create(mediaType, file1);
                MultipartBody.Part part = MultipartBody.Part.create(requestBody);
                final Call<ResponseBody> responseBodyCall = imageService.postImg(part);
                responseBodyCall.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call <ResponseBody> call, Response<ResponseBody> response) {
                        int code = response.code();
                        try {
                            String string = response.body().string();
                            boolean successful = response.isSuccessful();
                            Log.i("我",code+""+successful);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call <ResponseBody> call, Throwable t) {

                    }
                });
                cursor.close();

            }
        }
    }


}
