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

import java.util.HashSet;

import de.joinout.criztovyl.tools.directory.index.DirectoryIndex;
import de.joinout.criztovyl.tools.file.Path;

/**
 * @author criztovyl
 * 
 */
public class DirectorySync {

	/**
	 * Update first directory
	 */
	public static final int FIRST = 1;
	/**
	 * Update second directory
	 */
	public static final int SECOND = 2;
	/**
	 * Synchronises the files in the first and second directory<br>
	 * Not implemented yet.
	 */
	public static final int SYNC = 3;

//	private final Logger logger;
	private final DirectoryIndex first, second;

	/**
	 * Creates a new directory synchroniser.
	 * 
	 * @param first
	 *            path for the newer of the both directories
	 * @param second
	 *            path for the second of the directories
	 */
	public DirectorySync(DirectoryIndex first, DirectoryIndex second) {

//		logger = LogManager.getLogger();

		this.first = first;
		this.second = second;

	}

	/**
	 * Creates a new directory synchroniser. Will create the directory indexes with
	 * the given regular expression, see
	 * {@link DirectoryIndex#DirectoryIndex(Path, String)}.
	 * 
	 * @param first
	 *            path for first of the directories
	 * @param second
	 *            path for the second of the directories
	 * @param ignoreRegex
	 *            the regular expression
	 */
	public DirectorySync(Path first, Path second, String ignoreRegex) {
		this(new DirectoryIndex(first, ignoreRegex), new DirectoryIndex(second,
				ignoreRegex));
	}

	/**
	 * Will synchronise the two folders.<br>
	 * <br>
	 * As first copy new files, as second update changed files and as third remove deleted files and directories.<br>
	 * Before a change, the file will be backuped.
	 */
	public void sync() {

		//Create new set
		HashSet<Path> differences = first.symmetricDifferences(second);

		for(Path path: differences){
			if(!first.containsRelative(path)){
				
			}
			//Have to be in second
			else{

			}
		}
	}

}
