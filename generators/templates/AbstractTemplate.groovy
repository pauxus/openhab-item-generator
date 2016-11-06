package templates

import com.blackbuild.openhab.generator.model.Thing

abstract class AbstractTemplate {

    abstract Thing getThing()

    def createItem(ItemType type, String suffix, String label, String icon, List<String> groups, List<String> tags = [], String channel = null) {

        def output = []

        output << type
        output << "i${thing.parentGroup.canonicalName}_$suffix"
        output << /"$label"/
        if (icon) output << "<$icon>"
        if (groups) output << "(${groups.join(',')})"
        if (tags) output << "[" + tags.collect { /"$it"/ }.join(",") + "]"
        if (channel) output << "{ channel=\"${thing.getBinding(channel)}\" }"

        return output.join(' ')
    }

}
