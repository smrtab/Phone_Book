package phonebook

import java.util.*

class HashTable(private val capacity: Int) {

    private val table: Array<LinkedList<Person>?> = Array(capacity) { null }

    fun get(key: String): Person? {
        val index = this.normalizeHash(key.hashCode())
        return this.searchPersonInBucket(index, key)
    }

    fun put(key: String, value: Int): Int = this.insert(key, value)
    fun add(key: String, value: Int): Int = this.insert(key, value)
    fun insert(key: String, value: Int): Int {
        val person = Person(key, value)
        val index = this.normalizeHash(person.hashCode)
        this.insertIntoBucket(index, person)
        return index
    }

    fun remove(key: String): Person? {

        val index = this.normalizeHash(key.hashCode())
        val bucket = this.table[index] ?: return null
        var person = this.searchPersonInBucket(index, key)

        bucket.remove(person)

        return person
    }

    private fun insertIntoBucket(index: Int, person: Person) {

        if (this.table[index] == null) {
            this.table[index] = LinkedList<Person>()
        }

        val bucket = this.table[index]!!
        var existingPerson = this.searchPersonInBucket(index, person.name)

        if (existingPerson == null) {
            bucket.add(person)
        } else {
            existingPerson.number = person.number
        }
    }

    private fun searchPersonInBucket(index: Int, key: String): Person? {
        val bucket = this.table[index] ?: return null
        for (item in bucket) {
            if (item.name == key)
                return item
        }
        return null
    }

    private fun normalizeHash(keyHash: Int): Int = (keyHash and 0x7FFFFFFF) % capacity

}