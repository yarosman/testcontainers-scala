package com.dimafeng.testcontainers.scalatest

import com.dimafeng.testcontainers.Container
import org.junit.runner.Description
import org.scalatest._

trait ForAllTestContainer extends SuiteMixin {
  self: Suite =>

  val container: Container

  implicit private val suiteDescription = Description.createSuiteDescription(self.getClass)

  abstract override def run(testName: Option[String], args: Args): Status = {
    if (expectedTestCount(args.filter) == 0) {
      new CompositeStatus(Set.empty)
    } else {
      container.starting()
      try {
        afterStart()
        super.run(testName, args)
      } finally {
        try {
          beforeStop()
        }
        finally {
          container.finished()
        }
      }
    }
  }

  def afterStart(): Unit = {}

  def beforeStop(): Unit = {}
}
