package com.example.favourojiaku.ui.cars

import android.content.res.AssetManager
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.favourojiaku.R
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader


class CarFragment : Fragment() {

    var db: SQLiteDatabase? = null
    val CONTACTS_TABLE_NAME = "cars"
    private lateinit var homeViewModel: CarViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProvider(this).get(CarViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_car, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    fun readCsvFile() {
        db?.execSQL(

                "create table 'CONTACTS_TABLE_NAME' " +
                        "(id integer primary key, first_name, last_name, email, country, car_model, car_model_year, car_color, gender, job_title, bio)"
        )
        val csvFile = "owners/car_ownsers_data.csv"
        Log.e("favour ", "" + csvFile)
        val assetManager: AssetManager? = context?.assets
        var inputStream: InputStream? = null
        try {
            if (assetManager != null) {
                inputStream = assetManager.open(csvFile)
            }
        } catch (e: IOException){
            e.printStackTrace()
        }

        val br = BufferedReader(InputStreamReader(inputStream))
        Log.e("favour said okay ", "" + br)
        var line = ""
        val tableName = "cars"

        val columns = "id, first_name, last_name, email, country, car_model, car_model_year, car_color, gender, job_title, bio"
        val query = "INSERT INTO $tableName ($columns) values("
        val query2 = ");"
        Log.e("favour said okay ", "" + query)
        Log.e("favour said okay ", "" + query2)

        db!!.beginTransaction()
        try {
            while (br.readLine().also { line = it } != null) {
                val sb = StringBuilder(query)
                val str = line.split(",").toTypedArray()
                sb.append("'" + str[0] + "',")
                sb.append(str[1] + "',")
                sb.append(str[2] + "',")
                sb.append(str[3] + "'")
                sb.append(str[4] + "'")
                sb.append(query2)
                db!!.execSQL(sb.toString())
            }
            db!!.setTransactionSuccessful()
            db!!.endTransaction()
        } catch (e: IOException) {
        }
    }
}