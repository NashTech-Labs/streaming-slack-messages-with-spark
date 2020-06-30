name := "spark-streaming"

version := "0.1"

scalaVersion := "2.11.8"

resolvers += "jitpack" at "https://jitpack.io"

libraryDependencies ++= Seq("org.apache.spark" % "spark-streaming_2.11" % "1.6.1",

  "org.scalaj" %% "scalaj-http" % "2.2.1",

  "org.jfarcand" % "wcs" % "1.5")
