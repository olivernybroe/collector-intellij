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

val collectionType = PhpType().apply {
    collectionClasses.forEach { this.add(it) }
}

private val higherOrderCollectionType = PhpType().apply {
    this.add("\\Illuminate\\Support\\HigherOrderCollectionProxy")
}

val Method.isCollectionMethod: Boolean
    get() = this.containingClass?.type?.isCollection(this.project) ?: false

val MethodReference.isCollectionMethod: Boolean
    get() = (this.resolve() as? Method)?.isCollectionMethod ?: false

val PhpReference.isCollectionType: Boolean
    get() = this.type.global(this.project).isCollection(this.project)

val PhpReference.isCollectionTypeStrict: Boolean
    get() = this.type.global(this.project).isCollectionStrict(this.project)

val Function.isShortArrowFunction: Boolean
    get() = FunctionImpl.isShortArrowFunction(this)

fun PhpType.isCollection(project: Project): Boolean {
    val filteredType = this.filterMixed().filterUnknown()

    if (filteredType.isEmpty) {
        return false
    }

    return collectionType.isConvertibleFrom(
        filteredType,
        PhpIndex.getInstance(project)
    )
}

fun PhpType.isCollectionStrict(project: Project): Boolean {
    if (this.types.isEmpty()) {
        return false
    }

    return this.types.none { !PhpType().add(it).isCollection(project) }
}

fun PhpType.isHigherOrderCollection(project: Project): Boolean {
    val filteredType = this.filterMixed()

    if (filteredType.isEmpty) {
        return false
    }

    return higherOrderCollectionType.isConvertibleFrom(
        filteredType,
        PhpIndex.getInstance(project)
    )
}
