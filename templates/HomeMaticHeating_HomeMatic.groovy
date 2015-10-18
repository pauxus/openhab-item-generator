import com.blackbuild.openhab.generator.model.HomeMaticHeating

def heating = item as HomeMaticHeating

out.println """//-------------------------------------------- Heizung $heating.parentGroup.label
Group ${heating.fullName} "$heating.parentGroup.label $heating.name" (${heating.allGroupsAsString})
Number ${heating.fullName}_Temp      "Temperatur $heating.parentGroup.label [%.1f °C]"                           <temperature>  (${heating.fullName},gTemperatur)   {homematic="address=$heating.thermostat, channel=1, parameter=TEMPERATURE"}
Number ${heating.fullName}_Set       "Soll-Temperatur $heating.parentGroup.label [%.1f °C]"                      <temperature>  (${heating.fullName},gTemperatur)   {homematic="address=$heating.thermostat, channel=2, parameter=SETPOINT"}
Number ${heating.fullName}_Humid     "Feuchtigkeit $heating.parentGroup.label [%d %%]"                           <water>        (${heating.fullName},gFeuchtigkeit) {homematic="address=$heating.thermostat, channel=1, parameter=HUMIDITY"}
Number ${heating.fullName}_Rssi      "RSSI Thermostat $heating.parentGroup.label [SCALE(rssi.scale):%s]"         <signal>       (${heating.fullName},gWarnungen)    {homematic="address=$heating.thermostat, channel=0, parameter=RSSI_DEVICE"}
Dimmer ${heating.fullName}_Rssi_perc "RSSI Thermostat $heating.parentGroup.label [%d %%]"                        <signal>       (${heating.fullName},gWarnungen)
String ${heating.fullName}_LowBat    "Batterie Thermostat $heating.parentGroup.label [MAP(lowbat.map):%s]"       <battery>      (${heating.fullName},gWarnungen)    {homematic="address=$heating.thermostat, channel=0, parameter=LOWBAT"}
String ${heating.fullName}_Pending   "Pending Thermostat $heating.parentGroup.label [MAP(configpending.map):%s]" <water>        (${heating.fullName},gWarnungen)    {homematic="address=$heating.thermostat, channel=0, parameter=CONFIG_PENDING"}
"""

heating.windows.each {out.println """
String  ${heating.fullName}_${safe(it.key)}_Kontakt "$it.key $heating.parentGroup.label [MAP(window.map):%s]"              <contact> (${heating.fullName},gFenster)   {homematic="address=${it.value}, channel=1, parameter=STATE"}
String  ${heating.fullName}_${safe(it.key)}_LowBat  "$it.key $heating.parentGroup.label Batterie [MAP(lowbat.map):%s]"     <battery> (gWarnungen)                     {homematic="address=${it.value}, channel=1, parameter=LOWBAT"}
String  ${heating.fullName}_${safe(it.key)}_Error   "$it.key $heating.parentGroup.label Sabotage [MAP(MDirError.map):%s]"  <contact> (gWarnungen)                     {homematic="address=${it.value}, channel=1, parameter=ERROR"}
"""}

heating.valves.each {out.println """
Dimmer ${heating.fullName}_${safe(it.key)} "$it.key $heating.parentGroup.label [%d %%]" <heating> (${heating.fullName},gVentile) {homematic="address=${it.value}, channel=1, parameter=VALVE_STATE"}
"""}