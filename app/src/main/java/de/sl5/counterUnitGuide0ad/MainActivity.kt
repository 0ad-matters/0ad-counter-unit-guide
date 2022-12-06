package de.sl5.counterUnitGuide0ad
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        findViewById<ImageButton>(R.id.r1col1).setOnClickListener {
//            toastContentDescription(it)
        for (i in 1..7) {
            val idR1: Int = resources.getIdentifier("r1col$i", "id", this.packageName)
            val idR2: Int = resources.getIdentifier("r2col$i", "id", this.packageName)
            findViewById<ImageButton>(idR1).setOnClickListener {
                toastContentDescription(it)
            }
            findViewById<ImageButton>(idR2).setOnClickListener {
                toastContentDescription(it)
            }
        }
    }
    private fun toastContentDescription(it: View) {
        val contentDescription = it.contentDescription
        val myToast = Toast.makeText(applicationContext, contentDescription, Toast.LENGTH_SHORT)
        myToast.show()
    }

}