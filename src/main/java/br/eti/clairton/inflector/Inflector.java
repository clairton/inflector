package br.eti.clairton.inflector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Controla a pluralização/singuralização para Português.
 * 
 * @author Clairton Rodrigo Heinzen<clairton.rodrigo@gmail.com>
 */
public class Inflector {
	private final Collection<String> uncountable = new ArrayList<>();

	private final Collection<Rule> singular = new ArrayList<>();

	private final Map<String, String> irregular = new HashMap<>();

	private final Collection<Rule> plural = new ArrayList<>();

	private static final ThreadLocal<Map<Locale, Inflector>> INFLECTORS = new ThreadLocal<Map<Locale, Inflector>>() {
		private final Map<Locale, Inflector> map = new HashMap<>();
		{
			final Inflector infletor = new Inflector();
			infletor.addPlural("ção$", "ções");
			infletor.addPlural("cao$", "coes");
			infletor.addPlural("z$", "zes");
			infletor.addPlural("$", "s");
			infletor.addSingular("ções$", "ção");
			infletor.addSingular("coes$", "cao");
			infletor.addSingular("zes$", "z");
			infletor.addSingular("s$", "");
			map.put(Locale.pt_BR, infletor);
		}

		@Override
		public Map<Locale, Inflector> get() {
			return map;
		}
	};

	public String singularize(final String orig) {
		if (uncountable.contains(orig)) {
			return orig;
		}
		for (final Entry<String, String> entry : irregular.entrySet()) {
			if (entry.getValue().equals(orig)) {
				return entry.getKey();
			}
		}
		for (final Rule r : singular) {
			final Matcher m = r.regex.matcher(orig);
			if (m.find()) {
				return m.replaceAll(r.replacement);
			}
		}
		return orig;
	}

	public String pluralize(final String orig) {
		if (uncountable.contains(orig)) {
			return orig;
		}
		if (irregular.containsKey(orig)) {
			return irregular.get(orig);
		}
		for (final Rule r : plural) {
			final Matcher m = r.regex.matcher(orig);
			if (m.find()) {
				return m.replaceAll(r.replacement);
			}
		}
		return orig;
	}

	public String capitalize(final String orig) {
		return Character.toUpperCase(orig.charAt(0)) + orig.substring(1);
	}

	public String uncapitalize(final String orig) {
		return Character.toLowerCase(orig.charAt(0)) + orig.substring(1);
	}

	public void addPlural(final String regex, final String replacement) {
		plural.add(new Rule(regex, replacement));
	}

	public void addSingular(final String regex, final String replacement) {
		singular.add(new Rule(regex, replacement));
	}

	public void addIrregular(final String orig, final String replacement) {
		irregular.put(orig, replacement);
	}

	public void addUncountable(final String[] words) {
		uncountable.addAll(Arrays.asList(words));
	}

	public void addUncountable(final String word) {
		uncountable.add(word);
	}

	private static class Rule {
		private final Pattern regex;

		private final String replacement;

		public Rule(final String regex, final String replacement) {
			this.regex = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
			this.replacement = replacement;
		}
	}

	public static Inflector getForLocale(final Locale language) {
		final Inflector inflector;
		if (INFLECTORS.get().containsKey(language)) {
			inflector = INFLECTORS.get().get(language);
		} else {
			inflector = new Inflector();
			INFLECTORS.get().put(language, inflector);
		}
		return inflector;
	}
}
