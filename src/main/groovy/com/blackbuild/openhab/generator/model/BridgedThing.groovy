package com.blackbuild.openhab.generator.model

import com.blackbuild.groovy.configdsl.transform.DSL
import com.blackbuild.openhab.generator.model.bridges.Bridge

/**
 * A bound item represents an item defined by a bridge
 */
@DSL
abstract class BridgedThing extends Thing {

    Bridge getBridge() {
        config.bridges.find { it.thingType.isInstance(this)}
    }

    abstract String getId()
    abstract String getType()

    String getTypeDescription() {
        type
    }
    String getParameterString() {
        ""
    }

    String getBinding(String channel) {
        "$bridge.namespace:$type:$bridge.id:$id:$channel"
    }

    String getDefinition() {
        String roomName = getParentOfType(Room)?.label
        $/Thing $type $id "$typeDescription $name" ${ parameterString ? "[ $parameterString ]" : ""} ${roomName ? "// @ \"$roomName\"" : ""}/$
    }

}
