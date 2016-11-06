package com.blackbuild.openhab.generator.model.bridges

import com.blackbuild.groovy.configdsl.transform.DSL
import com.blackbuild.openhab.generator.model.FritzBoxThing

/**
 * Created by stephan on 06.11.2016.
 */
@DSL
class FritzAhaBridge extends Bridge<FritzBoxThing> {

    final Class thingType = FritzBoxThing
    final String bridgeType = "fritzbox"
    final String namespace = "avmfritz"

    String password
    String ipAddress

    final List<String> parameterProperties = ['password', 'ipAddress']


}
