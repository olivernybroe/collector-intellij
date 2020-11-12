package dev.nybroe.collector.listeners

import com.intellij.openapi.components.service
import com.intellij.openapi.project.Project
import com.intellij.openapi.project.ProjectManagerListener
import dev.nybroe.collector.services.ChecksInspectionsEnabledService

class ChecksInspectionsEnabledProjectManagerListener : ProjectManagerListener {
    override fun projectOpened(project: Project) {
        project.service<ChecksInspectionsEnabledService>()
    }
}
