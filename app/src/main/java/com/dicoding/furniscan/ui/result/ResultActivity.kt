package com.dicoding.furniscan.ui.result

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.dicoding.furniscan.R
import com.dicoding.furniscan.TensorModel
import com.dicoding.furniscan.adapter.PredictAdapter
import com.dicoding.furniscan.databinding.ActivityResultBinding
import com.dicoding.furniscan.databinding.DialogBottomBinding
import com.dicoding.furniscan.ui.PredictModelFactory
import com.dicoding.furniscan.ui.scan.ScanActivity
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class ResultActivity : AppCompatActivity() {
    private lateinit var tensorModel: TensorModel
    private var currentImageUri: Uri? = null
    private lateinit var viewModel: ResultViewModel
    private lateinit var bottomBinding: DialogBottomBinding

    private lateinit var binding: ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bottomBinding = DialogBottomBinding.inflate(layoutInflater)

        val adapter = PredictAdapter()
        val layoutManager = GridLayoutManager(this, 2)
        bottomBinding.rvFavorite.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        bottomBinding.rvFavorite.addItemDecoration(itemDecoration)

        val imageUriString = intent.getStringExtra(ScanActivity.EXTRA_CAMERAX_IMAGE)
        currentImageUri = Uri.parse(imageUriString)

        val factory: PredictModelFactory =
            PredictModelFactory.getInstance(
                this,
            )
        viewModel = ViewModelProvider(this, factory)[ResultViewModel::class.java]

        val localCurrentImageUri = currentImageUri
        if (localCurrentImageUri != null) {
            val file = File(localCurrentImageUri.path)
            val requestFile = file.asRequestBody("image/*".toMediaTypeOrNull())
            val body = MultipartBody.Part.createFormData("image", file.name, requestFile)

            viewModel.getPredictResult(body).observe(this@ResultActivity) { response ->
                when (response) {
                    is com.dicoding.furniscan.Result.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                        Toast.makeText(this@ResultActivity, "Loading...", Toast.LENGTH_SHORT)
                            .show()
                    }

                    is com.dicoding.furniscan.Result.Success -> {
                        binding.progressBar.visibility = View.GONE
                        val data = response.data
                        val predict = data.data
                        showBottomSheetDialog()
                        bottomBinding.rvFavorite.adapter = adapter
                        Toast.makeText(
                            this@ResultActivity,
                            "Success: ${data.data}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    is com.dicoding.furniscan.Result.Error -> {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(
                            this@ResultActivity,
                            "Error: ${response.error}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }

        binding.ivPreview.setImageURI(currentImageUri)
    }
    private fun showBottomSheetDialog() {
        val bottomSheetDialog = BottomSheetDialog(this)
        val view = layoutInflater.inflate(R.layout.dialog_bottom, null)

        val displayMetrics = resources.displayMetrics
        val screenHeight = displayMetrics.heightPixels

        val layoutParams = CoordinatorLayout.LayoutParams(
            CoordinatorLayout.LayoutParams.MATCH_PARENT,
            screenHeight
        )
        view.layoutParams = layoutParams

        bottomSheetDialog.setContentView(view)

        val bottomSheetBehavior = BottomSheetBehavior.from(view.parent as View)
        bottomSheetBehavior.isFitToContents = false
        bottomSheetBehavior.setExpandedOffset(0)
        bottomSheetDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        bottomSheetDialog.show()
    }

    companion object {
        const val EXTRA_RESULT = "extra_result"
    }


}