package dev.nybroe.collector

import com.intellij.openapi.project.Project
import com.jetbrains.php.PhpIndex
import com.jetbrains.php.lang.psi.elements.Function
import com.jetbrains.php.lang.psi.elements.FunctionReference
import com.jetbrains.php.lang.psi.elements.Method
import com.jetbrains.php.lang.psi.elements.MethodReference
import com.jetbrains.php.lang.psi.elements.PhpReference
import com.jetbrains.php.lang.psi.elements.impl.FunctionImpl
import com.jetbrains.php.lang.psi.resolve.types.PhpType

fun FunctionReference.isGlobalFunctionCallWithName(name: String): Boolean {
    return this.name == name
}

private val collectionClasses = listOf(
    "\\Illuminate\\Support\\Collection",
    "\\Illuminate\\Support\\Traits\\EnumeratesValues",
)

private val collectionType = PhpType().apply {
    collectionClasses.forEach { this.add(it) }
}

val Method.isCollectionMethod: Boolean
    get() = this.containingClass?.type?.isCollection(this.project) ?: false

val MethodReference.isCollectionMethod: Boolean
    get() = (this.resolve() as? Method)?.isCollectionMethod ?: false

val Function.returnsCollection: Boolean
    get() = this.type.global(this.project).isCollection(this.project)

val FunctionReference.returnsCollection: Boolean
    get() = (this.resolve() as? Function)?.returnsCollection ?: false

val PhpReference.isCollectionType: Boolean
    get() = this.type.global(this.project).isCollection(this.project)

val Function.isShortArrowFunction: Boolean
    get() = FunctionImpl.isShortArrowFunction(this)

fun PhpType.isCollection(project: Project): Boolean {
    return collectionType.isConvertibleFrom(this, PhpIndex.getInstance(project))
}
