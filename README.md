# Gradle Scala Template
This is a simple template making Scala development easier. This template provides:
- Shortcuts for Scala library, official modules and third party dependencies
- Dotty support, including shortcuts, required dependencies and compiling task.

A plugin designed to automatically implement these features to your project will be created later.

# Usage
## Scala 2
The `scala.gradle` file is already imported. The main build script already implements the scala main library.

### Change Scala version
Simply change the `scalaVersion` variable:
```gradle
ext.scalaVersion = '2.13.3' //Default value is 2.13.3
```

### Import scala official module or third party dependency
This template provides shortcuts and automatic artifact id generation based on `scalaVersion`.
```gradle
dependencies {
  implementation scalaLib() //Main library
  implementation scalaModule('scala-collection-contrib', '0.2.1') //org.scala-lang.modules:scala-collection-contrib_2.13:0.2.1
  implementation scalaDependency('com.typesafe.akka', 'akka-http', '10.2.0-M1') //com.typesafe.akka:akka-http_2.13:10.2.0-M1
}
```

## Dotty
You firstly need to replace the `scala.gradle` import by `dotty.gradle` as it already implements the scala script.

**Note that your IDE (like Intellij IDEA) could not highlight Dotty features correctly. This does not mean you're unable to compile your project.**

### Version and build
To change the dotty version use the `dottyVersion` variable like the `scalaVersion` one.
Because central's dotty artifacts change frequently, you can easily edit the wanted build using the `dottyBuild` variable:
```gradle
ext {
  dottyVersion = '0.26.0' //The latest version (02/07/2020) and the template's default.
  dottyBuild = '20200630-6cbb458-NIGHTLY' //Latest build and default.
}
```

### Compiling
Compile your Dotty sources is pretty simple: just use the `compileDotty` task.