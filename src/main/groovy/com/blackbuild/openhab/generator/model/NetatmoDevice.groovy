package com.blackbuild.openhab.generator.model

import com.blackbuild.groovy.configdsl.transform.DSL

@DSL
class NetatmoDevice extends Item {

    enum Type { BASE, OUTDOOR, INDOOR, RAIN }

    Type type

    String address

}
