package com.blackbuild.openhab.generator.model.homematic

import com.blackbuild.groovy.configdsl.transform.DSL
import com.blackbuild.openhab.generator.Visitor
import com.blackbuild.openhab.generator.model.Group

@DSL
class HomeMaticHeating extends Group {

    HomeMaticThing thermostat
    List<HomeMaticThing> windows
    List<HomeMaticThing> valves

    List<Float> fixValues

    def thermostat(@DelegatesTo Closure values) {
        thermostat("Thermostat", values)
    }


    @Override
    def accept(Visitor visitor) {
        visitor.visit(this)
        thermostat?.accept(visitor)
        windows*.accept(visitor)
        valves*.accept(visitor)

    }


}

