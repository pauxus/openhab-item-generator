import com.blackbuild.openhab.generator.model.HomeMaticItem

def display = item as HomeMaticItem

out.println """//-------------------------------------------- Status Display $display.parentGroup.label
Group $display.fullName "$display.parentGroup.label $display.name"  (${display.allGroupsAsString})"""

(1..8).each {
    out.println """String ${display.fullName}_L${it} {homematic="address=$display.address, channel=$it, parameter=LED_STATUS"}"""
}
(9..16).each {
    out.println """String ${display.fullName}_R${it - 8} {homematic="address=$display.address, channel=$it, parameter=LED_STATUS"}"""
}
