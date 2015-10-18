package com.blackbuild.openhab.generator

import com.blackbuild.openhab.generator.model.OpenHabConfig

File templatesDir = args[0] as File
File configFile = args[1] as File
File outputDir = args[2] as File

println "Reading config from $configFile"
println "Using templates from $templatesDir"
println "Writing to $outputDir"


OpenHabConfig config = new GroovyShell().run(configFile, []) as OpenHabConfig

SimpleItemsDumper v = new SimpleItemsDumper(templatesDir)

config.accept(v)

v.outputs.each { category, text ->
    println "--------- $category ---------"
    println text

}






