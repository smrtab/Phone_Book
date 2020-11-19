package phonebook

enum class Type(val key: String, val category: String) {
    TYPE_BUBBLE_SORT("bubbleSort", "sort"),
    TYPE_QUICK_SORT("quickSort", "sort"),
    TYPE_LINEAR_SEARCH("linearSearch", "search"),
    TYPE_BINARY_SEARCH("binarySearch", "search"),
    TYPE_HASH_SEARCH("hashSearch", "search"),
    TYPE_HASH_TABLE_CREATE("hashTableCreating", "create"),
    TYPE_JUMP_SEARCH("jumpSearch", "search");

    companion object {
        fun findByKey(key: String): Type {
            for (enum in Type.values()) {
                if (key == enum.key) return enum
            }

            throw Exception("Wrong type")
        }
    }
}