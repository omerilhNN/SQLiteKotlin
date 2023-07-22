package com.omrilhn.sqlitekotlin

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Saving data even though user closes app
        try {
            val myDatabase = this.openOrCreateDatabase("Events", Context.MODE_PRIVATE,null)
            //Execute SQL
            myDatabase.execSQL("CREATE TABLE IF NOT EXISTS events(id INTEGER PRIMARY KEY,name VARCHAR, place VARCHAR)")

            //Set data into the table
            myDatabase.execSQL("INSERT INTO events (name,age) VALUES ('DUMAN','IF Armada') ")
            myDatabase.execSQL("INSERT INTO events (name,age) VALUES ('Halloween','Central') ")
            myDatabase.execSQL("INSERT INTO events (name,age) VALUES ('Christmas','ODTU Visnelik') ")

            //Select all "events" from the Query
            val cursor = myDatabase.rawQuery("SELECT * FROM events",null)

            val nameIndex = cursor.getColumnIndex("name")
            val placeIndex = cursor.getColumnIndex("place")
            val idIndex = cursor.getColumnIndex("id")

            while(cursor.moveToNext()){
                println("Name:" +cursor.getString(nameIndex)) //If variable is Int then use getInt
                println("Place: " +cursor.getString(placeIndex))
                println("Id:" +cursor.getInt(idIndex))
            }
            cursor.close()
        }
        catch(e: Exception)
        {
            e.printStackTrace()
        }
    }
}