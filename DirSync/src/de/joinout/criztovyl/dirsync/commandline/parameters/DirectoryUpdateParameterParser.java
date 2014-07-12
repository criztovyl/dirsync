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
package de.joinout.criztovyl.dirsync.commandline.parameters;

import de.joinout.criztovyl.commandLineParameters.ParameterParser;
import de.joinout.criztovyl.dirsync.Main;
import de.joinout.criztovyl.dirsync.commandline.actions.DirectoryUpdateActions;

/**
 * @author criztovyl
 * 
 */
public class DirectoryUpdateParameterParser extends ParameterParser {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9073162484385676004L;

	private static final String description = String.format(Main
			.getString("DirUpdateParametersParser.DescriptionText", Main.getString("Name")));

	/**
	 * Creates a new object that holds all command line parameters
	 */
	public DirectoryUpdateParameterParser(String[] args) {

		// Set up map
		super(args, DirectoryUpdateParameterParser.description);

		// put parameters
		put(Regex.NAME, new Regex());
		put(Separator.NAME, new Separator());
		put(Directory.NAME, new Directory());
		put(Single.NAME, new Single());
		getActions().putAll(new DirectoryUpdateActions());
		
		super.parse();
	}
}
