package templates

import com.blackbuild.openhab.generator.model.homematic.HomeMaticHeating
import com.blackbuild.openhab.generator.model.homematic.HomeMaticThing
import groovy.transform.InheritConstructors
import groovy.transform.TupleConstructor

@TupleConstructor
def class HeatingTemplate {

    @Delegate
    HomeMaticHeating thing

    ThermostatTemplate getThermostat() {
        new ThermostatTemplate(thing.thermostat)
    }

    List<ValveTemplate> getValves() {
        thing.valves.collect{ new ValveTemplate(it) }
    }

    List<WindowTemplate> getWindows() {
        thing.windows.collect{ new WindowTemplate(it) }
    }

    String getGroupDefinition() {
        """Group ${fullName} "$parentGroup.label Heizung" (${allGroupsAsString}) ["Thermostat"]"""
    }

}

@TupleConstructor
class HomematicGroupTemplate extends AbstractTemplate {
    @Delegate
    HomeMaticThing thing
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

