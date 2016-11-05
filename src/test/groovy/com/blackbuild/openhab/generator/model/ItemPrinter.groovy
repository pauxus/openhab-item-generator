package com.blackbuild.openhab.generator.model

import own.OwnConfig


OpenHabConfig config = OpenHabConfig.createFromScript(OwnConfig)

config.bridges.each {

    println it.definition


}

