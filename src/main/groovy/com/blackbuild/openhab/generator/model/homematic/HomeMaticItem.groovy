package com.blackbuild.openhab.generator.model.homematic

import com.blackbuild.groovy.configdsl.transform.DSL
import com.blackbuild.openhab.generator.model.BoundItem

/**
 * Generic HomeMatic Item. Is defined by its address and its type. The template name is automatically derived
 * from the type.
 */
@DSL
class HomeMaticItem extends BoundItem {

}
