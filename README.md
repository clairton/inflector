# Transforma palavras no singular para o plural e vice-versa
	Segue alguma informações interessantes sobre o projeto.
```
	Inflector inflector = Inflector.getForLocale(Locale.pt_BR)
	inflector.pluralize("coração") > "corações";
```
	
# Usando no cdi
	Produces
```
	@Produces
	public Inflector getForLanguage(final InjectionPoint ip){
		final Locale language;
		if (ip.getAnnotated().isAnnotationPresent(Language.class)) {
			language = ip.getAnnotated().getAnnotation(Language.class).value();
		} else {
			language = Locale.pt_BR;
		}
		reutrn Inflector.getForLocale(language);
	}
```

	Injetando no bean.
```	
	@Inject @Language Inflector inflector
```