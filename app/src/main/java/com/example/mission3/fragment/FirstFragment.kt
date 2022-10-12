package com.example.mission3.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import com.example.mission3.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {

    private var fragmentBinding: FragmentFirstBinding? = null
    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = fragmentBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        fragmentBinding = FragmentFirstBinding.inflate(inflater, container, false)

        binding.btnSendTextToActivity.setOnClickListener {
            setFragmentResult("requestKeyForInputText",
                                bundleOf("SetText" to binding.ToActivityText.text.toString()))
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentBinding = null
    }

}