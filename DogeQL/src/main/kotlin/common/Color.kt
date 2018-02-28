package common

data class Color(var r: Byte, var g: Byte, var b: Byte, var a: Byte) {

    override fun toString(): String {
        return "#$r$g$b$a"
    }

}