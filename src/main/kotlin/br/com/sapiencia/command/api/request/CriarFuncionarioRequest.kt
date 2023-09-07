package br.com.sapiencia.command.api.request

data class CriarFuncionarioRequest(
    val matricula: String,
    val nome: String,
    val cpf: String,
    val telefone: String,
    val email: String,
    val cargoName: String,
    val senha: String
)
