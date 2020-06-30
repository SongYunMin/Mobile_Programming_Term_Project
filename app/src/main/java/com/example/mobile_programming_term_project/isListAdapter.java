package com.example.mobile_programming_term_project;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import android.widget.TextView;
import java.util.ArrayList;
import java.util.LinkedList;

public class isListAdapter extends BaseAdapter {
    private Context mContext;
    private LinkedList<String> list_result;
    private ViewHolder mViewHolder;
    public isListAdapter(Context mContext, LinkedList<String> list_result) {
        this.mContext = mContext;
        this.list_result = list_result;
    }
    @Override
    public int getCount() {                 // list 의 size 반환
        return list_result.size();
    }
    @Override
    public Object getItem(int position) {   // item 의 data return
        return list_result.get(position);
    }
    @Override                               // item data 들의 ID (식별자)반환
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // View Holder 패턴
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate
                    (R.layout.test_item, parent, false);
            mViewHolder = new ViewHolder(convertView);
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }
        // View에 Data 세팅
        mViewHolder.txt_name.setText(list_result.get(position));
        return convertView;
    }
    public class ViewHolder {
        private TextView txt_name;
        public ViewHolder(View convertView) {
            txt_name = (TextView) convertView.findViewById(R.id.txt_name);
        }
    }
}