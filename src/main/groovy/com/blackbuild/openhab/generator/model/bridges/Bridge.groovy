package com.blackbuild.openhab.generator.model.bridges

import com.blackbuild.groovy.configdsl.transform.DSL
import com.blackbuild.groovy.configdsl.transform.Key
import com.blackbuild.groovy.configdsl.transform.Owner
import com.blackbuild.openhab.generator.model.BridgedThing
import com.blackbuild.openhab.generator.model.OpenHabConfig

/**
 * Represents a OpenHab2 Bridge, i.e. the surrounding tag of a things file.
 */
@DSL
abstract class Bridge<T extends BridgedThing> {

    @Owner OpenHabConfig config
    @Key String id

    List<T> getThings() {
        config.all(thingType)
    }

    abstract String getNamespace()

    abstract String getBridgeType()

    abstract Class<T> getThingType()

    String getBridgeAddress() {
        "$namespace:$bridgeType:$id"
    }

    abstract String getParameterString()


    String getDefinition() {
        StringWriter result = new StringWriter()

        result.with {
            println "Bridge $bridgeAddress [ $parameterString ] {"

            things.each {
                println "  $it.definition"
            }

            println "}"
        }

        return result
    }

}
