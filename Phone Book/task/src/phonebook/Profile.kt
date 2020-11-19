package phonebook

import java.util.concurrent.TimeUnit

data class Entry(val start: Long, val end: Long, val status: Int) {
    object Status {
        const val STARTED = 0
        const val FINISHED = 1
        const val INTERRUPTED = 2
    }
}

object Profile {

    private val data: MutableMap<String, Entry> = mutableMapOf()

    fun start(type: Type) {
        val entry = Entry(System.currentTimeMillis(), 0L, Entry.Status.STARTED)
        this.data[type.key] = entry
    }

    fun finish(type: Type, status: Int = Entry.Status.FINISHED) {
        if (!this.data.containsKey(type.key)) {
            throw Exception("There is no such profile type exists.")
        }
        val entry = this.data[type.key]?.copy(end = System.currentTimeMillis(), status = status)!!
        this.data[type.key] = entry
    }

    fun worksTooLong(profileToCompare: Type, predicate: (Long) -> Boolean): Boolean {
        if (!this.data.containsKey(profileToCompare.key)) {
            return false
        }
        return predicate(getElapsedTime(profileToCompare))
    }

    fun getElapsedTime(type: Type): Long {
        return if (this.data[type.key]!!.status == Entry.Status.STARTED) {
            System.currentTimeMillis() - this.data[type.key]!!.start
        } else {
            this.data[type.key]!!.end - this.data[type.key]!!.start
        }
    }

    fun getFormattedResult(type: Type): String {
        val timeSpent = this.data[type.key]!!.end - this.data[type.key]!!.start
        return this.getFormattedResult(timeSpent)
    }

    fun getFormattedResult(timeSpent: Long): String {

        val minutes = TimeUnit.MILLISECONDS.toMinutes(timeSpent)
        val seconds = TimeUnit.MILLISECONDS.toSeconds(timeSpent) % 60
        val milliseconds = timeSpent % 100

        return String.format("%d min. %d sec. %d ms.", minutes, seconds, milliseconds)
    }

    fun report(count: Int, size: Int, types: Array<Type>) {

        val report: MutableList<String> = mutableListOf()
        var timeSpent = 0L

        for (type in types) {
            val entry = this.data[type.key]!!
            when (type.category) {
                "sort" -> {
                    var line = "Sorting time. ${Profile.getFormattedResult(type)}"
                    if (entry.status == Entry.Status.INTERRUPTED) {
                        line += " - STOPPED, moved to linear search"
                    }
                    report.add(line)
                }
                "search" -> report.add("Searching time. ${Profile.getFormattedResult(type)}")
                "create" -> report.add("Creating time. ${Profile.getFormattedResult(type)}")
            }

            timeSpent += entry.end - entry.start
        }

        println("Found $count / $size entries. Time taken: ${Profile.getFormattedResult(timeSpent)}")
        println(report.joinToString("\n"))
    }

    fun report(count: Int, size: Int, type: Type) {
        val entry = this.data[type.key]!!
        val timeSpent = entry.end - entry.start
        println("Found $count / $size entries. Time taken: ${Profile.getFormattedResult(timeSpent)}")
    }
}