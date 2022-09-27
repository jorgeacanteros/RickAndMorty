package com.example.myexamplemvvm.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myexamplemvvm.R
import com.example.myexamplemvvm.databinding.CharacterItemBinding
import com.example.myexamplemvvm.domain.model.CharacterModel

class AdapterCharacter( private val clickListener:(CharacterModel)->Unit) :ListAdapter<CharacterModel, CharacterViewHolder>(DiffCallback) {


  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return  CharacterViewHolder(layoutInflater.inflate(R.layout.character_item,parent,false))

  }

  override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val item = getItem(position)

        holder.render(item)
    holder.itemView.setOnClickListener{clickListener(item)}
  }



  companion object {
    private val DiffCallback = object : DiffUtil.ItemCallback<CharacterModel>() {
      override fun areItemsTheSame(oldItem: CharacterModel, newItem: CharacterModel): Boolean {
        return (oldItem.name == newItem.name
            )
      }

      override fun areContentsTheSame(oldItem: CharacterModel, newItem: CharacterModel): Boolean {
        return oldItem == newItem
      }
    }


}}

class CharacterViewHolder(view: View): RecyclerView.ViewHolder(view) {
  private val binding = CharacterItemBinding.bind(view)

  fun render(characaterModel: CharacterModel) {
    binding.NameIDCharacter.text = characaterModel.name
    binding.StatusID.text = characaterModel.status
     Glide.with(binding.root.context).load(characaterModel.image).into(binding.imageCharacterID)
  }
}
