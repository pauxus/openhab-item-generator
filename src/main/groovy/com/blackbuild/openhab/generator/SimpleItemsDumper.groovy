package com.blackbuild.openhab.generator

import com.blackbuild.openhab.generator.model.Group
import com.blackbuild.openhab.generator.model.Item
import com.blackbuild.openhab.generator.model.OpenHabElement

class SimpleItemsDumper implements Visitor {

    private final static String DEFAULT_TEMPLATE_NAME = "default"

    StringWriter output = new StringWriter()


    @Override
    def visit(OpenHabElement element) {
        println "Ignoring $element"
    }

    def visit(Group group) {

        output.println("Group $group.fullName ${surround('"', group.label,'"')} ${surround('<', group.icon, '>')} ${surround('(' ,group.allGroupsAsString, ')')}")
    }

    def visit(Item item) {


    }

    def getOutput() {
        output.toString()
    }

    static String surround(String before, String value, String after) {
        if (value)
            "$before$value$after"
        else
            ""
    }
}
