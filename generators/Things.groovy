import com.blackbuild.openhab.generator.model.BridgelessThing

into("things/generated.things") { out ->

    config.bridges.each { bridge ->
        out.println bridge.definition
    }

    out.println "// Bridgeless things"

    config.all(BridgelessThing).each { thing ->
        out.println thing.definition
    }

}

