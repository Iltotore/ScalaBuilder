# ScalaBuilder
ScalaBuilder is a new way to build objects using a ScalaFX-like DSL.

# Install
<details>
<summary>Using Gradle</summary>

```gradle
repositories {
  mavenCentral()
}

dependencies {
  implementation 'io.github.iltotore:scalabuilder_2.13:version'
}
```
</details>

<details>
<summary>Using SBT</summary>

```sbt
libraryDependencies += "io.github.iltotore" %% "scalabuilder" % "version"
```
</details>

# Actual problem
You have a case class:
```scala
case class Item(material: String,
  amount: Int = 1,
  displayName: Option[String] = Option.empty,
  enchantments: Seq[Enchatment] = Seq.empty)
```

You can define your case class like this:
```scala
val item = Item("sword", 1, Option("King's sword"))
```
It can become pretty annoying to wrap in Option all your optional argument, in particular for bigger case classes.

This could be better using the builder pattern:
```scala
val item = Item.builder("sword")
  .named("King's sword")
  .withEnchantment(FireEnchatment(level = 2))
  .build
```

The problem is can it feels less elegant when you use enclosed builders:
```scala
val item = Item.builder("sword")
  .named("King's sword")
  .withEnchantment(FireEnchatment(level = 2))
  .withMeta(
    WeaponMeta.builder
      .withDamage(5)
      .withDurability(20)
      .withAttackSpeed(0.5)
      .build
  )
  .build
```

Thanks to Scala's flexibility, you can define your Item like this:
```scala
val item = new Item.Builder("sword") {
  name = "King's sword"
  enchantments = Seq(FireEnchantment(2))
  meta = new WeaponMeta.Builder {
    damage = 5
    durability = 10
    attackSpeed = 0.5
  }.build
}.build
```
by defining accessors like this:
```scala
private var name: Option[String] = Option.empty

def name: Option[String] = nameOpt
def name_=(value: String): Unit = nameOpt = Option(value)
```

# Provided solution

## Implicit build
Firstly, ScalaBuilder provides a simple implicit conversion method to build your object,
allowing you to do:
```scala
import io.github.iltotore.scalabuilder.Builder.autoBuild


val item: Item = new Item.Builder("sword") {
  name = "King's sword"
  enchantments = Seq(FireEnchantment(2))
  meta = new WeaponMeta.Builder {
    damage = 5
    durability = 10
    attackSpeed = 0.5
  }
}
```

ScalaBuilder generates the builder semi and fully-automatically using two annotations:

## Property annotation on variable
You can use the `@property(name)` annotation to generate variable accessors automatically:
```scala
@property("name")
private var nameOpt: Option[String] = Option.empty
```

will generate these methods:
```scala
private var nameOpt: Option[String] = Option.empty

def name: Option[String] = nameOpt
def name_=(value: String): Unit = nameOpt = Option(value)
```

## Full builder generation
You can annotate your case class using `@buildable:
```scala
@buildable
case class Item(material: String,
  amount: Int = 1,
  displayName: Option[String] = Option.empty,
  enchantments: Seq[Enchatment] = Seq.empty)
```
ScalaBuilder will generate your Item.Builder for you like shown [before](#implicit-build).