package com.example.githubrepos

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.ViewModel
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL


class RepositoryDetailsViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    fun getBitmapFromURL (src: String?) : Bitmap?  {
        return try {
            val url = URL(src)
            val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
            connection.setDoInput(true)
            connection.connect()
            val input: InputStream = connection.getInputStream()
            BitmapFactory.decodeStream(input)
        } catch (e: IOException) {
            // Log exception
            null
        }
    }
}