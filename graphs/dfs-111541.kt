// https://informatics.msk.ru/mod/statements/view.php?id=256&chapterid=111541#1

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
    fun create_hasCycles(edges: List<List<Int>>) = run {
        val used: MutableList<Boolean> = MutableList(edges.size) { false }

        fun hasCycles(index: Int, parent: Int): Boolean {
            used[index] = true
            for (edge in edges[index]) {
                if (used[edge] && edge != parent) {
                    return true
                }
                if (!used[edge] && hasCycles(edge, index)) {
                    return true
                }
            }

            return false
        }

        Pair(::hasCycles, { used })
    }
    val (n, edges) =
            run {
                val (n) = (readLine() ?: "0").trim().split(" ").map { it.toInt() }
                val matrix = (1..n).map { (readLine() ?: "").trim().split(" ").map { it.toInt() } }
                Pair(n, matrix_to_lists(matrix))
            }

    val (hasCycles, getUsed) = create_hasCycles(edges)

    if (hasCycles(0, -1) || getUsed().any { !it }) {
        return println("NO")
    } else {
        println("YES")
    }
}
