package com.rsschool.android2021

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.rsschool.android2021.databinding.FragmentSecondBinding
import com.rsschool.android2021.utils.viewBinding
import kotlin.random.Random

class SecondFragment : Fragment(R.layout.fragment_second), IOnBackPressed {

    private val binding: FragmentSecondBinding by viewBinding(FragmentSecondBinding::bind)

    private var listener: SecondFragmentEventListener? = null

    private var result = "0"

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = activity as? SecondFragmentEventListener
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val min = arguments?.getInt(MIN_VALUE_KEY) ?: 0
        val max = arguments?.getInt(MAX_VALUE_KEY) ?: 0

        result = generate(min, max).toString()
        binding.result.text = result

        binding.back.setOnClickListener {
            listener?.back(
                previousResult = result.toInt()
            )
        }
    }

    private fun generate(min: Int, max: Int): Int {
        return when {
            min > max -> {
                max + Random.nextInt(min - max)
            }
            max - min < Int.MAX_VALUE - 1 -> {
                min + Random.nextInt(max - min + 1)
            }
            else -> {
                min + Random.nextInt(max - min)
            }
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(min: Int, max: Int): SecondFragment {
            val fragment = SecondFragment()
            fragment.arguments = Bundle().apply {
                putInt(MIN_VALUE_KEY, min)
                putInt(MAX_VALUE_KEY, max)
            }
            return fragment
        }

        private const val MIN_VALUE_KEY = "MIN_VALUE"
        private const val MAX_VALUE_KEY = "MAX_VALUE"
    }

    override fun onBackPressed() {
        listener?.back(
            previousResult = result.toInt()
        )
        onDestroy()
    }
}

interface SecondFragmentEventListener {
    fun back(previousResult: Int)
}