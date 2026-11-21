package com.app.mailmanager.features.mailmanager.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.app.mailmanager.MailManagerApp
import com.app.mailmanager.R
import com.app.mailmanager.databinding.FragmentMailEditorBinding

class MailEditorFragment : Fragment() {

    private var _binding: FragmentMailEditorBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MailViewModel by activityViewModels {
        val app = requireActivity().application as MailManagerApp
        MailViewModel.Factory(app.mailRepository)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMailEditorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val isChangeMode = arguments?.getBoolean(ARG_IS_CHANGE_MODE, false) ?: false

        binding.tvScreenTitle.text = if (isChangeMode) {
            getString(R.string.change_mail)
        } else {
            getString(R.string.add_mail)
        }

        binding.etGmailInput.hint = if (isChangeMode) {
            getString(R.string.enter_new_gmail_hint)
        } else {
            getString(R.string.enter_gmail_hint)
        }

        binding.btnBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        binding.btnSaveMail.setOnClickListener {
            binding.tilGmailInput.error = null
            val input = binding.etGmailInput.text?.toString().orEmpty()
            when (val result = viewModel.validateAndSaveMail(input)) {
                is MailViewModel.ValidationResult.Success -> {
                    parentFragmentManager.popBackStack()
                }
                is MailViewModel.ValidationResult.Error -> {
                    binding.tilGmailInput.error = result.message
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val ARG_IS_CHANGE_MODE = "arg_is_change_mode"

        fun newInstance(isChangeMode: Boolean): MailEditorFragment {
            return MailEditorFragment().apply {
                arguments = Bundle().apply {
                    putBoolean(ARG_IS_CHANGE_MODE, isChangeMode)
                }
            }
        }
    }
}
