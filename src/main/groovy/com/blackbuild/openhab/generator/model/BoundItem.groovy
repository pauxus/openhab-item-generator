package com.blackbuild.openhab.generator.model

import com.blackbuild.groovy.configdsl.transform.DSL

/**
 * A bound item represents an item defined by an external binding.
 */
@DSL
class BoundItem extends Item {

    String type

    String address

}
