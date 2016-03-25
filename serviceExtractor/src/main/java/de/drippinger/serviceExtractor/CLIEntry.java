/**
 * Copyright (C) 2016 Dennis Rippinger (dennis.rippinger@msg-systems.com)
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.drippinger.serviceExtractor;

import de.drippinger.serviceExtractor.exception.ExtractorException;
import de.drippinger.serviceExtractor.pojo.RunParameter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.cli.*;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.properties.EncryptableProperties;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * CLIEntry
 *
 * @author Dennis Rippinger
 */
@Slf4j
public class CLIEntry {

	public static void main(String... args) {

		Banner.printBanner();

		try {

			CommandLineParser parser = new DefaultParser();
			CommandLine cmd = parser.parse(createOptions(), args);
			RunParameter parameter = null;

			if (cmd.hasOption("h")) {
				printHelp();
			} else if (cmd.hasOption("c")) {
				parameter = extractParameterFromConfigFile(cmd);
			} else {
				parameter = extractParameterFromCommandline(cmd);
			}

			if (parameter != null && parameter.hasSufficientData()) {
				parameter.printParameter();
				// TODO Do main stuff
			} else {
				log.warn("Not sufficient parameters provided. exiting");
			}

		} catch (ParseException | ExtractorException e) {
			log.error(e.toString());
		}
	}

	static RunParameter extractParameterFromConfigFile(CommandLine cmd) throws ExtractorException {
		RunParameter parameter = new RunParameter();

		StandardPBEStringEncryptor encryptors = new StandardPBEStringEncryptor();
		encryptors.setPassword(cmd.getOptionValue("s"));
		Properties properties = new EncryptableProperties(encryptors);
		try {
			properties.load(new FileInputStream(cmd.getOptionValue("c")));
			parameter
				.database(properties.getProperty("datasource.url"))
				.databaseUser(properties.getProperty("datasource.username"))
				.databasePassword(properties.getProperty("datasource.password"))
				.pathToFlows(properties.getProperty("wm.pathToFlows"));

		} catch (IOException e) {
			throw new ExtractorException("Could not load property file", e);
		}

		return parameter;

	}

	static RunParameter extractParameterFromCommandline(CommandLine cmd) {
		RunParameter parameter = new RunParameter();
		parameter
			.database(cmd.getOptionValue("d"))
			.databaseUser(cmd.getOptionValue("u"))
			.databasePassword(cmd.getOptionValue("p"))
			.pathToFlows(cmd.getOptionValue("f"));

		return parameter;
	}

	static void printHelp() {
		HelpFormatter helpFormatter = new HelpFormatter();
		helpFormatter.printHelp("serviceExtractor", createOptions());
	}

	static Options createOptions() {
		Options options = new Options();
		options.addOption("h", "help", false, "Prints this message");
		options.addOption("d", "database", true, "The database connection String, should contain the port");
		options.addOption("u", "user", true, "The database user");
		options.addOption("p", "password", true, "The password for the database user");
		options.addOption("f", "flowFiles", true, "The location of the flow files, will be searches recursively");
		options.addOption("c", "configFile", true, "Alternative to providing the input via console, one can also provide a config file. " +
			"Jasypt optional.");
		options.addOption("s", "configFilePassword", true, "The optional password in case the properties are encrypted");


		return options;
	}
}
