package com.vertical.app.module.goods;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vertical.app.R;
import com.vertical.app.common.widget.CatTextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ls on 10/27/17.
 */

public class GoodsManagementAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public String TAG = getClass().getSimpleName();
    private OnItemClickListener onItemClickListener;
    private List<GoodsBean> mData;

    public GoodsManagementAdapter(){
    }

    public void refreshData(List data) {
        mData = data;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ListViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_menu, null, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if(mData == null || mData.size() == 0)  return;

        GoodsBean goodsBean = mData.get(position);
        ListViewHolder listViewHolder = (ListViewHolder)holder;
        listViewHolder.content.setLeftIconRes(R.drawable.profile_icon_authorization);
        listViewHolder.content.setLeftTextString(goodsBean.getName());

        listViewHolder.root.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                if (null != onItemClickListener) {
                    onItemClickListener.onItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public interface OnItemClickListener {
        void onItemClick(int index);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    class ListViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.root)
        View root;
        @BindView(R.id.content)
        CatTextView content;

        public ListViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}