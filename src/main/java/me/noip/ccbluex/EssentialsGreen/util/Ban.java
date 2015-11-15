/**
 * 
 */
package me.noip.ccbluex.EssentialsGreen.util;

import java.io.IOException;
import java.util.Date;

/**
 * @author Marco
 *
 */
public interface Ban {

	public void setBan(boolean ban, String reason, String author, Date date) throws IOException;
	public void setTempBan(boolean ban, String reason, String author, Date date, int years, int months, int days, int minutes, int seconds);
	public boolean isBanned();
	public String getReason();
	public String getAuthor();
	public String getDate();
	public void setReason(String reason) throws IOException;
	public void setAuthor(String user) throws IOException;
	public void setDate(Date date) throws IOException;
	public void setType(BanType type) throws IOException;
	public BanType getType();
}