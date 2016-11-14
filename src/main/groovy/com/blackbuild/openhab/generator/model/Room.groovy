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

    def sonos(String rincon, SonosType sonosType, Closure closure = {}) {
        def item = new SonosPlayer("Sonos", rincon, sonosType)
        item.apply(closure)
        element(item)
    }
    def sonos(String rincon, String suffix, SonosType sonosType, Closure closure = {}) {
        def item = new SonosPlayer("Sonos$suffix", rincon, sonosType)
        item.apply(closure)
        element(item)
    }

    def <T extends OpenHabElement> T getElement(Class<T> type, Closure<Boolean> predicate = { true }) {
        return (T) elements.find{type.isInstance(it) && predicate(it)}
    }

    HomeMaticHeating getHeating() {
        return getElement(HomeMaticHeating)
    }

    HomeMaticThing getSmokedetector() {
        return getElement(HomeMaticThing, { it.@type == "HM-Sec-SD-2"})
    }

    HomeMaticThing getThermostat() {
        return getElement(HomeMaticThing, { it.@type == "HM-WDS40-TH-I"})
    }

    SonosPlayer getSonos() {
        getElement(SonosPlayer)
    }

    NetatmoThing getNetatmo() {
        getElement(NetatmoThing)
    }




}
