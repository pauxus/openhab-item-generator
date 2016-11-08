import com.blackbuild.openhab.generator.model.NetatmoThing
import templates.ItemType
import templates.NetatmoTemplate

into("items/netatmo.items") { out ->

    config.all(NetatmoThing).each { rawThing ->
        def netatmo = new NetatmoTemplate(rawThing)

        out.println "//-------------------------------------------- Netatmo $netatmo.parentGroup.label"

        netatmo.with {

            out.println """Group ${fullName} "$parentGroup.label Netatmo" (${allGroupsAsString})"""

            if (netatmo.rawType in [NetatmoThing.Type.BASE, NetatmoThing.Type.INDOOR,NetatmoThing.Type.OUTDOOR]) {

                out.println createItem(ItemType.Number, "Temp", "NA Temperatur $netatmo.parentGroup.label [%.1f °C]", "temperature", [netatmo.fullName, 'gTemperatur', 'gChart'], null, "Temperature")
                out.println createItem(ItemType.Number, "Humid", "NA Feuchtigkeit $netatmo.parentGroup.label [%d %%]", "water", [netatmo.fullName, 'gFeuchtigkeit', 'gChart'], null, "Humidity")

            }

            if (netatmo.rawType in [NetatmoThing.Type.BASE, NetatmoThing.Type.INDOOR]) {
                out.println createItem(ItemType.Number, "CO2", "CO2 $netatmo.parentGroup.label [%d]", "temperature", [netatmo.fullName, 'gCO2', 'gChart'], null, "Co2")
            }
        }

        out.println ""
    }
}


