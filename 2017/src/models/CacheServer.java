package models;

import java.util.HashSet;
import java.util.Set;

public class CacheServer {
	private int id;
	private int capacity;
	private int available;
	private HashSet<Video> videos;

	public CacheServer(int id, int es) {
		this.id = id;
		this.capacity = es;
		this.available = this.capacity;
		this.videos = new HashSet<Video>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getEmptysize() {
		return capacity;
	}

	public int getSize() {
		return available;
	}

	public void setSize(int size) {
		this.available = size;
	}

	public void addVideo(Video v) {
		videos.add(v);
		available -=  v.getDim();
	}

	public Set<Video> getVideos() {
		return videos;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this.id == ((CacheServer) obj).getId())
			return true;

		return false;
	}
	
	@Override
	public int hashCode() {
		return this.id;
	}

	@Override
	public String toString() {
		String ts = "CacheServer [id=" + id + ", emptysize=" + capacity + ", size=" + available + ", videos=";
		for (Video v : videos) {
			ts = ts + v.toString() + " ";
		}

		return ts + "]";
	}
}
