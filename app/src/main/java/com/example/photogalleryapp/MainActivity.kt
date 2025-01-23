package com.example.photogalleryapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.photogalleryapp.adapter.PhotoAdapter
//import com.example.photogalleryapp.adapter.PhotoAdapter
//import com.example.photogalleryapp.adapter.PhotoAdapter
import com.example.photogalleryapp.model.Photo
import com.example.photogalleryapp.network.RetrofitClient
//import com.example.photosapp.adapter.PhotoAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var photoAdapter: PhotoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Fetch photos from the API
        RetrofitClient.apiService.getPhotos().enqueue(object : Callback<List<Photo>> {
            override fun onResponse(call: Call<List<Photo>>, response: Response<List<Photo>>) {
                if (response.isSuccessful) {
                    val photos = response.body() ?: emptyList()
                    photoAdapter = PhotoAdapter(photos)
                    recyclerView.adapter = photoAdapter
                } else {
                    Toast.makeText(this@MainActivity, "Error: ${response.message()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Photo>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Failed to load data", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
