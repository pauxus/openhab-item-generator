package com.blackbuild.openhab.generator.model

import com.blackbuild.groovy.configdsl.transform.DSL
import com.blackbuild.groovy.configdsl.transform.Key
import com.blackbuild.groovy.configdsl.transform.Owner
import com.blackbuild.openhab.generator.Visitor

@DSL
abstract class OpenHabElement {

    @Key String name
    @Owner Object parentObject

    String label

    String icon

    String description

    Map<String, Object> infos

    Group getParentGroup() {
        parentObject instanceof Group ? parentObject : null
    }

    abstract String getPrefix()

    String getFullName() {
        "${prefix}$canonicalName"
    }

    String getCanonicalName() {
        "${parentGroup ? parentGroup.canonicalName + '_' : ''}$safeName"
    }

    String getSafeName() {
        name.replace(" ", "").replaceAll("\\W+", "")
    }

    List<Group> additionalGroups

    Map<String,String> properties

    List<Group> getAllGroups() {
        return additionalGroups + (parentGroup ?: [])
    }

    String getAllGroupsAsString() {
        return (allGroups*.fullName).join(",")
    }

    def accept(Visitor visitor) {
        visitor.visit(this)
    }

    @Override
    String toString() {
        return "${getClass().simpleName}: $name ($fullName)"
    }
}
