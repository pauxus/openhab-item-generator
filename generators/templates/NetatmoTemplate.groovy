package templates

import com.blackbuild.openhab.generator.model.NetatmoThing
import groovy.transform.TupleConstructor
/**
 * Created by stephan on 06.11.2016.
 */
@TupleConstructor
class NetatmoTemplate extends AbstractTemplate {

    @Delegate
    NetatmoThing thing

}
