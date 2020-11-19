package phonebook.search.engines

import phonebook.*

class HashTableSearchEngine() : SearchEngine() {

    override fun invoke(source: MutableList<Person>, items: List<String>): Int {
        val hashTable = SourceFactory.createHashTable(source)
        return this.invoke(hashTable, items)
    }

    fun invoke(source: HashTable, items: List<String>): Int {

        var count = 0

        Profile.start(Type.TYPE_HASH_SEARCH)
        for (item in items) {
            if (source.get(item) != null) count++
        }
        Profile.finish(Type.TYPE_HASH_SEARCH)

        return count
    }
}