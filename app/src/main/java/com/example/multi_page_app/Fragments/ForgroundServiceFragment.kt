package com.example.multi_page_app.Fragments

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentManager
import com.example.multi_page_app.service.MusicService
import com.example.multi_page_app.databinding.FragmentForgroundServiceBinding


class ForgroundServiceFragment : Fragment() {

    private var _binding: FragmentForgroundServiceBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentForgroundServiceBinding.inflate(inflater, container, false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnHome.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }

        binding.btnStart.setOnClickListener { startMusicService("START") }
        binding.btnPause.setOnClickListener { startMusicService("PAUSE") }
        binding.btnStop.setOnClickListener { startMusicService("STOP") }

        // Notification
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.POST_NOTIFICATIONS)
                != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(arrayOf(Manifest.permission.POST_NOTIFICATIONS), 102)
            }
        }

    }

    private fun startMusicService(action: String) {
        val serviceIntent = Intent(requireContext(), MusicService::class.java)
        serviceIntent.action = action
        requireContext().startService(serviceIntent)
        Toast.makeText(requireContext(), "Music $action", Toast.LENGTH_SHORT).show()
    }

}