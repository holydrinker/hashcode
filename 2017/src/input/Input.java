package input;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import models.CacheServer;
import models.EndPoint;
import models.Request;
import models.Video;

public class Input {

	private int v, e, r, x, c = 0;
	private ArrayList<Video> videos = new ArrayList<Video>();
	private HashSet<CacheServer> cacheServers = new HashSet<CacheServer>();
	private ArrayList<EndPoint> endpoints = new ArrayList<EndPoint>();
	private ArrayList<Request> requests = new ArrayList<Request>();

	private HashMap<Integer, EndPoint> endmap = new HashMap<Integer, EndPoint>();
	private HashMap<Integer, Video> videomap = new HashMap<Integer, Video>();
	
	

	public void getData(String path) throws IOException {
		File file = new File(path);
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line = br.readLine();
		String[] values = line.split(" ");
		this.v = Integer.parseInt(values[0]);
		this.e = Integer.parseInt(values[1]);
		this.r = Integer.parseInt(values[2]);
		this.c = Integer.parseInt(values[3]);
		this.x = Integer.parseInt(values[4]);

		// Videos
		line = br.readLine();
		String[] splitted = line.split(" ");
		int i = 0;
		for (i = 0; i < splitted.length; i++) {
			Video v = new Video(i);
			v.setDim(Integer.parseInt(splitted[i]));
			videos.add(v);
			videomap.put(v.getId(), v);
		}

		// Endpoints
		int endpoint = 0;
		while (br.ready() && endpoint < this.getE()) {
			line = br.readLine();
			String[] endp = line.split(" ");
			EndPoint e = new EndPoint(endpoint);
			e.setLatencyDC(Integer.parseInt(endp[0]));
			int numCaches = Integer.parseInt(endp[1]);
			HashMap<CacheServer, Integer> map = new HashMap<>();
			for (int k = 0; k < numCaches; k++) {
				line = br.readLine();
				String[] cache = line.split(" ");
				CacheServer cs = new CacheServer(Integer.parseInt(cache[0]), this.getX());
				cacheServers.add(cs);
				map.put(cs, Integer.parseInt(cache[1]));
				e.setLatenciesCS(map);
			}
			endmap.put(e.getId(), e);
			endpoints.add(e);
			endpoint++;
		}

		// Requests
		int request = 0;
		while (br.ready() && request < this.getR()) {
			line = br.readLine();
			String[] req = line.split(" ");

			Request r = new Request(videomap.get(Integer.parseInt(req[0])), endmap.get(Integer.parseInt(req[1])),
					Integer.parseInt(req[2]));
			requests.add(r);
			request++;
		}

		br.close();
	}

	public ArrayList<Video> getVideos() {
		return this.videos;
	}

	public Set<CacheServer> getCacheServers() {
		return this.cacheServers;
	}

	public ArrayList<EndPoint> getEndpoints() {
		return this.endpoints;
	}

	public ArrayList<Request> getRequests() {
		return this.requests;
	}

	public int getV() {
		return this.v;
	}

	public int getE() {
		return this.e;
	}

	public int getR() {
		return this.r;
	}

	public int getC() {
		return this.c;
	}

	public int getX() {
		return this.x;
	}

}
