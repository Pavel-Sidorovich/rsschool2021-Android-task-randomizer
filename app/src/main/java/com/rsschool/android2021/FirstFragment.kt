package com.rsschool.android2021

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.text.isDigitsOnly
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.rsschool.android2021.databinding.FragmentFirstBinding
import com.rsschool.android2021.utils.viewBinding

class FirstFragment : Fragment(R.layout.fragment_first) {

    private val binding: FragmentFirstBinding by viewBinding(FragmentFirstBinding::bind)

    private var listener: FirstFragmentEventListener? = null

    private var min = ""

    private var max = ""

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = activity as? FirstFragmentEventListener
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        changeEnabled()

        val result = arguments?.getInt(PREVIOUS_RESULT_KEY)
        binding.previousResult.text = "Previous result: ${result.toString()}"

        binding.minValue.addTextChangedListener {
            min = binding.minValue.text.toString()
            changeEnabled()
        }

        binding.maxValue.addTextChangedListener {
            max = binding.maxValue.text.toString()
            changeEnabled()
        }

        binding.generate.setOnClickListener {
            val minDigits = min.toInt()

            val maxDigits = max.toInt()

            listener?.generate(
                min = minDigits,
                max = maxDigits
            )
        }
    }

    private fun isDigits(): Boolean = min.isNotBlank() && min.isDigitsOnly() && max.isNotBlank() && max.isDigitsOnly()

    private fun isInt(): Boolean = (min.toIntOrNull() ?: -1) >= 0 && (max.toIntOrNull() ?: -1) >= 0

    private fun minLessMax(): Boolean = min.toInt() <= max.toInt()

    private fun changeEnabled() {
        binding.generate.isEnabled = isDigits() && isInt() && minLessMax()
    }

    companion object {

        @JvmStatic
        fun newInstance(previousResult: Int): FirstFragment {
            val fragment = FirstFragment()
            val args = Bundle()
            args.putInt(PREVIOUS_RESULT_KEY, previousResult)
            fragment.arguments = args
            return fragment
        }

        private const val PREVIOUS_RESULT_KEY = "PREVIOUS_RESULT"
    }
}

interface FirstFragmentEventListener {
    fun generate(min: Int, max: Int)
}
