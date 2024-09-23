package com.example.pr2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.pr2.databinding.FragmentThreeHandBinding

class FragmentThreeHandheld : Fragment() {
    private lateinit var binding: FragmentThreeHandBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentThreeHandBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toSecondFragHand.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.hand, FragmentTwoHandheld()).addToBackStack(null).commit()
        }
    }
}