package com.nouman.gittreadingrepo.ui

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.nouman.gittreadingrepo.R
import com.nouman.gittreadingrepo.databinding.ActivityMainBinding
import com.nouman.gittreadingrepo.models.GetRepoStates
import com.nouman.gittreadingrepo.models.Repo
import com.nouman.gittreadingrepo.utils.observe
import com.nouman.gittreadingrepo.viewmodel.MainViewModel
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.toolbar.view.*
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var mainViewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initUiAndData()
        mainViewModel.getTreadingRepos()
    }

    private fun MainViewModel.observeRepos() {
        observe(repos) {
            when (it) {
                is GetRepoStates.LoadReposToUi -> loadReposToUi(it.repoList)
                is GetRepoStates.ShowError -> handleError(it.isEmptyList)
                GetRepoStates.ShowLoading -> startShimmer()
            }
        }
    }

    private fun loadReposToUi(repoList: List<Repo>) {
        stopShimmer()
        updateRecyclerView(repoList)
        binding.toolbar.btnShowFromDB.visibility = View.VISIBLE
    }

    private fun handleError(isEmptyList: Boolean) {
        stopShimmer()
        showErrorRetryView(isEmptyList)
        binding.toolbar.btnShowFromDB.visibility = View.GONE
    }

    private fun showErrorRetryView(isEmptyList: Boolean) {
        binding.groupErrorRetry.visibility = View.VISIBLE
        binding.animationView.playAnimation()
        binding.txtError.text =
            if (isEmptyList) baseContext.resources.getString(R.string.no_result_error_message)
            else baseContext.resources.getString(
                R.string.no_internet_error_message
            )
    }

    private fun hideErrorRetryView() {
        binding.groupErrorRetry.visibility = View.GONE
    }

    private fun updateRecyclerView(repoList: List<Repo>) {
        binding.rvRepos.layoutManager = LinearLayoutManager(applicationContext)
        binding.rvRepos.adapter = RepoAdapter(repoList, ::onRepoClicked)
    }

    private fun onRepoClicked(repo: Repo) {
        RepoDetailsActivity.navigateToRepoDetails(this, repo)
    }

    private fun initUiAndData() {
        mainViewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        mainViewModel.observeRepos()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.btnRetry.setOnClickListener {
            handleRetryClick()
        }
        binding.toolbar.btnShowFromDB.setOnClickListener {
            handleLoadFromDB()
        }
    }

    private fun handleRetryClick() {
        hideErrorRetryView()
        startShimmer()
        mainViewModel.loadReposFromDb()
    }

    private fun handleLoadFromDB() {
        hideErrorRetryView()
        startShimmer()
        mainViewModel.loadReposFromDb()
    }

    private fun startShimmer() {
        binding.shimmerLayout.startShimmer()
        binding.shimmerLayout.visibility = View.VISIBLE
    }

    private fun stopShimmer() {
        binding.shimmerLayout.stopShimmer()
        binding.shimmerLayout.visibility = View.GONE
    }
}
