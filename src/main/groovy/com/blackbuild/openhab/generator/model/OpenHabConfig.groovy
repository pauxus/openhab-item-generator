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

    List<OpenHabElement> allElements() {
        def visitor = new Collector()
        accept(visitor)
        return visitor.result
    }

    def <T extends OpenHabElement> List<T> allElements(Class<T> type) {
        def visitor = new Collector(type)
        accept(visitor)
        return visitor.result
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
