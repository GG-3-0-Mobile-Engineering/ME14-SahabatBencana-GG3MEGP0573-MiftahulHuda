package com.miftahulhudaf.sahabatbencana.data.utils

import com.miftahulhudaf.sahabatbencana.data.model.DisasterType
import com.miftahulhudaf.sahabatbencana.data.model.LocationData

object LocalData {
    fun getSupportedLocation(): List<LocationData> {
        val locations = ArrayList<LocationData>().apply {
            add(
                LocationData("Aceh", "ID-AC")
            )
            add(
                LocationData("Bali", "ID-BA")
            )
            add(
                LocationData("Kep Bangka Belitung", "ID-BB")
            )
            add(
                LocationData("Banten", "ID-JT")
            )
            add(
                LocationData("Bengkulu", "ID-BE")
            )
            add(
                LocationData("Jawa Tengah", "ID-JT")
            )
            add(
                LocationData("Kalimantan Tengah", "ID-KT")
            )
            add(
                LocationData("Sulawesi Tengah", "ID-ST")
            )
            add(
                LocationData("Jawa Timur", "ID-JI")
            )
            add(
                LocationData("Kalimantan Timur", "ID-TI")
            )
            add(
                LocationData("Nusa Tenggara Timur", "ID-NT")
            )
            add(
                LocationData("Gorontalo", "ID-GO")
            )
            add(
                LocationData("DKI Jakarta", "ID-JK")
            )
            add(
                LocationData("Jambi", "ID-JA")
            )
            add(
                LocationData("Lampung", "ID-LA")
            )
            add(
                LocationData("Maluku", "ID-MA")
            )
            add(
                LocationData("Kalimantan Utara", "ID-KU")
            )
            add(
                LocationData("Maluku Utara", "ID-MU")
            )
            add(
                LocationData("Sulawesi Utara", "ID-SA")
            )
            add(
                LocationData("Sumatera Utara", "ID-SU")
            )
            add(
                LocationData("Papua", "ID-PA")
            )
            add(
                LocationData("Riau", "ID-RI")
            )
            add(
                LocationData("Kepulauan Riau", "ID-KR")
            )
            add(
                LocationData("Sulawesi Tenggara", "ID-SG")
            )
            add(
                LocationData("Kalimantan Selatan", "ID-KS")
            )
            add(
                LocationData("Sulawesi Selatan", "ID-SN")
            )
            add(
                LocationData("Sumatera Selatan", "ID-SS")
            )
            add(
                LocationData("DI Yogyakarta", "ID-YO")
            )
            add(
                LocationData("Jawa Barat", "ID-JB")
            )
            add(
                LocationData("Kalimantan Barat", "ID-KB")
            )
            add(
                LocationData("Nusa Tenggara Barat", "ID-NB")
            )
            add(
                LocationData("Papua Barat", "ID-PB")
            )
            add(
                LocationData("Sulawesi Barat", "ID-SR")
            )
            add(
                LocationData("Sumatera Barat", "ID-SB")
            )
        }
        return locations
    }

    fun getDisasterTypes(): List<DisasterType> {
        val disasterTypes = ArrayList<DisasterType>().apply {
            add(
                DisasterType("Banjir", "flood")
            )
            add(
                DisasterType("Gempa Bumi", "earthquake")
            )
            add(
                DisasterType("Kebakaran", "fire")
            )
            add(
                DisasterType("Kabut", "haze")
            )
            add(
                DisasterType("Angin Kencang", "wind")
            )
            add(
                DisasterType("Gunung Api", "volcano")
            )
        }
        return disasterTypes
    }
}