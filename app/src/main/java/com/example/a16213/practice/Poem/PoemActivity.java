package com.example.a16213.practice.Poem;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.a16213.practice.R;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PoemActivity extends AppCompatActivity {

    String page = "1";
    PoemRecycAdapter adapter;
    PoemnonetworkRecycAdapter noadapter;
    int i = Integer.parseInt(page);
    XRecyclerView recyclerView;
    String url = "https://api.apiopen.top/";
    PoemSqlDao sqlDao = new PoemSqlDao(this);
    List<PoemBean.ResultBean> list ;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==1){
                if (list!=null){
                    list.clear();
                }
                adapter = new PoemRecycAdapter();
                list = (List <PoemBean.ResultBean>) msg.obj;
                adapter.refresh(list);
                recyclerView.setAdapter(adapter);
                for (int i = 0;i<list.size();i++){
                    sqlDao.insertDao(list.get(i).getTitle(),list.get(i).getAuthors(),list.get(i).getContent());
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peom);
        recyclerView = findViewById(R.id.recyc);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        ConnectivityManager cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnectedOrConnecting()) {
            recyclerView.setPullRefreshEnabled(true);
            recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
                @Override
                public void onRefresh() {

                    i+=1;
                    RequestData(url, String.valueOf(i));
                    recyclerView.refreshComplete();
                }

                @Override
                public void onLoadMore() {

                }
            });
            RequestData(url,page);

        }else {
            noadapter = new PoemnonetworkRecycAdapter();
            List <PoemInfo> poemInfos = sqlDao.quearyDao();
            noadapter.refresh(poemInfos);
            recyclerView.setAdapter(noadapter);
        }


    }


    public void RequestData(final String url, final String page){
        Executors.newCachedThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(url)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                PoemService service = retrofit.create(PoemService.class);
                Call <PoemBean> peom = service.getPeom(page, "10");
                peom.enqueue(new Callback <PoemBean>() {
                    @Override
                    public void onResponse(Call <PoemBean> call, Response<PoemBean> response) {
                        Message message = new Message();
                        message.what=1;
                        message.obj = response.body().getResult();
                        handler.sendMessage(message);
                    }

                    @Override
                    public void onFailure(Call <PoemBean> call, Throwable t) {

                    }
                });
            }
        });

    }
}
