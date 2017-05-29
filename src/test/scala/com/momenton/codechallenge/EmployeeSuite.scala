package com.momenton.codechallenge

import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class EmployeeSuite extends FunSuite with EmployeeDef {

  // Set up test data
  val allan  = EmployeeRecord(name="Allan",  id=Some(100), managerId=Some(150))
  val martin = EmployeeRecord(name="Martin", id=Some(220), managerId=Some(100))
  val jamie  = EmployeeRecord(name="Jamie",  id=Some(150), managerId=None     )
  val alex   = EmployeeRecord(name="Alex",   id=Some(275), managerId=Some(100))
  val steve  = EmployeeRecord(name="Steve",  id=Some(400), managerId=Some(150))
  val david  = EmployeeRecord(name="David",  id=Some(190), managerId=Some(400))

  val james = EmployeeRecord(name="James",  id=Some(101), managerId=Some(151))
  val will  = EmployeeRecord(name="Will",   id=Some(221), managerId=Some(101))
  val adam  = EmployeeRecord(name="Adam",   id=Some(151), managerId=None     )
  val mike  = EmployeeRecord(name="Mike",   id=Some(276), managerId=Some(101))
  val matt  = EmployeeRecord(name="Matt",   id=Some(401), managerId=Some(151))
  val lucy  = EmployeeRecord(name="Lucy",   id=Some(191), managerId=Some(401))

  val spiderMan = EmployeeRecord(name="Spider-Man", id=Some(500), managerId=None)
  val hulk      = EmployeeRecord(name="Hulk",       id=Some(501), managerId=None)
  val thor      = EmployeeRecord(name="Thor",       id=Some(502), managerId=None)
  val ironMan   = EmployeeRecord(name="Iron Man",   id=Some(503), managerId=None)

  val DUMMY_EMPLOYEE_RECORDS = Seq(
    allan,
    martin,
    jamie, // CEO 1
    alex,
    steve,
    david,

    james,
    will,
    adam, // CEO 2
    mike,
    matt,
    lucy,

    // Employees with no manager
    spiderMan,
    hulk,
    thor,
    ironMan

  )

  override def getEmployeeRecords = DUMMY_EMPLOYEE_RECORDS

  test("EmployeeSuite can execute") {
    assert(true, true)
  }

  test("Employee Record toString") {
    assert(mike.toString == "Mike")
  }

  test("Can get employee records") {
    assert(getEmployeeRecords == DUMMY_EMPLOYEE_RECORDS)
  }

  test("Can identify CEOs") {
    val idOfCEOs = Seq(jamie, adam).flatMap(_.id)
    assert(idOfCEOs == ceos.flatMap(_.id))
  }

  test("Can identify manager") {
    val manager1 = allan
    val manager2 = steve

    val employee1 = david
    val employee2 = hulk

    assert(isManager(manager1) == true)
    assert(isManager(manager2) == true)

    assert(isManager(employee1) == false)
    assert(isManager(employee2) == false)
  }

  test("Can correctly get level of an employee") {
    assert(level(jamie) == 2)
    assert(level(allan) == 1)
    assert(level(david) == 0)
    assert(level(hulk) == 0)
  }

  test("Can identify direct subordinates") {
    assert(directSubordinates(jamie).flatMap(_.id) == Seq(100, 400))
    assert(directSubordinates(allan).flatMap(_.id) == Seq(220, 275))

    assert(directSubordinates(david).flatMap(_.id) == Nil)
    assert(directSubordinates(ironMan).flatMap(_.id) == Nil)
  }

  test("Can identify employees with no manager") {
    val idsOfEmployeesWithNoManager = employeesWithNoManager.flatMap(_.id)
    assert(idsOfEmployeesWithNoManager == Seq(500, 501, 502, 503))
  }

  test("Can correctly get placeholder text") {
    val result1 = getPlaceHolderStr(3, "-")
    assert(result1 == "---")

    val result2 = getPlaceHolderStr(3, "*")
    assert(result2 == "***")
  }

  test("Can correctly calculate level up") {
    assert(levelsUp(jamie, jamie) == 0)
    assert(levelsUp(jamie, allan) == 1)
    assert(levelsUp(jamie, alex) == 2)
    assert(levelsUp(jamie, thor) == 2)
  }

  test("Can correctly calculate level down") {
    assert(levelsDown(jamie, jamie) == 2)
    assert(levelsDown(jamie, allan) == 1)
    assert(levelsDown(jamie, alex) == 0)
    assert(levelsDown(jamie, thor) == 0)
  }
}
