package com.phistream.practicecode.webrtc

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.phistream.practicecode.R
import com.yalantis.ucrop.UCrop
import kotlinx.android.synthetic.main.activity_web_rtc.*
import kotlinx.android.synthetic.main.content_web_rtc.*
import java.io.File


class WebRtc : AppCompatActivity() {
    private var i = 0
    private val REQUEST_SELECT_PICTURE = 100
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_rtc)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            var picIntent = Intent()
            picIntent.type = "image/*"
            picIntent.action = Intent.ACTION_GET_CONTENT
            picIntent.addCategory(Intent.CATEGORY_OPENABLE)
            startActivityForResult(Intent.createChooser(picIntent, "Select Picture"), REQUEST_SELECT_PICTURE)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                REQUEST_SELECT_PICTURE -> {
                    val uri = data?.data
                    if (uri != null) {
                        startCropActivity(uri)
                    } else {
                        Toast.makeText(applicationContext, "can't retrieve selected image", Toast.LENGTH_SHORT).show();
                    }
                }
                UCrop.REQUEST_CROP -> {
                    handleCropResult(data)
                }
                UCrop.RESULT_ERROR -> {
                    /*handleCropError(data)*/
                }

            }
        }
    }

    private fun handleCropResult(data: Intent?) {
        if (data != null) {
            var resultUri = UCrop.getOutput(data)
            try {
                ivResult.setImageURI(resultUri)

            } catch (e: Exception) {
                e.printStackTrace()
            }


        }
    }

    private fun startCropActivity(uri: Uri) {
        var destinationFile = "SAMPLE_CROPPED_IMAGE_NAME${++i}.jpg"

        var uCrop = UCrop.of(uri, Uri.fromFile(File(cacheDir, destinationFile)))
        uCrop = basicConfig(uCrop)
        /*uCrop = advanceConfig(uCrop)*/
        uCrop.start(this@WebRtc)
    }


    private fun basicConfig(uCrop: UCrop?): UCrop? {
        var uCrop = uCrop?.useSourceImageAspectRatio()

        return uCrop
    }


    override fun onDestroy() {
        super.onDestroy()
    }
}
