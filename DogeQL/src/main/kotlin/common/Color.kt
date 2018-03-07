package common

data class Color(var r: Byte, var g: Byte, var b: Byte, var a: Byte) {

    constructor(value: String) : this(0, 0, 0, 0)

    override fun toString(): String {
        return "#$r$g$b$a"
    }

}