package br.com.sapiencia.command.exception

import kotlin.reflect.KClass

class NotFoundException(
    val classe: KClass<*>,
    override val message: String? = "Entity not found: ${classe.simpleName}",
    override val statusCode: Int = 400
) : BaseException(message, statusCode)
