#inflector [![Build Status](https://drone.io/github.com/clairton/inflector/status.png)](https://drone.io/github.com/clairton/inflector/latest)
Transforma palavras no singular para o plural e vice-versa
```java
Inflector inflector = Inflector.getForLocale(Locale.pt_BR)
inflector.pluralize("coração") >>>> "corações";
```
	
# Usando no cdi
Produces
```java
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
```java
@Inject @Language Inflector inflector
```

	Adicionando novos idiomas.
```java	
final Inflector inflector = new Inflector();
inflector.addPlural("ção$", "ções");
inflector.addSingular("ções$", "ção");
addLocale("en", inflector);
```	
	Download através do maven, dependência:
```xml
<dependeny>
	<groupId>br.eti.clairton</groupId>
	<artifactId>inflector</artifactId>
	<version>0.1.0</version>
</dependency>
```
	E adicionar o repositório
```xml
<repository>
	<id>mvn-repo-releases</id>
	<url>https://raw.github.com/clairton/mvn-repo/releases</url>
</repository>
```
