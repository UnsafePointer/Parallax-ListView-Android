package com.ruenzuo.testingapp.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.ruenzuo.testingapp.R;

/**
 * Created by renzocrisostomo on 14/06/14.
 */
public class PictureAdapter extends ArrayAdapter<String> {

    public PictureAdapter(Context context, int resource) {
        super(context, resource);
    }

    public static class MediaViewHolder {
        public ImageView imgViewPicture;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = ((Activity) getContext()).getLayoutInflater();
            convertView = inflater.inflate(R.layout.picture_row_layout, null);
            MediaViewHolder holder = new MediaViewHolder();
            holder.imgViewPicture = (ImageView) convertView.findViewById(R.id.imgViewPicture);
            convertView.setTag(holder);
        }
        MediaViewHolder holder = (MediaViewHolder)convertView.getTag();
        String picture = getItem(position);
        int resID = getContext().getResources().getIdentifier(picture , "drawable", getContext().getPackageName());
        holder.imgViewPicture.setImageDrawable(getContext().getResources().getDrawable(resID));
        return convertView;
    }

}
