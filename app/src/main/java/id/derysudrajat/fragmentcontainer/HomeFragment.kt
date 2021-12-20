package id.derysudrajat.fragmentcontainer

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import id.derysudrajat.fragmentcontainer.databinding.BaseFragmentBinding

class HomeFragment : Fragment() {


    private var _binding: BaseFragmentBinding? = null
    private lateinit var menuListener: MenuListener
    private val binding get() = _binding!!
    private var isDark = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BaseFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            textView.text = buildString { append("This is home fragment") }
            btnProduct.setOnClickListener {
                menuListener.onMenuSelected(1)
            }
            updateTextColor()

            tvColor.setOnClickListener {
                tvColor.setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        if (isDark) R.color.teal_100 else R.color.teal_700
                    )
                )
                updateTextColor()
                isDark = !isDark
            }
        }

    }

    private fun BaseFragmentBinding.updateTextColor() {
        tvColor.background.let {
            if (it is ColorDrawable) it.color.getInverseColor().let { color ->
                tvColor.setTextColor(ContextCompat.getColor(requireContext(), color))
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(listener: MenuListener) = HomeFragment().apply {
            menuListener = listener
        }
    }
}