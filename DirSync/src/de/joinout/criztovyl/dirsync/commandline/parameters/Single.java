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

import de.joinout.criztovyl.commandLineParameters.Parameter;
import de.joinout.criztovyl.commandLineParameters.ParameterName;
import de.joinout.criztovyl.dirsync.commandline.Names;

/**
 * Represents the single parameter
 * 
 * @author criztovyl
 * 
 */
public class Single extends Parameter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8774194774497906178L;

	// Set up name
	public static final ParameterName NAME = new ParameterName(Names.PARAM_SINGLE,
			Names.PARAM_SINGLE_SHORT);

	// Set up size
	private static final int size = 0;

	private static final String description = String
			.format("This parameter indicates arguments are single files only.%n"
					+ "Example:%n"
					+ "dirupdate -xt targetdir file1 file2%n"
					+ "Upadres files file1 and file2 at targetdir.");

	/**
	 * Creates a new separator parameter
	 */
	public Single() {
		super(Single.size);
		setDescription(Single.description);
	}

}
