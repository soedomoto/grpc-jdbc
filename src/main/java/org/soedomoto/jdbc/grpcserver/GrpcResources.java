/*
 *	CsvJdbc - a JDBC driver for CSV files
 *	Copyright (C) 2014	Simon Chenery
 *
 *	This library is free software; you can redistribute it and/or
 *	modify it under the terms of the GNU Lesser General Public
 *	License as published by the Free Software Foundation; either
 *	version 2.1 of the License, or (at your option) any later version.
 *
 *	This library is distributed in the hope that it will be useful,
 *	but WITHOUT ANY WARRANTY; without even the implied warranty of
 *	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *	Lesser General Public License for more details.
 *
 *	You should have received a copy of the GNU Lesser General Public
 *	License along with this library; if not, write to the Free Software
 *	Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */
package org.soedomoto.jdbc.grpcserver;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class GrpcResources
{
	private static ResourceBundle messages = PropertyResourceBundle.getBundle("org.soedomoto.jdbc.grpc.messages", Locale.getDefault());

	public static String getString(String key)
	{
		try
		{
			return messages.getString(key);
		}
		catch (MissingResourceException e)
		{
			return "[" + key + "]";
		}
	}
}
