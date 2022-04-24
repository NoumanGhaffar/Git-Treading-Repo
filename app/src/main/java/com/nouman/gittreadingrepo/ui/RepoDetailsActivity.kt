package com.nouman.gittreadingrepo.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.nouman.gittreadingrepo.R
import com.nouman.gittreadingrepo.databinding.ActivityRepoDetailsBinding
import com.nouman.gittreadingrepo.models.GetRepoStates
import com.nouman.gittreadingrepo.models.Repo
import com.nouman.gittreadingrepo.models.ShowRepoStates
import com.nouman.gittreadingrepo.utils.observe
import com.nouman.gittreadingrepo.viewmodel.MainViewModel
import com.nouman.gittreadingrepo.viewmodel.RepoDetailsViewModel
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class RepoDetailsActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var repoDetailsViewModel: RepoDetailsViewModel
    private lateinit var binding: ActivityRepoDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initUiAndData()
        intent.getParcelableExtra<Repo>(REPO_DATA_PARAMS)?.let { repoDetailsViewModel.loadRepoDetails(it) }
    }

    private fun RepoDetailsViewModel.observeRepo() {
        observe(repo) {
            when (it) {
                is ShowRepoStates.LoadReposToUi -> updateUi(it.repo)
            }
        }
    }

    private fun updateUi(repo: Repo) {
        binding.repo = repo
        binding.executePendingBindings()
    }

    private fun initUiAndData() {
        repoDetailsViewModel = ViewModelProvider(this, viewModelFactory).get(RepoDetailsViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_repo_details)
        repoDetailsViewModel.observeRepo()
    }

    companion object {

        private const val REPO_DATA_PARAMS = "repo_model"
        fun navigateToRepoDetails(context: Context, repoModel: Repo) {
            context.startActivity(newIntent(context, repoModel))
        }

        private fun newIntent(context: Context, repoModel: Repo): Intent {
            val intent = Intent(context, RepoDetailsActivity::class.java)
            intent.putExtra(REPO_DATA_PARAMS, repoModel)
            return intent
        }
    }
}
