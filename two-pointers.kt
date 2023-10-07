fun main() {
    val (_, r) = (readLine()?.split(" ") ?: listOf()).map { it.toInt() }
    val list = ((readLine()?.split(" ")) ?: listOf()).map { it.toLong() }

    var j = 0
    var sum: Long = 0

    for (i in 0..list.size - 1) {
        while (j < list.size && list[j] - list[i] <= r) {
            j++
        }

        sum += (list.size - j)
    }

    print(sum)
}
