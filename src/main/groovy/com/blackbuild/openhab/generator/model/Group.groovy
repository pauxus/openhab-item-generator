package com.blackbuild.openhab.generator.model
import com.blackbuild.groovy.configdsl.transform.DSL
import com.blackbuild.openhab.generator.Visitor

@DSL
class Group extends OpenHabElement {

    List<OpenHabElement> elements

    @Override
    String getPrefix() {
        "g"
    }

    def accept(Visitor visitor) {
        visitor.visit(this)

        elements.each {it.accept(visitor)}
    }
}
