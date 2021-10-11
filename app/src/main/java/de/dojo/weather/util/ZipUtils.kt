package de.dojo.weather.util

import org.w3c.dom.Document
import java.io.InputStream
import java.util.zip.ZipInputStream
import javax.xml.parsers.DocumentBuilderFactory

fun unzipStream(inputStream: InputStream): Document {
    ZipInputStream(inputStream).use { zipInputStream ->
        zipInputStream.nextEntry
        val xmlDocument = DocumentBuilderFactory
            .newInstance()
            .newDocumentBuilder()
            .parse(zipInputStream)
        xmlDocument.documentElement.normalize()

        return xmlDocument
    }
}
