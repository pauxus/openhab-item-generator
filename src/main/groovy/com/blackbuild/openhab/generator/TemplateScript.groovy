package com.blackbuild.openhab.generator

import com.blackbuild.openhab.generator.model.OpenHabElement

abstract class TemplateScript extends Script {

    OpenHabElement item;

    StringWriter out

    static String surround(String before, String value, String after) {
        if (value)
            "$before$value$after"
        else
            ""
    }

    static String surround(String value) {
        value.length() > 2 ? value : ""
    }

}
