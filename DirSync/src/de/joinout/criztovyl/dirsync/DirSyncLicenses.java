package de.joinout.criztovyl.dirsync;

import de.joinout.criztovyl.tools.Tools;
import de.joinout.criztovyl.tools.licenses.LibraryLicenses;
import de.joinout.criztovyl.tools.licenses.Licenses;

public class DirSyncLicenses extends LibraryLicenses{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4048083758600319383L;

	public DirSyncLicenses(){
		
		super();
		
		addLibrary(LibraryLicenses.LOG4J);
		addLibrary(LibraryLicenses.JSON);
		addLibrary(Tools.LIBRARY);
		addAlias("gpl", Licenses.GPL3.getName());
		
	}

}
