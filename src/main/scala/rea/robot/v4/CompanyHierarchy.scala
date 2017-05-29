package momenton.code.challenge

/**
  * A main object that can be used to execute this test.
  * The CompanyHierarchy will print(to STDOUT) the hierarchical structure of given company's employee records in 2 formats
  */
object CompanyHierarchy extends App with EmployeeDef {

  /* Clarifications:
     1. There is only ONE CEO in the entire employee records ?
     - This program handles the case where there are more than 1 CEO
     2. Quote from the code challenge: "A manager who is not an valid employee". What determines the validity of an employee ?
     - This program does not yet handle the case of an invalid employee.
     - Possible solution would be to report and exclude such employees when preparing the employee records before execution
   */

  def stdOut(ceo: EmployeeRecord, employeeRecord: EmployeeRecord) = {
    val placeHolder = " | "
    println(
      s"${getPlaceHolderStr(levelsUp(ceo, employeeRecord), placeHolder)}" +
      employeeRecord +
      s"${getPlaceHolderStr(levelsDown(ceo, employeeRecord), placeHolder)}"
    )
  }

  def htmlOut(ceo: EmployeeRecord, employeeRecord: EmployeeRecord) = {
    val placeHolder = "<td></td>"
    println(
      "<tr>" +
      s"${getPlaceHolderStr(levelsUp(ceo, employeeRecord), placeHolder)}" +
      s"<td>employeeRecord</td>" +
      s"${getPlaceHolderStr(levelsDown(ceo, employeeRecord), placeHolder)}" +
      "</tr>"
    )
  }

  def printHtml(ceos:Seq[EmployeeRecord]) = {
    ceos.foreach(ceo => {
      println("<table>")
      preOrderTraverse(ceo, (employee) => htmlOut(ceo, employee))
      println("</table>")
      println("")
    })

    println(s"Employees with no manager: ${employeesWithNoManager}")
  }

  def printToStdOut(ceos:Seq[EmployeeRecord]) = {
    ceos.foreach(ceo => {
      preOrderTraverse(ceo, (employee) => stdOut(ceo, employee))
      println("")
    })

    println(s"Employees with no manager: ${employeesWithNoManager}")
  }

  def output(outputFunc: (Seq[EmployeeRecord]) => Unit) = {
    ceos match {
      case Nil => println("Corrupted data - No CEOs found for this company")
      case list => outputFunc(list)
    }
  }

  output(printToStdOut)
  println("\n--------------------------\n")
  output(printHtml)
}
