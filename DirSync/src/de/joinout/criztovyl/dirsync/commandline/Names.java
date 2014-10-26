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

import de.joinout.criztovyl.dirsync.Main;
import de.joinout.criztovyl.dirsync.commandline.parameters.Directory;
import de.joinout.criztovyl.dirsync.commandline.parameters.Regex;

/**
 * @author criztovyl
 * 
 */
public class Names {

	/**
	 * Long name for the {@link Directory} parameter
	 */
	public static final String PARAM_DIRECTORY = Main.getString("Names.Param.Long.Directory"); 
	/**
	 * Short name for the {@link Directory} parameter
	 */
	public static final Character PARAM_DIRECTORY_SHORT = Main.getString("Names.Param.Short.Directory").charAt(0); 
	/**
	 * Long name for the {@link Regex} parameter
	 */
	public static final String PARAM_REGEX = Main.getString("Names.Param.Long.Regex"); 
	/**
	 * Short name for the {@link Regex} parameter
	 */
	public static final Character PARAM_REGEX_SHORT = Main.getString("Names.Param.Short.Regex").charAt(0); 

	public static final String ACTION_LICENSE = Main.getString("Names.Param.Action.License"); 

	public static final String ACTION_SYNC = Main.getString("Names.Param.Action.Sync"); 

	public static final String ACTION_HELP = Main.getString("Names.Param.Action.Help"); 
	
	public static final String ACTION_VERSION = Main.getString("Names.Param.Action.Version"); 
}
