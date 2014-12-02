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
	}

	@Test
	public void testPluralize() {
		assertEquals("operacoes", inflector.pluralize("operacao"));
		assertEquals("cruzes", inflector.pluralize("cruz"));
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
