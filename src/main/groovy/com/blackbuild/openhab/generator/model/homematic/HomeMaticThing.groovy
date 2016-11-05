package com.blackbuild.openhab.generator.model.homematic

import com.blackbuild.groovy.configdsl.transform.DSL
import com.blackbuild.openhab.generator.model.BridgedThing

/**
 * Generic HomeMatic Item. Is defined by its address and its type. The template name is automatically derived
 * from the type.
 */
@DSL
class HomeMaticThing extends BridgedThing {

    String serial

    String type

    @Override
    String getId() {
        return serial
    }

    String getType() {
        return bridge.homegear ? "HG-$type" : type
    }

    String getBinding(int channel, String parameter) {
        getBinding("$channel#$parameter")
    }
}
