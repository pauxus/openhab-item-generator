import com.blackbuild.openhab.generator.model.OpenHabConfig
import com.blackbuild.openhab.generator.model.homematic.HMLedDisplay
import com.blackbuild.openhab.generator.model.homematic.HomeMaticHeating


def a = new HMLedDisplay("bla")

OpenHabConfig.create {

    groups {

        group("DG") {

            items {
                item(HomeMaticHeating, "WZ") {

                }
                item(HMLedDisplay, "bla") {

                }
            }


        }



    }




}

