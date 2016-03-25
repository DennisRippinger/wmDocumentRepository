/**
 * Copyright (C) 2016 Dennis Rippinger (dennis.rippinger@msg-systems.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.drippinger.serviceExtractor;

import de.drippinger.serviceExtractor.pojo.RunParameter;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.io.FileUtils;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * CLIEntry Tester.
 *
 * @author Dennis Rippinger
 */
public class CLIEntryTest {

	@Rule
	public TemporaryFolder testFolder = new TemporaryFolder();

	private CommandLineParser parser = new DefaultParser();

	@Test
	public void testExtractParameterFromConfigFile() throws Exception {
		File testFile = testFolder.newFile();

		createTestFile(testFile);

		String[] testArgs = {"-c", testFile.getAbsolutePath(), "-s", "password"};

		CommandLine cmd = parser.parse(CLIEntry.createOptions(), testArgs);

		RunParameter parameter = CLIEntry.extractParameterFromConfigFile(cmd);

		assertThat(parameter.hasSufficientData()).isTrue();
		assertThat(parameter.databasePassword()).isEqualTo("myTestPassword");

		parameter.printParameter();
	}


	@Test
	public void testExtractParameterFromCommandline() throws Exception {
		String[] testArgs = {"-d", "Test", "-u", "Test", "-p", "Test", "-f", "Test"};

		CommandLine cmd = parser.parse(CLIEntry.createOptions(), testArgs);

		RunParameter parameter = CLIEntry.extractParameterFromCommandline(cmd);

		assertThat(parameter.hasSufficientData()).isTrue();

		parameter.printParameter();
	}

	private void createTestFile(File testFile) throws IOException {
		StringBuilder sb = new StringBuilder();
		sb.append("datasource.url = jdbc:mysql://localhost/test\n");
		sb.append("datasource.username = TestUser\n");
		sb.append("datasource.password = " + createEncryptedPassword() + "\n");
		sb.append("wm.pathToFlows = jdbc:mysql://localhost/test\n");

		FileUtils.write(testFile, sb.toString());
	}

	private String createEncryptedPassword() {
		StandardPBEStringEncryptor textEncryptor = new StandardPBEStringEncryptor();
		textEncryptor.setPassword("password");

		String myEncryptedText = textEncryptor.encrypt("myTestPassword");

		return String.format("ENC(%s)", myEncryptedText);
	}


}
