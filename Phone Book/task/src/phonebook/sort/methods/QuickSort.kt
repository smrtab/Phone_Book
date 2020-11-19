package phonebook.sort.methods

import phonebook.Person
import phonebook.Profile
import phonebook.Type

class QuickSort(): Sort() {

    override fun sort(input: MutableList<Person>) {
        Profile.start(Type.TYPE_QUICK_SORT)
        this.order(input, 0, input.lastIndex)
        Profile.finish(Type.TYPE_QUICK_SORT)
    }

    private fun orderMemoryComsuming(source: MutableList<Person>): MutableList<Person> {
        val pivot = source.last()
        val equal = source.filter { it.name == pivot.name }.toMutableList()
        val less = source.filter { it.name < pivot.name }.toMutableList()
        val greater = source.filter { it.name > pivot.name }.toMutableList()

        return (this.orderMemoryComsuming(less) + equal + this.orderMemoryComsuming(greater)).toMutableList()
    }

    private fun order(source: MutableList<Person>, low: Int, pivot: Int) {

        if (low > pivot)
            return

        var smaller = low - 1

        for (i in low until pivot) {
            if (source[i].name < source[pivot].name) {
                smaller++
                this.swap(source, smaller, i)
            }
        }
        this.swap(source, smaller + 1, pivot)

        this.order(source, low, smaller)
        this.order(source,smaller + 2, pivot)
    }
}