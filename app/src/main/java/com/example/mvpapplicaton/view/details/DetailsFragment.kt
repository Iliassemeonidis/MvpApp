package com.example.mvpapplicaton.view.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mvpapplicaton.App
import com.example.mvpapplicaton.databinding.FragmentDetailsBinding
import com.example.mvpapplicaton.presenter.details.DetailsPresenter
import com.example.mvpapplicaton.view.BackButtonListener
import com.example.mvpapplicaton.view.details.adapter.DetailsAdapter
import com.example.mvpapplicaton.view.user.GithubUser
import com.example.mvpapplicaton.view.user.UsersView
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

private const val ARG_PARAM1 = "param1"

class DetailsFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {

    private var vb: FragmentDetailsBinding? = null
    private lateinit var detailsAdapter: DetailsAdapter

    private val userRepoPresenter: DetailsPresenter by moxyPresenter {
        DetailsPresenter(
            arguments?.getParcelable<GithubUser>(ARG_PARAM1)?.reposUrl.toString()
        ).apply { App.instance.appComponent.inject(this) }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentDetailsBinding.inflate(inflater, container, false).also { vb = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        println(arguments?.getParcelable<GithubUser>(ARG_PARAM1)?.reposUrl.toString())
    }

    override fun backPressed() = userRepoPresenter.backPressed()

    override fun init() {
        detailsAdapter = DetailsAdapter(userRepoPresenter.userRepoPresenter)
        vb?.rvDetailsRep?.adapter = detailsAdapter
    }

    override fun updateList() {
        detailsAdapter.notifyDataSetChanged()
    }

    override fun onDestroy() {
        super.onDestroy()
        vb = null
    }

    companion object {
        @JvmStatic
        fun newInstance(data: GithubUser) =
            DetailsFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_PARAM1, data)
                }
            }
    }
}