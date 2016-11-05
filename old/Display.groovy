import com.blackbuild.openhab.generator.model.Group
import com.blackbuild.openhab.generator.model.homematic.HMLedDisplay
import com.blackbuild.openhab.generator.model.homematic.HomeMaticHeating

into("items/homematic.items") { out ->

    config.all(HMLedDisplay).each { display ->
        out.println """//-------------------------------------------- Display $display.description
Group $display.fullName $display.labelString $display.iconString $display.groupsString"""

        display.leftElements.eachWithIndex { item, index ->
            out.println """String ${display.fullName}_L${index + 1} {homematic="address=$display.address, channel=${index + 1}, parameter=LED_STATUS"}"""
        }
        display.rightElements.eachWithIndex { item, index ->
            out.println """String ${display.fullName}_R${index + 1} {homematic="address=$display.address, channel=${index + 8}, parameter=LED_STATUS"}"""
        }
    }
}


into("rules/display.rules") { out ->

    config.all(HMLedDisplay).each { HMLedDisplay display ->

        display.allElements.each { name, room ->
            if (!room instanceof Group) return
            def group = room as Group

            group.items.values().each { element ->

                if (element instanceof HomeMaticHeating) {
                    def heating = element as HomeMaticHeating
                    out.println """
rule ${display.fullName}_${name}
when
  ${heating.fullName}_Window_${asIdentifier(it.key)}_Kontakt

  Item iDG_Heating_Valve_Links_pos changed
  or Item iDG_Heating_Valve_Rechts_pos changed
  or Item iDG_Heating_Window_Rechts_Kontakt changed
then
 if (iDG_Heating_Valve_Links_pos.state > 0 || iDG_Heating_Valve_Rechts_pos.state > 0) {
    sendCommand(iDisplay_L1, "ORANGE")
 else if (iDG_Heating_Window_Rechts_Kontakt.state == "true") {
    sendCommand(iDisplay_L1, "GREEN")
 } else {
    sendCommand(iDisplay_L1, "OFF")
 }
end
"""
                }
            }
        }
    }
}
