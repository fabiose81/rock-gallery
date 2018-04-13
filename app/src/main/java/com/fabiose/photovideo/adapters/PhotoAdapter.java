package com.fabiose.photovideo.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.fabiose.photovideo.R;
import com.fabiose.photovideo.domain.Photo;
import com.fabiose.photovideo.fragments.PhotoGalleryFragment;

import java.util.List;

/**
 * Created by fabioestrela on 18-04-11.
 */

public class PhotoAdapter extends BaseAdapter {

    private List<Photo> photos;
    private LayoutInflater mInflater;
    private Context context;
    private PhotoGalleryFragment photoGalleryFragment;

    public PhotoAdapter(Context context, PhotoGalleryFragment photoGalleryFragment, List<Photo> photos) {
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.photoGalleryFragment = photoGalleryFragment;
        this.photos = photos;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView = mInflater.inflate(R.layout.adapter_photo, null);

        if (photos.get(position) != null) {
            final ImageView imageViewPhoto = (ImageView)convertView.findViewById(R.id.imageViewPhoto);
            int drawableResourceId = context.getResources().getIdentifier(photos.get(position).getName(), "mipmap", "com.fabiose.photovideo");
            imageViewPhoto.setImageResource(drawableResourceId);

            imageViewPhoto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    photoGalleryFragment.showPhotoSelected(photos.get(position).getName());
                }
            });
        }

        return convertView;
    }

    @Override
    public Object getItem(int position) {
        return photos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getCount() {
        return photos.size();
    }
}
