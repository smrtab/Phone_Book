package phonebook.search.engines

import phonebook.Person
import phonebook.Profile
import phonebook.Type
import kotlin.math.floor
import kotlin.math.sqrt

class JumpSearchEngine() : SearchEngine() {

    override fun invoke(source: MutableList<Person>, items: List<String>): Int {

        var count = 0
        val jump = floor(sqrt(source.size.toDouble())).toInt()

        Profile.start(Type.TYPE_JUMP_SEARCH)
        items@for (item in items) {

            if (source[0].name == item) {
                count++
                continue
            }

            var startIndex = 0
            phonebook@while (startIndex < source.lastIndex) {

                var border = startIndex + jump

                if (border > source.lastIndex)
                    border = source.lastIndex

                if (source[border].name < item) {
                    startIndex = border
                    continue
                }

                for (i in border downTo startIndex) {
                    if (source[i].name == item) {
                        count++
                        break@phonebook
                    }
                }
                break
            }
        }
        Profile.finish(Type.TYPE_JUMP_SEARCH)

        return count
    }
}