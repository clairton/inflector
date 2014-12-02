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
		Inflector inflector =  Inflector.getForLocale(language);
		//adicionando novas transformações
		inflector.addPlural("ção$", "ções");
		inflector.addSingular("ções$", "ção");
		return inflector;
	}
```

	Injetando no bean.
```	
	@Inject @Language Inflector inflector
```

	Adicionando novos idiomas.
```	
	final Inflector inflector = new Inflector();
	inflector.addPlural("ção$", "ções");
	inflector.addSingular("ções$", "ção");
	addLocale("en", inflector);
```