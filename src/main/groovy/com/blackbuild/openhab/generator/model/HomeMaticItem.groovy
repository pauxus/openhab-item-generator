package com.blackbuild.openhab.generator.model

import com.blackbuild.groovy.configdsl.transform.DSL

/**
 * Generic HomeMatic Item. Is defined by its address and its type. The template name is automatically derived
 * from the type.
 */
@DSL
class HomeMaticItem extends BoundItem {

    String address

    String getDefaultTemplate() {
        return type
    }
}
