package typechecker.pass

import common.Name
import data.question.Question
import java.util.HashMap
import kotlin.collections.HashSet

class DuplicatePass : TypeCheckerPass {

    fun findDuplicateLabels(table: HashMap<Name, Question>): HashSet<String> {

        val uniqueLabels = HashSet<String>()

        val duplicateLabels = HashSet<String>()

        table.forEach { _, question ->
            if (!uniqueLabels.add(question.label)) {
                duplicateLabels.add(question.label)
            }
        }

        duplicateLabels.forEach { label ->
            println("Warning duplicate label: $label")
        }

        return duplicateLabels
    }

}