package phonebook.search.engines

import phonebook.Person
import phonebook.Type
import java.lang.Exception

open abstract class SearchEngine() {

    object Factory {
        fun get(type: Type): SearchEngine {
            return when (type) {
                Type.TYPE_LINEAR_SEARCH -> LinearSearchEngine()
                Type.TYPE_JUMP_SEARCH -> JumpSearchEngine()
                Type.TYPE_BINARY_SEARCH -> BinarySearchEngine()
                Type.TYPE_HASH_SEARCH -> HashTableSearchEngine()
                else -> throw Exception("Wrong method")
            }
        }
    }

    open abstract fun invoke(source: MutableList<Person>, items: List<String>): Int
}