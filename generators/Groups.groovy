import com.blackbuild.openhab.generator.model.Group

into("items/groups.items") { out ->
    config.all(Group).each { group ->
        out.println "Group $group.fullName ${surround('"', group.label, '"')} ${surround('<', group.icon, '>')} ${surround('(', group.allGroupsAsString, ')')}"
    }
}
