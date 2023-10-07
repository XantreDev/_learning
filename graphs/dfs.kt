fun main() {
    fun matrix_to_edges(matrix: List<List<Int>>) = run {
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
    fun create_dfs(edges: List<List<Int>>) = run {
        val used = MutableList(edges.size) { false }

        fun dfs(index: Int) {
            used[index] = true
            for (edge in edges[index]) {
                if (!used[edge]) {
                    dfs(edge)
                }
            }
        }
        
        Pair(::dfs, {used})
    }
    val (n, start, edges) =
            run {
                val (n, start) = (readLine() ?: "0 0").split(" ").map { it.toInt() }
                val matrix = (1..n).map { (readLine() ?: "").trim().split(" ").map { it.toInt() } }

                Triple(n, start, matrix_to_edges(matrix))
            }
            
    val (dfs, getUsed) = create_dfs(edges)


    dfs(start - 1)
    
    println(getUsed().filter { it }.size)
}
