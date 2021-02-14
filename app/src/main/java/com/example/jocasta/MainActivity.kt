package com.example.jocasta

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuItemCompat
import com.example.jocasta.ui.main.MainFragment


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)

        // Get the search menu.
        // Get the search menu.
        val searchMenu: MenuItem = menu.findItem(R.id.search)

        // Get SearchView object.

        // Get SearchView object.
        val searchView: SearchView = searchMenu.actionView as SearchView

        // Get SearchView autocomplete object.

        // Get SearchView autocomplete object.
        val searchAutoComplete: SearchView.SearchAutoComplete =
            searchView.findViewById(androidx.appcompat.R.id.search_src_text) as SearchView.SearchAutoComplete
        searchAutoComplete.setBackgroundColor(Color.BLUE)
        searchAutoComplete.setTextColor(Color.GREEN)
        searchAutoComplete.setDropDownBackgroundResource(R.color.amber_A400)

        // Create a new ArrayAdapter and add data to search auto complete object.

        // Create a new ArrayAdapter and add data to search auto complete object.
        val dataArr = arrayOf(
            "Apple",
            "Amazon",
            "Amd",
            "Microsoft",
            "Microwave",
            "MicroNews",
            "Intel",
            "Intelligence"
        )
        val newsAdapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, dataArr)
        searchAutoComplete.setAdapter(newsAdapter)

        // Listen to search view item on click event.

        // Listen to search view item on click event.
        searchAutoComplete.setOnItemClickListener(OnItemClickListener { adapterView, view, itemIndex, id ->
            val queryString = adapterView.getItemAtPosition(itemIndex) as String
            searchAutoComplete.setText("" + queryString)
            Toast.makeText(
                this@MainActivity,
                "you clicked $queryString",
                Toast.LENGTH_LONG
            ).show()
        })

        // Below event is triggered when submit search query.

        // Below event is triggered when submit search query.
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                val alertDialog: AlertDialog = AlertDialog.Builder(this@MainActivity).create()
                alertDialog.setMessage("Search keyword is $query")
                alertDialog.show()
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })


        return super.onCreateOptionsMenu(menu)
//        return true
    }
}