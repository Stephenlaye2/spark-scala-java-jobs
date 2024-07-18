package com.stephen.tutorial

import com.stephen.Session

class Tutorial4 extends Session{
 val employeeData = Seq(
   (101, "Stephen", "6"),
   (102, "Shara", "3"),
   (103, "Jack", "4"),
   (104, "Mike", "8"),
   (105, "Joy", "5"),
   (106, "Angela", "2"),
   (107, "Ola", "7")
 )
  val employeeCols = Seq("id", "employee_name", "dept_number")

  val departmentData = Seq(
    ("4", "Marketing"),
    ("2", "Sales"),
    ("6", "IT"),
    ("1", "Engineering")
  )
  val departmentCols = Seq("dept_number", "dept_name")

  import sparkSn.implicits._

  println{"employee df"}
  val employeeDf = employeeData.toDF(employeeCols:_*)
  employeeDf.show(false)
  employeeDf.createOrReplaceTempView("emp")

  println{"department df"}
  val departmentDf = departmentData.toDF(departmentCols:_*)
  departmentDf.show(false)
  departmentDf.createOrReplaceTempView("dept")

  println{"left semi join"}
  val employeeWithDeptDf = employeeDf.join(departmentDf.select("dept_number"), Seq("dept_number"), "leftsemi")
  employeeWithDeptDf.show(false)

  println{"left anti join"}
  val noEmployeeDeptDf = employeeDf.join(departmentDf, employeeDf("dept_number") === departmentDf("dept_number"), "left_anti")
  noEmployeeDeptDf.show(false)

  println {"left or left outer join"}
  val leftJoin = employeeDf.join(departmentDf, Seq("dept_number"), "left_outer")
  leftJoin.show(false)
  sparkSn.sql("select * from emp e LEFT OUTER JOIN dept d ON e.dept_number = d.dept_number").show(false)

  println{"right or right outer join"}
  val rightJoin = employeeDf.join(departmentDf, Seq("dept_number"), "right_outer")
  rightJoin.show(false)
  sparkSn.sql("select * from emp e RIGHT OUTER JOIN dept d ON e.dept_number = d.dept_number").show(false)

  println{"full outer join"}
  val fullJoin = employeeDf.join(departmentDf, Seq("dept_number"), "full_outer")
  fullJoin.show(false)

  println{"cross join"}
  sparkSn.sql("select * from emp cross join dept").show(50,false)
  fullJoin.write.format("csv").mode("overwrite").save("src/resources/output")




}
