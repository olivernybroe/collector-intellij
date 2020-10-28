package dev.nybroe.collector

import com.jetbrains.php.lang.psi.elements.FunctionReference
import com.jetbrains.php.lang.psi.elements.Method
import com.jetbrains.php.lang.psi.elements.MethodReference
import com.jetbrains.php.lang.psi.elements.PhpClass

fun FunctionReference.isGlobalFunctionCallWithName(name: String): Boolean {
    return this.name == name
}

fun PhpClass.isCollectionClass(): Boolean {
    return this.fqn == "\\Illuminate\\Support\\Collection"
}

fun Method.isCollectionMethod(): Boolean {
    return this.containingClass?.isCollectionClass() ?: false
}

fun MethodReference.isCollectionMethod(): Boolean {
    return (this.resolve() as? Method)?.isCollectionMethod() ?: false
}
