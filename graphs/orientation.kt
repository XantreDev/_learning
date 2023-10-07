// fun consume_matrix() = {
//    val n = (readLine() ?: "0").toInt()
//    val matrix = (1..n).map { (readLine() ?: "").trim().split(" ").map { it.toInt() }}
   
//    Pair(n, matrix)
// }

fun main() {
   val n = (readLine() ?: "0").toInt()
   val matrix = (1..n).map { (readLine() ?: "").trim().split(" ").map { it.toInt() }}
   
   var isOriented = false
   for (i in 0..n-1) {
     for (j in 0..n-1) {
       if (matrix[i][j] != matrix[j][i] || (i == j && matrix[i][j] > 0))  {
        isOriented = true
       }
     }
   }
   
   println(if (!isOriented){ "YES" } else {"NO"})
}