package br.com.caelum.agiletickets.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.List;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.junit.Test;

public class EspetaculoTest {

	@Test
	public void deveInformarSeEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertTrue(ivete.Vagas(5));
	}

	@Test
	public void deveInformarSeEhPossivelReservarAQuantidadeExataDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertTrue(ivete.Vagas(6));
	}

	@Test
	public void DeveInformarSeNaoEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertFalse(ivete.Vagas(15));
	}

	@Test
	public void DeveInformarSeEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(4));

		assertTrue(ivete.Vagas(5, 3));
	}

	@Test
	public void DeveInformarSeEhPossivelReservarAQuantidadeExataDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(4));

		assertTrue(ivete.Vagas(10, 3));
	}

	@Test
	public void DeveInformarSeNaoEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(2));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertFalse(ivete.Vagas(5, 3));
	}

	private Sessao sessaoComIngressosSobrando(int quantidade) {
		Sessao sessao = new Sessao();
		sessao.setTotalIngressos(quantidade * 2);
		sessao.setIngressosReservados(quantidade);

		return sessao;
	}
	
	@Test
	public void deveCriarSessoesDiarias()
	{
		Espetaculo espetaculo = new Espetaculo();
		
		List<Sessao> list = espetaculo.criaSessoes(LocalDate.now(), LocalDate.now().plusDays(4), LocalTime.now(), Periodicidade.DIARIA);
		
		assertEquals(4, list.size());
	}
	
	@Test
	public void deveCriarSessoesDiariasMesmoDia()
	{
		Espetaculo espetaculo = new Espetaculo();
		
		List<Sessao> list = espetaculo.criaSessoes(LocalDate.now(), LocalDate.now(), LocalTime.now(), Periodicidade.DIARIA);
		
		assertEquals(1, list.size());
	}
	
	@Test
	public void deveCriarSessoesInicialMenorQueFinal()
	{
		Espetaculo espetaculo = new Espetaculo();
		
		List<Sessao> list = espetaculo.criaSessoes(LocalDate.now().plusDays(8), LocalDate.now(), LocalTime.now(), Periodicidade.DIARIA);
		
		assertEquals(0, list.size());
	}
	
	@Test
	public void deveCriarSessoesSemanaisMesmoDia()
	{
		Espetaculo espetaculo = new Espetaculo();
		
		List<Sessao> list = espetaculo.criaSessoes(LocalDate.now(), LocalDate.now(), LocalTime.now(), Periodicidade.SEMANAL);
		
		assertEquals(1, list.size());
	}
	
	@Test
	public void deveCriar2SessoesSemanais()
	{
		Espetaculo espetaculo = new Espetaculo();
		
		List<Sessao> list = espetaculo.criaSessoes(LocalDate.now(), LocalDate.now().plusDays(8), LocalTime.now(), Periodicidade.SEMANAL);
		
		assertEquals(2, list.size());
	}
}
