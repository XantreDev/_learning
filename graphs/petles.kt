fun main() {
  val (n, matrix) = run {
   val n = (readLine() ?: "0").toInt()
   val matrix = (1..n).map { (readLine() ?: "").trim().split(" ").map { it.toInt() }}
   
   Pair(n, matrix)
  }

    for (i in 0..n-1) {
        if (matrix[i][i] > 0) {
            println("YES")
            return
        }
    }
    
    println("NO")
}