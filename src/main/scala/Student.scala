class Student(val sid : String, val scores: Array[Double]) {
 /*
  * layout:
  *     index  converted_100%
  * Attend	0	7
  * HW1	1	7
  * HW2	2	7
  * HW3	3	7
  * Quiz	4	7
  * Final	5	70
  * 
  */
  val originalFull = Array(6, 51, 88, 75, 10, 100)
  val convertedFull = Array(10,7,7,7,7, 70)
  require(scores.length == originalFull.length, "scores ary length not consistent.")
  
  val convScores: Array[Double] = {
    scores.zipWithIndex.map(item => {
      (item._1 * convertedFull(item._2)) / originalFull(item._2)
    })
  }
  
  val finalGrade: Double = {
    val middles = convScores.drop(1).take(4)
    val temps = middles.sorted.drop(1) // because increasing order
    temps.sum + convScores.head + convScores.last
  }

}