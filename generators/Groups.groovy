import com.blackbuild.openhab.generator.model.Group
import com.blackbuild.openhab.generator.model.homematic.HomeMaticHeating

into("items/groups.items") { out ->

    def definedGroups = config.all(Group)
    definedGroups.each { group ->
        if (group instanceof HomeMaticHeating) return
        out.println "Group $group.fullName ${surround('"', group.label, '"')} ${surround('<', group.icon, '>')} ${surround('(', group.allGroupsAsString, ')')}"
    }




}
