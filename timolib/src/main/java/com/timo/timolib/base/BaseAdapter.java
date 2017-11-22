package com.timo.timolib.base;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;

import com.timo.timolib.Params;
import com.timo.timolib.base.adapter.ItemViewDelegate;
import com.timo.timolib.base.adapter.MultiItemTypeAdapter;
import com.timo.timolib.base.adapter.ViewHolder;

import java.util.List;

/**
 * Created by zhy on 16/4/9.
 */
public abstract class BaseAdapter<T> extends MultiItemTypeAdapter<T> {
    protected Context mContext;
    protected int mLayoutId;
    protected List<T> mDatas;
    protected LayoutInflater mInflater;

    public void setDatas(List datas) {
        this.mDatas = datas;
    }

    public BaseAdapter(final Context context, final int layoutId, List<T> datas) {
        super(context, datas);
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mLayoutId = layoutId;
        mDatas = datas;

        addItemViewDelegate(1, new ItemViewDelegate<T>() {
            @Override
            public int getItemViewLayoutId() {
                return layoutId;
            }

            @Override
            public boolean isForViewType(T item, int position) {
                return true;
            }

            @Override
            public void convert(ViewHolder holder, T t, int position) {
                BaseAdapter.this.convert(holder, t, position);
            }
        });
    }

    protected abstract void convert(ViewHolder holder, T t, int position);

    public void startActivity(Class<?> cls) {
        if (setParams == null) {
            mContext.startActivity(new Intent(mContext, cls));
        } else {
            mContext.startActivity(new Intent(mContext, cls).putExtra(BaseConstancts.BASE_PARAM, setParams));
        }
    }

    private Params setParams;

    public Params setParams() {
        if (setParams == null) {
            setParams = new Params();
        }
        return setParams;
    }
}