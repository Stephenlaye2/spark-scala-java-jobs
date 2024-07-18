package com.stephen.tutorial

import com.stephen.Session

import scala.collection.Map

class Tutorial3 extends Session{

  val df1 = sparkSn.read.format("csv").options(Map("header" -> "true", "inferSchema" -> "true", "delimiter" -> "\\t"))
    .load("src/main/resources/sales_data.txt").localCheckpoint(true)

  df1.show(false)

  System.in.read()
  sparkSn.stop()
}
