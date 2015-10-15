package com.blackbuild.openhab.generator.model

import com.blackbuild.groovy.configdsl.transform.DSL
import com.blackbuild.openhab.generator.Visitor


@DSL
class OpenHabConfig {

    List<Item> items

    List<Group> groups

    def accept(Visitor visitor) {
        items.each {it.accept(visitor)}
        groups.each {it.accept(visitor)}
    }

}
