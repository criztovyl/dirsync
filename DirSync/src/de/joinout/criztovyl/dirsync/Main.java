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

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.joinout.criztovyl.commandLineParameters.Parameters;
import de.joinout.criztovyl.dirsync.commandline.parameters.DirSyncParameters;
import de.joinout.criztovyl.tools.log4j.Log4JEnvironment;
import de.joinout.criztovyl.tools.strings.Strings;

/**
 * @author criztovyl
 * 
 */
public class Main {

	private static Main main;

	private static Strings messages;
	
	public static final String version = "2014-10-06#01";

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
		Main.messages = new Strings("de.joinout.criztovyl.dirsync.Strings");

		// Set up main
		Main.main = new Main(args);

		// Run main
		Main.getMain().run();

	}

	private final Logger logger;

	private final Log4JEnvironment log4jenv;

	private final Parameters params;

	public Main(String[] args) {

		// Set up environment

		// Set up Log4J
		log4jenv = new Log4JEnvironment();
		logger = LogManager.getLogger();

		// Print out license
		System.out.println(Main.messages.getString("Main.License",
				Main.getString("Name")));

		if (logger.isInfoEnabled())
			logger.info(Main.messages.getString("Main.StartingMsg", this
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
		params = new DirSyncParameters(args);

		if (logger.isTraceEnabled())
			logger.trace(Main.messages.getString("Main.ParamsParsedInfo"));


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

	public void run() {

		getParams().runAction();

		if (getLogger().isInfoEnabled())
			getLogger().info(Main.messages.getString("Main.EndInfo"));
	}

	public static String getVersion() {
		return version;
	}

}
