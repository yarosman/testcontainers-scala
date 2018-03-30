package com.dimafeng.testcontainers.integration

import java.sql.DriverManager

import com.dimafeng.testcontainers.containers.MySQLContainer
import com.dimafeng.testcontainers.scalatest.ForAllTestContainer
import org.scalatest.FlatSpec

class MysqlSpec extends FlatSpec with ForAllTestContainer {

  override val container = MySQLContainer()

  "Mysql container" should "be started" in {
    Class.forName(container.driverClassName)
    val connection = DriverManager.getConnection(container.jdbcUrl, container.username, container.password)

    val prepareStatement = connection.prepareStatement("select 1")
    try {
      val resultSet = prepareStatement.executeQuery()
      resultSet.next()
      assert(1 == resultSet.getInt(1))
      resultSet.close()
    } finally {
      prepareStatement.close()
    }

    connection.close()
  }
}
