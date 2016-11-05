package com.blackbuild.openhab.generator.model
import com.blackbuild.groovy.configdsl.transform.DSL

@DSL
abstract class Thing extends OpenHabElement {

    @Override
    String getPrefix() {
        return 'i'
    }

}
