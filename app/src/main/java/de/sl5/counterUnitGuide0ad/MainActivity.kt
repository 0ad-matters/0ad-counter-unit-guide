package de.sl5.counterUnitGuide0ad
import android.os.Bundle
import android.view.View
import android.view.animation.AlphaAnimation
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


/*
some resources online
barracks
https://github.com/0ad/0ad/blob/aa07435f392c1d16fc67635e3f6de4e41194acf0/binaries/data/mods/public/art/textures/ui/session/portraits/structures/barracks.png

emblems macedonians
https://github.com/0ad/0ad/blob/master/binaries/data/mods/public/art/textures/ui/session/portraits/emblems/emblem_macedonians.png

brit_infantry_spearman
https://github.com/0ad/0ad/blob/aa07435f392c1d16fc67635e3f6de4e41194acf0/binaries/data/mods/public/art/textures/ui/session/portraits/units/brit_infantry_spearman.png
 */


class MainActivity : AppCompatActivity() {

    private var itClickedPadding: Int = 0
    private var itClickedPaddingObj: Int = 0

    private var itClicked: View? = null
    private var itClickedObj: View? = null

    fun clickAnimation(): AlphaAnimation? {
        return AlphaAnimation(1f, 0.4f) // Change "0.4F" as per your recruitment.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        findViewById<ImageButton>(R.id.r1col1).setOnClickListener {
//            it.drawableState()
//        }

        for (i in 1..7) {
            val idR1: Int = resources.getIdentifier("r1col$i", "id", this.packageName)
            val idR2: Int = resources.getIdentifier("r2col$i", "id", this.packageName)
            val idR3: Int = resources.getIdentifier("r3col$i", "id", this.packageName)
            val idR4: Int = resources.getIdentifier("r4col$i", "id", this.packageName)
            findViewById<ImageButton>(idR1).setOnClickListener {
                showTextAboutOppCiv(it)
//                it.foreground("?android:attr/selectableItemBackground")
            }
            findViewById<ImageButton>(idR2).setOnClickListener {
                showTextAboutOppCiv(it)
            }
            findViewById<ImageButton>(idR3).setOnClickListener {
                showTextAboutOppObject(it)
            }
            findViewById<ImageButton>(idR4).setOnClickListener {
                showTextAboutOppObject(it)
            }
            // android:background="?android:selectableItemBackgroundBorderless"
        }
    }
    private fun showTextAboutOppCiv(it: View) {

        var msgOppCiv = "Your opponent use ${it.contentDescription} Civilization. "

        Toast.makeText(applicationContext, msgOppCiv, Toast.LENGTH_SHORT).show()

        if (itClicked !== null) {
//            itClicked!!.setPadding(20, 20, 20, 20)
            itClicked!!.setPadding(itClickedPadding, itClickedPadding, itClickedPadding, itClickedPadding)
        }

        itClicked = it
        itClickedPadding = it.paddingLeft

        it.setPadding(0,0,0,0)
        it.startAnimation(clickAnimation())

        val editMultiLineID: Int = resources.getIdentifier("editTextTextMultiLine", "id", this.packageName)
        when (it.contentDescription) {
            "Athenians" -> {
                msgOppCiv += """${it.contentDescription} has a 
						- Javelineers-Cavalry from phase 1(range of 30meters, speed of 16).
						- Slinger-Infrantry (range of 45meters) from phase 1.
						- from phase 2 stable: fast Swordsman-Cavalry (speed 18).
                - ...
            """
            }
            "Ptolemies" -> {
                msgOppCiv += """${it.contentDescription} has a 
                - slow Archer-Cavalry(Camels) from phase 1(range of 60meters, Camels only have a speed of 15).
                - Slinger-Infrantry (range of 45meters, not 60meters) from phase 1.
                - ...
            """
            }
            "Britons" -> {
                msgOppCiv += """${it.contentDescription} has a 
						- Javelineers-Cavalry from phase 1(range of 30meters, speed of 16).
						- Slinger-Infrantry (range of 45meters) from phase 1.
						- from phase 2 stable: fast Swordsman-Cavalry (speed 18).
                - ...
            """
            }
            "Carthaginians", "Mauryas","Kushites", "Persians" -> {
                msgOppCiv += """${it.contentDescription} has a 
- Javelineers-Cavalry from phase 1(range of 30meters).
- Archer-Infrantry (range of 60meters) from phase 1.
            """
                if(it.contentDescription == "Carthaginians"){
                    msgOppCiv += """- update Colonization for 25% less resource const and fast build time. (costs 250metal, 250wood))"""
                }
                if(it.contentDescription == "Kushites"){
                    msgOppCiv += """- update Mon-Architecture for 20% health/captureTime(costs 300stone)"""
                }
                if(it.contentDescription == "Mauryas"){
                    msgOppCiv += """- Worker Elefant cost 100food. (only available in Civilization /captureTime(costs 300stone)"""
                }
                if(it.contentDescription == "Persians"){
                    msgOppCiv += """- in Phase 2 has also Axeman-, Spearman- and Archer-Cavalry."""
                }

            }
            "Spartans", "Gauls", "Iberians", "Seleucids" -> {
                msgOppCiv += """${it.contentDescription} has a 
						- Javelineers-Cavalry from phase 1(range of 30meters).
						- Javelineers-Infrantry (range of 30meters) from phase 1.
            """
                if(it.contentDescription == "Spartans"){
                    msgOppCiv += """- update Hop-Tradition for 25% training and experience(costs 300metal, 400food)."""
                }
            }
            "Macedonians" -> {
                msgOppCiv += """${it.contentDescription} has a 
						- Spearman-Cavalry from phase 1."
						- Javelineers-Infrantry (range of 30meters) from phase 1."
            """
            }
            "Romans" -> {
                msgOppCiv += """${it.contentDescription} has a 
						- ..."
            """
            }
            "Han" -> {
                msgOppCiv += """${it.contentDescription} has a 
                        - fast Swordsman-Cavalry from phase 1 (speed 18).
						- Archer-Infrantry (with only a range of 45meters, not 60meters) from phase 1.
            """
            }
        }
        findViewById<EditText>(editMultiLineID).setText(msgOppCiv.replace("""[ \t]+""".toRegex(), " ").trimIndent())




    }
    private fun showTextAboutOppObject(it: View) {

        var msgOppObjName = "Your opponent use ${it.contentDescription} Civilization. "

        Toast.makeText(applicationContext, msgOppObjName, Toast.LENGTH_SHORT).show()

        if (itClickedObj !== null) {
//            itClicked!!.setPadding(20, 20, 20, 20)
            itClickedObj!!.setPadding(itClickedPadding, itClickedPadding, itClickedPadding, itClickedPadding)
        }

        itClickedObj = it
        itClickedPaddingObj = it.paddingLeft

        it.setPadding(0,0,0,0)
        it.startAnimation(clickAnimation())

        val editMultiLineID: Int = resources.getIdentifier("editTextTextMultiLine", "id", this.packageName)
        when (it.contentDescription) {
            "Athenians" -> {
                msgOppObjName += """${it.contentDescription} has a 
						- Javelineers-Cavalry from phase 1(range of 30meters, speed of 16).
						- Slinger-Infrantry (range of 45meters) from phase 1.
						- from phase 2 stable: fast Swordsman-Cavalry (speed 18).
                - ...
            """
            }
            "Ptolemies" -> {
                msgOppObjName += """${it.contentDescription} has a 
                - slow Archer-Cavalry(Camels) from phase 1(range of 60meters, Camels only have a speed of 15).
                - Slinger-Infrantry (range of 45meters, not 60meters) from phase 1.
                - ...
            """
            }
            "Britons" -> {
                msgOppObjName += """${it.contentDescription} has a 
						- Javelineers-Cavalry from phase 1(range of 30meters, speed of 16).
						- Slinger-Infrantry (range of 45meters) from phase 1.
						- from phase 2 stable: fast Swordsman-Cavalry (speed 18).
                - ...
            """
            }
            "Carthaginians", "Mauryas","Kushites", "Persians" -> {
                msgOppObjName += """${it.contentDescription} has a 
- Javelineers-Cavalry from phase 1(range of 30meters).
- Archer-Infrantry (range of 60meters) from phase 1.
            """
                if(it.contentDescription == "Carthaginians"){
                    msgOppObjName += """- update Colonization for 25% less resource const and fast build time. (costs 250metal, 250wood))"""
                }
                if(it.contentDescription == "Kushites"){
                    msgOppObjName += """- update Mon-Architecture for 20% health/captureTime(costs 300stone)"""
                }
                if(it.contentDescription == "Mauryas"){
                    msgOppObjName += """- Worker Elefant cost 100food. (only available in Civilization /captureTime(costs 300stone)"""
                }
                if(it.contentDescription == "Persians"){
                    msgOppObjName += """- in Phase 2 has also Axeman-, Spearman- and Archer-Cavalry."""
                }

            }
            "Spartans", "Gauls", "Iberians", "Seleucids" -> {
                msgOppObjName += """${it.contentDescription} has a 
						- Javelineers-Cavalry from phase 1(range of 30meters).
						- Javelineers-Infrantry (range of 30meters) from phase 1.
            """
                if(it.contentDescription == "Spartans"){
                    msgOppObjName += """- update Hop-Tradition for 25% training and experience(costs 300metal, 400food)."""
                }
            }
            "Macedonians" -> {
                msgOppObjName += """${it.contentDescription} has a 
						- Spearman-Cavalry from phase 1."
						- Javelineers-Infrantry (range of 30meters) from phase 1."
            """
            }
            "Romans" -> {
                msgOppObjName += """${it.contentDescription} has a 
						- ..."
            """
            }
            "Han" -> {
                msgOppObjName += """${it.contentDescription} has a 
                        - fast Swordsman-Cavalry from phase 1 (speed 18).
						- Archer-Infrantry (with only a range of 45meters, not 60meters) from phase 1.
            """
            }
        }
        msgOppObjName = " test 22-1216_1357-59 " + it.contentDescription
        findViewById<EditText>(editMultiLineID).setText(msgOppObjName.replace("""[ \t]+""".toRegex(), " ").trimIndent())




    }
}

