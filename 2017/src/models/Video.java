package models;

public class Video {
	private int id;
	private int dim;
	
	public Video(int id){
		this.id = id;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDim() {
		return dim;
	}

	public void setDim(int dim) {
		this.dim = dim;
	}
	
	@Override
	public boolean equals(Object obj) {
		Video v = (Video) obj;
		return this.id == v.id;
	}
	
	@Override
	public int hashCode() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Video [id=" + id + ", dim=" + dim + "]";
	}
	
}
