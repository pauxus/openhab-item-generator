package own

import com.blackbuild.openhab.generator.model.Group
import com.blackbuild.openhab.generator.model.NetatmoThing
import com.blackbuild.openhab.generator.model.OpenHabConfig
import com.blackbuild.openhab.generator.model.Room
import com.blackbuild.openhab.generator.model.bridges.HomegearBridge
import com.blackbuild.openhab.generator.model.bridges.NetatmoBridge
import com.blackbuild.openhab.generator.model.homematic.HomeMaticHeating
import com.blackbuild.openhab.generator.model.homematic.HomeMaticThing

Group alle = Group.create("Alle") {}

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
        }

    }

    groups {

        group(alle)

        group("WB") {
            label "Wohnbereich"
            additionalGroups {
                additionalGroup alle
            }


            groups {
                group(Room, "REB") {

                    label "Rebecca"

                    things {
                        thing(NetatmoThing, "Rebecca") {
                            equipmentId "03:00:00:01:3a:80"
                            parentId "70:ee:50:06:75:f4"
                            type(NetatmoThing.Type.INDOOR)
                        }
                    }
                    groups {
                        group(HomeMaticHeating, "Heating") {
                            thermostat { serial "HEQ0509781"; type "HM-CC-TC" }
                            window("Südfenster") { serial "JEQ0719115"; type "HM-Sec-SCo" }
                            valve("Heizkörper") { serial "HEQ0137974"; type "HM-CC-VD" }
                        }
                    }
                }


                group(Room, "MIC") {
                    label "Michael"

                    things {
                        thing(NetatmoThing, "NetAtmo") {
                            equipmentId "03:00:00:01:44:a0"
                            parentId "70:ee:50:06:75:f4"
                            type(NetatmoThing.Type.INDOOR)
                        }
                    }
                    groups {
                        group(HomeMaticHeating, "Heating") {
                            thermostat { serial "IEQ0170661"; type "HM-CC-TC" }
                            window("Nordfenster") { serial "IEQ0032480"; type "HM-Sec-SC" }
                            valve("Heizkörper") { serial "IEQ0172061"; type "HM-CC-VD" }
                        }
                    }
                }
                group(Room, "BAD") {
                    label "Badezimmer"
                    things {
                    }
                    groups {
                        group(HomeMaticHeating, "Heating") {
                            thermostat { serial "HEQ0079972"; type "HM-CC-TC" }
                            window("Fenster") { serial "HEQ0106396"; type "HM-Sec-SC" }
                            valve("Heizkörper") { serial "FEQ0069155"; type "HM-CC-VD" }
                        }
                    }
                }

                group(Room, "WOZ") {
                    label "Wohn-/Esszimmer"

                    things {
//                        thing(HomeMaticItem, "Display") {
//                            type "HMOULED16"
//                            address "JEQ0218880"
//                        }
                    }
                    groups {
                        group(HomeMaticHeating, "Heating") {
                            thermostat { serial "HEQ0079972"; type "HM-CC-TC" }
                            window("WZ") { serial "IEQ0206565"; type "HM-Sec-RHS" }
                            window("EZ") { serial "GEQ0128784"; type "HM-Sec-RHS" }
                            valve("WZ") { serial "HEQ0081426"; type "HM-CC-VD" }
                            valve("EZ") { serial "HEQ0081331"; type "HM-CC-VD" }
                        }
                    }
                }
                group(Room, "KUE") {
                    label "Küche"
                }
                group(Room, "SPS") {
                    label "Speisekammer"
                    things {
                    }
                    groups {
                        thing(HomeMaticThing, "Thermostat") {
                            type "HM-WDS40-TH-I"
                            serial "JEQ0064360"
                        }
                    }
                }
                group(Room, "SZI") {
                    label "Schlafzimmer"
                    things {
                    }
                    groups {
                        group(HomeMaticHeating, "Heating") {

                            thermostat { serial "IEQ0170436"; type "HM-CC-TC" }
                            window("Südfenster") { serial "IEQ0204643"; type "HM-Sec-SC" }
                            valve("Heizkörper") { serial "IEQ0172816"; type "HM-CC-VD" }
                        }
                    }
                }
                group(Room, "TRH") {
                    label "Treppenhaus"
                }
                group(Room, "GWC") {
                    label "Gästetoilette"
                }
            }
        }


        group(Room, "DG") {
            label "Büro"
            additionalGroups {
                additionalGroup alle
            }

            things {
            }
            groups {
                group(HomeMaticHeating, "Heating") {
                    thermostat { serial "HEQ0145776"; type "HM-CC-TC" }
                    window("Rechts") { serial "HEQ0106327"; type "HM-Sec-SC" }
                    valve("Links") { serial "HEQ0513531"; type "HM-CC-VD" }
                    valve("Rechts") { serial "HEQ0147527"; type "HM-CC-VD" }
                }
            }
        }
        group("UG") {
            additionalGroups {
                additionalGroup alle
            }
            label "Keller"

            groups {

                group(Room, "HZG") {
                    label "Heizungskeller"
                    things {
                        thing(HomeMaticThing, "Thermostat") {
                            type "HMWDS40THI"
                            serial "JEQ0218038"
                        }
                    }
                }
                group(Room, "VOR") {
                    label "Vorratskeller"

                    things {
                        thing(HomeMaticThing, "Thermostat") {
                            type "HMWDS40THI"
                            serial "JEQ0064575"
                        }
                    }

                }
                group(Room, "LGR") {
                    label "Lagerkeller"
                }
                group(Room, "WAK") {
                    label "Waschküche"
                    things {
                        thing(HomeMaticThing, "Trockner") {
                            type "HM-ES-PMSw1-Pl"
                            serial "MEQ0107141"
                        }
                        thing(HomeMaticThing, "Waschmaschine") {
                            type "HM-ES-PMSw1-Pl"
                            serial "LEQ0530758"
                        }
                    }
                }
            }
        }

        group("OUT") {
            label "Draussen"
            additionalGroups {
                additionalGroup alle
            }
        }
    }


    things {
//        thing(HMLedDisplay, "Display") {
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
