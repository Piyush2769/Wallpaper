package com.piyush.wallpaper.WebServices;

import com.piyush.wallpaper.Models.Photo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("photos")
    Call<List<Photo>> getPhotos();

}
