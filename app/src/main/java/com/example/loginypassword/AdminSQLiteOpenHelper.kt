package com.example.loginypassword

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class AdminSQLiteOpenHelper(
        context: Context?,
        name: String?,
        factory: SQLiteDatabase.CursorFactory?,
        version: Int
    ): SQLiteOpenHelper(context, name, factory, version){
        override fun onCreate(db: SQLiteDatabase?) {
            db?.execSQL("create table usuario (user integer primary key, cont text)")
        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            db?.execSQL("drop table if exists usuario")
            db?.execSQL("create table usuario (user integer primary key, cont text)")
        }
    }
