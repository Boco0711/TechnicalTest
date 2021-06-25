package com.leprincesylvain.altentest.technicaltest.ui.devices

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.leprincesylvain.altentest.technicaltest.R
import com.leprincesylvain.altentest.technicaltest.data.model.Device
import com.leprincesylvain.altentest.technicaltest.data.network.DataApi
import com.leprincesylvain.altentest.technicaltest.data.repository.DataRepository
import com.leprincesylvain.altentest.technicaltest.room.DataDatabase
import kotlinx.android.synthetic.main.devices_fragment.*


class DevicesFragment : Fragment(), RecyclerViewClickListener {

    private lateinit var factory: DevicesViewModelFactory
    private lateinit var viewModel: DevicesViewModel
    private lateinit var repository: DataRepository
    var adapter: DevicesAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.devices_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val api = DataApi()
        val dao = DataDatabase.getDataDatabase(this.requireContext()).dataDao()
        repository = DataRepository(dao, api)

        //repository = DataRepository(api)
        factory = DevicesViewModelFactory(repository)
        viewModel = ViewModelProviders.of(this, factory).get(DevicesViewModel::class.java)

        viewModel.getDevices()

        viewModel.devices.observe(viewLifecycleOwner, Observer { devices ->
            recycler_view_device.also {
                it.layoutManager = LinearLayoutManager(requireContext())
                it.setHasFixedSize(true)
                adapter = DevicesAdapter(devices, this)
                it.adapter = adapter
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.filter_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_type_light -> {
                adapter?.filter?.filter("Light")
            }
            R.id.menu_type_rollershutter -> {
                adapter?.filter?.filter("RollerShutter")
            }
            R.id.menu_type_heater -> {
                adapter?.filter?.filter("Heater")
            }
            R.id.menu_type_all -> {
                adapter?.filter?.filter("")
            }
            R.id.menu_profile -> {
                val action = DevicesFragmentDirections.actionDevicesFragmentToProfileFragment()
                findNavController().navigate(action)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onRecyclerViewItemClick(view: View, device: Device) {

    }

    override fun onItemModified(device: Device) {
    }
}