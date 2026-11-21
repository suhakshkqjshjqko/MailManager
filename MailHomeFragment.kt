package com.app.mailmanager.features.mailmanager.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.app.mailmanager.MainActivity
import com.app.mailmanager.MailManagerApp
import com.app.mailmanager.R
import com.app.mailmanager.core.utils.ClipboardHelper
import com.app.mailmanager.databinding.FragmentMailHomeBinding

class MailHomeFragment : Fragment() {

    private var _binding: FragmentMailHomeBinding? = null
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
        _binding = FragmentMailHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.savedMail.observe(viewLifecycleOwner) { email ->
            if (email.isNullOrBlank()) {
                binding.tvGmailAddress.text = getString(R.string.no_mail_saved)
                binding.tvGmailAddress.isClickable = false
                binding.btnActionMail.text = getString(R.string.add_mail)
            } else {
                binding.tvGmailAddress.text = email
                binding.tvGmailAddress.isClickable = true
                binding.btnActionMail.text = getString(R.string.change_mail)
            }
        }

        // Tap displayed Gmail address -> Copy Mail
        binding.tvGmailAddress.setOnClickListener {
            val currentMail = viewModel.savedMail.value
            if (!currentMail.isNullOrBlank()) {
                ClipboardHelper.copyText(requireContext(), "Gmail Address", currentMail)
                Toast.makeText(
                    requireContext(),
                    getString(R.string.mail_copied_successfully),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        // Add Mail / Change Mail button -> Opens dedicated MailEditorFragment
        binding.btnActionMail.setOnClickListener {
            val isChangeMode = !viewModel.savedMail.value.isNullOrBlank()
            val editorFragment = MailEditorFragment.newInstance(isChangeMode)
            (activity as? MainActivity)?.openFeatureFragment(editorFragment, addToBackStack = true)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.refreshMail()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
