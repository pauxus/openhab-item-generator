package com.blackbuild.openhab.generator.model.sonos

import com.blackbuild.groovy.configdsl.transform.DSL
import com.blackbuild.groovy.configdsl.transform.Ignore
import com.blackbuild.openhab.generator.Visitor
import com.blackbuild.openhab.generator.model.BridgedThing
import com.blackbuild.openhab.generator.model.BridgelessThing
import com.blackbuild.openhab.generator.model.CompositeElement
import com.blackbuild.openhab.generator.model.Room
import com.blackbuild.openhab.generator.model.homematic.HomeMaticThing

@DSL
class SonosPlayer extends CompositeElement {

    String id
    SonosType type

    SonosPlayer(String name, String id, SonosType type) {
        this(name)
        this.id = id
        this.type = type
        element(Homematic.create("HM") {})
        element(Sonos.create("Sonos") {})
    }

    public void setId(String id) {
        this.if
    }

    @DSL
    static class Homematic extends HomeMaticThing {

        @Override
        String getId() {
            return "$parentObject.id"
        }

        String getType() {
            return parentObject.type.homematicType
        }

        @Override
        String getThingLabel() {
            "$typeDescription ${getParentOfType(Room)?.label}"
        }
    }

    @DSL
    static class Sonos extends BridgelessThing {

        final String namespace = "sonos"

        @Override
        String getId() {
            return "sonos:$type:${getParentOfType(Room)?.name}"
        }

        @Override
        String getParameterString() {
            return ($/udn="RINCON_${parentObject.id}01400"/$)
        }

        @Override
        String getType() {
            return parentObject.type.sonosType
        }

        @Override
        String getThingLabel() {
            "$typeDescription ${getParentOfType(Room)?.label}"
        }

    }

    enum SonosType {
        PLAY_1, PLAY_5

        String getHomematicType() {
            "Sonos-${toString().replace('_','-')}"
        }

        String getSonosType() {
            return toString().replace("_", "")
        }
    }

}
