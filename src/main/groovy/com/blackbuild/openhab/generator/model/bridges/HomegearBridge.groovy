package com.blackbuild.openhab.generator.model.bridges

import com.blackbuild.groovy.configdsl.transform.DSL
import com.blackbuild.openhab.generator.model.homematic.HomeMaticThing

@DSL
class HomegearBridge extends Bridge<HomeMaticThing> {

    final Class thingType = HomeMaticThing
    final String bridgeType = "bridge"
    final String namespace = "homematic"

    String gatewayAddress

    boolean homegear

    final List<String> parameterProperties = ['gatewayAddress']

    @Override
    void additionalThings(StringWriter out) {
        out.println "  Thing GATEWAY-EXTRAS-${id.toUpperCase()} GWE00000000"
    }
}
