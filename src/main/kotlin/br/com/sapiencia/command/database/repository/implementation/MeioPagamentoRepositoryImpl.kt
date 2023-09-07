package br.com.sapiencia.command.database.repository.implementation

import br.com.sapiencia.command.database.entity.MeioPagamento
import br.com.sapiencia.command.database.repository.MeioPagamentoRepository
import br.com.sapiencia.command.database.repository.data.MeioPagamentoJpaRepository
import br.com.sapiencia.command.model.MeioPagamentoModel
import org.springframework.stereotype.Component

@Component
class MeioPagamentoRepositoryImpl(
    private val meioPagamentoJpaRepository: MeioPagamentoJpaRepository
) : MeioPagamentoRepository {
    override fun salvar(
        nomeMeioPagamento: String
    ) = meioPagamentoJpaRepository.save(MeioPagamento.of(nomeMeioPagamento)).toModel()
    override fun listarTodos(): List<MeioPagamentoModel> = meioPagamentoJpaRepository.findAll().map { it.toModel() }
    override fun procurarPorNome(nomeMeioPagamento: String): MeioPagamentoModel? {
        return meioPagamentoJpaRepository.findByNome(nomeMeioPagamento)?.toModel()
    }
}
