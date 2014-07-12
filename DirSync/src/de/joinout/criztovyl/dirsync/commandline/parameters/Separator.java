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

import java.io.File;

import de.joinout.criztovyl.commandLineParameters.Parameter;
import de.joinout.criztovyl.commandLineParameters.ParameterName;
import de.joinout.criztovyl.dirsync.commandline.Names;

/**
 * Represents the separator parameter
 * 
 * @author criztovyl
 * 
 */
public class Separator extends Parameter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2668134286080493306L;

	// Set up name
	public static final ParameterName NAME = new ParameterName(
			Names.PARAM_SEPARATOR, Names.PARAM_SEPARATOR_SHORT);

	// Set up size
	private static final int size = 1;

	private static final String description = String
			.format("This parameter should contain the separator of the entered paths. If not given '%s' (your system's default) will be used.%n"
					+ "Example:%n" + "-s \"/\"%n" + "Use / as separator.",
					File.separator);

	/**
	 * Creates a new separator parameter
	 */
	public Separator() {
		super(Separator.size);
		setDescription(Separator.description);
	}

}