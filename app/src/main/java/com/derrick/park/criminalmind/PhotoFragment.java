package com.derrick.park.criminalmind;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.File;

/**
 * Created by Paulo on 13/07/2017.
 */

public class PhotoFragment extends android.support.v4.app.DialogFragment {

    private File mPhotoFile;
    public static final String PHOTO = "photo";

    public static PhotoFragment newInstance(File mPhotoFile) {
        PhotoFragment pf = new PhotoFragment();
        Bundle args = new Bundle();
        args.putSerializable(PHOTO, mPhotoFile);
        pf.setArguments(args);
        return pf;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        mPhotoFile = (File) getArguments().getSerializable(PHOTO);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_photo, container, false);

        ImageView mPhotoView = (ImageView) v.findViewById(R.id.photo_zoom);
        Bitmap bitmap = BitmapFactory.decodeFile(mPhotoFile.getAbsolutePath());
        mPhotoView.setImageBitmap(bitmap);
        return v;
    }
}
