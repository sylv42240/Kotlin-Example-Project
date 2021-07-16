package com.sgranjon.kotlinexampleproject.presentation.wrapper

import android.content.Context
import com.sgranjon.kotlinexampleproject.R
import com.sgranjon.kotlinexampleproject.data.model.Location

class LocationViewDataWrapper(private val location: Location) {

    fun getId() = location.id

    fun getName() = location.name.split("(").first()

    fun getDimension(context: Context) = if (location.dimension == "unknown") {
        context.getString(R.string.location_dimension_unknown_label)
    } else {
        location.dimension
    }

    fun getType() = location.type

    fun getResidentCountText(context: Context) =
        context.getString(R.string.location_resident_count, location.residentList.size)
}