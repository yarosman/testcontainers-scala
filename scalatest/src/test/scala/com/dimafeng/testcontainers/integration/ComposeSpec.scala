package com.dimafeng.testcontainers.integration

import java.io.File

import com.dimafeng.testcontainers.DockerComposeContainer
import com.dimafeng.testcontainers.scalatest.ForAllTestContainer
import org.scalatest.FlatSpec

class ComposeSpec extends FlatSpec with ForAllTestContainer {
  private val redisPort = 6379

  override val container = DockerComposeContainer(new File("src/test/resources/docker-compose.yml"), exposedService = Map("redis_1" -> redisPort))

  "DockerComposeContainer" should "retrieve non-0 port for any of services" in {
    assert(container.getServicePort("redis_1", redisPort) > 0)
  }
}
