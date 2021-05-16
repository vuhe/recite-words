package top.vuhe.android

import org.json.JSONArray
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.io.InputStream
import java.io.Reader

data class Student(
    val id: String,
    val name: String,
    val age: String,
    val sex: String,
    val password: String
)

private class StudentBuilder {
    lateinit var id: String
    lateinit var name: String
    lateinit var age: String
    lateinit var sex: String
    lateinit var password: String

    fun build(): Student {
        return Student(id, name, age, sex, password)
    }
}

fun xmlCheckInfo(account: String, password: String, res: InputStream): Boolean {
    val list = res.reader(Charsets.UTF_8).use { parseXml(it) }
    val student = list[0]
    return student.id == account && student.password == password
}

fun jsonCheckInfo(account: String, password: String, res: InputStream): Boolean {
    val data = res.readBytes().toString(Charsets.UTF_8)
    val list = parseJson(data)
    val student = list[0]
    return student.id == account && student.password == password
}

private fun parseXml(xmlData: Reader): List<Student> {
    // 设置进入流
    val xmlPullParser = XmlPullParserFactory.newInstance().newPullParser()
    xmlPullParser.setInput(xmlData)
    // 设置进入类型
    var eventType = xmlPullParser.eventType
    var student = StudentBuilder()
    // 返回数组
    val list = ArrayList<Student>()
    // 遍历
    while (eventType != XmlPullParser.END_DOCUMENT) {
        val nodeName = xmlPullParser.name
        when (eventType) {
            // 开始一个节点
            XmlPullParser.START_TAG -> {
                when (nodeName) {
                    "id" -> student.id = xmlPullParser.nextText()
                    "name" -> student.name = xmlPullParser.nextText()
                    "age" -> student.age = xmlPullParser.nextText()
                    "sex" -> student.sex = xmlPullParser.nextText()
                    "password" -> student.password = xmlPullParser.nextText()
                }
            }
            // 结束一个节点
            XmlPullParser.END_TAG -> {
                if ("student" == nodeName) {
                    list.add(student.build())
                    student = StudentBuilder()
                }
            }
        }
        eventType = xmlPullParser.next()
    }
    return list
}

private fun parseJson(jsonData: String): List<Student> {
    val jsonArray = JSONArray(jsonData)
    val list = ArrayList<Student>()
    for (i in 0 until jsonArray.length()) {
        val jsonObject = jsonArray.getJSONObject(i)
        val student = Student(
            id = jsonObject.getString("id"),
            name = jsonObject.getString("name"),
            age = jsonObject.getString("age"),
            sex = jsonObject.getString("sex"),
            password = jsonObject.getString("password")
        )
        list.add(student)
    }
    return list
}