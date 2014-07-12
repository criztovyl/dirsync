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

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.joinout.criztovyl.commandLineParameters.ParameterAction;
import de.joinout.criztovyl.commandLineParameters.Parameters;
import de.joinout.criztovyl.dirsync.Main;
import de.joinout.criztovyl.tools.directory.index.DirectoryIndex;

/**
 * Represents the list parameter
 * 
 * @author criztovyl
 * 
 */
public class List{
	
	// action

	public static final ParameterAction ACTION = new ParameterAction() {

		@Override
		public void run(Parameters param) {

			final Logger logger = LogManager.getLogger();

			// Assign source and target directories to variables, repeating in
			// every time they are required produces too long lines
			final DirectoryIndex target = Main.getMain().getTargetDir();

			final ArrayList<DirectoryIndex> source = Main.getMain()
					.getSourceDirs();

			// Only write source directory info when there are source
			// directories
			if (source != null && !source.isEmpty() && logger.isInfoEnabled())
				logger.info("Listing source directories...");

			// Iterate over each source directory and print out the directory
			// list with a preceding line with the directory path
			for (final DirectoryIndex index : source) {
				if (logger.isInfoEnabled())
					logger.info("Listing source directory {}: ", index
							.getDirectory().getFile().getAbsolutePath());
				System.out.println(index.getListString());
			}

			// Check if target is not empty. List is styled like above.
			if (target != null) {
				if (logger.isInfoEnabled())
					logger.info("Listing target directory {}", target
							.getDirectory().getFile().getAbsolutePath());
				System.out.println(target.getListString());
			}
		}

		@Override
		public String getDescription() {
			// TODO Auto-generated method stub
			return Main.getString("List.Description", Main.getString("Name"));
		}
	};

}
