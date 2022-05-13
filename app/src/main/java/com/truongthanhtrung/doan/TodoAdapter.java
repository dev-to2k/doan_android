package com.truongthanhtrung.doan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class TodoAdapter extends BaseAdapter {
    Context context;
    List<Todo> data;
    int layoutResourceId;

    public TodoAdapter(Context context, int layoutResourceId, List<Todo> data) {
        this.context = context;
        this.data = data;
        this.layoutResourceId = layoutResourceId;
    }

    @Override
    public int getCount() {
        if(data.size() != 0 && !data.isEmpty()) {
            return data.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        StaffHolder holder = null;
        if(convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(layoutResourceId, parent, false);
            holder = new StaffHolder();
            holder.txtTodo = convertView.findViewById(R.id.txtTodo);
            convertView.setTag(holder);
        } else {
            holder = (StaffHolder) convertView.getTag();
        }

        Todo pf = data.get(i);
        holder.txtTodo.setText(pf.getTodo());

        return convertView;
    }

    class StaffHolder {
        TextView txtTodo;
    }
}