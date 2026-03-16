package com.orcamentoevendas.ui.viewmodel

import com.orcamentoevendas.data.local.dao.OrcamentoDao
import com.orcamentoevendas.data.local.entity.OrcamentoEntity
import com.orcamentoevendas.data.repository.OrcamentoRepository
import com.orcamentoevendas.testutils.MainDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CalculadoraViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Test
    fun calcularResultado_chapa_atualizaResultadoComQuantidade() = runTest {
        val dao = FakeOrcamentoDao()
        val repository = OrcamentoRepository(dao)
        val viewModel = CalculadoraViewModel(repository)

        viewModel.atualizarTipoPeca("Chapa")
        viewModel.atualizarComprimento("2")
        viewModel.atualizarLargura("1")
        viewModel.atualizarEspessura("10")
        viewModel.atualizarQuantidade("3")

        viewModel.calcularResultado()

        val resultado = viewModel.uiState.value.resultadoAtual
        assertNotNull(resultado)
        assertEquals(471.0, resultado!!, 0.0001)
    }

    @Test
    fun salvarResultadoAtual_insereOrcamentoComTotais() = runTest {
        val dao = FakeOrcamentoDao()
        val repository = OrcamentoRepository(dao)
        val viewModel = CalculadoraViewModel(repository)

        viewModel.atualizarTipoPeca("Chapa")
        viewModel.atualizarComprimento("2")
        viewModel.atualizarLargura("1")
        viewModel.atualizarEspessura("10")
        viewModel.atualizarQuantidade("1")
        viewModel.atualizarPrecoKg("2")

        viewModel.calcularResultado()
        viewModel.salvarResultadoAtual()

        advanceUntilIdle()

        val salvo = dao.salvos.single()
        assertEquals("Chapa", salvo.tipoPeca)
        assertEquals(157.0, salvo.pesoTotal, 0.0001)
        assertEquals(314.0, salvo.valorTotal, 0.0001)
    }

    private class FakeOrcamentoDao : OrcamentoDao {
        private val historicoFlow = MutableStateFlow<List<OrcamentoEntity>>(emptyList())
        val salvos = mutableListOf<OrcamentoEntity>()

        override suspend fun inserir(orcamento: OrcamentoEntity) {
            salvos += orcamento
            historicoFlow.value = salvos.toList()
        }

        override fun listarHistorico(): Flow<List<OrcamentoEntity>> = historicoFlow

        override suspend fun limparHistorico() {
            salvos.clear()
            historicoFlow.value = emptyList()
        }
    }
}
