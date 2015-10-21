import com.blackbuild.openhab.generator.model.homematic.HomeMaticHeating
import com.blackbuild.openhab.generator.model.OpenHabConfig

OpenHabConfig.create {

    groups {

        group("DG") {

            items {
                item(HomeMaticHeating, "WZ") {

                }
            }


        }



    }




}

