package io.softais.banner.spring.boot;

import org.springframework.boot.Banner;
import org.springframework.boot.ResourceBanner;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.PrintStream;
import java.util.Optional;

/**
 * Custom Spring Boot banner implementation that displays the softAIs logo during application startup.
 *
 * <p>The banner consists of a pre-defined ASCII art logo stored as a text constant. Additionally,
 * it supports loading a supplementary banner from a classpath resource file.</p>
 *
 * <p>Usage example:</p>
 * <pre>
 * new SpringApplicationBuilder()
 *     .sources(MySpringBootApp.class)
 *     .banner(new SoftAIsBanner())
 *     .run(args);
 * </pre>
 *
 * <p>To include an additional application-specific banner, place a text file named "appBanner.txt"
 * (or specified custom filename) in the classpath. If found, this banner will be displayed
 * after the softAIs logo.</p>
 *
 * @see org.springframework.boot.Banner
 * @see org.springframework.boot.ResourceBanner
 */
public final class SoftAIsBanner implements Banner {

	private static final String ADDITIONAL_APP_BANNER = "appBanner.txt";

	private final String additionalAppBanner;

	/**
	 * The default constructor for the SoftAIsBanner class.
	 * This constructor initializes an instance of the SoftAIsBanner with default settings,
	 * allowing for the default banner text to be used when printed.
	 */
	public SoftAIsBanner() {
		this(ADDITIONAL_APP_BANNER);
	}

	/**
	 * Constructs an instance of the SoftAIsBanner class with a custom banner text file.
	 * This constructor allows the user to specify the path to a banner text file
	 * located in the classpath, which will be used as the additional application banner.
	 *
	 * @param bannerTxtInClasspath the path to the custom banner text file in the classpath
	 */
	public SoftAIsBanner(String bannerTxtInClasspath) {
		this.additionalAppBanner = bannerTxtInClasspath;
	}

	@Override
	public void printBanner(Environment environment, Class<?> sourceClass, PrintStream printStream) {
		printStream.println();
		printStream.println(io.softais.banner.Banner.SOFTAIS_BANNER);
		printStream.println();

		getAdditionalAppBanner()
				.ifPresent(banner -> banner.printBanner(environment, sourceClass, printStream));

		printStream.println();
	}

	private Optional<Banner> getAdditionalAppBanner() {
		Resource resource = new ClassPathResource(additionalAppBanner);
		if (resource.exists()) {
			return Optional.of(new ResourceBanner(resource));
		}
		return Optional.empty();
	}

}
