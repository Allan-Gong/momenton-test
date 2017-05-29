# Momenton - code challenge

Momenton - code challenge

## Why use Scala

* Scala's rich collections make it easy to model the problem
* Declarative programming paradigm is easier to comprehend 

## Installing

1. [Download and install JDK 8](https://www3.ntu.edu.sg/home/ehchua/programming/howto/JDK_Howto.html) on your local machine
1. [Download and install Scala](https://www.scala-lang.org/download/)
1. [Install and setup SBT](http://www.scala-sbt.org/0.13/docs/Setup.html) on your local machine.
1. Open console and navigate to the root directory of this project
1. Run sbt (sbt will download/manage all dependencies) 

## Running the application using sbt

```
$ sbt run
```

Or from sbt console,

```
> run
```

Below is an example output:
```
>sbt run

[info] Running momenton.code.challenge.CompanyHierarchy
Jamie |  |
 | Allan |
 |  | Martin
 |  | Alex
 | Steve |
 |  | David

Adam |  |
 | James |
 |  | Will
 |  | Mike
 | Matt |
 |  | Lucy

Employees with no manager: List(Spider-Man, Hulk, Thor, Iron Man)

--------------------------

<table>
<tr><td>employeeRecord</td><td></td><td></td></tr>
<tr><td></td><td>employeeRecord</td><td></td></tr>
<tr><td></td><td></td><td>employeeRecord</td></tr>
<tr><td></td><td></td><td>employeeRecord</td></tr>
<tr><td></td><td>employeeRecord</td><td></td></tr>
<tr><td></td><td></td><td>employeeRecord</td></tr>
</table>

<table>
<tr><td>employeeRecord</td><td></td><td></td></tr>
<tr><td></td><td>employeeRecord</td><td></td></tr>
<tr><td></td><td></td><td>employeeRecord</td></tr>
<tr><td></td><td></td><td>employeeRecord</td></tr>
<tr><td></td><td>employeeRecord</td><td></td></tr>
<tr><td></td><td></td><td>employeeRecord</td></tr>
</table>

Employees with no manager: List(Spider-Man, Hulk, Thor, Iron Man)
[success] Total time: 3 s, completed 17/05/2017 2:31:43 PM
```

## Running the application (using the assembled jar file)

First assemble(using [sbt-assembly](https://github.com/sbt/sbt-assembly) plugin) the application with sbt:

```
$ sbt assembly
```
or from sbt console
```
> assembly
```

sbt-assembly will execute all unit tests before packaging the application jar file with all dependencies included.

The generated jar file is placed under target/scala-2.11/Momenton - code challenge-assembly-0.1-SNAPSHOT.jar

I have renamed the jar file to app.jar for convenience

To execute the generated jar file, go to the directory where the jar file is generated:
```
$ scala app.jar
```

## Running the unit tests

```
$ sbt test
```

### Run test with enabled coverage

```
$ sbt clean coverage test
```

### Generate the coverage reports

```
$ sbt coverageReport
```
The generated coverage reports(in HTML) can be found in target/scala-2.11/scoverage-report

## Built With

* [Scala](https://www.scala-lang.org/) - Object-Oriented Meets Functional
* [sbt](http://www.scala-sbt.org/) - The interactive build tool
* [Intellij IDE](https://www.jetbrains.com/idea/) - Capable and Ergonomic IDE for JVM

## Authors

* **Hanyun(Allan) Gong** - [Linkedin](https://www.linkedin.com/in/hanyung-gong-80101028/)

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
