package de.dojo.weather.util

import de.dojo.weather.data.repository.Forecast
import de.dojo.weather.data.repository.KMLConsts
import de.dojo.weather.data.repository.Weather
import de.dojo.weather.data.repository.WeatherCondition
import de.dojo.weather.data.repository.WeatherData
import de.dojo.weather.data.repository.WeatherStation
import org.w3c.dom.Document
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun parseKML(document: Document): Forecast {
    val listOfDates = document.getDates()
    val stationId = document.getStationId()
    val stationName = document.getStationName()
    val forecastValues = document.getForecastValues()
    val weatherData = getWeatherData(listOfDates, forecastValues)

    return Forecast(
        station = WeatherStation(stationId, stationName),
        weatherData = weatherData
    )
}


fun Document.getDates(): List<Date> {
    val listOfDates = mutableListOf<Date>()
    val dates = getElementsByTagName(KMLConsts.TimestampKey)
    for (x in 0 until dates.length) {
        val content = dates.item(x).textContent
        if (content != null) {
            SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
                .parse(content)
                ?.let { listOfDates.add(it) }
        }
    }
    return listOfDates
}

fun Document.getStationId(): String =
    getElementsByTagName(KMLConsts.StationIdKey).item(0).textContent

fun Document.getStationName(): String =
    getElementsByTagName(KMLConsts.StationNameKey).item(0).textContent

fun Document.getForecastValues(): Map<String, List<String>> {
    val forecastElements = getElementsByTagName(KMLConsts.Forecast.Key)

    val forecastValues: MutableMap<String, List<String>> = mutableMapOf()

    for (x in 0 until forecastElements.length) {
        val key = forecastElements.item(x).attributes.item(0).textContent
            .removePrefix(KMLConsts.Forecast.AttributePrefix)
            .removeSuffix(KMLConsts.Forecast.AttributeSuffix)

        val values = forecastElements.item(x).textContent
            .split("  ")
            .map { it.trim() }
            .filterNot { it.isEmpty() }
        forecastValues[key] = values
    }

    return forecastValues
}

fun getWeatherData(
    listOfDates: List<Date>,
    forecastValues: Map<String, List<String>>
): List<Weather> {
    val weatherData = mutableListOf<Weather>()

    for (i in listOfDates.indices) {
        weatherData.add(
            Weather(
                date = listOfDates[i],
                condition = WeatherCondition[forecastValues.getInt(WeatherData.Condition, i)]
                    ?: "Unbekannt",
                temperature = forecastValues.getFloat(WeatherData.Temperature, i),
                dewPoint = forecastValues.getFloat(WeatherData.DewPoint, i),
                pressure = forecastValues.getFloat(WeatherData.Pressure, i),
                windSpeed = forecastValues.getFloat(WeatherData.Wind.Speed, i),
                windDirection = forecastValues.getString(WeatherData.Wind.Direction, i),
                windGusts = forecastValues.getFloat(WeatherData.Wind.Gusts, i),
                precipitation = forecastValues.getFloat(WeatherData.Precipitation.Value, i),
                precipitationProbability = forecastValues
                    .getFloat(WeatherData.Precipitation.Probability, i),
                precipitationDuration = forecastValues
                    .getFloat(WeatherData.Precipitation.Duration, i),
                cloudCoverage = forecastValues.getFloat(WeatherData.CloudCoverage, i),
                visibility = forecastValues.getFloat(WeatherData.Visibility, i),
                sunDuration = forecastValues.getFloat(WeatherData.Sun.Duration, i),
                sunIrradiance = forecastValues.getFloat(WeatherData.Sun.Irradiance, i),
                fogProbability = forecastValues.getFloat(WeatherData.FogProbability, i),
                humidity = forecastValues.getFloat(WeatherData.Humidity, i),
            )
        )
    }
    return weatherData
}


fun Map<String, List<String>>.getFloat(key: String, position: Int): Float {
    val value = this[key]?.get(position)
    var out = -1f
    value?.run(
        ifNumber = { out = it.toFloat() }
    )
    return out
}

fun Map<String, List<String>>.getString(key: String, position: Int): String {
    return this[key]?.get(position) ?: ""
}

fun Map<String, List<String>>.getInt(key: String, position: Int): Int {
    val value = this[key]?.get(position)
    var out = -1
    value?.run(
        ifNumber = { out = it.toFloat().toInt() }
    )
    return out
}

fun String.run(ifNumber: (String) -> Unit, ifElse: ((String) -> Unit)? = null) {
    if (this.matches("-?\\d+(\\.\\d+)?".toRegex())) {
        ifNumber(this)
    } else {
        ifElse?.invoke(this)
    }
}
