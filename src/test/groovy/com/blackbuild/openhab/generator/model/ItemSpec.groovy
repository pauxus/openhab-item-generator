package com.blackbuild.openhab.generator.model

import com.blackbuild.openhab.generator.model.homematic.HomeMaticHeating
import spock.lang.Ignore
import spock.lang.Specification


class ItemSpec extends Specification {

    @Ignore
    def "HomeMaticHeating configurations"() {
        when:
        def item = HomeMaticHeating.create("bla"){
            fixValues(17, 20, 21)
        }

        then:
        item.fixValuesAsMap == ['17,0', '17,0°C']

    }


}