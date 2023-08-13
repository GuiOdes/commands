package br.com.sapiencia.command.exception

open class BaseException(
    override val message: String?,
    open val statusCode: Int = 500
) : RuntimeException(message)
