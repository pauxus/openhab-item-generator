package com.blackbuild.openhab.generator.model

import com.blackbuild.groovy.configdsl.transform.DSL

@DSL
class GenericThing extends Thing {

    String type

    String binding

    @Override
    String getDefinition() {
        return null
    }

    @Override
    String getBinding(String channel) {
        return null
    }
}
