package com.timo.timolib.view;


import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.timo.timolib.R;

import java.util.List;

public class SingleChoiceGridView extends GridView {
    private Context ctx;
    private AdapterSingleChoice adapter;

    public SingleChoiceGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public SingleChoiceGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SingleChoiceGridView(Context context) {
        super(context);
    }

    public void setData(Context context, List<String> type) {
        this.ctx = context;
        adapter = new AdapterSingleChoice(type);
        setAdapter(adapter);
    }

    public AdapterSingleChoice getAdapter() {
        return adapter;
    }

    public class AdapterSingleChoice extends BaseAdapter {
        private List<String> type;
        private LayoutInflater inflater;
        private int prePos = -1;
        private SparseArray<Boolean> sparseArray = new SparseArray<>();

        public AdapterSingleChoice(List<String> type) {
            this.type = type;
            this.inflater = (LayoutInflater) ctx
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            for (int i = 0; i < type.size(); i++) {
                sparseArray.put(i, false);
            }
        }

        public void notifyPos() {
            sparseArray.put(prePos, false);
            prePos = -1;
            notifyDataSetChanged();
        }

        private final class ViewHolder {
            TextView tv;
        }

        @Override
        public int getCount() {
            return type == null ? 0 : type.size();
        }

        @Override
        public String getItem(int position) {
            return type.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView,
                            ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = inflater.inflate(R.layout.item_mutiple_choice,
                        parent, false);
                holder.tv = (TextView) convertView.findViewById(R.id.tv);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            final int pos = position;
            holder.tv.setText(type.get(position));
            Boolean b = sparseArray.get(position);
            if (b) {
                holder.tv.setBackgroundResource(R.drawable.button_item_press);
                holder.tv.setTextColor(Color.WHITE);
            } else {
                holder.tv.setBackgroundResource(R.drawable.button_item_normal);
                holder.tv.setTextColor(Color.parseColor("#333333"));
            }

            holder.tv.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (prePos != pos) {
                        sparseArray.put(pos, true);
                        sparseArray.put(prePos, false);
                        prePos = pos;
                        notifyDataSetChanged();
                    } else {
                        sparseArray.put(prePos, false);
                        prePos = -1;
                        notifyDataSetChanged();
                    }
                }
            });
            return convertView;
        }

    }

    public String getPayTypeString() {
        int i = adapter.prePos;
        return i + "";
    }

    public int getPayTypeInt() {
        int i = adapter.prePos;
        return i + 1;
    }

}
