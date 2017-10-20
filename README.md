# sbt-stainless 0.2.0

[![Build Status](https://travis-ci.org/NiceKingWei/sbt-stainless.svg?branch=master)](https://travis-ci.org/NiceKingWei/sbt-stainless)

A sbt plugin for stainless users to verify code in sbt command line.

## Usage
Make sure your SBT version in project/build.properties:

sbt.version = 0.13.16
or

sbt.version = 1.0.0

Add the plugin in project/plugins.sbt:

```scala
addSbtPlugin("org.zjulambda.scala" % "sbt-stainless" % "0.2.0")
```

The default backend of stainless is scalac,if you want to use dotty,add this line in `build.bst`
```scala
stainlessBackend := "dotty"
```

And the default value of `stainlessFlags` is `Seq("--vccache")`.You can modify it in `build.bst` as well.

Then,run `sbt verify`,your code will be checked by stainless.

## Appendix
How to work with stainless smoothly.

1. clone [stainless](#https://github.com/epfl-lara/stainless) on github
1. compile it(`sbt clean compile`)
1. add `stainless/bin` into `path`
1. create a sbt project in a temporary directory.
1. copy `stainless/frontends/library/stainless` to `src/main/scala/` in the temporary sbt project.
1. run `sbt compile package` and you will get a `xxx.jar` file.
1. copy `xxx.jar` to `lib/` in your stainless project.
1. add this plugin and enjoy coding in stainless.

