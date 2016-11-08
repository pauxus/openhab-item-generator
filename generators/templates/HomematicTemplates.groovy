package templates

import com.blackbuild.openhab.generator.model.homematic.HomeMaticHeating
import com.blackbuild.openhab.generator.model.homematic.HomeMaticThing
import groovy.transform.InheritConstructors
import groovy.transform.TupleConstructor

@TupleConstructor
def class HeatingTemplate {

    @Delegate
    HomeMaticHeating thing

    CompositeMemberTemplate getThermostat() {
        new CompositeMemberTemplate(thing.thermostat)
    }

    List<HomematicGroupTemplate> getValves() {
        thing.valves.collect{ new HomematicGroupTemplate(it) }
    }

    List<HomematicGroupTemplate> getWindows() {
        thing.windows.collect{ new HomematicGroupTemplate(it) }
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

        def output = []

        output << type
        output << "i${thing.parentObject.canonicalName}_$suffix"
        output << /"$label"/
        if (icon) output << "<$icon>"
        if (groups) output << "(${groups.join(',')})"
        if (tags) output << "[" + tags.collect { /"$it"/ }.join(",") + "]"
        if (channel) output << "{ channel=\"${thing.getBinding(channel)}\" }"

        return output.join(' ')
    }
}

