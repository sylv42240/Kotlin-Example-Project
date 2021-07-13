package com.sgranjon.kotlinexampleproject.presentation.ui.character_list.item

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.sgranjon.kotlinexampleproject.R
import com.sgranjon.kotlinexampleproject.presentation.extensions.setOnClickDelayListener
import com.sgranjon.kotlinexampleproject.presentation.wrapper.CharacterViewDataWrapper

class CharacterViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(
        character: CharacterViewDataWrapper,
        onItemClicked: (Int) -> Unit
    ) {
        with(itemView) {
            val image = findViewById<ImageView>(R.id.view_character_item_image)
            val name = findViewById<TextView>(R.id.view_character_item_name)
            val gender = findViewById<TextView>(R.id.view_character_item_gender)
            val status = findViewById<TextView>(R.id.view_character_item_status)
            val genderIcon = findViewById<ImageView>(R.id.view_character_item_gender_icon)
            val statusIcon = findViewById<ImageView>(R.id.view_character_item_status_icon)

            Glide.with(context)
                .load(character.getImageUrl())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(image)
            name.text = character.getName()
            gender.text = character.getGender(context)
            status.text = character.getStatus(context)
            genderIcon.setImageDrawable(character.getGenderIcon(context))
            statusIcon.setImageDrawable(character.getStatusIcon(context))

            setOnClickDelayListener {
                onItemClicked(character.getId())
            }
        }
    }
}