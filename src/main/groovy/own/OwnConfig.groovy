package own

import com.blackbuild.openhab.generator.model.FritzBoxThing
import com.blackbuild.openhab.generator.model.Group
import com.blackbuild.openhab.generator.model.NetatmoThing
import com.blackbuild.openhab.generator.model.OpenHabConfig
import com.blackbuild.openhab.generator.model.bridges.FritzAhaBridge
import com.blackbuild.openhab.generator.model.bridges.HomegearBridge
import com.blackbuild.openhab.generator.model.bridges.NetatmoBridge

import com.blackbuild.openhab.generator.model.homematic.HomeMaticHeating
import com.blackbuild.openhab.generator.model.homematic.HomeMaticThing
import com.blackbuild.openhab.generator.model.sonos.SonosPlayer

Group alle

HomeMaticHeating.createTemplate {
    fixValues(17, 20, 21)
}

OpenHabConfig.create {

    bridges {

        bridge(NetatmoBridge, "na") {
            clientId '55f86fbf45a1e38a4b8b4578'
            clientSecret 'inTUedfTGvaJXhpVZFLhcrBVydgH'
            username 'stephan@pauxus.de'
            password 'zBZFMP8BDbCLvEnvMoL'
        }

        bridge(HomegearBridge, "hg") {
            gatewayAddress "localhost"
            homegear()
        }

        bridge(FritzAhaBridge, "fritz") {
            ipAddress "192.168.1.2"
            password '1431micong00'
        }

        // hue: mprCQMrnZ6imZHfJnuQ3Pqnleo3iineNHnlLMTnb
    }

    elements {

        alle = element(Group, "Alle") {}
        element(Group, "Temperatur") { additionalGroup alle }
        element(Group, "Chart") { additionalGroup alle }
        element(Group, "Ventile") { additionalGroup alle }
        element(Group, "Feuchtigkeit") { additionalGroup alle }
        element(Group, "Warnungen") { additionalGroup alle }
        element(Group, "Fenster") { additionalGroup alle }
        element(Group, "Rauchmelder") { additionalGroup alle }
        element(Group, "Co2") { additionalGroup alle }
        element(Group, "maintenanceItems") { additionalGroup alle }
        element(Group, "Power") { additionalGroup alle }
        element(Group, "Energy") { additionalGroup alle }

        element(Group, "WB") {
            label "Wohnbereich"
            additionalGroups {
                additionalGroup alle
            }


            elements {
                element(HomeMaticThing, "Rauchmelder_Team_Neu") {
                    serial "T-NEQ0247900"
                    type "HM-Sec-SD-2-Team"
                }

                room("REB", "Rebecca") {

                    sonos("5CAAFD2197A0", SonosPlayer.SonosType.PLAY_1) {
                        messages("Kommst du bitte runter","Essen ist fertig","Ich habe Sofort gesagt","Kommst du bitte ins Bad?")
                    }

                    smokedetector "NEQ0248173"

                    elements {
                        element(NetatmoThing, "NARebecca") {
                            equipmentId "03:00:00:01:3a:80"
                            parentId "70:ee:50:06:75:f4"
                            type(NetatmoThing.Type.INDOOR)
                        }
                        heating {
                            thermostat { serial "HEQ0509781"; type "HM-CC-TC" }
                            window("Südfenster") { serial "MEQ0717740"; type "HM-Sec-SCo" }
                            valve("Heizkörper") { serial "HEQ0137974"; type "HM-CC-VD" }
                        }
                    }
                }


                room("MIC", "Michael") {

                    smokedetector "NEQ0243894"
                    sonos("5CAAFD2154C2", SonosPlayer.SonosType.PLAY_1) {
                        messages("Kommst du bitte runter","Essen ist fertig","Ich habe Sofort gesagt","Kommst du bitte ins Bad?")
                    }

                    elements {
                        element(NetatmoThing, "NAMichael") {
                            equipmentId "03:00:00:01:44:a0"
                            parentId "70:ee:50:06:75:f4"
                            type(NetatmoThing.Type.INDOOR)
                        }
                        heating {
                            thermostat { serial "IEQ0170661"; type "HM-CC-TC" }
                            window("Nordfenster") { serial "IEQ0032480"; type "HM-Sec-SC" }
                            valve("Ventil") { serial "IEQ0172061"; type "HM-CC-VD" }
                        }
                    }
                }
                room("BAD", "Badezimmer") {
                    elements {
                        heating {
                            thermostat { serial "HEQ0079972"; type "HM-CC-TC" }
                            window("Fenster") { serial "HEQ0106396"; type "HM-Sec-SC" }
                            valve("Ventil") { serial "FEQ0069155"; type "HM-CC-VD" }
                        }
                    }
                }

                room("WOZ", "Wohn-/Esszimmer") {

                    smokedetector "NEQ0243890"
                    sonos("5CAAFD0AAC36", SonosPlayer.SonosType.PLAY_5)

                    elements {
//                        element(HomeMaticItem, "Display") {
//                            type "HMOULED16"
//                            address "JEQ0218880"
//                        }
                    }
                    elements {
                        element(FritzBoxThing, "AVGeräte") {
                            ain "087610014130"
                        }

                        heating {
                            thermostat { serial "HEQ0080137"; type "HM-CC-TC" }
                            window("WZ-Fenster") { serial "IEQ0206565"; type "HM-Sec-RHS" }
                            window("EZ-Fenster") { serial "GEQ0128784"; type "HM-Sec-RHS" }
                            valve("WZ-Ventil") { serial "HEQ0081426"; type "HM-CC-VD" }
                            valve("EZ-Ventil") { serial "HEQ0081331"; type "HM-CC-VD" }
                        }
                    }
                }
                room("KUE", "Küche") {
                    sonos("5CAAFD242B6C", SonosPlayer.SonosType.PLAY_1)
                    // sonos("5CAAFD218098", SonosPlayer.SonosType.PLAY_1)
                }
                room("SPS", "Speisekammer") {
                    elements {
                        element(HomeMaticThing, "Thermostat") {
                            type "HM-WDS40-TH-I"
                            serial "JEQ0064360"
                        }
                    }
                }
                room("SZI", "Schlafzimmer") {
                    smokedetector "NEQ0247900"

                    elements {
                        element(NetatmoThing, "NASchlafzimmer") {
                            equipmentId "03:00:00:01:55:e2"
                            parentId "70:ee:50:06:75:f4"
                            type(NetatmoThing.Type.INDOOR)
                        }
                        heating {

                            thermostat { serial "IEQ0170436"; type "HM-CC-TC" }
                            window("Südfenster") { serial "IEQ0204643"; type "HM-Sec-SC" }
                            valve("Ventil") { serial "IEQ0172816"; type "HM-CC-VD" }
                        }
                    }
                }
                room("TRH", "Treppenhaus") {

                    smokedetector "NEQ0244388", "EG"
                    smokedetector "NEQ0248218", "OG"
                }
                room("GWC", "Gästetoilette") {
                    elements {
                        element(HomeMaticThing, "Fenster") {
                            serial "KEQ0159530"
                            type "HM-Sec-SC"
                        }
                    }
                }
            }
        }


        room("DG", "Büro") {
            additionalGroups {
                additionalGroup alle
            }

            sonos("5CAAFD2155E8", SonosPlayer.SonosType.PLAY_1) {
                messages("Kommst du bitte runter","Essen ist fertig","Ich habe Sofort gesagt","Kommst du bitte ins Bad?")
            }

            elements {
                element(NetatmoThing, "NADG") {
                    equipmentId "70:ee:50:06:75:f4"
                    type(NetatmoThing.Type.BASE)
                }

                element(FritzBoxThing, "Rechner") {
                    ain "087610039040"
                }

                heating {
                    thermostat { serial "HEQ0145776"; type "HM-CC-TC" }
                    window("Fenster") { serial "HEQ0106327"; type "HM-Sec-SC" }
                    valve("Ventil_L") { serial "HEQ0513531"; type "HM-CC-VD" }
                    valve("Ventil_R") { serial "HEQ0147527"; type "HM-CC-VD" }
                }
            }
        }
        element(Group, "UG") {
            additionalGroups {
                additionalGroup alle
            }
            label "Keller"

            elements {

                room("HZG", "Heizungskeller") {
                    elements {
                        element(HomeMaticThing, "Thermostat") {
                            type "HM-WDS40-TH-I"
                            serial "JEQ0218038"
                        }
                        element(FritzBoxThing, "Server") {
                            ain "087610049084"
                        }
                        element(FritzBoxThing, "Heizung") {
                            ain "087610200779"
                        }
                    }
                }
                room("VOR", "Vorratskeller") {

                    elements {
                        element(HomeMaticThing, "Thermostat") {
                            type "HM-WDS40-TH-I"
                            serial "JEQ0064575"
                        }
                    }

                }
                room("LGR", "Lagerkeller") {
                }
                room("WAK", "Waschküche") {
                    elements {
                        element(HomeMaticThing, "Trockner") {
                            type "HM-ES-PMSw1-Pl"
                            serial "MEQ0107141"
                        }
                        element(HomeMaticThing, "Waschmaschine") {
                            type "HM-ES-PMSw1-Pl"
                            serial "LEQ0530758"
                        }
                    }
                }
            }
        }

        element(Group, "OUT") {
            label "Draussen"
            additionalGroups {
                additionalGroup alle
            }
        }
    }


    elements {
//        element(HMLedDisplay, "Display") {
//            serial "JEQ0218880"
//            leftElements {
//                leftElement find(Room, "DG")
//                leftElement find(Room, "BAD")
//                leftElement find(Room, "MIC")
//                leftElement find(Room, "REB")
//                leftElement find(Room, "SZI")
//                leftElement find(Room, "GWC")
//                leftElement find(Room, "WOZ")
//                // leftElement find(Room, "KUE")
//            }
//        }
    }

}
