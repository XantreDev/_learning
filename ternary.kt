fun ternary(f: (param: Double) -> Double, _l: Double, _r: Double, e: Double): Double {
    var l = _l
    var r = _r

    while (r - l > e) {
        val dist = r - l
        val m1 = l + dist / 3
        val m2 = r - dist / 3

        if (f(m1) < f(m2)) {
            r = m2
        } else {
            l = m1
        }
    }

    return l
}

fun main() {
    val e = Math.pow(10.0, -7.0)
    val (vp, vf) = (readLine()?.split(" ") ?: listOf()).map { it.toDouble() }
    val y = 1 - (readLine()?.toDouble() ?: 0.0)
    fun f(point: Double): Double {
        return (Math.hypot(y, point) / vp) + (Math.hypot(1 - y, 1 - point) / vf)
    }
    println(ternary(::f, 0.0, 1.0, e))
}
