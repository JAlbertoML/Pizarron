package mx.ipn.upiicsa.poo.pizarron.model;

import java.util.Date;
import java.util.List;

import com.google.gson.Gson;

public class Diagram {
	public static final Integer PNG_EXPORT = 0;
	public static final Integer JPG_EXPORT = 1;

	private Integer id;
	private String name;
	private String description;
	private Date creationDate;
	private Date updatedDate;
	private String json;
	private List<Shape> shapes;

	public Diagram(String name, String description, Date creationDate, Date updatedDate) {
		super();
		this.name = name;
		this.description = description;
		this.creationDate = creationDate;
		this.updatedDate = updatedDate;
	}

	public Diagram(Integer id, String name, String description, Date creationDate, Date updatedDate) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.creationDate = creationDate;
		this.updatedDate = updatedDate;
	}

	public Diagram(String name, String description, Date creationDate, Date updatedDate, String json) {
		super();
		this.name = name;
		this.description = description;
		this.creationDate = creationDate;
		this.updatedDate = updatedDate;
		this.json = json;
	}
	
	public void generateJson() {
		Gson gson = new Gson();
		String diagramJson = gson.toJson(shapes);
		System.out.println(diagramJson);
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	public List<Shape> getShapes() {
		return shapes;
	}

	public void setShapes(List<Shape> shapes) {
		this.shapes = shapes;
	}
}
