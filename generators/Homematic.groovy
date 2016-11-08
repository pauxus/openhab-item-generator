import com.blackbuild.openhab.generator.model.homematic.HomeMaticThing
import templates.HomematicGroupTemplate
import templates.ItemType

into("items/homematic.items") { out ->

    config.all(HomeMaticThing).findAll{it.rawType == 'HM-ES-PMSw1-Pl' }.each { rawThing ->
        def plug = new HomematicGroupTemplate(rawThing)

        out.println "//-------------------------------------------- Schalter $plug.parentGroup.label"

        plug.with {

            out.println """Group ${fullName} "Verbrauchsmesser $name" (${allGroupsAsString})"""

            out.println createItem(ItemType.Number, "Power", "Leistung $name [%.1f W]", null, [fullName, 'gPower', 'gChart'], null, "2#POWER")
            out.println createItem(ItemType.Number, "Energy", "Verbrauch $name [%d]", null, [fullName, 'gEnergy', 'gChart'], null, "2#ENERGY_COUNTER")

            /*
            Switch iUG_WAK_Waschmaschine_Steckdose    "Waschmaschine Steckdose"             <switch> (iUG_WAK_Waschmaschine)         {homematic="address=LEQ0530758, channel=1, parameter=STATE"}
Number iUG_WAK_Waschmaschine_Leistung     "Waschmaschine Leistung [%.1f W]"     <switch> (iUG_WAK_Waschmaschine, Power)  {homematic="address=LEQ0530758, channel=2, parameter=POWER"}
Number iUG_WAK_Waschmaschine_Strom        "Waschmaschine Strom [%.1f A]"        <switch> (iUG_WAK_Waschmaschine)         {homematic="address=LEQ0530758, channel=2, parameter=CURRENT"}
Number iUG_WAK_Waschmaschine_Spannung     "Waschmaschine Spannung  [%.1f V]"    <switch> (iUG_WAK_Waschmaschine)         {homematic="address=LEQ0530758, channel=2, parameter=VOLTAGE"}
Number iUG_WAK_Waschmaschine_Frequenz     "Waschmaschine Frequenz  [%.2f Hz]"   <switch> (iUG_WAK_Waschmaschine)         {homematic="address=LEQ0530758, channel=2, parameter=FREQUENCY"}
Number iUG_WAK_Waschmaschine_Zaehler      "Waschmaschine Verbrauch  [%.1f Wh]"  <switch> (iUG_WAK_Waschmaschine,Energy)  {homematic="address=LEQ0530758, channel=2, parameter=ENERGY_COUNTER"}

             */


        }

        out.println ""
    }
}


