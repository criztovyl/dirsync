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

import java.util.HashMap;

import de.joinout.criztovyl.commandLineParameters.ParameterAction;
import de.joinout.criztovyl.dirsync.commandline.Names;

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
		put(Names.ACTION_HELP, Help.ACTION);
		put(Names.ACTION_LICENSE, License.ACTION);
		put(Names.ACTION_SYNC, Sync.ACTION);
	}

}
