package com.dimafeng.testcontainers.integration

import com.dimafeng.testcontainers.scalatest.SeleniumTestContainerSuite
import org.openqa.selenium.remote.DesiredCapabilities
import org.scalatest.FlatSpec
import org.scalatest.selenium.WebBrowser

class SeleniumSpec extends FlatSpec with SeleniumTestContainerSuite with WebBrowser {
  override def desiredCapabilities: DesiredCapabilities = DesiredCapabilities.chrome()

  "Browser" should "show google" in {
    go to "http://google.com"
  }
}
