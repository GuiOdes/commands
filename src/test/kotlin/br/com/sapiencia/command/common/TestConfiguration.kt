package br.com.sapiencia.command.common

import org.springframework.context.ConfigurableApplicationContext

interface TestConfiguration {
    fun configure(applicationContext: ConfigurableApplicationContext)
}
