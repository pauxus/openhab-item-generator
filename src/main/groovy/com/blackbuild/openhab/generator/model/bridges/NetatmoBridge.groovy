package com.blackbuild.openhab.generator.model.bridges

import com.blackbuild.groovy.configdsl.transform.DSL
import com.blackbuild.groovy.configdsl.transform.Validation
import com.blackbuild.openhab.generator.model.NetatmoThing
/**
 * A netatmo bridge
 */
@DSL
@Validation(option=Validation.Option.VALIDATE_UNMARKED)
class NetatmoBridge extends Bridge<NetatmoThing> {

    final Class thingType = NetatmoThing
    final String bridgeType = "netatmoapi"
    final String namespace = "netatmo"

    String clientId
    String clientSecret
    String username
    String password

    @Override
    String getParameterString() {
        $/clientId="$clientId", clientSecret="$clientSecret", username="$username", password="$password"/$
    }
}
