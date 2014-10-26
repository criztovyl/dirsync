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

import java.io.IOException;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.joinout.criztovyl.commandLineParameters.ParameterAction;
import de.joinout.criztovyl.commandLineParameters.Parameters;
import de.joinout.criztovyl.dirsync.Main;
import de.joinout.criztovyl.dirsync.DirSyncLicenses;
import de.joinout.criztovyl.dirsync.commandline.Names;
import de.joinout.criztovyl.tools.directory.DirectorySync;
import de.joinout.criztovyl.tools.file.Path;
import de.joinout.criztovyl.tools.licenses.LibraryLicense;

/**
 * @author criztovyl
 * 
 */
public class DirSyncActions extends HashMap<String, ParameterAction> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7280693831665455356L;

	public DirSyncActions() {
		super();

		// Put actions
		put(Names.ACTION_HELP, ACTION_HELP);
		put(Names.ACTION_LICENSE, ACTION_LICENSE);
		put(Names.ACTION_SYNC, ACTION_SYNC);
		put(Names.ACTION_VERSION, ACTION_VERSION);
	}
	
	//Help
	public static ParameterAction ACTION_HELP = new ParameterAction() {

		@Override
		public String getDescription() {
			return Main.getString("Help.Description");
		}

		@Override
		public void run(Parameters param) {
			System.out.println(param.getHelp());
		}
	};

	//License
	public final static ParameterAction ACTION_LICENSE = new ParameterAction() {

		@Override
		public String getDescription() {
			return Main.getString("License.Description");
		}

		@Override
		public void run(Parameters param) {
			DirSyncLicenses tp = new DirSyncLicenses();
			if(param.getArguments().size() == 0){

				System.out.println("Used Third-Party-Software: ");

				for(LibraryLicense l : tp.values())
					System.out.println("\t\t" + l.getShort());
			}
			else if(param.getArguments().size() == 1){
				String arg = (String) param.getArguments().get(0);
				System.out.println(tp.getLibrary(arg) == null ?  arg + " not found!" : tp.getLibrary(arg).getText());
			}
			else{
				for(String arg : param.getArguments())
					System.out.println(tp.getLibrary(arg) == null ?  arg + " not found!" : tp.getLibrary(arg).getShort());
			}

		}
	};

	// Sync
	public final static ParameterAction ACTION_SYNC = new ParameterAction() {

		@Override
		public String getDescription() {
			return Main.getString("Sync.Description");
		}

		@Override
		public void run(Parameters param) {

			//Set up logger
			final Logger logger = LogManager.getLogger();

			//Set up base directory			
			Path base = new Path(param.get(Names.PARAM_DIRECTORY).get(0));

			//Set up regular expression
			String regex = param.get(Names.PARAM_REGEX).wasPresent() ? param.get(Names.PARAM_REGEX).get(0) : "";

			//Check how to use arguments
			if(param.getArguments().size() == 1){

				//Simply sync base with branch.

				Path branch = new Path(param.getArguments().get(0).toString());

				try{

					DirectorySync ds = new DirectorySync(base, branch, regex);


					//logger.debug("Current: {}", ds.getCurrentList());
					//logger.debug("Previous: {}", ds.getPreviousList());

					if(!(ds.getChangedFiles().size() > 500))
						logger.info("Changed: {}", ds.getChangedFiles());
					else
						logger.info("More than 500 files changed, will not write out to console.");
					
					if(!(ds.getNewFiles().size() > 500))
						logger.info("New    : {}", ds.getNewFiles());
					else
						logger.info("More than 500 new files, will not write out to console.");
					
					if(!(ds.getDeletedFiles().size() > 500))
						logger.info("Deleted: {}", ds.getDeletedFiles());
					else
						logger.info("More than 500 files deleted, will not write out to console.");

					if(param.containsKey("interactive"))
						if(param.get("interactive").wasPresent()){
							System.out.println("Press a key to start sync...");
							System.in.read();
						}
							
					ds.sync();

					ds.save();
				} catch(IOException e){
					logger.catching(e);
					logger.warn("Unable to create real path of base or branch directory! {}", e.toString());
				}

			}
			else if(param.getArguments().size() >= 2){

				for(String branchS : param.get(Names.PARAM_DIRECTORY)){

					Path branch = new Path(branchS);

					try{

						DirectorySync ds = new DirectorySync(base.append(branch.getBasename()), branch, regex);

						logger.debug(ds.getCurrentList());
						logger.debug(ds.getPreviousList());

						logger.info("Changed: {}", ds.getChangedFiles());
						logger.info("New    : {}", ds.getNewFiles());
						logger.info("Deleted: {}", ds.getDeletedFiles());

						ds.sync();

						ds.save();
					} catch(IOException e){
						logger.catching(e);
						logger.warn("Unable to create real path of base or branch directory! {}", e.toString());
					}
				}
			}

			if(logger.isInfoEnabled())
				logger.info("Done.");

		}
	};
	
	public static ParameterAction ACTION_VERSION = new ParameterAction() {
		
		public void run(Parameters param) {
			System.out.println("DirSync Version " + Main.getVersion());
			
		}
		
		public String getDescription() {
			// TODO Auto-generated method stub
			return null;
		}
	};

}
