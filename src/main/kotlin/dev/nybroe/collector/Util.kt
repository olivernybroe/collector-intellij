package dev.nybroe.collector

import com.jetbrains.php.lang.psi.elements.Function
import com.jetbrains.php.lang.psi.elements.FunctionReference
import com.jetbrains.php.lang.psi.elements.Method
import com.jetbrains.php.lang.psi.elements.MethodReference
import com.jetbrains.php.lang.psi.elements.PhpClass
import com.jetbrains.php.lang.psi.elements.PhpReference
import com.jetbrains.php.lang.psi.elements.impl.FunctionImpl
import com.jetbrains.php.lang.psi.resolve.types.PhpType

fun FunctionReference.isGlobalFunctionCallWithName(name: String): Boolean {
    return this.name == name
}

val PhpClass.isCollectionClass: Boolean
    get() = this.fqn == "\\Illuminate\\Support\\Collection"

val Method.isCollectionMethod: Boolean
    get() = this.containingClass?.isCollectionClass ?: false

val MethodReference.isCollectionMethod: Boolean
    get() = (this.resolve() as? Method)?.isCollectionMethod ?: false

val Function.returnsCollection: Boolean
    get() = this.type.global(this.project).isCollection

val FunctionReference.returnsCollection: Boolean
    get() = (this.resolve() as? Function)?.returnsCollection ?: false

val PhpReference.isCollectionType: Boolean
    get() = this.type.global(this.project).isCollection

val Function.isShortArrowFunction: Boolean
    get() = FunctionImpl.isShortArrowFunction(this)

val PhpType.isCollection: Boolean
    get() = this.types.contains("\\Illuminate\\Support\\Collection")
