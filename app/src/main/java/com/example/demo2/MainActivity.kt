package com.example.demo2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import com.example.demo2.databinding.ActivityMainBinding
import com.example.demo2.face_detection.FaceDetectionActivity

class MainActivity : ComponentActivity() {

    private val cameraPermission = android.Manifest.permission.CAMERA;
    private lateinit var binding: ActivityMainBinding

    private val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) {
        isGranted ->
        if (isGranted) {
            startFaceDetection()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button2.setOnClickListener {
            requestCameraAndStartScanner()
        }

    }


    private fun requestCameraAndStartScanner() {
        if (isPermissionGranted(cameraPermission)) {
            //start scan
            startFaceDetection()
        }
        else {
            requestCameraPermission()
        }
    }


    private fun startFaceDetection() {
        FaceDetectionActivity.start(this)
    }

    private fun requestCameraPermission() {
        when {
            shouldShowRequestPermissionRationale(cameraPermission) -> {
                cameraPermissionRequest { openPermissionSetting() }
            }
            else -> {
                requestPermissionLauncher.launch(cameraPermission)
            }


        }
    }
}

