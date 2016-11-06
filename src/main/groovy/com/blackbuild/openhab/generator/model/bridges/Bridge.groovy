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

    abstract List<String> getParameterProperties()

    String getParameterString() {
        parameterProperties.collectEntries { [it, getProperty(it)] }.findResults { key, value ->
            if (!value) return null

            $/$key="$value"/$
        }.join(",")
    }


    String getDefinition() {
        StringWriter result = new StringWriter()

        result.println "Bridge $bridgeAddress [ $parameterString ] {"

        configuredThings(result)

        result.println "}"

        return result
    }

    void configuredThings(StringWriter result) {
        things.each {
            result.println "  $it.definition"
        }

        result.println ""

        additionalThings(result)
    }

    void additionalThings(StringWriter out) {

    }
}
