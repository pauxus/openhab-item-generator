package com.blackbuild.openhab.generator.model

import com.blackbuild.groovy.configdsl.transform.DSL
/**
 * A bound item represents an item defined by a bridge
 */
@DSL
abstract class BridgelessThing extends Thing {

    abstract String getNamespace()
    abstract String getId()
    abstract String getType()

    String getTypeDescription() {
        type
    }

    String getParameterString() {
        ""
    }

    String getThingDefinition() {
        "$namespace:$type:$id"
    }

    String getBinding(String channel) {
        "$thingDefinition:$channel"
    }

    String getThingLabel() {
        return "$typeDescription $name"
    }

    String getDefinition() {
        String roomName = getParentOfType(Room)?.label
        $/Thing $thingDefinition "$thingLabel" ${ parameterString ? "[ $parameterString ]" : ""} ${roomName ? "// @ \"$roomName\"" : ""}/$
    }

}
