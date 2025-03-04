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
import android.widget.Button
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentManager
import com.example.multi_page_app.MusicService
import com.example.multi_page_app.R


class ForgroundServiceFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_forground_service, container, false)

        val btnHome = view.findViewById<Button>(R.id.btnHome)
        btnHome.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }


        val btnStart = view.findViewById<Button>(R.id.btnStart)
        val btnPause = view.findViewById<Button>(R.id.btnPause)
        val btnStop = view.findViewById<Button>(R.id.btnStop)

        btnStart.setOnClickListener { startMusicService("START") }
        btnPause.setOnClickListener { startMusicService("PAUSE") }
        btnStop.setOnClickListener { startMusicService("STOP") }

        // Request notification permission if needed (Android 13+)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.POST_NOTIFICATIONS)
                != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(arrayOf(Manifest.permission.POST_NOTIFICATIONS), 102)
            }
        }

        return view

    }

    private fun startMusicService(action: String) {
        val serviceIntent = Intent(requireContext(), MusicService::class.java)
        serviceIntent.action = action
        requireContext().startService(serviceIntent)
        Toast.makeText(requireContext(), "Music $action", Toast.LENGTH_SHORT).show()
    }

}