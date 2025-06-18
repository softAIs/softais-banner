package io.softais.banner.spring.boot;

import org.junit.jupiter.api.Test;
import org.springframework.core.env.Environment;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import static io.softais.banner.Banner.SOFTAIS_BANNER;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class SoftAIsBannerTest {

	@Test
	void testPrintBannerWithDefaultBanner() {
		Environment environment = mock(Environment.class);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(outputStream);
		SoftAIsBanner banner = new SoftAIsBanner();

		banner.printBanner(environment, null, printStream);

		String bannerOutput = outputStream.toString();
		assertTrue(bannerOutput.contains(SOFTAIS_BANNER), "Default banner text is missing.");
	}

	@Test
	void testPrintBannerWithCustomBannerFileExists() {
		Environment environment = mock(Environment.class);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(outputStream);
		when(environment.getProperty("spring.banner.charset", Charset.class, StandardCharsets.UTF_8)).thenReturn(StandardCharsets.UTF_8);
		// Here, "customBanner.txt" must exist in your project's classpath for this test to pass
		SoftAIsBanner banner = new SoftAIsBanner("customBanner.txt");

		banner.printBanner(environment, null, printStream);

		String bannerOutput = outputStream.toString();
		assertTrue(bannerOutput.contains(SOFTAIS_BANNER), "Default banner text is missing.");
		assertTrue(bannerOutput.contains("This is a custom banner"), "Custom banner text is missing.");
	}

	@Test
	void testPrintBannerWithCustomBannerFileDoesNotExist() {
		Environment environment = mock(Environment.class);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(outputStream);
		SoftAIsBanner banner = new SoftAIsBanner("nonExistentBanner.txt");

		banner.printBanner(environment, null, printStream);

		String bannerOutput = outputStream.toString();
		assertTrue(bannerOutput.contains(SOFTAIS_BANNER), "Default banner text is missing.");
	}

}
