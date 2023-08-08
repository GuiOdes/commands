package br.com.sapiencia.command.common

import io.mockk.clearAllMocks
import org.junit.jupiter.api.BeforeEach
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import org.testcontainers.junit.jupiter.Testcontainers

@Testcontainers
@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@AutoConfigureMockMvc
@ContextConfiguration(
    initializers = [
        IntegrationTestsInitializer::class,
    ]
)
class IntegrationTests {

    @BeforeEach
    fun setUp() {
        clearAllMocks()
    }
}
