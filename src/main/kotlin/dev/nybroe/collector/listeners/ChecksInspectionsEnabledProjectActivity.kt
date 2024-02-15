package dev.nybroe.collector.listeners

import com.intellij.openapi.components.service
import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.ProjectActivity
import dev.nybroe.collector.services.ChecksInspectionsEnabledService

class ChecksInspectionsEnabledProjectActivity : ProjectActivity {

    override suspend fun execute(project: Project) {
        project.service<ChecksInspectionsEnabledService>()
    }
}
