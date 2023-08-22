package br.com.sapiencia.command.exception

class OperacaoInvalidaException(
    override val message: String = "Operação Invalida",
    override val statusCode: Int = 400
) : BaseException(message, statusCode)
