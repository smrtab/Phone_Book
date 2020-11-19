package phonebook

class Person {

    var number: Int
    val name: String
    val hashCode: Int

    constructor(name: String, number: Int) {
        this.number = number
        this.name = name
        this.hashCode = this.name.hashCode()
    }
    constructor(data: List<String>) {
        this.number = data[0].toInt()
        this.name = "${data[1]} ${data[2]}"
        this.hashCode = this.name.hashCode()
    }
    constructor(line: String) {
        this.number = line.substringBefore(' ').toInt()
        this.name = line.substringAfter(' ')
        this.hashCode = this.name.hashCode()
    }

    override fun toString(): String {
        return "$number $name"
    }

    fun equals(other: Person): Boolean {
        if (this.hashCode != other.hashCode) return false
        return this.name == other.name
    }
}