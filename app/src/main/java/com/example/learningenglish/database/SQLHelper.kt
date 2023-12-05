package com.example.learningenglish.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.learningenglish.model.Folder


class SQLHelper(context: Context?) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        // Tạo bảng folder
        db.execSQL(
            "CREATE TABLE folder (" +
                    "folder_id INTEGER PRIMARY KEY," +
                    "folder_name TEXT NOT NULL)"
        )

        // Tạo bảng topic
        db.execSQL(
            "CREATE TABLE topic (" +
                    "topic_id INTEGER PRIMARY KEY," +
                    "topic_name TEXT NOT NULL," +
                    "folder_id INTEGER," +
                    "FOREIGN KEY (folder_id) REFERENCES folder(folder_id))"
        )

        // Tạo bảng word
        db.execSQL(
            "CREATE TABLE word (" +
                    "word_id INTEGER PRIMARY KEY," +
                    "word_name TEXT NOT NULL," +
                    "topic_id INTEGER," +
                    "FOREIGN KEY (topic_id) REFERENCES topic(topic_id))")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Xử lý nâng cấp cơ sở dữ liệu khi có sự thay đổi version
        // Ví dụ: db.execSQL("DROP TABLE IF EXISTS table_name");
        // Sau đó gọi onCreate để tạo lại cấu trúc cơ sở dữ liệu mới
        // this.onCreate(db);
    }

    companion object {
        private val DATABASE_NAME = "learningEnglish"
        private val DATABASE_VERSION = 1
    }

    override fun onOpen(db: SQLiteDatabase?) {
        super.onOpen(db)
    }

    fun getAllFolder(): List<Folder> {
        val list = arrayListOf<Folder>()
        val st = readableDatabase
        val order = "folder_id DESC"
        val rs = st.query(
            "folder", null, null,
            null, null, null, order
        )
        while (rs != null && rs.moveToNext()) {
            val id = rs.getInt(0)
            val name = rs.getString(1)
            list.add(Folder(id, name))
        }
        return list
    }

    fun addFolder(folder: Folder): Long {
        val values = ContentValues()
        values.put("folder_name", folder.name)
        val sqLiteDatabase = writableDatabase
        return sqLiteDatabase.insert("folder", null, values)
    }

    fun searchByNameFolder(key: String): List<Folder> {
        val list = arrayListOf<Folder>()
        val whereClause = "folder_name like ?"
        val whereArgs = arrayOf("%$key%")
        val st = readableDatabase
        val rs = st.query("folder", null, whereClause, whereArgs, null, null, null)
        while (rs != null && rs.moveToNext()) {
            val id = rs.getInt(0)
            val name = rs.getString(1)
            list.add(Folder(id, name))
        }
        return list
    }
}
