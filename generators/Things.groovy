into("things/generated.things") { out ->

    config.bridges.each { bridge ->
        out.println bridge.definition

    }
}

