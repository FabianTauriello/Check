package io.github.fabiantauriello.check;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<CustomSpinnerItem> {

    public CustomAdapter(Context context, ArrayList<CustomSpinnerItem> dueDateSpinnerList) {
        super(context, 0, dueDateSpinnerList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    private View initView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_spinner_item, parent, false);
        }

        TextView textView = convertView.findViewById(R.id.custom_spinner_text_view);

        CustomSpinnerItem item = getItem(position);

        if (item != null) {
            textView.setText(item.getText());
        }

        if (position == 0) {
            convertView.setVisibility(View.GONE);
        }

        return convertView;

//        View v = null;
//        if (position == 0) {
//            TextView tv = new TextView(getContext());
//            tv.setVisibility(View.GONE);
//            v = tv;
//        } else {
//            v = super.getDropDownView(position, null, parent);
//        }
//        return v;


    }
}
