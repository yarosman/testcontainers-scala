package com.dimafeng.testcontainers.scalatest

import java.io.File

import com.dimafeng.testcontainers.containers.SeleniumContainer
import org.openqa.selenium.WebDriver
import org.openqa.selenium.remote.DesiredCapabilities
import org.scalatest.Suite
import org.testcontainers.containers.BrowserWebDriverContainer

trait SeleniumTestContainerSuite extends ForEachTestContainer {
  self: Suite =>

  def desiredCapabilities: DesiredCapabilities

  def recordingMode: (BrowserWebDriverContainer.VncRecordingMode, File) = null

  val container = SeleniumContainer(desiredCapabilities, recordingMode)

  implicit def webDriver: WebDriver = container.webDriver

}


