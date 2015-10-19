import com.blackbuild.openhab.generator.model.Group

into("groups.items") { out ->
    config.allElements(Group).each { group ->
        out.println "Group $group.fullName ${surround('"', group.label, '"')} ${surround('<', group.icon, '>')} ${surround('(', group.allGroupsAsString, ')')}"
    }
}
