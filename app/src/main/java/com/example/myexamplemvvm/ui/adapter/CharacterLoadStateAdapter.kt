package com.example.myexamplemvvm.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myexamplemvvm.databinding.ItemLoadingStateBinding

class CharacterLoadStateAdapter (  private val retry: () -> Unit
) : LoadStateAdapter<CharacterLoadStateAdapter.CharacterLoadStateViewHolder>() {

  inner class CharacterLoadStateViewHolder(
    private val binding: ItemLoadingStateBinding,
    private val retry: () -> Unit
  ) : RecyclerView.ViewHolder(binding.root) {

    fun bind(loadState: LoadState) {
      if (loadState is LoadState.Error) {
        binding.textViewError.text = loadState.error.localizedMessage
      }
      binding.progressbar.isVisible =(loadState is LoadState.Loading)
      binding.buttonRetry.isVisible =(loadState is LoadState.Error)
      binding.textViewError.isVisible =(loadState is LoadState.Error)
      binding.buttonRetry.setOnClickListener {
        retry()
      }
      binding.progressbar.visibility = View.VISIBLE
    }
  }

  override fun onBindViewHolder(holder: CharacterLoadStateViewHolder, loadState: LoadState) {
    holder.bind(loadState)
  }

  override fun onCreateViewHolder(
    parent: ViewGroup,
    loadState: LoadState
  ) = CharacterLoadStateViewHolder(
    ItemLoadingStateBinding.inflate(LayoutInflater.from(parent.context), parent, false),
    retry
  )
}