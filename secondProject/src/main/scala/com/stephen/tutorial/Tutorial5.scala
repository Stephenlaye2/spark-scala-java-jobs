package com.stephen.tutorial

object Tutorial5 {
  def main(args: Array[String]): Unit = {

    val lstInt: List[Int] = List(1, 2, 3, 4, 5, 6, 7, 8)
    val mapInt = lstInt.map(x => x*2)
    println(mapInt)
    val findEven = lstInt.flatMap(x => lstInt.filter(y => y % 2 == 0 && y == x))
    println(s"find even: $findEven")
    val findEven2 = lstInt.map(x => lstInt.filter(y => y % 2 == 0 && y == x))
    println(s"findEven2: $findEven2")
    println("filter list: " + lstInt.filter(_ % 2 == 0))
    val findOdd = lstInt.flatMap(x => lstInt.filter(y => x % 2 != 0 && x == y ).map(_*2))
    println("findOdd: " + findOdd)

    val lstNested: List[List[Int]] = List(List(11), List(12), List(13, 8), List(1), List(2), List(3))
    val map2 = lstNested.map(x => x)
    println(map2)


    val flatMap1 = lstNested.flatMap(x => x.filter(_%2 !=0))
    println(flatMap1)

    val lstStr = List("John", "Doe", "Engineer")
    val flatMap2 = lstStr.flatMap(x => x.toUpperCase())
    val map3 = lstStr.map(x => x.toUpperCase())
    println(flatMap2)
    println(map3)

//    find the second highest element in a list in scala, using a loop
    val numbers = List(3, 7, 1, 9, 5)
    def findSecondHighest(lst: List[Int]): Option[Int] = {
      if(lst.isEmpty || lst.length < 2) None
      else {
        var max = Int.MinValue
        var secondMax = Int.MinValue

        for (i <- lst) {
          if (i > max) {
            secondMax = max
            max = i
          } else if (i > secondMax && i != max) secondMax = i // 5, 6, 7
        }
        Some(secondMax)
      }
    }
    val secondMax = findSecondHighest(numbers)
    println(secondMax.getOrElse("secondMax not found!"))


  }

}
