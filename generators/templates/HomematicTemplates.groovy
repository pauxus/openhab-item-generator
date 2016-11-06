package templates

import com.blackbuild.openhab.generator.model.homematic.HomeMaticHeating
import com.blackbuild.openhab.generator.model.homematic.HomeMaticThing
import groovy.transform.InheritConstructors
import groovy.transform.TupleConstructor

@TupleConstructor
def class HeatingTemplate {

    @Delegate
    HomeMaticHeating thing

    HomematicGroupTemplate getThermostat() {
        new HomematicGroupTemplate(thing.thermostat)
    }

    List<CompositeMemberTemplate> getValves() {
        thing.valves.collect{ new CompositeMemberTemplate(it) }
    }

    List<CompositeMemberTemplate> getWindows() {
        thing.windows.collect{ new CompositeMemberTemplate(it) }
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
class CompositeMemberTemplate extends HomematicGroupTemplate {
    def createItem(ItemType type, String suffix, String label, String icon, List<String> groups, List<String> tags = [], String channel = null) {
        super.createItem(type, "${safeName}_$suffix", label, icon, groups, tags, channel)
    }
}

