import com.blackbuild.openhab.generator.model.HomeMaticHeating
import com.blackbuild.openhab.generator.model.OpenHabConfig

OpenHabConfig.create {

    groups {

        group("DG") {

            items {
                item("") {

                }


                item(HomeMaticHeating, "WZ") {

                }
            }


        }



    }




}

