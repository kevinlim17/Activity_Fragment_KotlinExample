package com.example.mission3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit

import com.example.mission3.databinding.ActivitySecondBinding
import com.example.mission3.fragment.FirstFragment
import com.example.mission3.fragment.SecondFragment
import com.google.android.material.navigation.NavigationBarItemView
import com.google.android.material.navigation.NavigationBarView

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding
    private var fragmentArray = ArrayList<Fragment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fragmentArray.add(FirstFragment())
        fragmentArray.add(SecondFragment())

    }

    override fun onStart() {
        super.onStart()

        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        changeFrame(binding, 1) //Initialize Frame

        //Click Button to Switch Frame
        binding.switchFragment1.setOnClickListener{
            changeFrame(binding, 1)
        }

        binding.switchFragment2.setOnClickListener {
            changeFrame(binding,2)
        }

        //Switching Frame by bottomNavigationView
        binding.selectFragmentNavi.run {
            setOnItemSelectedListener {
                when(it.itemId){
                    R.id.select_firstFragment -> changeFrame(binding, 1)
                    R.id.select_secondFragment -> changeFrame(binding, 2)
                }
                true
            }
        }

        // Get Text From FirstFragment
        supportFragmentManager
                .setFragmentResultListener("requestKeyForInputText", this) { _, bundle ->

                    if(bundle.containsKey("SetText")) {
                        val bundleText = bundle.getString("SetText")
                        binding.TextFromFragment.setText(bundleText)
                        Toast.makeText(this, "Successful Send : $bundleText", Toast.LENGTH_SHORT).show()
                    }
                }
    }

    private fun changeFrame(binding: ActivitySecondBinding, mode : Int){
        supportFragmentManager.commit{
            replace(binding.fragmentFrame.id, fragmentArray[mode-1])
        }
    }
}