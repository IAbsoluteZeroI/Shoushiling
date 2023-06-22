package com.example.kotlinsecond

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.kotlinsecond.databinding.FragmentGameBinding
import kotlin.random.Random


class GameFragment : Fragment() {
    private var _binding: FragmentGameBinding? = null
    private val binding get() = _binding!!

    private var rockCardsNum = 4
    private var scissorsCardsNum = 4
    private var paperCardsNum = 4
    private var botRockCardsNum = 4
    private var botScissorsCardsNum = 4
    private var botPaperCardsNum = 4

    private var starsNum = 3
    private var botStarsNum = 3

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.botStarsNum.text = "x$botStarsNum"
        binding.starsNum.text = "$starsNum"
        binding.rockImage.init("rock", true)
        binding.scissorsImage.init("scissors", true)
        binding.paperImage.init("paper", true)
        binding.rockImageBot.init("rock", false)
        binding.scissorsImageBot.init("scissors", false)
        binding.paperImageBot.init("paper", false)
        var choise = 4
        binding.rockImage.setOnClickListener {
            if ((choise == -1) or (!isGamerHaveCards(0))) {
                resetGame()
                choise = 4
            } else {
                if (!binding.rockImage.mIsFrontShowing) {
                    binding.rockImage.flip()
                }
                if (binding.scissorsImage.mIsFrontShowing) {
                    binding.scissorsImage.flip()
                }
                if (binding.paperImage.mIsFrontShowing) {
                    binding.paperImage.flip()
                }
                choise = 0
            }
        }
        binding.scissorsImage.setOnClickListener {
            if ((choise == -1) or (!isGamerHaveCards(1))) {
                resetGame()
                choise = 4
            } else {
                if (binding.rockImage.mIsFrontShowing) {
                    binding.rockImage.flip()
                }
                if (!binding.scissorsImage.mIsFrontShowing) {
                    binding.scissorsImage.flip()
                }
                if (binding.paperImage.mIsFrontShowing) {
                    binding.paperImage.flip()
                }
                choise = 1
            }
        }
        binding.paperImage.setOnClickListener {
            if ((choise == -1) or (!isGamerHaveCards(2))) {
                resetGame()
                choise = 4
            } else {
                if (binding.rockImage.mIsFrontShowing) {
                    binding.rockImage.flip()
                }
                if (binding.scissorsImage.mIsFrontShowing) {
                    binding.scissorsImage.flip()
                }
                if (!binding.paperImage.mIsFrontShowing) {
                    binding.paperImage.flip()
                }
                choise = 2
            }
        }
        binding.buttonReady.setOnClickListener {
            if (choise <= -1 || choise > 3) {
                resetGame()
                choise = 4
            } else {
                when (choise) {
                    0 -> {
                        rockCardsNum -= 1
                    }
                    1 -> {
                        scissorsCardsNum -= 1
                    }
                    2 -> {
                        paperCardsNum -= 1
                    }
                }
                startinGame(choise)
                choise = -1
                binding.buttonReady.text = "Restart"
                if (isEnd()) {
                    findNavController().navigate(R.id.action_gameFragment_to_mainFragment)
                }
            }
        }
    }

    private fun isEnd() : Boolean {
        if ((rockCardsNum <= 0) and (scissorsCardsNum <= 0) and (paperCardsNum <= 0)) {
            return true
        }
        if ((botRockCardsNum <= 0) and (botScissorsCardsNum <= 0) and (botPaperCardsNum <= 0)) {
            return true
        }
        if(starsNum <= 0) {
            return true
        }
        if (botStarsNum <= 0) {
            findNavController().navigate(R.id.action_gameFragment_to_resultFragment)
        }
        return false
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun startinGame(choise : Int) {
        var botChoise : Int = Random.nextInt(0, 3)
        while (!isBotHaveCards(botChoise)) {
            botChoise = Random.nextInt(0, 3)
        }
        if (botChoise == 0) {
            binding.rockImageBot.flip()
            botRockCardsNum -= 1
        }
        if (botChoise == 1) {
            binding.scissorsImageBot.flip()
            botScissorsCardsNum -= 1
        }
        if (botChoise == 2) {
            binding.paperImageBot.flip()
            botPaperCardsNum -= 1
        }

        if ((choise + 1) % 3 == botChoise) {

            botStarsNum -= 1
            starsNum += 1
        }
        if ((choise + 2) % 3 == botChoise) {

            botStarsNum += 1
            starsNum -= 1
        }
        setNumText()
    }

    private fun isBotHaveCards(card : Int) : Boolean {
        when (card) {
            0 -> {
                return (botRockCardsNum > 0)
            }
            1 -> {
                return (botScissorsCardsNum > 0)
            }
            2 -> {
                return (botPaperCardsNum > 0)
            }
        }
        return true
    }

    private fun isGamerHaveCards(card : Int) : Boolean {
        when (card) {
            0 -> {
                return (rockCardsNum > 0)
            }
            1 -> {
                return (scissorsCardsNum > 0)
            }
            2 -> {
                return (paperCardsNum > 0)
            }
        }
        return true
    }

    @SuppressLint("SetTextI18n")
    private fun setNumText() {
        binding.botStarsNum.text = "x$botStarsNum"
        binding.starsNum.text = "x$starsNum"
        binding.botRockNum.text = "x$botRockCardsNum"
        binding.botScissorsNum.text = "x$botScissorsCardsNum"
        binding.botPaperNum.text = "x$botPaperCardsNum"
        binding.rockNum.text = "x$rockCardsNum"
        binding.scissorsNum.text = "x$scissorsCardsNum"
        binding.paperNum.text = "x$paperCardsNum"
    }

    private fun resetGame() {
        binding.buttonReady.text = "Ready"
        if (!binding.rockImage.mIsFrontShowing) {
            binding.rockImage.flip()
        }
        if (!binding.scissorsImage.mIsFrontShowing) {
            binding.scissorsImage.flip()
        }
        if (!binding.paperImage.mIsFrontShowing) {
            binding.paperImage.flip()
        }
        if (binding.rockImageBot.mIsFrontShowing) {
            binding.rockImageBot.flip()
        }
        if (binding.scissorsImageBot.mIsFrontShowing) {
            binding.scissorsImageBot.flip()
        }
        if (binding.paperImageBot.mIsFrontShowing) {
            binding.paperImageBot.flip()
        }
    }

    private fun showDialog(str : String) {
        val manager = parentFragmentManager
        when(str) {
            "Win" -> {
                val dialog = GameDialogFragment("Win")
                dialog.show(manager, "Win")
            }
            "Loss" -> {
                val dialog = GameDialogFragment("Loss")
                dialog.show(manager, "Loss")
            }
            "Draw" -> {
                val dialog = GameDialogFragment("Draw")
                dialog.show(manager, "Draw")
            }
            else -> {}
        }
    }
}