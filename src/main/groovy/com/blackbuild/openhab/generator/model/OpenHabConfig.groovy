package com.blackbuild.openhab.generator.model

import com.blackbuild.groovy.configdsl.transform.DSL
import com.blackbuild.openhab.generator.Visitor
import com.blackbuild.openhab.generator.model.bridges.Bridge


@DSL
class OpenHabConfig {

    List<Bridge> bridges

    List<OpenHabElement> elements


    def accept(Visitor visitor) {
        elements.each {it.accept(visitor)}
    }

    List<OpenHabElement> allElements() {
        def visitor = new Collector()
        accept(visitor)
        return visitor.result
    }

    def <T extends OpenHabElement> T find(Class<T> type, String name) {
        return all(type).find { it.name == name }
    }

    def <T> T getParentOfType(Class<T> type) {
        return null
    }

    def <T extends OpenHabElement> List<T> all(Class<T> type) {
        def visitor = new Collector(type)
        accept(visitor)
        return visitor.result
    }

    def <T extends OpenHabElement> Map<String, T> allAsMap(Class<T> type) {
        all(type).collectEntries { [it.name, it]}
    }

    def room(String name, String theLabel, @DelegatesTo(Room) Closure closure) {
        def room = Room.create(name) {
            label(theLabel)
        }.apply(closure)
        element(room)
    }

    static class Collector implements Visitor {
        List<OpenHabElement> result = []
        Class type

        Collector(Class type = Object) {
            this.type = type
        }

        @Override
        def visit(OpenHabElement element) {
            if (type.isInstance(element)) result << element
        }
    }


}
