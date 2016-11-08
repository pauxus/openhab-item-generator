import com.blackbuild.openhab.generator.model.FritzBoxThing
import templates.FritzBoxTemplate
import templates.ItemType

into("items/fritzbox.items") { out ->

    config.all(FritzBoxThing).each { rawThing ->
        def fritz = new FritzBoxTemplate(rawThing)

        out.println "//-------------------------------------------- FritzAHA $fritz.parentGroup.label"

        fritz.with {

            out.println """Group ${fullName} "Verbrauchsmesser $name" (${allGroupsAsString})"""
            out.println createItem(ItemType.Number, "Power", "Leistung $name [%.1f W]", null, [fullName, 'gPower', 'gChart'], null, "power")
            out.println createItem(ItemType.Number, "Energy", "Verbrauch $name [%d]", null, [fullName, 'gEnergy', 'gChart'], null, "energy")
        }

        out.println ""
    }
}


