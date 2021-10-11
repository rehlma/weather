package de.dojo.weather.data.repository

object KMLConsts {
    const val TimestampKey = "dwd:TimeStep"
    const val StationIdKey = "kml:name"
    const val StationNameKey = "kml:description"
    object Forecast {
        const val Key = "dwd:Forecast"
        const val AttributePrefix = "dwd:elementName=\""
        const val AttributeSuffix = "\""
    }

}
