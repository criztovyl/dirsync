/**
    This is a Program that updates a folder with the data from another folder.
    Copyright (C) 2014 Christoph "criztovyl" Schulz

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package de.joinout.criztovyl.dirsync.commandline.actions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.joinout.criztovyl.commandLineParameters.ParameterAction;
import de.joinout.criztovyl.commandLineParameters.Parameters;
import de.joinout.criztovyl.dirsync.Main;
import de.joinout.criztovyl.tools.directory.index.DirectoryIndex;
import de.joinout.criztovyl.tools.file.Path;
import de.joinout.criztovyl.tools.file.version.Versions;

/**
 * Represents the recover parameter
 * 
 * @author criztovyl
 * 
 */
public class Recover {

	// action

	public static final ParameterAction ACTION = new ParameterAction() {

		@Override
		public void run(Parameters param) {

			final Logger logger = LogManager.getLogger();

			if (logger.isTraceEnabled())
				logger.trace(Main.getString("Recover.IterateArgumentsMsg"));

			for (final String path : param.getArguments()) {

				final Path file = new Path(path);
				final Versions versions = Main.getMain().getTargetDir()
						.getVersions();

				// Check if file is in versions directory
				if (file.isInDirectory(versions.getPath())) {

					if (logger.isTraceEnabled())
						logger.trace(Main.getString(
								"Recover.RecoverVersionMsg", file,
								file.getSuffix()));

					// Restore from special version
					versions.recover(file);
				} else {

					if (logger.isTraceEnabled())
						logger.trace(Main.getString(
								"Recover.RecoverVersionMsg", file, versions
										.get(file) != null ? versions.get(file)
										.getLatestVersion() : "Unknown"));

					// Restore from latest version
					versions.recoverLatest(file);

				}
			}
		}

		@Override
		public String getDescription() {
			return Main.getString("Recover.Description", DirectoryIndex.VERSIONS_DIRECTORY);
		}
	};
}
