import com.blackbuild.openhab.generator.model.HomeMaticItem

def plug = item as HomeMaticItem

out.println """//-------------------------------------------- Steckdose $plug.name
Group ${plug.fullName} "Schalter $plug.name" (${plug.allGroupsAsString})
Switch ${plug.fullName}_Steckdose    "$plug.name Steckdose"             <switch> (${plug.fullName})         {homematic="address=${plug.address}, channel=1, parameter=STATE"}
Number ${plug.fullName}_Leistung     "$plug.name Leistung [%.1f W]"     <switch> (${plug.fullName}, Power)  {homematic="address=${plug.address}, channel=2, parameter=POWER"}
Number ${plug.fullName}_Strom        "$plug.name Strom [%.1f A]"        <switch> (${plug.fullName})         {homematic="address=${plug.address}, channel=2, parameter=CURRENT"}
Number ${plug.fullName}_Spannung     "$plug.name Spannung  [%.1f V]"    <switch> (${plug.fullName})         {homematic="address=${plug.address}, channel=2, parameter=VOLTAGE"}
Number ${plug.fullName}_Frequenz     "$plug.name Frequenz  [%.2f Hz]"   <switch> (${plug.fullName})         {homematic="address=${plug.address}, channel=2, parameter=FREQUENCY"}
Number ${plug.fullName}_Zaehler      "$plug.name Verbrauch  [%.1f Wh]"  <switch> (${plug.fullName},Energy)  {homematic="address=${plug.address}, channel=2, parameter=ENERGY_COUNTER"}
"""
