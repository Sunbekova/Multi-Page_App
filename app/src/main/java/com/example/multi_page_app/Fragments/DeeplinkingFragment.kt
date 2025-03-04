package com.example.multi_page_app.Fragments

import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.FragmentManager
import com.example.multi_page_app.R
import com.example.multi_page_app.databinding.FragmentDeeplinkingBinding


class DeeplinkingFragment : Fragment() {

    private var _binding: FragmentDeeplinkingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDeeplinkingBinding.inflate(inflater, container, false)

        binding.btnHome.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }

        binding.btnChooseImage.setOnClickListener(){
            pickImage()
        }

        return binding.root
    }

    private fun pickImage() {
        pickImageLauncher.launch("image/*")
    }

    private val pickImageLauncher =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            uri?.let {
                shareToInstagramStory(it)
            }
        }

    private fun shareToInstagramStory(imageUri: Uri) {
        if (!isInstagramInstalled()) {
            val playStoreIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.instagram.android"))
            startActivity(playStoreIntent)
            return
        }

        val intent = Intent("com.instagram.share.ADD_TO_STORY").apply {
            setDataAndType(imageUri, "image/*")
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            setPackage("com.instagram.android")
        }

        requireActivity().grantUriPermission(
            "com.instagram.android",
            imageUri,
            Intent.FLAG_GRANT_READ_URI_PERMISSION
        )

        try {
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(requireContext(), "Instagram sharing is not available.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun isInstagramInstalled(): Boolean {
        return try {
            requireContext().packageManager.getPackageInfo("com.instagram.android", 0)
            true
        } catch (e: PackageManager.NameNotFoundException) {
            false
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}