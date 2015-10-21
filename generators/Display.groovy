import com.blackbuild.openhab.generator.model.homematic.HMLedDisplay

into("items/homematic.items") { out ->

    config.all(HMLedDisplay).each { display ->
        out.println """//-------------------------------------------- Display $display.description
Group $display.fullName "${display.label ?: $display.name}" (${display.allGroupsAsString})"""

        if (display.leftElements) (1..display.leftElements.size()).each {
            out.println """String ${display.fullName}_L${it} {homematic="address=$display.address, channel=$it, parameter=LED_STATUS"}"""
        }
        if (display.rightElements) (9..(display.rightElements.size() + 8)).each {
            out.println """String ${display.fullName}_R${it - 8} {homematic="address=$display.address, channel=$it, parameter=LED_STATUS"}"""
        }
    }
}

/**
into("rules/display.rules") { out ->

    config.all(HMLedDisplay).each { display ->
    }
}
 */