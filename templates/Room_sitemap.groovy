import com.blackbuild.openhab.generator.model.HomeMaticHeating
import com.blackbuild.openhab.generator.model.Room

Room room = item as Room

out.println """
Text label="$room.label" icon="attic" {"""

HomeMaticHeating heating = room.items.find { it instanceof HomeMaticHeating } as HomeMaticHeating

if (heating) {
    out.println """
    Text item=${heating.fullName}_Temp
    Setpoint item=${heating.fullName}_Set minValue=5 maxValue=28 step=0.5"""

    heating.windows.each {
        out.println "    Text item=${heating.fullName}_${safe(it.key)}_Kontakt"
    }

    heating.valves.each {
        out.println "    Text item=${heating.fullName}_${safe(it.key)}_pos"
    }
}

out.println "}"



