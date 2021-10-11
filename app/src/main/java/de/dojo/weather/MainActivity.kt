package de.dojo.weather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import de.dojo.weather.data.network.DWDBackend
import de.dojo.weather.data.repository.DWDRepository
import de.dojo.weather.ui.theme.WeatherTheme
import io.ktor.client.*
import kotlinx.coroutines.runBlocking
import java.text.SimpleDateFormat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val backend = DWDBackend(HttpClient())
        val repository = DWDRepository(backend)
        val stationId = "P0489" //Lübeck "10156"
        val forecast = runBlocking { repository.getForecast(stationId) }

        setContent {
            WeatherTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Column {
                        Text(forecast.station.stationName)
                        LazyColumn {
                            items(count = forecast.weatherData.size) {
                                Card(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 16.dp, vertical = 8.dp)
                                ) {
                                    Column(modifier = Modifier.padding(8.dp)) {
                                        Text(
                                            SimpleDateFormat.getDateTimeInstance()
                                                .format(forecast.weatherData[it].date)
                                        )
                                        val tempK = forecast.weatherData[it].temperature
                                        Text(text = "${(tempK - 272.15).toInt()} °C")
                                        Text(forecast.weatherData[it].condition)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    WeatherTheme {
        Greeting("Android")
    }
}
