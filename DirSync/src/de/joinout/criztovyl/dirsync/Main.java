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
package de.joinout.criztovyl.dirsync;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.joinout.criztovyl.commandLineParameters.ParameterParser;
import de.joinout.criztovyl.commandLineParameters.Parameters;
import de.joinout.criztovyl.dirsync.commandline.Names;
import de.joinout.criztovyl.dirsync.commandline.parameters.DirectoryUpdateParameterParser;
import de.joinout.criztovyl.tools.directory.index.DirectoryIndex;
import de.joinout.criztovyl.tools.file.Path;
import de.joinout.criztovyl.tools.log4j.Log4JEnvironment;
import de.joinout.criztovyl.tools.strings.Strings;

/**
 * @author criztovyl
 * 
 */
public class Main {

	private static Main main;

	private static Strings messages;

	/**
	 * 
	 * @return the main object
	 */
	public static Main getMain() {
		return Main.main;
	}

	/**
	 * Pass-through to {@link Strings#getString(String, Object...)}
	 * 
	 * @param key
	 * @return a formatted string from the external strings
	 */
	public static String getString(String key, Object... args) {
		return Main.messages.getString(key, args);
	}

	/**
	 * Main method
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {

		// Set up external strings
		Main.messages = new Strings("de.joinout.criztovyl.dirsync.messages");

		//Set up main
		Main.main = new Main(args);

		//Run main
		Main.getMain().run();

	}

	private final Logger logger;

	private final String separator, regex;

	private final ArrayList<DirectoryIndex> sourceDirs;

	private DirectoryIndex targetDir;
	
	private final Log4JEnvironment log4jenv;

	private final ParameterParser params;

	public Main(String[] args) {

		// Set up environment

		// Set up Log4J
		log4jenv = new Log4JEnvironment();
		logger = LogManager.getLogger();

		// Print out license
		System.out.println(Main.messages.getString("Main.License", getString("Name")));

		if (logger.isInfoEnabled())
			logger.info(Main.messages.getString("Main.StartinMsg", this
					.getClass().getName()));

		if (logger.isInfoEnabled())
			if (log4jenv.getConfigFile() != null)
				logger.info(Main.messages.getString("Main.Log4JCfgInfo",
						log4jenv.getConfigFile()));
			else
				logger.info(Main.messages.getString("Main.Log4JStdCfgInfo"));

		if (logger.isTraceEnabled())
			logger.trace(Main.messages.getString("Main.ParamEnvSetupInfo"));

		// Set up parameters
		params = new DirectoryUpdateParameterParser(args);

		if (logger.isTraceEnabled())
			logger.trace(Main.messages.getString("Main.ParamsParsedInfo"));

		// Set up variables

		if (logger.isTraceEnabled())
			logger.trace(Main.messages
					.getString("Main.SeperatorAndRegexSetupInfo"));

		// Set path separator if was given as parameter, otherwise use
		// File#separator

		// Check if is in parameters
		if (params.containsKey(Names.PARAM_SEPARATOR))

			// Check if has value and was present
			if (params.get(Names.PARAM_SEPARATOR).size() == 1
					&& params.get(Names.PARAM_SEPARATOR).wasPresent())

				// Set separator to first value
				separator = params.get(Names.PARAM_SEPARATOR).get(0);
			else
				separator = File.separator;
		else
			separator = File.separator;

		// Set up regular expression if was given as parameter, otherwise empty
		// string

		// Check if is in parameters
		if (params.containsKey(Names.PARAM_REGEX))

			// Check if has value and was present
			if (params.get(Names.PARAM_REGEX).size() == 1
					&& params.get(Names.PARAM_REGEX).wasPresent())

				// Set regular expression to first value
				regex = params.get(Names.PARAM_REGEX).get(0);
			else
				regex = "";
		else
			regex = "";

		if (logger.isTraceEnabled())
			logger.trace(Main.messages.getString("Main.SrcDirSetup"));

		// Set up source directories
		sourceDirs = setupDirectoryIndex(params.getArguments());

		if (logger.isTraceEnabled())
			logger.trace(Main.messages.getString("Main.DestDirSetup"));

		// Set up target directory if was given as parameter
		targetDir = null;
		if (params.containsKey(Names.PARAM_DIRECTORY))
			if (params.get(Names.PARAM_DIRECTORY).wasPresent())
				targetDir = new DirectoryIndex(new Path(params.get(
						Names.PARAM_DIRECTORY).get(0), separator), regex);

		if (logger.isTraceEnabled())
			logger.trace(Main.messages.getString("Main.SetupSuccessInfo", this
					.getClass().getName()));

	}

	/**
	 * @return the environment for Log4J
	 */
	public Log4JEnvironment getLog4JEnv() {
		return log4jenv;
	}

	/**
	 * @return the logger
	 */
	public Logger getLogger() {
		return logger;
	}

	/**
	 * @return the parameters
	 */
	public Parameters getParams() {
		return params;
	}

	/**
	 * @return the source directories
	 */
	public ArrayList<DirectoryIndex> getSourceDirs() {
		return sourceDirs;
	}

	/**
	 * @return the target directory
	 */
	public DirectoryIndex getTargetDir() {
		return targetDir;
	}

	/**
	 * 
	 * @return true when source directories are not null
	 */
	public boolean hasSourceDirs() {
		return sourceDirs != null;
	}

	/**
	 * 
	 * @return true when target directories is not null
	 */
	public boolean hasTargetDir() {
		return targetDir != null;
	}

	public void run() {
		
		getParams().runAction();

		if (getLogger().isInfoEnabled())
			getLogger().info(Main.messages.getString("Main.EndInfo"));
	}

	/**
	 * Creates a list of directory indexes from a list of paths.
	 * 
	 * @param paths
	 *            the list of paths
	 * @return the list of the directory indexes
	 */
	public ArrayList<DirectoryIndex> setupDirectoryIndex(ArrayList<String> paths) {

		if (logger.isTraceEnabled())
			logger.trace(Main.messages.getString("Main.DirIndexSetupInfo"));

		// Create list for indexes been created
		final ArrayList<DirectoryIndex> indexes = new ArrayList<>();

		if (logger.isTraceEnabled())
			logger.trace(Main.messages.getString("Main.PathIteratingInfo"));

		// Iterate over all paths, create directory index and add to index list
		// if file exists
		for (final String path : paths)

			// Add to index list if file exists
			if (new Path(path, separator).getFile().exists()) {

				if (logger.isTraceEnabled())
					logger.trace(Main.messages
							.getString("Main.DirIndexCreationInfo"));

				indexes.add(new DirectoryIndex(new Path(path, separator), regex));

				if (logger.isDebugEnabled())
					logger.debug(Main.messages.getString(
							"Main.DirIndexCreatedInfo", path));
			}

			else

			if (logger.isWarnEnabled())
				logger.warn(Main.messages.getString("Main.FileNotFoundInfo",
						path));
		// return indexes
		return indexes;
	}
}
