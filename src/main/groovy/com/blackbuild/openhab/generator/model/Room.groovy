package com.blackbuild.openhab.generator.model

import com.blackbuild.groovy.configdsl.transform.DSL
import com.blackbuild.openhab.generator.model.homematic.HomeMaticHeating
import com.blackbuild.openhab.generator.model.homematic.HomeMaticThing
import com.blackbuild.openhab.generator.model.sonos.SonosPlayer
import com.blackbuild.openhab.generator.model.sonos.SonosPlayer.SonosType

@DSL
class Room extends Group {

    @Override
    String getPrefix() {
        return "r"
    }

    def heating(@DelegatesTo(HomeMaticHeating) Closure closure) {
        element(HomeMaticHeating, "Heating", closure)
    }

    def smokedetector(String serialNumber, @DelegatesTo(HomeMaticThing) Closure closure = {}) {
        def item = HomeMaticThing.create("Smoke") {
            type "HM-Sec-SD-2"
            serial(serialNumber)
        }.apply(closure)

        element(item)
    }

    def smokedetector(String serialNumber, String name, @DelegatesTo(HomeMaticThing) Closure closure = {}) {
        def item = HomeMaticThing.create(name) {
            type "HM-Sec-SD-2"
            serial(serialNumber)
        }.apply(closure)

        element(item)
    }

    def sonos(String rincon, SonosType sonosType) {
        def item = new SonosPlayer("Sonos", rincon, sonosType)
        element(item)
    }
    def sonos(String rincon, String suffix, SonosType sonosType) {
        def item = new SonosPlayer("Sonos$suffix", rincon, sonosType)
        element(item)
    }
}
