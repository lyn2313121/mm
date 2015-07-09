package yinyue;
import java.io.Serializable;
public class ListItem implements Serializable{
	private static final long serialVersionUID = 1L;
	private String name;
	private String path;
	public ListItem()
	{}
	public ListItem(String name, String path)
	{
		this.name = name;
		this.path = path;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getPath()
	{
		return path;
	}
	public void setPath(String path)
	{
		this.path = path;
	}
	public String toString()
	{
		return name;
	}

}
