import com.blackbuild.openhab.generator.model.homematic.HomeMaticHeating
import com.blackbuild.openhab.generator.model.homematic.HomeMaticItem
import com.blackbuild.openhab.generator.model.NetatmoDevice
import com.blackbuild.openhab.generator.model.Room

into("sitemaps/default.sitemap") { out ->

    config.all(Room).each { room ->

        if (!room.items) return

        out.println """
Text label="$room.label" ${room.icon ? "icon=\"$room.icon\"" : ''} {"""

        HomeMaticHeating heating = room.items.find { it instanceof HomeMaticHeating } as HomeMaticHeating
        if (heating) {
            out.println """    //-------------------- Heating
    Text item=${heating.fullName}_Temp  label="Ist-Temperatur [%.1f °C]"
    Setpoint item=${heating.fullName}_Set label="Soll-Temperatur [%.1f °C]" minValue=5 maxValue=28 step=0.5"""

            if (heating.fixValues) {
                out.println "Switch item=${heating.fullName}_Set label=\"Soll-Temperatur\" mappings=[${heating.fixValues.collect {sprintf('"%.f"="%.f°C"', it, it)}.join(",")}]"
            }

            heating.windows.each {
                out.println """    Text item=${heating.fullName}_Window_${asIdentifier(it.key)}_Kontakt label="$it.key [MAP(window.map):%s]" """
            }

            heating.valves.each {
                out.println """    Text item=${heating.fullName}_Valve_${asIdentifier(it.key)}_pos label="Ventil $it.key [%d %%]" """
            }
        }

        HomeMaticItem thermostat = room.items.find {
            it instanceof HomeMaticItem && it.type == "HMWDS40THI"
        } as HomeMaticItem
        if (thermostat) {
            out.println """    //-------------------- Thermostat
    Text item=${thermostat.fullName}_Temp  label="Ist-Temperatur [%.1f °C]" """
        }

        NetatmoDevice netatmo = room.items.find { it instanceof NetatmoDevice } as NetatmoDevice
        if (netatmo) {
            out.println """    //-------------------- Heating
    Text item=${netatmo.fullName}_CO2  label="CO2 [%d ppm]" """

            if (netatmo.type == "Base")
                out.println """
    Number ${netatmo.fullName}_Noise       label="Schall [%d db]"               (${netatmo.fullName},Netatmo)
    Number ${netatmo.fullName}_Pressure    label="Luftdruck [%.2f mbar]"        (${netatmo.fullName},Netatmo)"""

        }


        out.println "}"


    }
}
