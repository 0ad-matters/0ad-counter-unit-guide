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
            val idR2Civ: Int = resources.getIdentifier("r2col$i", "id", this.packageName)
            val idR3Civ: Int = resources.getIdentifier("r3col$i", "id", this.packageName)
            val idR4Obj: Int = resources.getIdentifier("r4col$i", "id", this.packageName)
            findViewById<ImageButton>(idR1).setOnClickListener {
                showTextAboutOppCiv(it)
//                it.foreground("?android:attr/selectableItemBackground")
            }
            findViewById<ImageButton>(idR2Civ).setOnClickListener {
                showTextAboutOppCiv(it)
            }
            findViewById<ImageButton>(idR3Civ).setOnClickListener {
                clickedObjectAction(it)
            }
            findViewById<ImageButton>(idR4Obj).setOnClickListener {
                clickedObjectAction(it)
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
    private fun clickedObjectAction(it: View) {

        var msgOppObjName = "Your opponent use "

        Toast.makeText(applicationContext, msgOppObjName, Toast.LENGTH_SHORT).show()

        if (itClickedObj !== null) {
//            itClicked!!.setPadding(20, 20, 20, 20)
            itClickedObj!!.setPadding(itClickedPaddingObj, itClickedPaddingObj, itClickedPaddingObj, itClickedPaddingObj)
        }

        itClickedObj = it
        itClickedPaddingObj = it.paddingLeft

        it.setPadding(0,0,0,0)
        it.startAnimation(clickAnimation())

        val editMultiLineID: Int = resources.getIdentifier("editTextTextMultiLine", "id", this.packageName)
        when (it.contentDescription) {
            "infantry_spearman" -> {
                msgOppObjName += """${it.contentDescription} 
Infantry Spearman: Best versus ranged cavalry. 

Deal hack damage in combat with significant bonuses against Cavalry, 
in addition to often being heavily armored. 

Although often slower than most infantry, 
they are much more sturdy and can quite easily chase away ranged units and especially ranged cavalry.
 
They typically are used to simply obstruct and 
keep away enemy troops and cavalry 
from reaching your own vulnerable ranged units.

They also excel like most units that deal hack damage, in tearing down enemy buildings and siege units. 

Promoted Citizen Infantry Spearmen receive upgrades to their attack, hitpoints and all of their armor - while decreasing their resource gathering abilities.
https://0ad.fandom.com/wiki/Infantry_Spearman
"""
            }
            "infantry_javelinist" -> {
                msgOppObjName += """  
Infantry Javelinist === Infantry Skirmisher):
ranged infantry throw spears, 
their weak/vulnerable, need distance from attacker especially from enemy Cavalry. 

Examples: https://0ad.fandom.com/wiki/Infantry_Skirmisher
            """
            }
            "cavalry_javelinist" -> {
                msgOppObjName += """ 
Javelineers-Cavalry === Cavalry Skirmishers (range of 30meters, speed of 16).
Especially strong against Archers and Cavalry Swordsmen. 
Deal pierce damage.
 
produced at Civilization Center
and/or Barracks sometimes. 
May also find them in Stables. 
https://0ad.fandom.com/wiki/Cavalry_Skirmisher
            """
            }
            "infantry_slinger" -> {
                msgOppObjName += """
Infantry Slingers do pierce damage (Range 48 meters, Speed 9).
Extremely vulnerable.

Maybe should only used in mass. 

produced at Civilization Center
and/or Barracks sometimes. 
https://0ad.fandom.com/wiki/Infantry_Slinger
            """
            }
            "cavalry_swordsman" -> {
                msgOppObjName += """
                    Cavalry Swordsmen do hack damage.
                     Very strong vs Support Units and Siege,
                     and Archers, healers. 
                     Very quick.
produced at Civilization Center
and/or Barracks sometimes. 
                    https://0ad.fandom.com/wiki/Cavalry_Swordsman
            """
            }
            "champion_chariot" -> {
                msgOppObjName += """ 
Champion-Chariot:"
shoot arrows.
Better protected then cavalry and runs a bit slower.
            """
            }
            "Rams" -> {
                msgOppObjName += """
Siege Rams knock down enemy structures, walls and especially gates.
Well protected from arrow fire and capable of garrisoning anywhere from 5 to 10 infantry for extra protection.

Siege Rams are typically constructed at your Fortress. 
                     
                     https://0ad.fandom.com/wiki/Siege_Ram
            """
            }
        }
        msgOppObjName += " " + it.contentDescription
        findViewById<EditText>(editMultiLineID).setText(msgOppObjName.replace("""[ \t]+""".toRegex(), " ").trimIndent())




    }
}

