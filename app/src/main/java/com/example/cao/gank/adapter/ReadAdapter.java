package com.example.cao.gank.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.TimeUtils;
import com.bumptech.glide.Glide;
import com.example.cao.gank.R;
import com.example.cao.gank.congiure.Utils;
import com.example.cao.gank.model.ItemBean;
import com.example.cao.gank.ui.InfoActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/1.
 */

public class ReadAdapter extends RecyclerView.Adapter<ReadAdapter.ViewHolder> {
    private List<ItemBean.ResultsBean> mlist = new ArrayList<>();
    private Context context;
    private String TAG = "caorui";

    public ReadAdapter(Context context, List<ItemBean.ResultsBean> mlist){
        this.mlist =mlist;
        this.context = context;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_read,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnItemClickListener != null) {
                    //注意这里使用getTag方法获取数据
                    mOnItemClickListener.onItemClick(view,(ItemBean.ResultsBean) view.getTag());
                }
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ItemBean.ResultsBean resultsBean = mlist.get(position);
        holder.title.setText(resultsBean.getDesc());
        holder.author.setText(resultsBean.getWho());
        holder.time.setText(TimeUtils.getFriendlyTimeSpanByNow(Utils.formatDateFromStr(resultsBean.getPublishedAt())));
        Log.d(TAG, "Who: "+resultsBean.getWho());
       // Log.d(TAG, "image: "+resultsBean.getImages().get(0));
        if (resultsBean.getImages()!=null&&resultsBean.getImages().size()>0){
            Glide.with(context).load(resultsBean.getImages().get(0)).placeholder(R.drawable.ic_launcher).into(holder.imageView);
        }else {
            //Glide.with(context).load(resultsBean.getImages().get(0)).placeholder(R.drawable.ic_launcher).error(R.drawable.ic_launcher).into(holder.imageView);
            holder.imageView.setImageResource(R.drawable.ic_launcher);
        }

        holder.itemView.setTag(mlist.get(position));
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: "+mlist.size());
        return mlist.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView title,author,time;
        public ViewHolder(View view) {
            super(view);
            imageView = view.findViewById(R.id.item_img);
            title = view.findViewById(R.id.tv_title);
            author = view.findViewById(R.id.tv_author);
            time = view.findViewById(R.id.tv_time);
        }
    }

    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(View view , ItemBean.ResultsBean data);
    }
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;
    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
}
