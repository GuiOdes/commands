package br.com.sapiencia.command

import br.com.sapiencia.command.common.IntegrationTests
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CommandApplicationTests : IntegrationTests() {

    @Test
    fun contextLoads() {
        println("Hello, world!")
    }
}
