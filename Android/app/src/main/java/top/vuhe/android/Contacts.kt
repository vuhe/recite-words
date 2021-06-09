package top.vuhe.android

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

private lateinit var helper: SQLiteHelper
private const val dataName = "contact"

data class Contact(
    val name: String,
    val phone: String
)

private class SQLiteHelper(
    context: Context,
    name: String,
    version: Int
) : SQLiteOpenHelper(context, name, null, version) {

    private val createContacts = """
        create table $dataName (
            name text primary key,
            phone text)
    """.trimIndent()

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(createContacts)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVlu: Int, newVlu: Int) {
    }
}

fun initSQLiteHelper(context: Context) {
    helper = SQLiteHelper(context, dataName, 1)
}

fun searchData(data: Contact): Boolean {
    val ans = ArrayList<Contact>()
    helper.readableDatabase.query(
        dataName,
        null,
        "name = ?",
        arrayOf(data.name),
        null, null, null
    ).use {
        if (it.moveToFirst()) {
            do {
                val contact = Contact(
                    name = it.getString(it.getColumnIndex("name")),
                    phone = it.getString(it.getColumnIndex("phone"))
                )
                ans.add(contact)
            } while (it.moveToNext())
        }
    }
    return ans.size > 0
}

fun insertData(data: Contact): Boolean {
    val values = ContentValues().apply {
        put("name", data.name)
        put("phone", data.phone)
    }
    val ans = helper.writableDatabase.insert(dataName, null, values)
    return ans != -1L
}

fun updateData(data: Contact): Boolean {
    val values = ContentValues().apply {
        put("phone", data.phone)
    }
    helper.writableDatabase
        .update(dataName, values, "name = ?", arrayOf(data.name))
    return true
}

fun deleteData(data: Contact): Boolean {
    helper.writableDatabase
        .delete(dataName, "name = ?", arrayOf(data.name))
    return true;
}