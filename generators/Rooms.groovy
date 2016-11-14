import com.blackbuild.openhab.generator.model.NetatmoThing
import com.blackbuild.openhab.generator.model.Room
import com.blackbuild.openhab.generator.model.homematic.HomeMaticHeating
import com.blackbuild.openhab.generator.model.homematic.HomeMaticThing
import com.blackbuild.openhab.generator.model.sonos.SonosPlayer

into("sitemaps/default.sitemap") { out ->

    out.println "sitemap default label=\"Default\" {"

    config.all(Room).each { room ->

        if (!room.elements) {
            return
        }

        boolean content = false

        HomeMaticHeating heating = room.heating
        HomeMaticThing thermostat = room.thermostat
        NetatmoThing netatmo = room.netatmo
        SonosPlayer sonos = room.sonos

        out.println ""
        if (heating || thermostat)
            out.println """Text item=i${room.canonicalName}_ClimateLabel label="$room.label [%s]" ${room.icon ? "icon=\"$room.icon\"" : ''} {"""
        else if (thermostat)
            out.println """Text item=i${thermostat.canonicalName}_Temp label="$room.label [%.1f °C]" ${room.icon ? "icon=\"$room.icon\"" : ''} {"""
        else
            out.println """Text label="$room.label" ${room.icon ? "icon=\"$room.icon\"" : ''} {"""

        if (heating) {
            content = true
            out.println """    //-------------------- Heating
    Text item=i${heating.canonicalName}_Temp  label="Temperatur [%.1f °C]"
    Setpoint item=i${heating.canonicalName}_Set label="Soll [%.1f °C]" minValue=5 maxValue=28 step=0.5"""

            if (heating.fixValues) {
                out.println """    Switch item=i${heating.canonicalName}_Set label=\"Soll\" mappings=[${heating.fixValues.collect { sprintf('"%.1f"="%.1f°C"', it, it).replace(',', '.') }.join(",")}]"""
            }

            heating.windows.each {
                out.println """    Text item=i${heating.canonicalName}_${asIdentifier(it.name)}_Kontakt label="$it.name [MAP(window.map):%s]" """
            }

            heating.valves.each {
                out.println """    Text item=i${heating.canonicalName}_${asIdentifier(it.name)}_Pos label="Ventil $it.name [%d %%]" """
            }
        }

        if (thermostat) {
            content = true
            out.println """    //-------------------- Thermostat
    Text item=i${thermostat.canonicalName}_Temp  label="Temperatur [%.1f °C]" """
        }

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

        if (sonos && sonos.messages) {
            out.println """    // Sonos"""
            out.println """    Selection item=i${room.canonicalName}_SonosHG_Speak label="Sonos" mappings=[${sonos.messages.collect{"\"$it\"=\"$it\""}.join(",")}]"""
        }

        if (!content)
            out.println """  Text label="empty" """

        out.println "}"

    }
    out.println "}"
}


into('rules/roomlabels.rules') { out ->

    config.all(Room).findAll{it.heating}.each { room ->

        HomeMaticHeating heating = room.heating
        NetatmoThing netatmo = room.netatmo

        List<String> items = []

        items.addAll(  ['Temp', 'Set', 'Mode'].collect { "i${heating.canonicalName}_$it" } )

        if (netatmo)
            items << "i${netatmo.parentObject.canonicalName}_CO2"

        items.addAll "g${heating.parentGroup.canonicalName}_Windows"

        out.println """
rule ${room.fullName}_ClimateLabel
when
${items.collect{"  Item $it received update"}.join(" or\n") }
then
    var String co2 = ""
    var String mode = ""

${if (netatmo) """
    if (i${netatmo.canonicalName}_CO2.state > 1000) {
        co2 = "!"
    } else if (i${netatmo.canonicalName}_CO2.state > 2000) {
        co2 = "!!!"
    }""" else ""}
    if (i${heating.canonicalName}_Mode.state == "heat") {
        mode = "*"
    }

    if (g${heating.parentGroup.canonicalName}_Windows.state == OPEN) {
        i${heating.parentGroup.canonicalName}_ClimateLabel.postUpdate(String::format("(%.1f°C)%s", (i${heating.canonicalName}_Temp.state as DecimalType).floatValue(), co2))
    } else {
        i${heating.parentGroup.canonicalName}_ClimateLabel.postUpdate(String::format("%s%.1f°C/%.1f°C%s", mode, (i${heating.canonicalName}_Temp.state as DecimalType).floatValue(), (i${heating.canonicalName}_Set.state as DecimalType).floatValue(), co2))
    }
end
"""



    }

}
