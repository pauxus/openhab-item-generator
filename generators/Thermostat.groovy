import com.blackbuild.openhab.generator.model.homematic.HomeMaticThing
import templates.HomematicGroupTemplate
import templates.ItemType

into("items/homematic.items") { out ->

    config.all(HomeMaticThing).findAll { thing -> thing.rawType == "HM-WDS40-TH-I" }.each { rawThermostat ->

        def thermostat = new HomematicGroupTemplate(rawThermostat)

        out.println "//-------------------------------------------- Thermostat $thermostat.parentGroup.label"

        thermostat.with {
            out.println  """Group ${fullName} "$parentGroup.label Thermostat" (${allGroupsAsString})"""

            out.println createItem(ItemType.Number, "Temp", "Ist-Temperatur $thermostat.parentGroup.label [%.1f °C]", "temperature", [ thermostat.fullName, 'gTemperatur', 'gChart' ], ["CurrentTemperature"], "1#TEMPERATURE")
            out.println createItem(ItemType.Number, "Humid", "Feuchtigkeit $thermostat.parentGroup.label [%d %%]", "water", [ thermostat.fullName, 'gFeuchtigkeit', 'gChart' ], ["CurrentHumidity"], "1#HUMIDITY")

            out.println createItem(ItemType.Number, "Rssi", "RSSI Thermostat $thermostat.parentGroup.label [SCALE(rssi.scale):%s]", "signal", [ thermostat.fullName, 'gWarnungen' ], null, "0#RSSI_DEVICE")
            out.println createItem(ItemType.Dimmer, "Rssi_perc", "RSSI Thermostat $thermostat.parentGroup.label [%d %%]", "signal", [ thermostat.fullName, 'gWarnungen' ])

            out.println createItem(ItemType.String, "LowBat", "Batterie Thermostat $thermostat.parentGroup.label [MAP(lowbat.map):%s]", "battery", [ thermostat.fullName, 'gWarnungen' ], null, "0#LOWBAT")
            out.println createItem(ItemType.String, "Pending", "Pending Thermostat $thermostat.parentGroup.label [MAP(configpending.map):%s", "error", [ thermostat.fullName, 'gWarnungen' ], null, "0#CONFIG_PENDING")
        }

        out.println ""
    }
}

