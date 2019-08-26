package com.arenko.tvshowguide

import com.arenko.tvshowguide.utils.Constants
import org.junit.Test
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import java.nio.charset.Charset.defaultCharset


class ApiTest {

    @Test
    @Throws(Exception::class)
    fun testURLwithApiKey() {
        val connection =
            URL(Constants.BASE_URL + "tv/popular?api_key=" + Constants.API_KEY).openConnection()
        val response = connection.getInputStream()

        val buffer = StringBuffer()
        BufferedReader(InputStreamReader(response, defaultCharset())).use { reader ->
            while (reader.readLine() != null) {
                buffer.append(reader.readLine())
            }
        }

        assert(buffer.length > 0)
    }
}