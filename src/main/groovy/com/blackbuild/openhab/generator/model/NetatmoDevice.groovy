package com.blackbuild.openhab.generator.model

import com.blackbuild.groovy.configdsl.transform.DSL

@DSL
class NetatmoDevice extends BoundItem {

    static class Type {
        static final String INDOOR = "Indoor"
        static final String OUTDOOR = "Outdoor"
        static final String BASE = "Base"
        static final String RAIN = "Rain"
    }

    @Override
    String getDefaultTemplate() {
        return "NA$type"
    }
}
