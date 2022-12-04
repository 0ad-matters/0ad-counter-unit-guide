package de.sl5.counterUnitGuide0ad

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<ImageButton>(R.id.r1col1).setOnClickListener {
            toastContentDescription(it)
        }
        findViewById<ImageButton>(R.id.r1col2).setOnClickListener {
            toastContentDescription(it)
        }
        findViewById<ImageButton>(R.id.r1col3).setOnClickListener {
            toastContentDescription(it)
        }


    }

    private fun toastContentDescription(it: View) {
        val contentDescription = it.contentDescription
        val myToast = Toast.makeText(applicationContext, contentDescription, Toast.LENGTH_SHORT)
        myToast.show()
    }

}