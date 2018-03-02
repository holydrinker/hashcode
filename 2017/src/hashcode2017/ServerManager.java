package hashcode2017;

import java.util.HashMap;
import java.util.List;

import models.*;

public class ServerManager {
	
	public void manage(List<Request> request) {
		/*
		 * OBIETTIVO: Creare una lista con priorità. RECORD -> k
		 * Ogni RECORD della lista rappresenta una richiesta, identificata da VIDEO - PriorityList delle cache candidate 
		 * Il numero associato alla lista è il numero di richieste, in modo tale da gestire prima le richieste più numerose
		 */
		
		// Creo la lista
		PriorityList<Record> list = new PriorityList<>();
		
		for(Request req : request){
			Video video = req.getVideo();
			PriorityList<CacheServer> candidate = new PriorityList<CacheServer>();
			
			EndPoint e = req.getEndpoint();
			HashMap<CacheServer, Integer> cache_latency = e.getLatenciesCS();
			
			for(CacheServer cache : cache_latency.keySet()){
				int latency = cache_latency.get(cache);
				candidate.insert(cache, latency);
				
			}
			candidate.sort();
			
			if(!candidate.isEmpty()){
				list.insert(new Record(video, candidate), req.getNrReq());
			}
		}
		list.sortDesc();
		
		
		// Sistemo ogni video nella cache più efficiente
		for(Record record : list){
			choiceCache(record);
		}
		
	}
	
	private static void choiceCache(Record record) {
		Video video = record.video;
		PriorityList<CacheServer> candidate = record.priorityList;
		
		for(CacheServer cache: candidate){
			if(video.getDim() < cache.getSize()){
				cache.addVideo(video);
				break;
			}	
		}
	}
	
	private class Record {
		Video video;
		PriorityList<CacheServer> priorityList;
		
		public Record(Video video, PriorityList<CacheServer> priorityList) {
			this.video = video;
			this.priorityList = priorityList;
		}
	}
}
