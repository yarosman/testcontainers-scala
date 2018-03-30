package com.dimafeng.testcontainers.specs2

import java.io.File

import com.dimafeng.testcontainers.containers.SeleniumContainer
import org.openqa.selenium.WebDriver
import org.openqa.selenium.remote.DesiredCapabilities
import org.specs2.Specification
import org.testcontainers.containers.BrowserWebDriverContainer


trait SeleniumTestContainerSuite extends ForEachTestContainer {
  self: Specification =>

  def desiredCapabilities: DesiredCapabilities

  def recordingMode: (BrowserWebDriverContainer.VncRecordingMode, File) = null

  val container = SeleniumContainer(desiredCapabilities, recordingMode)

  implicit def webDriver: WebDriver = container.webDriver

}
