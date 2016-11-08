import com.blackbuild.openhab.generator.model.NetatmoThing
import com.blackbuild.openhab.generator.model.Room
import com.blackbuild.openhab.generator.model.homematic.HomeMaticHeating
import com.blackbuild.openhab.generator.model.homematic.HomeMaticThing

into("sitemaps/default.sitemap") { out ->

    out.println "sitemap default label=\"Default\" {"

    config.all(Room).each { room ->

        if (!room.elements) {
            return
        }

        boolean content = false

        out.println """
Text label="$room.label" ${room.icon ? "icon=\"$room.icon\"" : ''} {"""

        HomeMaticHeating heating = room.elements.find{it instanceof HomeMaticHeating} as HomeMaticHeating
        if (heating) {
            content = true
            out.println """    //-------------------- Heating
    Text item=i${heating.canonicalName}_Temp  label="Ist-Temperatur [%.1f °C]"
    Setpoint item=i${heating.canonicalName}_Set label="Soll-Temperatur [%.1f °C]" minValue=5 maxValue=28 step=0.5"""

            if (heating.fixValues) {
                out.println """    Switch item=i${heating.canonicalName}_Set label=\"Soll-Temperatur\" mappings=[${heating.fixValues.collect { sprintf('"%.1f"="%.1f°C"', it, it) }.join(",")}]"""
            }

            heating.windows.each {
                out.println """    Text item=i${heating.canonicalName}_${asIdentifier(it.name)}_Kontakt label="$it.name [MAP(window.map):%s]" """
            }

            heating.valves.each {
                out.println """    Text item=i${heating.canonicalName}_${asIdentifier(it.name)}_Pos label="Ventil $it.name [%d %%]" """
            }
        }

        HomeMaticThing thermostat = room.elements.find {
            it instanceof HomeMaticThing && it.@type == "HM-WDS40-TH-I"
        } as HomeMaticThing
        if (thermostat) {
            content = true
            out.println """    //-------------------- Thermostat
    Text item=i${thermostat.canonicalName}_Temp  label="Ist-Temperatur [%.1f °C]" """
        }

        NetatmoThing netatmo = room.elements.find { it instanceof NetatmoThing } as NetatmoThing
        if (netatmo) {
            content = true
            out.println """    //-------------------- Heating
    Text item=${netatmo.fullName}_CO2  label="CO2 [%d ppm]" """

//            if (netatmo.@type == NetatmoThing.Type.BASE)
//                out.println """
//    Number ${netatmo.fullName}_Noise       label="Schall [%d db]"               (${netatmo.fullName},Netatmo)
//    Number ${netatmo.fullName}_Pressure    label="Luftdruck [%.2f mbar]"        (${netatmo.fullName},Netatmo)"""
//
        }
        if (!content)
            out.println """  Text label="empty" """

        out.println "}"

    }
    out.println "}"
}
