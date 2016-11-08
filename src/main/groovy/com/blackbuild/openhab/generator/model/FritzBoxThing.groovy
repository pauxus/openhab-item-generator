package com.blackbuild.openhab.generator.model

import com.blackbuild.groovy.configdsl.transform.DSL

/**
 * Created by stephan on 06.11.2016.
 */
@DSL
class FritzBoxThing extends BridgedThing {

    final String type = "FRITZ_DECT_200"

    String ain

    @Override
    String getId() {
        return ain
    }

    @Override
    String getParameterString() {
        return (/ain="$ain"/)
    }
}
