object Practice extends App{
  def palindron(word: String): Boolean = {
    word == word.reverse
  }
  println(palindron("racecare"))

  def fizzBuzz(num: Int): Unit = {
    for (i <- Range(1, num)){
      if (i%5 == 0 && i %3 ==0)println("FizzBuzz")
      else if(i % 5 == 0) println("Buzz")
      else if(i % 3 == 0 )println("Fizz")
      else println(i)
    }
  }
  fizzBuzz(20)

  def anagramChecker(word1: String, word2: String): Boolean = {
    word1.toLowerCase().sorted == word2.toLowerCase().sorted
  }

  println(anagramChecker("SIlent", "Listen"))
}

case class Person(var name: String)

class yy{

    val p: Person = Person("Dinesh");
    changeName(p)

    p.name.equals("Dinesh")

//    p.getName.equals("Dinesh") // true/false ?



  def changeName(x: Person){

    x = Person("x")

  }

}