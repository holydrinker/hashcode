package hashcode2017;

import java.io.IOException;
import java.util.List;

import input.*;
import models.*;

public class Runner {

	public static void main(String[] args) {
		Input input = new Input();
		try {
			input.getData("data/kittens.in");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		List<Request> request = input.getRequests();
		ServerManager manager = new ServerManager();
		manager.manage(request);
		
		int count = 0;
		for(CacheServer cache : input.getCacheServers()){
			if(!cache.getVideos().isEmpty())
				count++;
		}
		
		System.out.println(count);
		
		for(CacheServer cache: input.getCacheServers()){
			if(!cache.getVideos().isEmpty()){
				System.out.print(cache.getId() + " ");
				
				for(Video v : cache.getVideos()){
					System.out.print(v.getId() + " ");
				}
				System.out.println("");
			}
			
		}

	}

}
