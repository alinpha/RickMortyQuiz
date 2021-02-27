package com.example.rickmortyquiz.screens.game

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
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

        viewModel.eventGameFinish.observe(viewLifecycleOwner, Observer<Boolean> { hasFinished ->
            if (hasFinished) gameFinished()
        })


        return binding.root
    }

    private fun gameFinished() {
        Toast.makeText(activity, "Game has just finished", Toast.LENGTH_SHORT).show()
        val action = GameFragmentDirections.actionGameFragmentToGameOverFragment(viewModel.score.value ?: 0)
       // action.score = viewModel.score.value ?: 0
        NavHostFragment.findNavController(this).navigate(action)
        viewModel.onGameFinishComplete()

    }

}