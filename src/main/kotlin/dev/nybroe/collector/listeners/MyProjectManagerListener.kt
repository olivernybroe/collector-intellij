package dev.nybroe.collector.listeners

import com.intellij.openapi.project.Project
import com.intellij.openapi.project.ProjectManagerListener
import dev.nybroe.collector.services.MyProjectService

internal class MyProjectManagerListener : ProjectManagerListener {
    override fun projectOpened(project: Project) {
        project.getService(MyProjectService::class.java)
    }
}
