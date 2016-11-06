import com.blackbuild.openhab.generator.model.BridgelessThing


config.bridges.each { bridge ->
    into("things/${bridge.namespace}.things") { out ->
        out.println bridge.definition
    }
}

into("thing/bridgeless.things") { out ->
    out.println "// Bridgeless things"

    config.all(BridgelessThing).each { thing ->
        out.println thing.definition
    }

}
