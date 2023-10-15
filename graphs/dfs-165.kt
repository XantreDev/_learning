// https://informatics.msk.ru/mod/statements/view.php?id=256&chapterid=165#1
// wow first try
fun main() {
    fun create_dfs(edges: List<List<Int>>) = run {
        // -1 not used. 0, 1 - colors
        val used = MutableList(edges.size) { -1 }

        fun dfs(index: Int, prevColor: Int): Boolean {
            val currentColor = (prevColor + 1) % 2
            used[index] = currentColor
            for (edge in edges[index]) {
                if (used[edge] == -1 && !dfs(edge, currentColor)) {
                    return false
                }

                if (used[edge] != prevColor) {
                    return false
                }
            }

            return true
        }

        Pair(::dfs, { used })
    }
    val (n, lists) =
            run {
                val (n, m) = (readLine() ?: "0 0").split(" ").map { it.toInt() }
                val edges = (1..m).map { (readLine() ?: "").trim().split(" ").map { it.toInt() } }

                var lists = List(n) { mutableListOf<Int>() }

                for ((i, j) in edges) {
                    lists[i - 1].add(j - 1)
                    lists[j - 1].add(i - 1)
                }

                Pair(n, lists)
            }

    val (dfs, getUsed) = create_dfs(lists)

    var canSitForTables = true
    for (i in (0..n - 1)) {
        if (getUsed()[i] == -1 && !dfs(i, 1)) {
            canSitForTables = false
            break
        }
    }

    println(
            if (canSitForTables) {
                "YES"
            } else {
                "NO"
            }
    )
    if (canSitForTables) {
        println(
                getUsed()
                        .mapIndexed { index, it -> Pair(index, it) }
                        .filter { it.second == 0 }
                        .map { it.first + 1 }
                        .joinToString(" ")
        )
    }
}
