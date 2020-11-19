package phonebook.search.engines

import phonebook.Person
import phonebook.Profile
import phonebook.Type

class BinarySearchEngine() : SearchEngine() {

    override fun invoke(source: MutableList<Person>, items: List<String>): Int {

        var count = 0

        Profile.start(Type.TYPE_BINARY_SEARCH)
        loop@for (item in items) {
            if (this.search(item, source, 0, source.lastIndex) != -1) {
                count++
            }
        }
        Profile.finish(Type.TYPE_BINARY_SEARCH)

        return count
    }

    private fun search(item: String, source: MutableList<Person>, left: Int, right: Int): Int {

        if (left > right)
            return -1

        val middle = (left + right) / 2
        return when  {
            item == source[middle].name -> middle
            item > source[middle].name -> this.search(item, source, middle + 1, right)
            item < source[middle].name -> this.search(item, source, left, middle - 1)
            else -> -1
        }
    }
}