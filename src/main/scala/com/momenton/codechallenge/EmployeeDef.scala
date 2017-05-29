package com.momenton.codechallenge

import com.typesafe.scalalogging.LazyLogging

trait EmployeeDef extends LazyLogging{

  case class EmployeeRecord(name:String, id:Option[Int], managerId:Option[Int]) {
    override def toString: String = s"$name"
  }

  lazy val employeeRecords= getEmployeeRecords

  lazy val ceos = employeeRecords.filter(e => e.managerId.isEmpty && isManager(e))

  lazy val employeesWithNoManager = employeeRecords.filter(e => e.managerId.isEmpty && !isManager(e))

  /*
  Pretend to be a service call to some kind of data store to retrieve the company's employee records.
  Mark it as protected so we can override it with dummy data in unit test
  * */
  protected def getEmployeeRecords = {
    Seq(
      EmployeeRecord(name="Allan",  id=Some(100), managerId=Some(150)),
      EmployeeRecord(name="Martin", id=Some(220), managerId=Some(100)),
      EmployeeRecord(name="Jamie",  id=Some(150), managerId=None     ), // CEO 1
      EmployeeRecord(name="Alex",   id=Some(275), managerId=Some(100)),
      EmployeeRecord(name="Steve",  id=Some(400), managerId=Some(150)),
      EmployeeRecord(name="David",  id=Some(190), managerId=Some(400)),

      EmployeeRecord(name="James",  id=Some(101), managerId=Some(151)),
      EmployeeRecord(name="Will",   id=Some(221), managerId=Some(101)),
      EmployeeRecord(name="Adam",   id=Some(151), managerId=None     ), // CEO 2
      EmployeeRecord(name="Mike",   id=Some(276), managerId=Some(101)),
      EmployeeRecord(name="Matt",   id=Some(401), managerId=Some(151)),
      EmployeeRecord(name="Lucy",   id=Some(191), managerId=Some(401)),

      // Employees with no manager
      EmployeeRecord(name="Spider-Man", id=Some(500), managerId=None),
      EmployeeRecord(name="Hulk",       id=Some(501), managerId=None),
      EmployeeRecord(name="Thor",       id=Some(502), managerId=None),
      EmployeeRecord(name="Iron Man",   id=Some(503), managerId=None)
    )
  }

  def isManager(employeeRecord: EmployeeRecord) = directSubordinates(employeeRecord).size > 0

  def directSubordinates(manager: EmployeeRecord) = employeeRecords.filter(_.managerId == manager.id)

  def level(employeeRecord: EmployeeRecord): Int = {
    1 + directSubordinates(employeeRecord).foldLeft(-1)((h, c) => {
      h max level(c)
    })
  }

  def preOrderTraverse(root: EmployeeRecord, visit: (EmployeeRecord) => Unit) ={
    def recur(employee:EmployeeRecord): Unit = {
      visit(employee)
      for ( child <- directSubordinates(employee) ) recur(child)
    }

    recur(root)
  }

  /*
  Get a string of n placeholder text concatenated together
  E.g getPlaceHolderStr(3,"- ") returns "- - - "
      getPlaceHolderStr(2,"<td></td>") returns "<td></td><td></td>"
  * */
  def getPlaceHolderStr(n:Int, placeHolder:String) = (0 until n).foldLeft("")((acc, n) => acc + placeHolder)

  /*
  Get the level gap of an employee comparing to the top level
  E.g CEO would return 0, an direct subordinate of a CEO would return 1
  * */
  def levelsUp(ceo: EmployeeRecord, employeeRecord: EmployeeRecord) = level(ceo) - level(employeeRecord)

  /*
  Get the level gap of an employee comparing to the bottom level. It is the opposite of levelsUp
  * */
  def levelsDown(ceo: EmployeeRecord, employeeRecord: EmployeeRecord) = level(ceo) - levelsUp(ceo, employeeRecord)

}