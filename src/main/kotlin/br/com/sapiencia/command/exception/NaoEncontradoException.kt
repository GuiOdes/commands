package br.com.sapiencia.command.exception

import kotlin.reflect.KClass

class NaoEncontradoException(
    val classe: KClass<*>,
    override val message: String? = "Entidade não encontrada: ${classe.simpleName}",
    override val statusCode: Int = 404
) : BaseException(message, statusCode)
