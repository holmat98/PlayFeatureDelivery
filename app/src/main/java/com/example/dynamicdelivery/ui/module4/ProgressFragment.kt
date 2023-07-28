package com.example.dynamicdelivery.ui.module4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.dynamicfeatures.fragment.ui.AbstractProgressFragment
import androidx.navigation.fragment.findNavController
import com.example.dynamicdelivery.databinding.FragmentProgessBinding
import com.example.dynamicdelivery.extensions.toInstallationState

class ProgressFragment : AbstractProgressFragment() {

    private var binding: FragmentProgessBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentProgessBinding.inflate(inflater, container, false)

        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onCancelled() {
        context?.let { Toast.makeText(it, "Cancelled", Toast.LENGTH_SHORT).show() }
        findNavController().navigateUp()
    }

    override fun onFailed(errorCode: Int) {
        context?.let { Toast.makeText(it, "Failed", Toast.LENGTH_SHORT).show() }
        findNavController().navigateUp()
    }

    override fun onProgress(status: Int, bytesDownloaded: Long, bytesTotal: Long) {
        binding?.progressText?.text = status.toInstallationState.name
    }

}
