package com.blackbuild.openhab.generator

import com.blackbuild.openhab.generator.model.OpenHabElement
import groovy.io.FileType
import groovy.text.GStringTemplateEngine
import groovy.text.Template
import org.codehaus.groovy.control.CompilerConfiguration

class SimpleItemsDumper implements Visitor {

    private final static String DEFAULT_TEMPLATE_NAME = "default"

    Map<String, StringWriter> outputs = [:].withDefault { new StringWriter() }
    Map<String, Map<String, Class<? extends TemplateScript>>> groovyTemplates = [:].withDefault { [:] }
    Map<String, Map<String, Template>> textTemplates = [:].withDefault { [:] }

    SimpleItemsDumper(File templateDir) {
        readTemplates(templateDir)
    }

    def readTemplates(File templateDir) {

        def gcl = new GroovyClassLoader(Thread.currentThread().contextClassLoader, new CompilerConfiguration(scriptBaseClass: TemplateScript.name))
        def engine = new GStringTemplateEngine()

        templateDir.eachFile(FileType.FILES) {

            def m = it.name =~ /(\w+)_(\w+)\.groovy/

            if (m) {
                def (_, template, category) = m[0]
                groovyTemplates[template][category] = gcl.parseClass(it)
                println "adding groovy template: $it"
                return
            }

            m = it.name =~ /(\w+)_(\w+)\.template/

            if (m) {
                def (_, template, category) = m[0]
                textTemplates[template][category] = engine.createTemplate(it)
                println "adding text template: $it"
                return
            }

            println "Skipping unknown file type : $it"
        }
    }

    @Override
    def visit(OpenHabElement element) {
        println "Handling $element.fullName ($element.template)"

        int appliedGroovyTemplates = 0
        int appliedTextTemplates = 0

        groovyTemplates[element.template].each { category, templateScript ->
            println "  Groovy::$category"
            appliedGroovyTemplates++
            templateScript.newInstance(item: element, out: outputs[category]).run()
        }
        textTemplates[element.template].each { category, template ->
            println "  Text::$category"
            appliedTextTemplates++
            outputs[category].println template.make(item: element)
        }

        if (appliedGroovyTemplates + appliedTextTemplates == 0)
            println "WARNING: No templates found for $element.fullName ($element.template)"
    }
}
