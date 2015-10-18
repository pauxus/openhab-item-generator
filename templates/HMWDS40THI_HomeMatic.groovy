import com.blackbuild.openhab.generator.model.HomeMaticItem

def thermostat = item as HomeMaticItem

out.println """//-------------------------------------------- Thermostat $thermostat.parentGroup.label
Group ${thermostat.fullName} "$thermostat.parentGroup.label $thermostat.name" (${thermostat.allGroupsAsString})
Number ${thermostat.fullName}_Temp      "Temperatur $thermostat.parentGroup.label [%.1f °C]"                           <temperature>  (${thermostat.fullName},gTemperatur)   {homematic="address=$thermostat.address, channel=1, parameter=TEMPERATURE"}
Number ${thermostat.fullName}_Humid     "Feuchtigkeit $thermostat.parentGroup.label [%d %%]"                           <water>        (${thermostat.fullName},gFeuchtigkeit) {homematic="address=$thermostat.address, channel=1, parameter=HUMIDITY"}
Number ${thermostat.fullName}_Rssi      "RSSI Thermostat $thermostat.parentGroup.label [SCALE(rssi.scale):%s]"         <signal>       (${thermostat.fullName},gWarnungen)    {homematic="address=$thermostat.address, channel=0, parameter=RSSI_DEVICE"}
Dimmer ${thermostat.fullName}_Rssi_perc "RSSI Thermostat $thermostat.parentGroup.label [%d %%]"                        <signal>       (${thermostat.fullName},gWarnungen)
String ${thermostat.fullName}_LowBat    "Batterie Thermostat $thermostat.parentGroup.label [MAP(lowbat.map):%s]"       <battery>      (${thermostat.fullName},gWarnungen)    {homematic="address=$thermostat.address, channel=0, parameter=LOWBAT"}
String ${thermostat.fullName}_Pending   "Pending Thermostat $thermostat.parentGroup.label [MAP(configpending.map):%s]" <water>        (${thermostat.fullName},gWarnungen)    {homematic="address=$thermostat.address, channel=0, parameter=CONFIG_PENDING"}
"""
