package de.dojo.weather.data.repository

import de.dojo.weather.data.network.DWDBackend
import de.dojo.weather.util.parseKML
import de.dojo.weather.util.unzipStream
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DWDRepository(
    private val dwdBackend: DWDBackend,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend fun getForecast(stationId: String): Forecast = withContext(dispatcher) {
        val inputStream = dwdBackend.getWeatherData(stationId = stationId)
        val document = unzipStream(inputStream)
        parseKML(document = document)
    }
}
