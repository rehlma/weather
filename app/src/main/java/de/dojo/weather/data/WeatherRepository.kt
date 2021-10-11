package de.dojo.weather.data

import java.util.Calendar
import java.util.Date

object WeatherRepository {
    val currentWeather = Weather(
        date = getDate(0),
        place = "gxc headquarter",
        weatherType = WeatherType.PARTLY_SUNNY,
        temperature = 24,
        windSpeed = 2.2f,
        feelsLikeTemperature = 25,
        indexUV = 2,
        pressure = 1014
    )
    val forecast = listOf(
        WeatherForecast(
            date = getDate(0),
            place = "gxc headquarter",
            weatherType = WeatherType.SUNNY,
            highestTemperature = 32,
            lowestTemperature = 31
        ),
        WeatherForecast(
            date = getDate(1),
            place = "gxc headquarter",
            weatherType = WeatherType.RAINY,
            highestTemperature = 22,
            lowestTemperature = 23
        ),
        WeatherForecast(
            date = getDate(2),
            place = "gxc headquarter",
            weatherType = WeatherType.SUNNY,
            highestTemperature = 30,
            lowestTemperature = 31
        ),
        WeatherForecast(
            date = getDate(3),
            place = "gxc headquarter",
            weatherType = WeatherType.CLOUDY,
            highestTemperature = 24,
            lowestTemperature = 26
        ),
        WeatherForecast(
            date = getDate(4),
            place = "gxc headquarter",
            weatherType = WeatherType.PARTLY_SUNNY,
            highestTemperature = 26,
            lowestTemperature = 27
        ),
        WeatherForecast(
            date = getDate(5),
            place = "gxc headquarter",
            weatherType = WeatherType.PARTLY_SUNNY,
            highestTemperature = 27,
            lowestTemperature = 28
        ),
        WeatherForecast(
            date = getDate(6),
            place = "gxc headquarter",
            weatherType = WeatherType.RAINY,
            highestTemperature = 22,
            lowestTemperature = 23
        ),
    )
}

data class Weather(
    val date: Date,
    val place: String,
    val weatherType: WeatherType,
    val temperature: Int,
    val windSpeed: Float,
    val feelsLikeTemperature: Int,
    val indexUV: Int,
    val pressure: Int
)

data class WeatherForecast(
    val date: Date,
    val place: String,
    val weatherType: WeatherType,
    val highestTemperature: Int,
    val lowestTemperature: Int
)

enum class WeatherType {
    SUNNY, PARTLY_SUNNY, CLOUDY, RAINY
}

fun getDate(days: Int): Date {
    return Calendar.getInstance().apply {
        add(Calendar.DATE, days)
    }.time
}
