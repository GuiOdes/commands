package br.com.sapiencia.command.database.entity

import br.com.sapiencia.command.api.request.CriarFuncionarioRequest
import br.com.sapiencia.command.model.FuncionarioModel
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import org.hibernate.annotations.Cascade
import org.hibernate.annotations.CascadeType
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Where
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.UUID

@Entity
@Table
@SQLDelete(
    sql = "UPDATE funcionario SET deletado = true WHERE id = ? AND deletado = false"
)
@Where(clause = "deletado=false")
data class Funcionario(
    @Id
    @GeneratedValue(generator = "uuid-hibernate-generator")
    @GenericGenerator(name = "uuid-hibernate-generator", strategy = "org.hibernate.id.UUIDGenerator")
    val id: UUID? = null,

    val matricula: String,

    val nome: String,

    val cpf: String,

    val telefone: String,

    val email: String,

    val deletado: Boolean = false,

    @ManyToOne
    @JoinColumn(name = "cargo_id")
    val cargo: Cargo,

    @OneToOne
    @JoinColumn(name = "login_id")
    @Cascade(CascadeType.ALL)
    val login: Login
) : UserDetails {

    fun toModel() = FuncionarioModel(
        id = id,
        matricula = matricula,
        nome = nome,
        cpf = cpf,
        telefone = telefone,
        email = email,
        deletado = deletado,
        cargo = cargo.toModel(),
        login = login.toModel()
    )

    companion object {
        fun of(
            funcionarioModel: FuncionarioModel
        ) = Funcionario(
            id = funcionarioModel.id,
            matricula = funcionarioModel.matricula,
            nome = funcionarioModel.nome,
            cpf = funcionarioModel.cpf,
            telefone = funcionarioModel.telefone,
            email = funcionarioModel.email,
            deletado = funcionarioModel.deletado,
            cargo = Cargo.of(funcionarioModel.cargo),
            login = Login.of(funcionarioModel.login)
        )

        fun of(
            request: CriarFuncionarioRequest,
            cargo: Cargo
        ) = Funcionario(
            matricula = request.matricula,
            nome = request.nome,
            cpf = request.cpf,
            telefone = request.telefone,
            email = request.email,
            cargo = cargo,
            login = Login(
                usuario = request.cpf,
                senha = request.senha
            )
        )
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return mutableListOf(SimpleGrantedAuthority(cargo.nome))
    }

    override fun getPassword() = login.senha

    override fun getUsername() = cpf

    override fun isAccountNonExpired() = true

    override fun isAccountNonLocked() = true

    override fun isCredentialsNonExpired() = true

    override fun isEnabled() = true
}
