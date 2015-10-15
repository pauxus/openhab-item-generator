package com.blackbuild.openhab.generator.model
import com.blackbuild.groovy.configdsl.transform.DSL

@DSL
class HomeMaticHeating extends Item {

    String thermostat
    Map<String, String> windows
    Map<String, String> valves
}

