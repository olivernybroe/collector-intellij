package dev.nybroe.collector.types

import com.intellij.openapi.project.DumbService
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiElement
import com.jetbrains.php.PhpIndex
import com.jetbrains.php.lang.psi.elements.FieldReference
import com.jetbrains.php.lang.psi.elements.MethodReference
import com.jetbrains.php.lang.psi.elements.PhpNamedElement
import com.jetbrains.php.lang.psi.resolve.types.PhpType
import com.jetbrains.php.lang.psi.resolve.types.PhpTypeProvider4
import dev.nybroe.collector.collectionType
import dev.nybroe.collector.isHigherOrderCollection
import gnu.trove.THashSet

class HigherOrderTypeProvider : PhpTypeProvider4 {
    override fun getKey(): Char {
        return '\u0171'
    }

    override fun getType(psiElement: PsiElement): PhpType? {
        if (DumbService.isDumb(psiElement.project)) return null

        // Check that our current element is a field or method reference
        // $collection->map->data, $collection->map->data()
        val fieldOrMethodReference = psiElement as? FieldReference
            ?: psiElement as? MethodReference
            ?: return null

        // Check that parent is a field reference.
        // $collection->map
        val parentField = fieldOrMethodReference.classReference as? FieldReference ?: return null

        return PhpType().add("#${this.key}${parentField.type}")
    }

    override fun complete(s: String, project: Project): PhpType? {
        return null
    }

    /**
     * Here you can extend the signature lookups
     * @param expression Signature expression to decode. use PhpIndex.getBySignature() to look up expression internals.
     * @param visited Recursion guard: Pass this on into any phpIndex calls having same parameter
     * @param depth Recursion guard: Pass this on into any phpIndex calls having same parameter
     * @param project well so you can reach the PhpIndex
     * @return null if no match
     */
    override fun getBySignature(
        expression: String,
        visited: MutableSet<String>,
        depth: Int,
        project: Project
    ): MutableCollection<out PhpNamedElement>? {
        // Remove key from signature
        val signature = expression.removePrefix("#${key}")

        // Resolve type of the signature.
        val type = PhpIndex.getInstance(project)
            .getBySignature(signature.split('|')[0])
            .map { it.type }
            .reduceOrNull { acc, phpType -> acc.add(phpType) }
            ?.global(project)

        if (type === null) return null

        if (!type.isHigherOrderCollection(project)) return null

        return collectionType.types
            .flatMap { PhpIndex.getInstance(project).getClassesByFQN(it.toString()) }
            .toCollection(THashSet())
    }
}
