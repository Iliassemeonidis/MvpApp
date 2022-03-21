package com.example.mvpapplicaton.view.info

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mvpapplicaton.R
import com.example.mvpapplicaton.databinding.FragmentInfoBinding


private const val ARG_PARAM1 = "param1"

class InfoFragment : Fragment() {

    private var vb: FragmentInfoBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentInfoBinding.inflate(inflater, container, false).also { vb = it }.root


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fork = arguments?.getInt(ARG_PARAM1) ?: 0
        vb?.forkText?.text = "Кол-во форков = $fork"
    }

    override fun onDestroy() {
        super.onDestroy()
        vb = null
    }

    companion object {
        @JvmStatic
        fun newInstance(fork: Int) =
            InfoFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, fork)
                }
            }
    }
}