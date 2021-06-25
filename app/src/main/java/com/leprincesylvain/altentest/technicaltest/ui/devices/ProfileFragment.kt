package com.leprincesylvain.altentest.technicaltest.ui.devices

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.leprincesylvain.altentest.technicaltest.R
import com.leprincesylvain.altentest.technicaltest.data.network.DataApi
import com.leprincesylvain.altentest.technicaltest.data.repository.DataRepository
import kotlinx.android.synthetic.main.devices_fragment.*
import kotlinx.android.synthetic.main.profile_fragment.*
import java.text.SimpleDateFormat
import java.util.*

class ProfileFragment : Fragment(R.layout.profile_fragment) {
    private lateinit var factory: UserViewModelFactory
    private lateinit var viewModel: UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.profile_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val api = DataApi()
        val repository = DataRepository(api)

        factory = UserViewModelFactory(repository)
        viewModel = ViewModelProviders.of(this, factory).get(UserViewModel::class.java)

        viewModel.getUser()

        viewModel.user.observe(viewLifecycleOwner, Observer {
            it.let {
                user_firstname_text.text = it.firstName
                user_lastname_text.text = it.lastName
                user_city_text.text = it.address.city
                user_postalcode_text.text = it.address.postalCode.toString()
                user_street_text.text = it.address.street
                user_streetcode_text.text = it.address.streetCode
                user_country_text.text = it.address.country
                user_birthdate_text.text = getDate(it.birthDate, "dd/MM/yyyy")
            }
        })
    }

    fun getDate(milliSeconds: Long, dateFormat: String?): String? {
        // Create a DateFormatter object for displaying date in specified format.
        val formatter = SimpleDateFormat(dateFormat)

        // Create a calendar object that will convert the date and time value in milliseconds to date.
        val calendar: Calendar = Calendar.getInstance()
        calendar.setTimeInMillis(milliSeconds)
        return formatter.format(calendar.getTime())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.profile_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_list -> {
                Navigation.findNavController(this.requireView())
                    .navigate(R.id.action_profileFragment_to_devicesFragment)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}