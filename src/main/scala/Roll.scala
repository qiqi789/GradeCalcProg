import scala.io.Source

object Roll extends App {

  val filename = "worksheet_test.csv"
  val filesource = Source.fromFile(filename)

  val rows = filesource.getLines./:(Map[String, Array[Double]]())((map, line) => {
    val temp = line.split(",")
    val key = temp.head
    val scores = temp.tail
    map + (key -> scores.map(_.toDouble))

  })
//  println(mat.size)
//  mat.take(7).foreach(r => {
//    println(s"${r._1} : ${r._2.toList}")
//  })
  
  def deSerialize(list: List[Student], row: (String, Array[Double])) = {
    val stu = new Student(row._1, row._2)
    list.::(stu)
  }
  
  val students = rows./:(List[Student]())(deSerialize)
  
  val classSize = students.length
  val average = ( students./:(0.0)((sum, s) => sum + s.finalGrade) ) / classSize
  val failRate = (students.filter(_.finalGrade < 60.0).length) / classSize
  
  println(s"class size is $classSize")
  println(s"average is $average")
  println(s"fail rate is $failRate")

}