# softAIs 🍦 - Spring Boot Banner 

This library contains the special softAIs banner to include in spring boot applications. 

Include this lib as dependency in your Application ( pom.xml or build.gradle ):

e.g.
```
<dependencies>

    <dependency>
        <groupId>io.softais</groupId>
        <artifactId>spring-boot-banner</artifactId>
        <version>X.Y.Z</version>
    </dependency>

</dependencies>
```

To use the Banner adjust your Spring Boot main method like this:

```
@SpringBootApplication
public class MySpringBootApp {

	public static void main(String[] args) {
		new SpringApplicationBuilder()
				.sources(MySpringBootApp.class)
				.banner(new SoftAIsBanner())
				.run(args);
	}

}
```

If you like to use an additional (common app banner), then put a file appBanner.txt in the classpath.