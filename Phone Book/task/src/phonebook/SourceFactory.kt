package phonebook

import java.io.File

object SourceFactory {

    var list: MutableList<Person>? = null
    var hashTable: HashTable? = null

    fun getPhonebook(path: String): MutableList<Person> {

        if (this.list != null) {
            return this.list!!
        }

        Profile.start(Type.TYPE_HASH_TABLE_CREATE)

        val input = File(path).readLines().toMutableList()
        this.list = mutableListOf()

        for (line in input) {
            this.list!!.add(Person(line))
        }

        Profile.finish(Type.TYPE_HASH_TABLE_CREATE)

        return this.list!!
    }

    fun createHashTable(source: MutableList<Person>): HashTable {

        if (this.hashTable != null) {
            return this.hashTable!!
        }

        Profile.start(Type.TYPE_HASH_TABLE_CREATE)

        this.hashTable = HashTable(source.size)
        for (person in source) {
            this.hashTable!!.put(person.name, person.number)
        }

        Profile.finish(Type.TYPE_HASH_TABLE_CREATE)

        return this.hashTable!!
    }
}