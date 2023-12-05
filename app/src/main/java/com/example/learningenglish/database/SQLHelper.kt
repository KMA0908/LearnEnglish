package com.example.learningenglish.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class SQLHelper(context: Context?) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
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
}
