package com.example.pr2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.pr2.databinding.FragmentTwoHandBinding

class FragmentTwoHandheld : Fragment() {
    private lateinit var binding: FragmentTwoHandBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTwoHandBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toFirstFragHand.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.hand, FragmentOneHandheld()).addToBackStack(null).commit()
        }

        binding.toThirdFragHand.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.hand, FragmentThreeHandheld()).addToBackStack(null).commit()
        }
    }
}