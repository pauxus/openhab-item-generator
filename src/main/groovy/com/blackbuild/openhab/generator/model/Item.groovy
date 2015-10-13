package com.blackbuild.openhab.generator.model
import com.blackbuild.groovy.configdsl.transform.DSL

@DSL
class Item extends OpenHabElement{

    String template

    @Override
    String getPrefix() {
        return 'i'
    }
}
