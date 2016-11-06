package com.blackbuild.openhab.generator.model

import com.blackbuild.groovy.configdsl.transform.DSL

@DSL
class NetatmoThing extends BridgedThing {

    Type type

    String getType() {
        return type.moduleName
    }

    Type getRawType() {
        return type
    }

    String equipmentId
    String parentId
    int refreshInterval

    String address

    void doValidate() {
        if (address) {
            assert !equipmentId
            assert !parentId
            def split = address.split(/#/)
            equipmentId = split[0]
            if (split.length > 1)
                parentId = split[1]
        }

        assert equipmentId

        if (type == Type.BASE)
            assert !getParentId()
        else
            assert getParentId()
    }

    @Override
    String getTypeDescription() {
        return type.description
    }

    @Override
    String getId() {
        return name
    }

    @Override
    String getParameterString() {
        def result = [/equipmentId="$equipmentId"/]

        if (parentId)
            result << /parentId="$parentId"/

        if (refreshInterval)
            result << /refreshInterval=$refreshInterval/

        return result.join(",")
    }

    enum Type {

        INDOOR("NAModule4", "Innenmodul"),
        OUTDOOR("NAModule1", "Aussenmodul"),
        BASE("NAMain", "Basisstation"),
        RAIN("NAModule3", "Regenmesser")

        final String moduleName
        final String description

        Type(String moduleName, String description) {
            this.moduleName = moduleName
            this.description = description
        }
    }
}
