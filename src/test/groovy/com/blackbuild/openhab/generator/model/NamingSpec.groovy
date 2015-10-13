package com.blackbuild.openhab.generator.model

import spock.lang.Specification
import spock.lang.Unroll


class NamingSpec extends Specification {

    @Unroll
    def "Basic namings"() {
        expect:
        config.fullName == name

        where:
        [config, name] << [
                [Group.create("FirstFloor") {}, "gFirstFloor"],
                [Group.create("FirstFloor"){
                    subGroups {
                        subGroup("Bath") {}
                    }
                }.subGroups[0], "gFirstFloor_Bath"]
        ]
    }


}