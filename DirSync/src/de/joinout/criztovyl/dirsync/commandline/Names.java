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
package de.joinout.criztovyl.dirsync.commandline;

import de.joinout.criztovyl.dirsync.commandline.parameters.Directory;
import de.joinout.criztovyl.dirsync.commandline.parameters.Regex;
import de.joinout.criztovyl.dirsync.commandline.parameters.Separator;
import de.joinout.criztovyl.dirsync.commandline.parameters.Single;

/**
 * @author criztovyl
 * 
 */
public class Names {

	/**
	 * Long name for the {@link Separator} parameter
	 */
	public static final String PARAM_SEPARATOR = "separator";
	/**
	 * Short name for the {@link Separator} parameter
	 */
	public static final Character PARAM_SEPARATOR_SHORT = "s".charAt(0);
	/**
	 * Long name for the {@link Directory} parameter
	 */
	public static final String PARAM_DIRECTORY = "directory";
	/**
	 * Short name for the {@link Directory} parameter
	 */
	public static final Character PARAM_DIRECTORY_SHORT = "d".charAt(0);
	/**
	 * Long name for the {@link Regex} parameter
	 */
	public static final String PARAM_REGEX = "regex";
	/**
	 * Short name for the {@link Regex} parameter
	 */
	public static final Character PARAM_REGEX_SHORT = "r".charAt(0);
	/**
	 * Long name for the {@link Single} parameter
	 */
	public static final String PARAM_SINGLE = "single";
	/**
	 * Short name for the {@link Single} parameter
	 */
	public static final Character PARAM_SINGLE_SHORT = "x".charAt(0);
	
	public static final String ACTION_UPDATE = "update";
	
	public static final String ACTION_LICENSE = "license";
	
	public static final String ACTION_LIST = "list";
	
	public static final String ACTION_SYNC = "sync";
	
	public static final String ACTION_HELP = "help";
	
	public static final String ACTION_RECOVER = "recover";
}
