package com.example.a16213.practice.Poem;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.a16213.practice.R;

import java.util.ArrayList;
import java.util.List;

public class PoemRecycAdapter extends RecyclerView.Adapter<PoemRecycAdapter.MyHolder>{

    List<PoemBean.ResultBean> list = new ArrayList <>();

    public void refresh(List<PoemBean.ResultBean> list){
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.peom_item,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
        myHolder.title.setText("诗词名:"+list.get(i).getTitle());
        myHolder.writer.setText("诗词作者:"+list.get(i).getAuthors());
        myHolder.content.setText("诗词内容:"+list.get(i).getContent());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{

        TextView title;
        TextView writer;
        TextView content;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.Peomtitle);
            writer = itemView.findViewById(R.id.PeomWriter);
            content = itemView.findViewById(R.id.PeomContent);
        }
    }

}
