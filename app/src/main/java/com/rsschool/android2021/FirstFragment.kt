package com.rsschool.android2021

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.text.isDigitsOnly
import androidx.fragment.app.Fragment
import com.rsschool.android2021.databinding.FragmentFirstBinding
import com.rsschool.android2021.utils.viewBinding

class FirstFragment : Fragment(R.layout.fragment_first) {

    private val binding: FragmentFirstBinding by viewBinding(FragmentFirstBinding::bind)

    private var listener: FirstFragmentEventListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = activity as? FirstFragmentEventListener
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val result = arguments?.getInt(PREVIOUS_RESULT_KEY)
        binding.previousResult.text = "Previous result: ${result.toString()}"

        binding.generate.setOnClickListener {
            val min = binding.minValue.text.toString()
            Log.d("PaveSid", min)
            val minDigits = if (min.isNotBlank() && min.isDigitsOnly()) {
                min.toInt()
            } else {
                0
            }

            Log.d("PaveSid", minDigits.toString())

            val max = binding.maxValue.text.toString()
            val maxDigits = if (max.isNotBlank() && max.isDigitsOnly()) {
                max.toInt()
            } else {
                Int.MAX_VALUE
            }

            listener?.generate(
                min = minDigits,
                max = maxDigits
            )
        }
    }

    interface FirstFragmentEventListener {
        fun generate(min: Int, max: Int)
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