package own

import com.blackbuild.openhab.generator.model.Group
import com.blackbuild.openhab.generator.model.NetatmoThing
import com.blackbuild.openhab.generator.model.OpenHabConfig
import com.blackbuild.openhab.generator.model.Room
import com.blackbuild.openhab.generator.model.bridges.HomegearBridge
import com.blackbuild.openhab.generator.model.bridges.NetatmoBridge
import com.blackbuild.openhab.generator.model.homematic.HomeMaticHeating
import com.blackbuild.openhab.generator.model.homematic.HomeMaticThing

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

    }

    elements {

        alle = element(Group, "Alle") {}
        element(Group, "gTemperatur") { additionalGroup alle }
        element(Group, "gVentile") { additionalGroup alle }
        element(Group, "gFeuchtigkeit") { additionalGroup alle }
        element(Group, "gWarnungen") { additionalGroup alle }
        element(Group, "gFenster") { additionalGroup alle }
        element(Group, "gRauchmelder") { additionalGroup alle }

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

                element(Room, "REB") {

                    label "Rebecca"

                    elements {
                        element(NetatmoThing, "Rebecca") {
                            equipmentId "03:00:00:01:3a:80"
                            parentId "70:ee:50:06:75:f4"
                            type(NetatmoThing.Type.INDOOR)
                        }
                        element(HomeMaticHeating, "Heating") {
                            thermostat { serial "HEQ0509781"; type "HM-CC-TC" }
                            window("Südfenster") { serial "MEQ0717740"; type "HM-Sec-SCo" }
                            valve("Heizkörper") { serial "HEQ0137974"; type "HM-CC-VD" }
                        }
                        element(HomeMaticThing, "Rauchmelder") {
                            serial "NEQ0248173"
                            type "HM-Sec-SD-2"
                        }
                    }
                }


                element(Room, "MIC") {
                    label "Michael"

                    elements {
                        element(NetatmoThing, "Michael") {
                            equipmentId "03:00:00:01:44:a0"
                            parentId "70:ee:50:06:75:f4"
                            type(NetatmoThing.Type.INDOOR)
                        }
                        element(HomeMaticHeating, "Heating") {
                            thermostat { serial "IEQ0170661"; type "HM-CC-TC" }
                            window("Nordfenster") { serial "IEQ0032480"; type "HM-Sec-SC" }
                            valve("Heizkörper") { serial "IEQ0172061"; type "HM-CC-VD" }
                        }
                        element(HomeMaticThing, "Rauchmelder") {
                            serial "NEQ0243894"
                            type "HM-Sec-SD-2"
                        }
                    }
                }
                element(Room, "BAD") {
                    label "Badezimmer"
                    elements {
                        element(HomeMaticHeating, "Heating") {
                            thermostat { serial "HEQ0079972"; type "HM-CC-TC" }
                            window("Fenster") { serial "HEQ0106396"; type "HM-Sec-SC" }
                            valve("Heizkörper") { serial "FEQ0069155"; type "HM-CC-VD" }
                        }
                    }
                }

                element(Room, "WOZ") {
                    label "Wohn-/Esszimmer"

                    elements {
//                        element(HomeMaticItem, "Display") {
//                            type "HMOULED16"
//                            address "JEQ0218880"
//                        }
                    }
                    elements {
                        element(HomeMaticHeating, "Heating") {
                            thermostat { serial "HEQ0080137"; type "HM-CC-TC" }
                            window("WZ") { serial "IEQ0206565"; type "HM-Sec-RHS" }
                            window("EZ") { serial "GEQ0128784"; type "HM-Sec-RHS" }
                            valve("WZ") { serial "HEQ0081426"; type "HM-CC-VD" }
                            valve("EZ") { serial "HEQ0081331"; type "HM-CC-VD" }
                        }
                        element(HomeMaticThing, "Rauchmelder") {
                            serial "NEQ0243890"
                            type "HM-Sec-SD-2"
                        }
                    }
                }
                element(Room, "KUE") {
                    label "Küche"
                }
                element(Room, "SPS") {
                    label "Speisekammer"
                    elements {
                        element(HomeMaticThing, "Thermostat") {
                            type "HM-WDS40-TH-I"
                            serial "JEQ0064360"
                        }
                    }
                }
                element(Room, "SZI") {
                    label "Schlafzimmer"
                    elements {
                        element(HomeMaticHeating, "Heating") {

                            thermostat { serial "IEQ0170436"; type "HM-CC-TC" }
                            window("Südfenster") { serial "IEQ0204643"; type "HM-Sec-SC" }
                            valve("Heizkörper") { serial "IEQ0172816"; type "HM-CC-VD" }
                        }
                        element(HomeMaticThing, "Rauchmelder") {
                            serial "NEQ0247900"
                            type "HM-Sec-SD-2"
                        }
                    }
                }
                element(Room, "TRH") {
                    label "Treppenhaus"
                    elements {
                        element(HomeMaticThing, "Rauch_EG") {
                            serial "NEQ0244388"
                            type "HM-Sec-SD-2"
                        }
                        element(HomeMaticThing, "Rauch_OG") {
                            serial "NEQ0248218"
                            type "HM-Sec-SD-2"
                        }

                    }
                }
                element(Room, "GWC") {
                    label "Gästetoilette"
                    elements {
                        element(HomeMaticThing, "Fenster") {
                            serial "KEQ0159530"
                            type "HM-Sec-SC"
                        }
                    }
                }
            }
        }


        element(Room, "DG") {
            label "Büro"
            additionalGroups {
                additionalGroup alle
            }

            elements {
            }
            elements {
                element(HomeMaticHeating, "Heating") {
                    thermostat { serial "HEQ0145776"; type "HM-CC-TC" }
                    window("Rechts") { serial "HEQ0106327"; type "HM-Sec-SC" }
                    valve("Links") { serial "HEQ0513531"; type "HM-CC-VD" }
                    valve("Rechts") { serial "HEQ0147527"; type "HM-CC-VD" }
                }
            }
        }
        element(Group, "UG") {
            additionalGroups {
                additionalGroup alle
            }
            label "Keller"

            elements {

                element(Room, "HZG") {
                    label "Heizungskeller"
                    elements {
                        element(HomeMaticThing, "Thermostat") {
                            type "HM-WDS40-TH-I"
                            serial "JEQ0218038"
                        }
                    }
                }
                element(Room, "VOR") {
                    label "Vorratskeller"

                    elements {
                        element(HomeMaticThing, "Thermostat") {
                            type "HM-WDS40-TH-I"
                            serial "JEQ0064575"
                        }
                    }

                }
                element(Room, "LGR") {
                    label "Lagerkeller"
                }
                element(Room, "WAK") {
                    label "Waschküche"
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
