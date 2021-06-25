package com.leprincesylvain.altentest.technicaltest.ui.devices

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.SeekBar
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.leprincesylvain.altentest.technicaltest.R
import com.leprincesylvain.altentest.technicaltest.data.model.Device
import com.leprincesylvain.altentest.technicaltest.databinding.RecyclerviewDeviceBinding
import java.util.*
import kotlin.collections.ArrayList

class DevicesAdapter(
    private val devices: MutableList<Device>,
    private val listener: RecyclerViewClickListener
) : RecyclerView.Adapter<DevicesAdapter.DevicesViewHolder>(), Filterable {

    var deviceFilterList = ArrayList<Device>()
    init {
        deviceFilterList = devices as ArrayList<Device>
    }

    override fun getItemCount(): Int = deviceFilterList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DevicesViewHolder(
            DataBindingUtil.inflate<RecyclerviewDeviceBinding>(
                LayoutInflater.from(parent.context),
                R.layout.recyclerview_device,
                parent,
                false
            )
        )

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: DevicesViewHolder, position: Int) {
        holder.recyclerviewDeviceBinding.device = deviceFilterList[position]
        setSwitchButton(deviceFilterList[position], holder)
        setSeekBarValue(deviceFilterList[position], holder)
        holder.recyclerviewDeviceBinding.buttonDelete.setOnClickListener {
            devices.remove(deviceFilterList[position])
            notifyDataSetChanged()
        }
    }

    private fun setSwitchButton(device: Device, holder: DevicesAdapter.DevicesViewHolder) {
        if (device.mode == null) {
            println("here we lost button")
            holder.recyclerviewDeviceBinding.deviceModeSwitch.visibility = View.GONE
        } else {
            holder.recyclerviewDeviceBinding.deviceModeSwitch.visibility = View.VISIBLE
            if (device.mode == "ON") {
                println("here we lost button")
                holder.recyclerviewDeviceBinding.deviceModeSwitch.isChecked = true
            } else
                holder.recyclerviewDeviceBinding.deviceModeSwitch.isChecked = false
            holder.recyclerviewDeviceBinding.deviceModeSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked) {
                    device.mode = "OFF"
                } else
                    device.mode = "ON"
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun setSeekBarValue(device: Device, holder: DevicesViewHolder) {
        when (device.productType) {
            "Light" -> {
                holder.recyclerviewDeviceBinding.seekBarTextview.text = device.intensity.toString()
                holder.recyclerviewDeviceBinding.deviceSeekbar.max = 100
                holder.recyclerviewDeviceBinding.deviceSeekbar.min = 0
                holder.recyclerviewDeviceBinding.deviceSeekbar.progress = device.intensity
            }
            "RollerShutter" -> {
                holder.recyclerviewDeviceBinding.seekBarTextview.text = device.position.toString()
                holder.recyclerviewDeviceBinding.deviceSeekbar.max = 100
                holder.recyclerviewDeviceBinding.deviceSeekbar.min = 0
                holder.recyclerviewDeviceBinding.deviceSeekbar.progress = device.position
            }
            "Heater" -> {
                holder.recyclerviewDeviceBinding.seekBarTextview.text =
                    device.temperature.toString()
                holder.recyclerviewDeviceBinding.deviceSeekbar.max = 28
                holder.recyclerviewDeviceBinding.deviceSeekbar.min = 7
                holder.recyclerviewDeviceBinding.deviceSeekbar.progress = device.temperature
            }
        }
        holder.recyclerviewDeviceBinding.deviceSeekbar.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(
                seekBar: SeekBar?,
                progress: Int,
                fromUser: Boolean
            ) {
                holder.recyclerviewDeviceBinding.seekBarTextview.text = progress.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })
    }

    class DevicesViewHolder(
        val recyclerviewDeviceBinding: RecyclerviewDeviceBinding
    ) : RecyclerView.ViewHolder(recyclerviewDeviceBinding.root)

    override fun getFilter(): Filter {
        println("Here we bug")
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    deviceFilterList = devices as ArrayList<Device>
                } else {
                    val resultList = ArrayList<Device>()
                    for (item in devices) {
                        if (item.productType.toLowerCase().equals(constraint.toString().toLowerCase())) {
                            resultList.add(item)
                        }
                    }
                    deviceFilterList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = deviceFilterList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                deviceFilterList = results?.values as ArrayList<Device>
                notifyDataSetChanged()
            }
        }
    }
}