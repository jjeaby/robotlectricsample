package com.sds.testlab.robotlectricsample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by sds on 2017-04-12.
 */

public class InfoListViewAdapter extends BaseAdapter {

    private ArrayList<ListViewItem> listViewItemList = new ArrayList<ListViewItem>() ;

    @Override
    public int getCount() {
        return listViewItemList.size() ;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();


        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.lists_notice, parent, false);
        }

         TextView textViewTitle = (TextView) convertView.findViewById(R.id.title) ;
        TextView textViewId = (TextView) convertView.findViewById(R.id.id) ;

         ListViewItem listViewItem = listViewItemList.get(position);

         textViewTitle.setText(listViewItem.getTitle());
        textViewId.setText(listViewItem.getId());

        return convertView;
    }
    @Override
    public long getItemId(int position) {
        return position ;
    }

     @Override
    public Object getItem(int position) {
        return listViewItemList.get(position) ;
    }

    public void addItem(String title, String id) {
        ListViewItem item = new ListViewItem();

        item.setTitle(title);
        item.setId(id);

        listViewItemList.add(item);
    }



}
