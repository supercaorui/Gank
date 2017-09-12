package com.example.cao.gank.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cao.gank.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/3.
 */

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder>{
    private List<String> mList = new ArrayList<>();
    private Context mContext;

    private OnRecyclerViewItemClickListener listener;
    private DeleteItem deleteItem;
    //用来点击删除某条搜索记录
    public interface DeleteItem{
        void deleteItem(int position);
    }
    public interface OnRecyclerViewItemClickListener{
        void onItemClick(View view,String string);
    }
    public SearchAdapter(List<String> mList, Context mContext) {
        this.mList = mList;
        this.mContext = mContext;
    }

    @Override
    public SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.search_recy_item,parent,false);
        final SearchViewHolder holder = new SearchViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(view,(String) view.getTag());
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(SearchViewHolder holder, final int position) {
        holder.textView.setText(mList.get(position));
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteItem.deleteItem(position);
            }
        });
        holder.itemView.setTag(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class SearchViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        private ImageView imageView;
        public SearchViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.search_item_text);
            imageView = itemView.findViewById(R.id.search_item_delete);
        }
    }
    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.listener = listener;
    }
}
