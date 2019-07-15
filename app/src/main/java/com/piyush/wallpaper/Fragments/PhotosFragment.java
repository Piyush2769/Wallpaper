package com.piyush.wallpaper.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.piyush.wallpaper.Models.Photo;
import com.piyush.wallpaper.PhotosAdapter;
import com.piyush.wallpaper.R;
import com.piyush.wallpaper.WebServices.ApiInterface;
import com.piyush.wallpaper.WebServices.ServiceGenerator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhotosFragment extends Fragment {

    private final String TAG=PhotosFragment.class.getSimpleName();
    @BindView(R.id.fragment_photos_progress)
    ProgressBar progressBar;
    @BindView(R.id.fragment_photos_recycler)
    RecyclerView recyclerView;

    private Unbinder unbinder;


    private PhotosAdapter photosAdapter;
    private List<Photo> photos=new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view=inflater.inflate(R.layout.fragment_photos,container,false);
       unbinder= ButterKnife.bind(this,view);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        photosAdapter=new PhotosAdapter(getActivity(),photos);
        recyclerView.setAdapter(photosAdapter);

        showProgress(true);
        getPhotos();

       return view;
    }

    private void getPhotos()
    {
        ApiInterface apiInterface= ServiceGenerator.createService(ApiInterface.class);
        Call<List<Photo>> call=apiInterface.getPhotos();
        call.enqueue(new Callback<List<Photo>>() {
            @Override
            public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {
                 if(response.isSuccessful())
                 {
                     photos.addAll(response.body());
                     photosAdapter.notifyDataSetChanged();
                 }
                 else
                 {
                     Log.e(TAG,"fail"+response.message());
                 }
                 showProgress(false);
            }

            @Override
            public void onFailure(Call<List<Photo>> call, Throwable t) {
                Log.e(TAG,"fail"+t.getMessage());
                showProgress(false);
            }
        });
    }

    private void showProgress(Boolean isShow)
    {
        if(isShow)
        {
            progressBar.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }
        else
        {
            progressBar.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void onDestroyView() {

        super.onDestroyView();
        unbinder.unbind();
    }
}
