package models;

import java.util.ArrayList;

public class DataCenter {
	private ArrayList<Video> videos;
	
	public DataCenter(){
		this.videos = new ArrayList<Video>();
	}
	
	public ArrayList<Video> getVideos() {
		return videos;
	}

	public void setVideos(ArrayList<Video> videos) {
		this.videos = videos;
	}

	@Override
	public String toString() {
		return "DataCenter [videos=" + videos + "]";
	}
	
	
}
