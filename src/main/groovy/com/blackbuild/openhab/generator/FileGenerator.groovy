package com.blackbuild.openhab.generator
import com.blackbuild.openhab.generator.model.OpenHabConfig
import groovy.text.GStringTemplateEngine
import groovy.text.Template

abstract class FileGenerator extends Script {

    OpenHabConfig config

    File outputDir
    File generatorDir

    List<String> modifiedFiles = []
    GStringTemplateEngine templateEngine = new GStringTemplateEngine()

    static String surround(String before, String value, String after) {
        if (value)
            "$before$value$after"
        else
            ""
    }

    @Override
    Object run() {
        doRun()
        if (!modifiedFiles) log "WARNING: no output generated!"
    }

    def abstract doRun()

    static String surround(String value) {
        value.length() > 2 ? value : ""
    }

    static String asIdentifier(String value) {
        value.replace(" ", "").replaceAll(/\W+/, "")
    }

    def log(Object message) {
        println(message)
    }

    def into(String name, String marker, Closure actions) {
        def out = new StringWriter()

        actions.call(out)

        insertIntoFile(new File(outputDir, name), out.toString(), marker)

        modifiedFiles << name
    }

    def into(String name, Closure actions) {
        into(name, getClass().simpleName, actions)
    }

    private static void insertIntoFile(File file, String text, String marker) {
        if (!file.parentFile.isDirectory())
            file.parentFile.mkdirs()
        List<String> lines = file.isFile() ? file.readLines("UTF-8") : []

        createBackupFile(file)

        List<String> before
        List<String> after

        int indexOfBlockBegin = lines.findIndexOf { it =~ "GENERATED $marker"  }
        int indexOfBlockEnd = lines.findIndexOf { it =~ "END $marker"  }

        if (indexOfBlockBegin < 0) {
            before = lines
            after = []
        } else {
            if (indexOfBlockEnd < 0)
                throw new IllegalStateException("Generator $file.name has 'GENERATED $marker', but no matchin 'END $marker'")

            before = indexOfBlockBegin > 0 ? lines[0..(indexOfBlockBegin - 1)] : []
            after = indexOfBlockEnd + 1 == lines.size() ? [] : lines[(indexOfBlockEnd + 1)..-1]
        }

        file.withWriter { out ->
            before.each { out.println it }
            out.println "// GENERATED $marker ${new Date()}"
            out.println text
            out.println "// END $marker"
            after.each { out.println it }
        }
    }

    private static void createBackupFile(File file) {
        if (!file.isFile()) return
        File backup = new File(file.parent, file.name + ".bak")
        backup.text = file.text
    }

    Template getTemplate(String name = getClass().simpleName + ".template") {
        templateEngine.createTemplate(new File(generatorDir, name))
    }
}
