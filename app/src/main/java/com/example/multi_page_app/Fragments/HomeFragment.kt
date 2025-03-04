package com.example.multi_page_app.Fragments

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.multi_page_app.R
import com.example.multi_page_app.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var airplaneModeReceiver: BroadcastReceiver

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Handle Navigation Buttons
        binding.btnDeeplink.setOnClickListener {
            view.findNavController().navigate(R.id.action_homeFragment_to_deeplinkingFragment3)
        }
        binding.btnForgraund.setOnClickListener {
            view.findNavController().navigate(R.id.action_homeFragment_to_forgroundServiceFragment)
        }
        binding.btnContentprovider.setOnClickListener {
            view.findNavController().navigate(R.id.action_homeFragment_to_contentProviderFragment)
        }
        // Opens Airplane Mode Settings
        binding.btnAirplaneModeCheck.setOnClickListener {
            startActivity(Intent(Settings.ACTION_AIRPLANE_MODE_SETTINGS))
        }

    }

    override fun onResume() {
        super.onResume()

        // Register BroadcastReceiver to listen for Airplane Mode changes
        airplaneModeReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                val isAirplaneModeEnabled =
                    Settings.Global.getInt(context?.contentResolver, Settings.Global.AIRPLANE_MODE_ON, 0) != 0

                val message = if (isAirplaneModeEnabled) "Airplane Mode is ON" else "Airplane Mode is OFF"
                Toast.makeText(context, message, Toast.LENGTH_LONG).show()
            }
        }

        requireContext().registerReceiver(
            airplaneModeReceiver,
            IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        )
    }

    override fun onStop() {
        super.onStop()
        requireContext().unregisterReceiver(airplaneModeReceiver)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Prevent memory leaks
    }
}