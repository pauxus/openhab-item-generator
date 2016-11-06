package templates

import com.blackbuild.openhab.generator.model.sonos.SonosPlayer
import groovy.transform.TupleConstructor

@TupleConstructor
def class SonosPlayerTemplate {

    @Delegate
    SonosPlayer thing

    String getGroupDefinition() {
        """Group ${fullName} "$parentGroup.label Sonos" (${allGroupsAsString})"""
    }

    SonosTemplate getSonos() {
        return new SonosTemplate(thing.sonos)
    }

    HomematicTemplate getHomematic() {
        return new HomematicTemplate(thing.homematic)
    }

    @TupleConstructor
    static class SonosTemplate extends AbstractTemplate {

        @Delegate SonosPlayer.Sonos thing

        def createItem(ItemType type, String suffix, String label, String icon, List<String> groups, List<String> tags = [], String channel = null) {
            super.createItem(type, "Sonos_$suffix", label, icon, groups, tags, channel)
        }
    }

    @TupleConstructor
    static class HomematicTemplate extends AbstractTemplate {

        @Delegate SonosPlayer.Homematic thing

        def createItem(ItemType type, String suffix, String label, String icon, List<String> groups, List<String> tags = [], String channel = null) {
            super.createItem(type, "SonosHG_$suffix", label, icon, groups, tags, channel)
        }

    }



}







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
