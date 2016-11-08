package templates

import com.blackbuild.openhab.generator.model.FritzBoxThing
import groovy.transform.TupleConstructor

@TupleConstructor
class FritzBoxTemplate extends AbstractTemplate {

    @Delegate
    FritzBoxThing thing



}
