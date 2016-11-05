package com.blackbuild.openhab.generator.model
import com.blackbuild.groovy.configdsl.transform.DSL
import com.blackbuild.openhab.generator.Visitor

@DSL
class Group extends OpenHabElement {

    List<Thing> things
    List<Group> groups

    @Override
    String getPrefix() {
        "g"
    }

    def accept(Visitor visitor) {
        visitor.visit(this)

        things.each {it.accept(visitor)}
        groups.each {it.accept(visitor)}
    }
}
