package dev.nybroe.collector

import com.jetbrains.php.lang.psi.elements.FunctionReference

fun FunctionReference.isGlobalFunctionCallWithName(name: String): Boolean {
    return this.name == name
}
