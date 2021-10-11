package de.dojo.weather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import de.dojo.weather.data.Weather
import de.dojo.weather.data.WeatherRepository
import de.dojo.weather.data.WeatherType
import de.dojo.weather.ui.theme.Shapes
import de.dojo.weather.ui.theme.Teal200
import de.dojo.weather.ui.theme.Typography
import de.dojo.weather.ui.theme.WeatherTheme
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Column(Modifier.padding(16.dp)) {
                        Location(WeatherRepository.currentWeather.place)
                        CurrentForecast(WeatherRepository.currentWeather)
                    }
                }
            }
        }
    }
}

@Composable
fun Location(city: String, country: String? = null) {
    Row(
        Modifier.padding(bottom = 16.dp)
    ) {
        Text(text = city)
        country?.let { Text(text = it) }
    }
}

@Composable
fun CurrentForecast(weather: Weather) {
    val dateParse = SimpleDateFormat("EEEE, dd mm", Locale.getDefault())

    Box(
        Modifier
            .fillMaxWidth()
            .clip(Shapes.medium)
            .background(Teal200)
            .padding(16.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = getIconForWeatherType(weather.weatherType)),
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .padding(top = 24.dp)
            )
            Text(
                text = getStringForWeatherType(weather.weatherType),
                modifier = Modifier.padding(top = 12.dp),
                style = Typography.body1
            )
            Text(
                text = dateParse.format(weather.date),
                modifier = Modifier.padding(top = 8.dp),
                style = Typography.caption
            )
            Text(
                text = String.format("%d °", weather.temperature),
                modifier = Modifier.padding(top = 24.dp, bottom = 24.dp),
                fontSize = 60.sp
            )

            Divider(color = Color.White)
            Row(modifier = Modifier.fillMaxWidth()) {
                CurrentForecastCell(
                    R.drawable.ic_wind,
                    "WIND",
                    String.format("%fkm/h", weather.windSpeed)
                )
//                Divider(color = Color.White)
                CurrentForecastCell(
                    R.drawable.ic_thermostat,
                    "FEELS LIKE",
                    String.format("%d°", weather.feelsLikeTemperature)
                )
            }
            Divider(color = Color.White)
            Row(modifier = Modifier.fillMaxWidth()) {
                CurrentForecastCell(
                    R.drawable.ic_wind,
                    "WIND",
                    String.format("%fkm/h", weather.windSpeed)
                )
//                Divider(color = Color.White)
                CurrentForecastCell(
                    R.drawable.ic_thermostat,
                    "FEELS LIKE",
                    String.format("%d°", weather.feelsLikeTemperature)
                )
            }
        }
    }
}

@Composable
fun CurrentForecastCell(@DrawableRes icon: Int, title: String, value: String) {
    Row(
        modifier = Modifier
            .padding(4.dp)
            .size(100.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = null,
            modifier = Modifier
                .size(40.dp)
                .padding(top = 24.dp)
                .weight(.4f)
        )
        Column(
            modifier = Modifier
                .padding(top = 24.dp)
                .weight(.6f)
        ) {
            Text(text = title)
            Text(text = value)
        }
    }
}

private fun getIconForWeatherType(type: WeatherType) = when (type) {
    WeatherType.SUNNY -> R.drawable.ic_sunny
    WeatherType.PARTLY_SUNNY -> R.drawable.ic_partly_cloudy
    WeatherType.CLOUDY -> R.drawable.ic_cloudy
    WeatherType.RAINY -> R.drawable.ic_rainy
}

private fun getStringForWeatherType(type: WeatherType) = when (type) {
    WeatherType.SUNNY -> "Sunny"
    WeatherType.PARTLY_SUNNY -> "Partly sunny"
    WeatherType.CLOUDY -> "Cloudy"
    WeatherType.RAINY -> "Rainy"
}