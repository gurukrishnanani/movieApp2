package com.example.photogalleryapp.network

import com.example.photogalleryapp.model.Photo
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("photos") // Ensure the correct endpoint path, relative to the base URL
    fun getPhotos(): Call<List<Photo>>
}
