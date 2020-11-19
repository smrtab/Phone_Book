package phonebook.search.engines

import phonebook.Person
import phonebook.Profile
import phonebook.Type

class LinearSearchEngine() : SearchEngine() {

    override fun invoke(source: MutableList<Person>, items: List<String>): Int {

        var count = 0

        Profile.start(Type.TYPE_LINEAR_SEARCH)
        loop@for (item in items) {
            for (line in source) {
                if (line.name.contains(item)) {
                    count++
                    continue@loop
                }
            }
        }
        Profile.finish(Type.TYPE_LINEAR_SEARCH)

        return count
    }
}