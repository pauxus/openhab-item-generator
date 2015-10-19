package com.blackbuild.openhab.generator
import com.blackbuild.openhab.generator.model.OpenHabConfig
import org.codehaus.groovy.control.CompilerConfiguration

File templatesDir = args[0] as File
File configFile = args[1] as File
File outputDir = args[2] as File

println "Reading config from $configFile"
println "Using templates from $templatesDir"
println "Writing to $outputDir"


OpenHabConfig config = new GroovyShell().run(configFile, []) as OpenHabConfig

outputDir.mkdirs()


def gcl = new GroovyClassLoader(Thread.currentThread().contextClassLoader, new CompilerConfiguration(scriptBaseClass: FileGenerator.name))

templatesDir.listFiles().findAll { it.name ==~ ~/.*\.groovy/ }.sort{ it.name }.each { it ->

    println "Running $it"
    FileGenerator script = gcl.parseClass(it).newInstance(config: config, outputDir: outputDir, generatorDir: templatesDir) as FileGenerator

    script.run()
}

