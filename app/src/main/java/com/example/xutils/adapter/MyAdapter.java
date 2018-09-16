package com.example.xutils.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xutils.R;
import com.example.xutils.bean.InfoBean;

import org.xutils.image.ImageOptions;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Created by ming on 2018/09/16.
 */

public class MyAdapter extends BaseAdapter{

    private Context context;

    private List<InfoBean.InfoListBean> data;

    public MyAdapter(Context context, List<InfoBean.InfoListBean> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.list_item,parent,false);
            holder = new ViewHolder();
//            x.view().inject(holder,view);
            holder.iv_showImage = view.findViewById(R.id.iv_showImage);
            holder.tv_content = view.findViewById(R.id.tv_content);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }
        InfoBean.InfoListBean trailersBean = data.get(position);
        InfoBean.InfoListBean.ObjectBean objectBean = trailersBean.getObject();
        holder.tv_content.setText(objectBean.getTitle());
        ImageOptions options = new ImageOptions.Builder()
                .setFailureDrawableId(R.mipmap.ic_launcher)
                .setLoadingDrawableId(R.mipmap.ic_launcher)
                .setIgnoreGif(false)
                .setUseMemCache(true)
                .setFadeIn(true)
                .build();
        x.image().bind(holder.iv_showImage,objectBean.getImgUrl(),options);
        return view;
    }

    class ViewHolder{

        @ViewInject(R.id.iv_showImage)
        ImageView iv_showImage;

        @ViewInject(R.id.tv_content)
        TextView tv_content;

    }
}
