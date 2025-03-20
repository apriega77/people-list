package com.apriega77.data.mapper

import com.apriega77.data.network.model.PeopleDetailResponse
import com.apriega77.model.KeyValue
import com.apriega77.model.People
import com.apriega77.model.PeopleDetail
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone
import javax.inject.Inject

class PeopleMapper @Inject constructor() {


    fun mapPeopleList(peopleResponse: List<PeopleDetailResponse>): List<People> {
        return peopleResponse.map { response ->
            People(
                id = response.id.orEmpty(),
                name = response.name.orEmpty(),
                date = formatDate(response.createdAt),
                image = response.avatar.orEmpty(),
                peopleDetail = mapPeopleDetail(response)
            )
        }
    }

    private fun mapPeopleDetail(response: PeopleDetailResponse): PeopleDetail {
        return PeopleDetail(
            image = response.avatar.orEmpty(),
            keyValue = listOf(
                KeyValue(key = "First Name", value = response.name?.split(" ")?.first().orEmpty()),
                KeyValue(key = "Last Name", value = response.name?.split(" ")?.last().orEmpty()),
                KeyValue(
                    key = "Address",
                    value = "${response.addressNo} ${response.street} ${response.county} ${response.zipCode} ${response.country}"
                )
            )
        )

    }

    private fun formatDate(timestamp: String?): String {
        return try {
            if (timestamp.isNullOrBlank()) return ""

            val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US)
            inputFormat.timeZone = TimeZone.getTimeZone("UTC")

            val outputFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
            outputFormat.timeZone = TimeZone.getDefault()

            val date = inputFormat.parse(timestamp) ?: return ""
            outputFormat.format(date)
        } catch (e: Exception) {
            ""
        }
    }

}