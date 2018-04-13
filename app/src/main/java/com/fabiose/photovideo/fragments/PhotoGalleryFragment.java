package com.fabiose.photovideo.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.GridView;
import android.widget.ImageView;

import com.fabiose.photovideo.R;
import com.fabiose.photovideo.adapters.PhotoAdapter;
import com.fabiose.photovideo.domain.Photo;
import com.fabiose.photovideo.utils.AnimationUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fabioestrela on 18-04-10.
 */

public class PhotoGalleryFragment extends Fragment {

    private ImageView imageViewPhotoSelected;
    private GridView gridPhotoGallery;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_photo_gallery, container, false);

        List<Photo> photos = new ArrayList<Photo>();
        for (int i=1; i<31; i++){
            Photo photo = new Photo("img_".concat(String.valueOf(i)));
            photos.add(photo);
        }

        gridPhotoGallery = (GridView)rootView.findViewById(R.id.gridPhotoGallery);

        imageViewPhotoSelected = (ImageView)rootView.findViewById(R.id.imageViewPhotoSelected);

        imageViewPhotoSelected.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                gridPhotoGallery.bringToFront();
                gridPhotoGallery.setVisibility(View.VISIBLE);
                imageViewPhotoSelected.setVisibility(View.INVISIBLE);;
                imageViewPhotoSelected.invalidate();

                return false;
            }
        });

        final PhotoAdapter photoAdapter = new PhotoAdapter(getActivity(),this, photos);
        gridPhotoGallery.setAdapter(photoAdapter);

        return rootView;
    }

    public void showPhotoSelected(String img){
        int drawableResourceId = getResources().getIdentifier(img, "mipmap", "com.fabiose.photovideo");
        imageViewPhotoSelected.setImageResource(drawableResourceId);

        imageViewPhotoSelected.bringToFront();
        imageViewPhotoSelected.setVisibility(View.VISIBLE);
        gridPhotoGallery.setVisibility(View.INVISIBLE);;
        gridPhotoGallery.invalidate();

    }
}
