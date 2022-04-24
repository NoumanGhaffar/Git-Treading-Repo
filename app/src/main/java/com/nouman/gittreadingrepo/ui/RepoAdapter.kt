package com.nouman.gittreadingrepo.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nouman.gittreadingrepo.databinding.RowRepoItemBinding
import com.nouman.gittreadingrepo.models.Repo
import com.nouman.gittreadingrepo.ui.RepoAdapter.RepoViewHolder

class RepoAdapter(list: List<Repo>?, val onItemClicked: (Repo) -> Unit) :
    RecyclerView.Adapter<RepoViewHolder>() {

    private val mList = list

    inner class RepoViewHolder(private val binding: RowRepoItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Repo?) {
            item?.let { repo ->
                binding.mainLayout.setOnClickListener {
                    onItemClicked(repo)
                }
                binding.repo = repo
                binding.executePendingBindings()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RowRepoItemBinding.inflate(layoutInflater)
        return RepoViewHolder(binding)
    }

    override fun getItemCount(): Int {
        mList?.let {
            return it.size
        }
        return 0
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) = holder.bind(mList?.get(position))
}
