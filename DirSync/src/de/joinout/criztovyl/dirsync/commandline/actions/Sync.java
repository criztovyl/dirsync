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
import de.joinout.criztovyl.dirsync.commandline.Names;
import de.joinout.criztovyl.tools.directory.DirectorySync;
import de.joinout.criztovyl.tools.file.Path;

/**
 * Represents the help parameter
 * 
 * @author criztovyl
 * 
 */
public class Sync {

	// action
	public final static ParameterAction ACTION = new ParameterAction() {

		@Override
		public String getDescription() {
			return Main.getString("Sync.Description");
		}

		@Override
		public void run(Parameters param) {

			final Logger logger = LogManager.getLogger();
			
			Path base = new Path(param.get(Names.PARAM_DIRECTORY).get(0));
			
			String regex = param.get(Names.PARAM_REGEX).wasPresent() ? param.get(Names.PARAM_REGEX).get(0) : "";
			
			if(param.getArguments().size() == 1){
				
				Path branch = new Path(param.getArguments().get(0).toString());
				
				DirectorySync ds = new DirectorySync(base, branch, regex);
				
				logger.debug(ds.getCurrentList());
				logger.debug(ds.getPreviousList());
				
				logger.info("Changed: {}", ds.getChangedFiles());
				logger.info("New    : {}", ds.getNewFiles());
				logger.info("Deleted: {}", ds.getDeletedFiles());
				
				ds.sync();
				
				ds.save();
				
			}
			else if(param.getArguments().size() >= 2){
				
				for(String branchS : param.get(Names.PARAM_DIRECTORY)){
					
					Path branch = new Path(branchS);
					
					new DirectorySync(base.append(branch.getBasename()), branch, regex).sync();
				}
			}

			if(logger.isInfoEnabled())
				logger.info("Done.");

		}
	};

}
