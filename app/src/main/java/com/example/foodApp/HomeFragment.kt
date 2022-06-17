package com.example.foodApp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.foodApp.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {
private lateinit var binding:FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentHomeBinding.inflate(layoutInflater)

        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val orderFragment = OrderFragment()
        val accountFragment= AccountFragment()
        val menuFragment= MenuFragment()
        openFragment(menuFragment)

        binding.bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {

                R.id.restaurant -> {
                    openFragment(menuFragment)
                }
                R.id.offers -> {
                    openFragment(orderFragment)
                }
                R.id.account -> {
                    openFragment(accountFragment)
                }
            }
            true
        }
    }

    private fun openFragment(fragment: Fragment) {
        fragmentManager?.beginTransaction()?.apply {
            replace(R.id.container, fragment)
            commit()
        }
    }

}