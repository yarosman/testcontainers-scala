package com.dimafeng.testcontainers.specs2

import com.dimafeng.testcontainers.Container
import org.junit.runner.Description
import org.specs2.Specification
import org.specs2.specification.BeforeAfterEach

trait ForEachTestContainer extends BeforeAfterEach {
  self: Specification =>

  val container: Container

  implicit private val suiteDescription = Description.createSuiteDescription(self.getClass)

  override protected def before: Any = {
    self.before
    container.starting()
    afterStart()
  }

  override protected def after: Any = {
    self.after
    beforeStop()
  }

  def afterStart(): Unit = {}

  def beforeStop(): Unit = {}

}
