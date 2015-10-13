package com.blackbuild.openhab.generator.model
import com.blackbuild.groovy.configdsl.transform.DSL

@DSL
class Group extends OpenHabElement {

    List<Item> items
    List<Group> groups

    @Override
    String getPrefix() {
        "g"
    }
}
