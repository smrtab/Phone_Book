package phonebook.sort.methods

import phonebook.Person
import phonebook.Profile
import phonebook.Type
import java.lang.Exception

class BubbleSort(): Sort() {

    override fun sort(input: MutableList<Person>) {

        Profile.start(Type.TYPE_BUBBLE_SORT)

        external@for (i in 0..input.lastIndex) {
            internal@for (j in 0 until input.lastIndex - i) {
                if (input[j].name > input[j + 1].name) {
                    this.swap(input, first = j, second = j + 1)
                }

                if (Profile.worksTooLong(Type.TYPE_LINEAR_SEARCH) {
                            it * 10 < Profile.getElapsedTime(Type.TYPE_BUBBLE_SORT)
                        }) {
                    throw Exception("Interrupted")
                }
            }
        }
        Profile.finish(Type.TYPE_BUBBLE_SORT)
    }
}