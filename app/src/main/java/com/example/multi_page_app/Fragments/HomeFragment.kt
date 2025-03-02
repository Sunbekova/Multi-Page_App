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


class HomeFragment : Fragment() {

    private lateinit var airplaneModeReceiver: BroadcastReceiver

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_home, container, false)

        // Handle Navigation Buttons
        view.findViewById<Button>(R.id.btn_deeplink).setOnClickListener{
            view.findNavController().navigate(R.id.action_homeFragment_to_deeplinkingFragment3)}
        view.findViewById<Button>(R.id.btn_forgraund).setOnClickListener{
            view.findNavController().navigate(R.id.action_homeFragment_to_forgroundServiceFragment)}
        view.findViewById<Button>(R.id.btn_contentprovider).setOnClickListener{
            view.findNavController().navigate(R.id.action_homeFragment_to_contentProviderFragment)}

        // Opens Airplane Mode Settings
        val airplaneModeChangeReciever = view.findViewById<Button>(R.id.btn_AirplaneModeCheck)
        airplaneModeChangeReciever.setOnClickListener() {
            startActivity(Intent(Settings.ACTION_AIRPLANE_MODE_SETTINGS))
        }

        return view
    }

    override fun onResume() {
        super.onResume()

        // Register BroadcastReceiver to listen for Airplane Mode changes
        airplaneModeReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                val isAirplaneModeEnabled =
                    Settings.Global.getInt(context?.contentResolver, Settings.Global.AIRPLANE_MODE_ON, 0) != 0

                if (isAirplaneModeEnabled) {
                    Toast.makeText(context, "Airplane Mode is ON", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(context, "Airplane Mode is OFF", Toast.LENGTH_LONG).show()
                }
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
}