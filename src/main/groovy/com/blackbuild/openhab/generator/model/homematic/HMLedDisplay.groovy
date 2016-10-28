package com.blackbuild.openhab.generator.model.homematic
import com.blackbuild.groovy.configdsl.transform.DSL
import com.blackbuild.openhab.generator.model.OpenHabElement

@DSL
class HMLedDisplay extends HomeMaticItem {

    List<OpenHabElement> leftElements

    List<OpenHabElement> rightElements

    Map<String, OpenHabElement> getAllElements() {
        leftElements.indexed(1).collectEntries { ["${this.fullName}_L${it.key}", it.value] } +
        rightElements.indexed(1).collectEntries { ["${this.fullName}_R${it.key}", it.value] }
    }

}
