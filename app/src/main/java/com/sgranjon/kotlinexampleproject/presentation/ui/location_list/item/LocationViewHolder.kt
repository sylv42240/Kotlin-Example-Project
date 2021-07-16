package com.sgranjon.kotlinexampleproject.presentation.ui.location_list.item

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sgranjon.kotlinexampleproject.R
import com.sgranjon.kotlinexampleproject.presentation.wrapper.LocationViewDataWrapper

class LocationViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(
        location: LocationViewDataWrapper,
    ) {
        with(itemView) {
            val name = findViewById<TextView>(R.id.view_location_item_name)
            val dimension = findViewById<TextView>(R.id.view_location_item_dimension)
            val type = findViewById<TextView>(R.id.view_location_item_type)
            val residentCount = findViewById<TextView>(R.id.view_location_item_residents_count)

            name.text = location.getName()
            dimension.text = location.getDimension(context)
            type.text = location.getType()
            residentCount.text = location.getResidentCountText(context)
        }
    }
}