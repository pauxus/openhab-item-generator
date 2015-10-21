package com.blackbuild.openhab.generator.model.homematic
import com.blackbuild.groovy.configdsl.transform.DSL
import com.blackbuild.openhab.generator.model.Item

@DSL
class HomeMaticHeating extends Item {

    String thermostat
    Map<String, String> windows
    Map<String, String> valves

}

