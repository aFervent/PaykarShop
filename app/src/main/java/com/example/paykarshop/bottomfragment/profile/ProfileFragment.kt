package com.example.paykarshop.bottomfragment.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.paykarshop.R
import kotlinx.android.synthetic.main.fragment_profile.*

import android.content.SharedPreferences
import android.net.Uri
import androidx.appcompat.app.AlertDialog
import com.example.paykarshop.bottomfragment.profile.actions.SalesActivity
import com.example.paykarshop.bottomfragment.profile.favofite.FavoriteActivity
import com.example.paykarshop.bottomfragment.profile.history.HistoryActivity
import com.example.paykarshop.bottomfragment.profile.notification.NotificationActivity
import com.example.paykarshop.bottomfragment.profile.settings.SettingsActivity


class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        notification_button.setOnClickListener {
            startActivity(Intent(requireContext(), NotificationActivity::class.java))
        }

        settings_button.setOnClickListener {
            startActivity(Intent(requireContext(), SettingsActivity::class.java))
        }
        val settings: SharedPreferences =
            requireActivity().applicationContext.getSharedPreferences("paykarShop", 0)
        val numberLogin = settings.getString("numberLogin", "ALL")

        phone_profile.text = numberLogin

        val nameLogin = settings.getString("nameLogin", "All")

        name_profile.text = nameLogin

        history_activity.setOnClickListener {
            startActivity(Intent(requireContext(), HistoryActivity::class.java))
        }

        sale_info.setOnClickListener {
            startActivity(Intent(requireContext(), SalesActivity::class.java))
        }

        webView_watch.setOnClickListener {
            val url = "https://paykar24.tj/online/shop"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }

        save_order.setOnClickListener {
            startActivity(Intent(requireContext(), FavoriteActivity::class.java))
        }

        logout.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle("Вы уверены что хотите выйти?")

//builder.setPositiveButton("OK", DialogInterface.OnClickListener(function = x))

            builder.setPositiveButton("Да") { dialog, which ->
                startActivity(Intent(requireContext(), LoginActivity::class.java))
            }

            builder.setNegativeButton("Нет") { dialog, which ->

            }

            builder.show()

        }
    }



}