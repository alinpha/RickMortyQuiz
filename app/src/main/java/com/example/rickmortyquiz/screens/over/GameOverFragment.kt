package com.example.rickmortyquiz.screens.over

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.rickmortyquiz.R
import com.example.rickmortyquiz.databinding.FragmentGameOverBinding
import com.example.rickmortyquiz.screens.game.GameViewModel


class GameOverFragment : Fragment() {

    private lateinit var viewModel: GameOverViewModel
    private lateinit var viewModelFactory: GameOverViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentGameOverBinding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_game_over,
                container,
                false
        )

        viewModelFactory = GameOverViewModelFactory(GameOverFragmentArgs.fromBundle(requireArguments()).score)
        viewModel = ViewModelProvider(this, viewModelFactory)
                .get(GameOverViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }


}