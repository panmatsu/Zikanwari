package com.matsu.zikanwari;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.List;
import java.util.Map;

/**
 * Created by user on 2016/04/15.
 */
public class Simple2Adapter extends SimpleAdapter {
    private int mButton;

    public Simple2Adapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to, int button) {
        super(context, data, resource, from, to);
        mButton = button;
    }

    public View getView(final int position, View convertView, final ViewGroup parent) {
        View view = super.getView(position, convertView, parent);

        Button btn = (Button) view.findViewById(mButton);
        btn.setTag(position);

        final ListView list =  (ListView) parent;
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg) {
                AdapterView.OnItemClickListener listener = list.getOnItemClickListener();
                long id = getItemId(position);
                listener.onItemClick((AdapterView<?>) parent, arg, position, id);
            }
        });

        return view;
    }
}