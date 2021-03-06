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
package de.drippinger.serviceExtractor.pojo;

import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * Contains parameter for the code generator.
 *
 * @author Dennis Rippinger
 */
@Data
@Slf4j
@Accessors(fluent = true, chain = true)
public class RunParameter {

	private String database;

	private String databaseUser;

	private String databasePassword;

	private String pathToFlows;

	public boolean hasSufficientData() {
		return StringUtils.isNotEmpty(database) //
			&& StringUtils.isNotEmpty(databaseUser) //
			&& StringUtils.isNotEmpty(pathToFlows) //
			&& StringUtils.isNotEmpty(databasePassword);
	}

	public void printParameter() {
		log.info("Database: {}", database);
		log.info("Database User: {}", databaseUser);
		log.info("Database Password: {}", StringUtils.repeat("*", databasePassword.length()));
		log.info("Path to Flows: {}", pathToFlows);
	}

}
