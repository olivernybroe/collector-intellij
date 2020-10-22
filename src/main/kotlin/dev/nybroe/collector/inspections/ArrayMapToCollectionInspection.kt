package dev.nybroe.collector.inspections

import com.intellij.codeInspection.ProblemsHolder
import com.intellij.psi.PsiElementVisitor
import com.intellij.util.ObjectUtils
import com.jetbrains.php.lang.PhpLangUtil
import com.jetbrains.php.lang.inspections.PhpInspection
import com.jetbrains.php.lang.psi.elements.Function
import com.jetbrains.php.lang.psi.elements.FunctionReference
import com.jetbrains.php.lang.psi.visitors.PhpElementVisitor

class ArrayMapToCollectionInspection : PhpInspection() {
    override fun buildVisitor(holder: ProblemsHolder, isOnTheFly: Boolean): PsiElementVisitor {
        return object : PhpElementVisitor() {
            override fun visitPhpFunctionCall(reference: FunctionReference) {
                val function = (reference.resolve() ?: return) as Function

                if (!PhpLangUtil.isGlobalNamespaceFQN(function.namespaceName)) {
                    return
                }

                if (function.name !== "array_map") {
                    return
                }

                holder.registerProblem(

                )
            }
        }
    }
}