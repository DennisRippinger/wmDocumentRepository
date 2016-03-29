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
package de.drippinger.serviceExtractor.metaModel;

import de.drippinger.serviceExtractor.exception.ExtractorException;
import de.drippinger.serviceExtractor.pojo.DataType;
import de.drippinger.serviceExtractor.pojo.MetaModel;
import de.drippinger.serviceExtractor.util.XmlUtil;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.Node;

import java.io.File;
import java.util.List;

/**
 * NdfDeflector
 *
 * @author Dennis Rippinger
 */
@Slf4j
public class NdfDeflector {

	public List<MetaModel> collectMetaModels(List<File> ndfFiles) {

		for (File ndfFile : ndfFiles) {
			try {
				Document document = XmlUtil.parse(ndfFile);

				DataType dataType = deflectParser(document, ndfFile);

				// TODO


			} catch (ExtractorException e) {
				log.warn(e.getMessage());
			}
		}

		return null;
	}

	DataType deflectParser(Document document, File ndfFile) {
		Node node = document.selectSingleNode("//value[@name='node_type']");
		if (node == null) {
			log.warn("NodeType is null for file: {}", ndfFile.getAbsolutePath());
			return DataType.NONE;
		}
		switch (node.getText()) {
			case "interface":
				return DataType.NONE;
			case "record":
				return DataType.DOCUMENT;
			case "flow":
				return DataType.SERVICE;
		}

		log.warn("Unknown node type: {}", node.getText());
		return DataType.NONE;
	}


}
