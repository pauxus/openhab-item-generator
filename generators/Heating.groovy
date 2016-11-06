import templates.HeatingTemplate
import templates.ItemType
import com.blackbuild.openhab.generator.model.homematic.HomeMaticHeating

into("items/homematic.items") { out ->

    config.all(HomeMaticHeating).each { rawHeating ->
        def heating = new HeatingTemplate(rawHeating)

        out.println "//-------------------------------------------- Heizung $heating.parentGroup.label"

        out.println heating.groupDefinition

        heating.thermostat.with {
            out.println createItem(ItemType.Number, "Temp", "Ist-Temperatur $heating.parentGroup.label [%.1f °C]", "temperature", [ heating.fullName, 'gTemperatur', 'gChart' ], ["CurrentTemperature"], "1#TEMPERATURE")
            out.println createItem(ItemType.Number, "Set", "Soll-Temperatur $heating.parentGroup.label [%.1f °C]", "temperature", [ heating.fullName, 'gTemperatur', 'gChart' ], ["TargetTemperature"], "2#SETPOINT")
            out.println createItem(ItemType.Number, "Humid", "Feuchtigkeit $heating.parentGroup.label [%d %%]", "water", [ heating.fullName, 'gFeuchtigkeit', 'gChart' ], ["CurrentHumidity"], "1#HUMIDITY")

            out.println createItem(ItemType.String, "Mode", "Dummy Modus $heating.parentGroup.label [%s]", null, [ heating.fullName ], ["homekit:HeatingCoolingMode"])

            out.println createItem(ItemType.Number, "Rssi", "RSSI Thermostat $heating.parentGroup.label [SCALE(rssi.scale):%s]", "signal", [ heating.fullName, 'gWarnungen' ], null, "0#RSSI_DEVICE")
            out.println createItem(ItemType.Dimmer, "Rssi_perc", "RSSI Thermostat $heating.parentGroup.label [%d %%]", "signal", [ heating.fullName, 'gWarnungen' ])

            out.println createItem(ItemType.String, "LowBat", "Batterie Thermostat $heating.parentGroup.label [MAP(lowbat.map):%s]", "battery", [ heating.fullName, 'gWarnungen' ], null, "0#LOWBAT")
            out.println createItem(ItemType.String, "Pending", "Pending Thermostat $heating.parentGroup.label [MAP(configpending.map):%s", "error", [ heating.fullName, 'gWarnungen' ], null, "0#LOWBAT")
        }

        heating.windows.each {
            it.with {
                out.println createItem(ItemType.Contact, "Kontakt", "$name $heating.parentGroup.label [MAP(window.map):%s]", "window", [ heating.fullName, 'gFenster', 'gChart' ], null, "1#STATE")
                out.println createItem(ItemType.String, "LowBat", "$it.name $heating.parentGroup.label Batterie [MAP(lowbat.map):%s]", "battery", [ heating.fullName, 'gWarnungen' ], null, "1#CONFIG_PENDING")
//String  ${heating.fullName}_Window_${asIdentifier(it.name)}_Error   "$it.name $heating.parentGroup.label Sabotage [MAP(MDirError.map):%s]"  <contact> (gWarnungen)                     {homematic="address=${it.value}, channel=1, parameter=ERROR"}
            }
        }

        heating.valves.each {
            it.with {
                out.println createItem(ItemType.Number, "Pos", "$it.name $heating.parentGroup.label [%d %%]", "heating", [ heating.fullName, 'gVentile', 'gChart' ], null, "1#VALVE_STATE")
            }
        }

        out.println ""
    }
}


