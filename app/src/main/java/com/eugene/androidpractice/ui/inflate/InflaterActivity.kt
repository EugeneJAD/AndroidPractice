package com.eugene.androidpractice.ui.inflate

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import com.eugene.androidpractice.R
import kotlinx.android.synthetic.main.activity_inflater.*
import java.util.ArrayList

class InflaterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inflater)
        add_view_button.setOnClickListener { addViewToLayout() }
        print_results_button.setOnClickListener { printResults() }
    }

    private fun printResults() {
        val resultsArray = ArrayList<String>()
        var deletedViews = 0
        for (i in 0..list_container.childCount) {
            val view = list_container.getChildAt(i - deletedViews)
            if (view is EditText) {
                val text = view.text.toString().trim()
                if (text.isEmpty()) {
                    //remove view
                    list_container.removeViewAt(i - deletedViews++)
                    //remove divider
                    list_container.removeViewAt(i + 1 - deletedViews++)
                } else
                    resultsArray.add(text)
            }
        }
        results.text = resultsArray.toString()
    }

    private fun addViewToLayout() {
        layoutInflater.inflate(R.layout.edit_text_layout, list_container)
        layoutInflater.inflate(R.layout.divider, list_container)
    }
}
