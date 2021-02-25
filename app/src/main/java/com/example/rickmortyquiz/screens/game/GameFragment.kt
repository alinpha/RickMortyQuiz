package com.example.rickmortyquiz.screens.game

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.rickmortyquiz.R
import com.example.rickmortyquiz.databinding.FragmentGameBinding


class GameFragment : Fragment() {
    private lateinit var viewModel: GameViewModel
    private lateinit var binding:FragmentGameBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentGameBinding>(inflater,
                R.layout.fragment_game,container,false)
        binding.lifecycleOwner = this//viewLifecycleOwner
        viewModel = ViewModelProvider(requireActivity()).get(GameViewModel::class.java)


        binding.viewModel = viewModel




        return binding.root
    }


}