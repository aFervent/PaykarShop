package com.example.paykarshop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.paykarshop.bottomfragment.*
import com.example.paykarshop.bottomfragment.basket.BasketFragment
import com.example.paykarshop.bottomfragment.catalog.CatalogFragment
import com.example.paykarshop.bottomfragment.profile.ProfileFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val homeFragment = HomeFragment()
        val catalogFragment = CatalogFragment()
        val searchFragment = SearchFragment()
        val profileFragment = ProfileFragment()
        val basketFragment = BasketFragment()

        setCurrentFragment(homeFragment)

        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home->setCurrentFragment(homeFragment)
                R.id.catalog->setCurrentFragment(catalogFragment)
                R.id.search->setCurrentFragment(searchFragment)
                R.id.profile->setCurrentFragment(profileFragment)
                R.id.basket->setCurrentFragment(basketFragment)

            }
            true
        }

    }

    private fun setCurrentFragment(fragment: Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment,fragment)
            commit()
        }
}