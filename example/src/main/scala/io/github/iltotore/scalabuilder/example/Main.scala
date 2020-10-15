package io.github.iltotore.scalabuilder.example

object Main {

  def main(args: Array[String]): Unit = {
    val item: Item = new Item.Builder("carrot") {
      name = "God's vegetable"
    }
    println(item)
  }
}
