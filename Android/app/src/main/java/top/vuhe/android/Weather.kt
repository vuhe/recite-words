package top.vuhe.android

import org.json.JSONArray
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.io.InputStream
import java.io.Reader

data class Weather(
    val temp: String,
    val weather: String,
    val name: String,
    val pm: String,
    val wind: String
)

private class WeatherBuilder {
    lateinit var temp: String
    lateinit var weather: String
    lateinit var name: String
    lateinit var pm: String
    lateinit var wind: String

    fun build(): Weather {
        return Weather(temp, weather, name, pm, wind)
    }
}

fun getDataFromXml(res: InputStream): Map<String, Weather> {
    return res.reader(Charsets.UTF_8).use { parseXml(it) }
}

fun getDataFromJson(res: InputStream): Map<String, Weather> {
    return parseJson(res.readBytes().toString(Charsets.UTF_8))
}

private fun parseXml(xmlData: Reader): Map<String, Weather> {
    // 设置进入流
    val xmlPullParser = XmlPullParserFactory.newInstance().newPullParser()
    xmlPullParser.setInput(xmlData)
    // 设置进入类型
    var eventType = xmlPullParser.eventType
    var weather = WeatherBuilder()
    // 返回数组
    val map = HashMap<String, Weather>()
    // 遍历
    while (eventType != XmlPullParser.END_DOCUMENT) {
        val nodeName = xmlPullParser.name
        when (eventType) {
            // 开始一个节点
            XmlPullParser.START_TAG -> {
                when (nodeName) {
                    "temp" -> weather.temp = xmlPullParser.nextText()
                    "weather" -> weather.weather = xmlPullParser.nextText()
                    "name" -> weather.name = xmlPullParser.nextText()
                    "pm" -> weather.pm = xmlPullParser.nextText()
                    "wind" -> weather.wind = xmlPullParser.nextText()
                }
            }
            // 结束一个节点
            XmlPullParser.END_TAG -> {
                if ("city" == nodeName) {
                    map[weather.name] = weather.build()
                    weather = WeatherBuilder()
                }
            }
        }
        eventType = xmlPullParser.next()
    }
    return map
}

private fun parseJson(jsonData: String): Map<String, Weather> {
    val jsonArray = JSONArray(jsonData)
    val map = HashMap<String, Weather>()
    for (i in 0 until jsonArray.length()) {
        val jsonObject = jsonArray.getJSONObject(i)
        val weather = Weather(
            temp = jsonObject.getString("temp"),
            weather = jsonObject.getString("weather"),
            name = jsonObject.getString("name"),
            pm = jsonObject.getString("pm"),
            wind = jsonObject.getString("wind")
        )
        map[weather.name] = weather
    }
    return map
}