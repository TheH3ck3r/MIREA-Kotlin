package com.example.pr2_v2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.pr2_v2.databinding.FragmentOneHandBinding

class FragmentOneHandheld : Fragment() {
    private lateinit var binding: FragmentOneHandBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOneHandBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toSecondFragHand.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.hand, FragmentTwoHandheld()).addToBackStack(null).commit()
        }
    }
}