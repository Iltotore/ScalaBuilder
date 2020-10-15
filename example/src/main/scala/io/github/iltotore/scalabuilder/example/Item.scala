package io.github.iltotore.scalabuilder.example

import io.github.iltotore.scalabuilder.annotation.buildable

@buildable
case class Item(material: String, amount: Int = 0, name: Option[String])
