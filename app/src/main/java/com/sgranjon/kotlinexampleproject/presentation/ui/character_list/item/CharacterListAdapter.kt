package com.sgranjon.kotlinexampleproject.presentation.ui.character_list.item

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sgranjon.kotlinexampleproject.R
import com.sgranjon.kotlinexampleproject.presentation.wrapper.CharacterViewDataWrapper
import javax.inject.Inject


class CharacterListAdapter @Inject constructor() : RecyclerView.Adapter<CharacterViewHolder>() {

    private val items = mutableListOf<CharacterViewDataWrapper>()

    var onItemClicked: (Int) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.view_character_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(items[position], onItemClicked)
    }

    override fun getItemCount(): Int = items.size

    fun setItems(newItems: List<CharacterViewDataWrapper>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }
}