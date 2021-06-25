package com.leprincesylvain.altentest.technicaltest.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.leprincesylvain.altentest.technicaltest.R
import org.json.JSONObject
import java.io.IOException
import java.io.InputStream


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        loadData()
        saveData()
        //_______________________________________________________________________________
    }

    private fun loadData() {
        val sharedPreferences : SharedPreferences = getSharedPreferences("sharedPref", Context.MODE_PRIVATE)
    }

    private fun saveData() {

    }

    private fun readJson() {
        var json: String? = null

        val list: MutableList<Any> = ArrayList()

        try {
            val inputStream: InputStream = assets.open("data.json")
            json = inputStream.bufferedReader().use { it.readText() }
            var jsonObject = JSONObject(json)
            val jsonArray = jsonObject.getJSONArray("devices")
            for (i in 0 until jsonArray.length()) {
                val device = jsonArray.getJSONObject(i)
                val type = device.getString("productType")
                val id = device.getInt("id")
                val deviceName = device.getString("deviceName")
                var intensity = 0
                var position = 0
                var temperature = 0
                var modeString = ""
                if (device.has("intensity")) {
                    intensity = device.getInt("intensity")
                }
                if (device.has("position")) {
                    position = device.getInt("position")
                }
                if (device.has("temperature")) {
                    temperature = device.getInt("temperature")
                }
                if (device.has("mode")) {
                    modeString = device.getString("mode")
                }
                var mode = false
                if (modeString == "ON") {
                    mode = true
                }
                /*when (type) {
                    "Light" -> {
                        val light = Light(id, deviceName, intensity, mode)
                        list.add(light)
                    }
                    "RollerShutter" -> {
                        val rollerShutter = RollerShutter(id, deviceName, position)
                        list.add(rollerShutter)
                    }
                    "Heater" -> {
                        val heater = Heater(id, deviceName, mode, temperature)
                        list.add(heater)
                    }
                }

                 */
            }
        } catch (e: IOException) {

        }


    }
}