package com.blackbuild.openhab.generator

import com.blackbuild.openhab.generator.model.OpenHabElement

interface Visitor {

    def visit(OpenHabElement element);
}
