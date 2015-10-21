package com.blackbuild.openhab.generator.model.homematic
import com.blackbuild.groovy.configdsl.transform.DSL
import com.blackbuild.openhab.generator.model.OpenHabElement

@DSL
class HMLedDisplay extends HomeMaticItem {

    List<OpenHabElement> leftElements

    List<OpenHabElement> rightElements

}
