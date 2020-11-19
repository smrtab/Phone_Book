package phonebook

import phonebook.search.engines.SearchEngine
import phonebook.sort.methods.Sort
import java.io.File
import java.lang.Exception

fun main() {

    var filePath = "/Users/Shared/directory.txt"
    val itemsPath = "/Users/Shared/find.txt"

    val items = File(itemsPath).readLines()
    val source = SourceFactory.getPhonebook(filePath)

    val linearSearch = SearchEngine.Factory.get(Type.TYPE_LINEAR_SEARCH)
    val jumpSearch = SearchEngine.Factory.get(Type.TYPE_JUMP_SEARCH)
    val binarySearch = SearchEngine.Factory.get(Type.TYPE_BINARY_SEARCH)
    val hasTable = SearchEngine.Factory.get(Type.TYPE_HASH_SEARCH)

    val bubbleSort = Sort.Factory.get(Type.TYPE_BUBBLE_SORT)
    val quickSort = Sort.Factory.get(Type.TYPE_QUICK_SORT)

    try {

        println("Start searching (linear search)...")
        var count = linearSearch.invoke(source, items)
        Profile.report(count, items.size, Type.TYPE_LINEAR_SEARCH)

        try {
            println("\nStart searching (bubble sort + jump search)...")
            bubbleSort.sort(source)
            count = jumpSearch.invoke(source, items)

            Profile.report(count, items.size, arrayOf(Type.TYPE_BUBBLE_SORT, Type.TYPE_JUMP_SEARCH))

        } catch (e: Exception) {
            Profile.finish(Type.TYPE_BUBBLE_SORT, Entry.Status.INTERRUPTED)
            count = linearSearch.invoke(source, items)

            Profile.report(count, items.size, arrayOf(Type.TYPE_BUBBLE_SORT, Type.TYPE_LINEAR_SEARCH))
        }

        println("\nStart searching (quick sort + binary search)...")
        quickSort.sort(source)
        count = binarySearch.invoke(source, items)

        Profile.report(count, items.size, arrayOf(Type.TYPE_QUICK_SORT, Type.TYPE_BINARY_SEARCH))

        println("\nStart searching (hash table)...")
        count = hasTable.invoke(source, items)

        Profile.report(count, items.size, arrayOf(Type.TYPE_HASH_TABLE_CREATE, Type.TYPE_HASH_SEARCH))

    } catch (e: Exception) {
        println(e.message)
    }
}