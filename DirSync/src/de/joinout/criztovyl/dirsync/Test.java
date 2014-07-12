/**
    This is part of a program that updates a folder with the data from another folder.
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
import java.util.HashSet;
import java.util.Set;

import de.joinout.criztovyl.tools.file.Path;

/**
 * @author criztovyl
 * 
 */
public class Test {

	public static void main(String args[]) throws IOException {

		Path path1 = new Path("troet");
		Path path2 = new Path("baum");
		Path path3 = new Path("foo");
		Path path4 = new Path("bar");
		Path path5 = new Path("fünf");
		
		Set<Path> list1 = new HashSet<>();
		Set<Path> list2 = new HashSet<>();
		
		list1.add(path1);
		list1.add(path3);
		list1.add(path5);
		
		list2.add(path1);
		list2.add(path2);
		list2.add(path4);
		
		Set<Path> list3 = new HashSet<>();
		list3.addAll(list2);
		
		list3.remove(path4);
		
		System.out.println(list2);
		System.out.println(list3);
		
	}
}
