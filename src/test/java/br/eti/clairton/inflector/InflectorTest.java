package br.eti.clairton.inflector;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class InflectorTest {
	private Inflector inflector = Inflector.getForLocale(Locale.pt_BR);

	@Test
	public void testSingularize() {
		assertEquals("operacao", inflector.singularize("operacoes"));
		assertEquals("recurso", inflector.singularize("recursos"));
		assertEquals("cruz", inflector.singularize("cruzes"));
		assertEquals("seletor", inflector.singularize("seletores"));
		assertEquals("contabil", inflector.singularize("contabeis"));
		assertEquals("civil", inflector.singularize("civis"));
	}

	@Test
	public void testUnderscore() {
		assertEquals("operacao_lava_jato", inflector.underscore("OperacaoLavaJato"));
		assertEquals("operacao_lava_jato", inflector.underscore("operacaoLavaJato"));
		assertEquals("operacao_lava_jat_o", inflector.underscore("operacaoLavaJatO"));
	}
	
	@Test
	public void testDasherize() {
		assertEquals("operacao-lava-jato", inflector.dasherize("OperacaoLavaJato"));
		assertEquals("operacao-lava-jato", inflector.dasherize("operacaoLavaJato"));
		assertEquals("operacao-lava-jat-o", inflector.dasherize("operacaoLavaJatO"));
	}

	@Test
	public void testPluralize() {
		assertEquals("operacoes", inflector.pluralize("operacao"));
		assertEquals("cruzes", inflector.pluralize("cruz"));
		assertEquals("seletores", inflector.pluralize("seletor"));
		assertEquals("contabeis", inflector.pluralize("contabil"));
		assertEquals("civis", inflector.pluralize("civil"));
	}

	@Test
	public void testCapitalize() {
		assertEquals("Ab", inflector.capitalize("ab"));
	}

	@Test
	public void testUncapitalize() {
		assertEquals("aB", inflector.uncapitalize("AB"));
	}

}
