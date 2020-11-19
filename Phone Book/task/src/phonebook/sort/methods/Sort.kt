package phonebook.sort.methods

import phonebook.Person
import phonebook.Type
import java.lang.Exception

open abstract class Sort {

    object Factory {
        fun get(type: Type): Sort {
            return when (type) {
                Type.TYPE_BUBBLE_SORT -> BubbleSort()
                Type.TYPE_QUICK_SORT -> QuickSort()
                else -> throw Exception("Wrong method")
            }
        }
    }

    fun swap(source: MutableList<Person>, first: Int, second: Int) {
        source[first] = source[second].also { source[second] = source[first] }
    }

    open abstract fun sort(input: MutableList<Person>)
}