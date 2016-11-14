import com.blackbuild.openhab.generator.model.Group
import com.blackbuild.openhab.generator.model.Room
import com.blackbuild.openhab.generator.model.homematic.HomeMaticHeating
import com.blackbuild.openhab.generator.model.homematic.HomeMaticThing

into("items/groups.items") { out ->

    def definedGroups = config.all(Group)
    definedGroups.each { group ->
        if (group instanceof HomeMaticHeating) return
        out.println "Group $group.fullName ${surround('"', group.label, '"')} ${surround('<', group.icon, '>')} ${surround('(', group.allGroupsAsString, ')')}"

        if (group instanceof Room &&
                (group.elements.find{it instanceof HomeMaticHeating} ||
                        group.elements.find {it instanceof HomeMaticThing && it.@type == "HM-WDS40-TH-I"}))
        {
            out.println "String i${group.canonicalName}_ClimateLabel ($group.fullName)"
        }
    }

    out.println "String gOUT_ClimateLable (gOUT)"

}
