package com.leprincesylvain.altentest.technicaltest.ui.devices

import android.view.View
import com.leprincesylvain.altentest.technicaltest.data.model.Device

interface RecyclerViewClickListener {
    fun onRecyclerViewItemClick(view: View, device: Device)
    fun onItemModified(device: Device)
}