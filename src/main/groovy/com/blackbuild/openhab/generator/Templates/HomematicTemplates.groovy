package com.blackbuild.openhab.generator.Templates

import com.blackbuild.openhab.generator.model.homematic.HomeMaticHeating
import com.blackbuild.openhab.generator.model.homematic.HomeMaticThing
import groovy.transform.InheritConstructors
import groovy.transform.TupleConstructor

@TupleConstructor
def class HeatingTemplate {

    @Delegate
    HomeMaticHeating delegate

    ThermostatTemplate getThermostat() {
        new ThermostatTemplate(delegate.thermostat)
    }

    List<ValveTemplate> getValves() {
        delegate.valves.collect{ new ValveTemplate(it) }
    }

    List<WindowTemplate> getWindows() {
        delegate.windows.collect{ new WindowTemplate(it) }
    }

    String getGroupDefinition() {
        """Group ${fullName} "$parentGroup.label Heizung" (${allGroupsAsString}) ["Thermostat"]"""
    }

}

@TupleConstructor
abstract class HomematicGroupTemplate {
    @Delegate
    HomeMaticThing delegate

    def createItem(ItemType type, String suffix, String label, String icon, List<String> groups, List<String> tags = [], String channel = null) {

        def output = []

        output << type
        output << "${parentGroup.fullName}_$suffix"
        if (icon) output << "<$icon>"
        if (groups) output << "(${groups.join(',')})"
        if (tags) output << "[" + tags.collect { /"$it"/ }.join(",") + "]"
        if (channel) output << "{ channel=\"${getBinding(channel)}\" }"

        return output.join(' ')
    }

}

@InheritConstructors
class ThermostatTemplate extends HomematicGroupTemplate {


}
@InheritConstructors
class ValveTemplate extends HomematicGroupTemplate {
    def createItem(ItemType type, String suffix, String label, String icon, List<String> groups, List<String> tags = [], String channel = null) {
        super.createItem(type, "${safeName}_$suffix", label, icon, groups, tags, channel)
    }
}
@InheritConstructors
class WindowTemplate extends HomematicGroupTemplate {
    def createItem(ItemType type, String suffix, String label, String icon, List<String> groups, List<String> tags = [], String channel = null) {
        super.createItem(type, "${safeName}_$suffix", label, icon, groups, tags, channel)
    }
}

