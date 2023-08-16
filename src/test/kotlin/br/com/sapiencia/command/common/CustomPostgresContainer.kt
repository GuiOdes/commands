package br.com.sapiencia.command.common

import org.flywaydb.core.Flyway
import org.springframework.boot.test.util.TestPropertyValues
import org.springframework.context.ConfigurableApplicationContext
import org.testcontainers.containers.PostgreSQLContainer

class CustomPostgresContainer : PostgreSQLContainer<CustomPostgresContainer>("postgres:14-alpine"), TestConfiguration {

    init {
        withUsername("app_command")
        withPassword("app_command")
        withDatabaseName("command")

        start()

        Flyway
            .configure()
            .dataSource(jdbcUrl, username, password)
            .locations("classpath:db/migration", "classpath:db/scripts")
            .load()
            .migrate()
    }

    override fun configure(applicationContext: ConfigurableApplicationContext) {
        TestPropertyValues.of("spring.datasource.url=${this.jdbcUrl}").applyTo(applicationContext)
    }
}
