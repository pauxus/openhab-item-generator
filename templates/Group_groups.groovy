import com.blackbuild.openhab.generator.model.Group

Group group = item as Group


println "Group $group.fullName ${surround('"', group.label,'"')} ${surround('<', group.icon, '>')} ${surround('(' ,group.allGroupsAsString, ')')}"


