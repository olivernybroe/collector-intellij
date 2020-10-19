package com.github.olivernybroe.collectionsintellij.services

import com.intellij.openapi.project.Project
import com.github.olivernybroe.collectionsintellij.MyBundle

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
