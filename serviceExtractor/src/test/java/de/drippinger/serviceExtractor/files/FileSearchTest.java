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
package de.drippinger.serviceExtractor.files;

import de.drippinger.serviceExtractor.pojo.RunParameter;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * FileSearch Tester.
 *
 * @author Dennis Rippinger
 */
public class FileSearchTest {

	@Rule
	public TemporaryFolder testFolder = new TemporaryFolder();


	@Before
	public void prepareFolder() throws IOException {
		testFolder.create();
		testFolder.newFile("test.ndf");
	}

	@Test
	public void testFindNdfFiles() throws Exception {
		RunParameter parameter = new RunParameter();
		parameter.pathToFlows(testFolder.getRoot().getAbsolutePath());
		Collection<File> ndfFiles = FileSearch.findNdfFiles(parameter);

		assertThat(ndfFiles).isNotEmpty();
	}

}
