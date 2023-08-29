package br.com.sapiencia.command.configurations.roles

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl

@Configuration
class RoleHierarchyConfiguration {

    @Bean
    fun roleHierarchy() = RoleHierarchyImpl()
        .setHierarchy(
            """
            ADMIN > COMUM
            """.trimIndent()
        )
}
