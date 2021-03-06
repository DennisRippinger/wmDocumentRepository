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
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;

import java.io.File;
import java.util.Collection;

/**
 * FileSearch
 *
 * @author Dennis Rippinger
 */
@Slf4j
public class FileSearch {

	/**
	 * Find all ndf files.
	 *
	 * @param parameter the run parameter
	 * @return a collection of ndf files
	 */
	public static Collection<File> findNdfFiles(@NonNull RunParameter parameter) {
		File rootFolder = new File(parameter.pathToFlows());

		Collection<File> files = FileUtils.listFiles(rootFolder, FileFilterUtils.suffixFileFilter("ndf"), TrueFileFilter.INSTANCE);

		log.debug("Found {} NDF files", files.size());

		return files;
	}
}
