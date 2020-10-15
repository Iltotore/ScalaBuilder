package io.github.iltotore.scalabuilder

import scala.language.implicitConversions

trait Builder[T] {

  def build: T
}

object Builder {

  implicit def autoBuild[T](builder: Builder[T]): T = builder.build
}