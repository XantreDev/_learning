// https://informatics.msk.ru/mod/statements/view.php?id=256&chapterid=111540#1
// fails 19 test
sealed class Option<out T> {
    object None : Option<Nothing>()
    data class Some<out T>(val value: T) : Option<T>()
}

fun main() {
    fun matrix_to_lists(matrix: List<List<Int>>) = run {
        val edges: List<MutableList<Int>> = List(matrix.size) { mutableListOf() }

        for (i in 0..matrix.size - 1) {
            for (j in 0..matrix.size - 1) {
                if (matrix[i][j] > 0) {
                    edges[i].add(j)
                }
            }
        }
        edges
    }
    fun edges_to_lists(pointsCount: Int, edges: List<Pair<Int, Int>>) = run {
        val lists: List<MutableList<Int>> = List(pointsCount) { mutableListOf() }

        for ((i, j) in edges) {
            lists[i].add(j)
            lists[j].add(i)
        }
        lists
    }
    fun create_dfs(edges: List<List<Int>>) = run {
        val color: MutableList<Option<Int>> = MutableList(edges.size) { Option.None }

        fun dfs(index: Int, currentColor: Int) {
            color[index] = Option.Some(currentColor)
            for (edge in edges[index]) {
                when (color[edge]) {
                    is Option.Some -> {}
                    is Option.None -> dfs(edge, currentColor)
                }
            }
        }

        Pair(::dfs, { color })
    }
    val (n, edges) =
            run {
                val (n, m) = (readLine() ?: "0 0").split(" ").map { it.toInt() }
                val edges =
                        (1..m).map { (readLine() ?: "").trim().split(" ").map { it.toInt() } }.map {
                            Pair(it[0] - 1, it[1] - 1)
                        }

                Pair(n, edges_to_lists(n, edges))
            }

    val (dfs, getUsed) = create_dfs(edges)

    var currentColor = 0
    for (i in 0..n - 1) {
        when (getUsed()[i]) {
            is Option.None -> {
                dfs(i, currentColor)
                currentColor++
            }
            is Option.Some -> {}
        }
    }

    val linkedComponents =
            getUsed().withIndex().groupBy { it.value }.mapValues { it.value.map { it.index } }

    println(linkedComponents.keys.size)
    linkedComponents.forEach({
        println(it.value.size)
        println(it.value.map { it + 1 }.joinToString(" "))
    })
}
