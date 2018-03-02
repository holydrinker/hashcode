package models;

public class Request {
	private Video video;
	private EndPoint endpoint;
	private int nrReq;
	
	public Request(Video v, EndPoint ep, int nr){
		this.video = v;
		this.endpoint = ep;
		this.nrReq = nr;
	}

	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

	public EndPoint getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(EndPoint endpoint) {
		this.endpoint = endpoint;
	}

	public int getNrReq() {
		return nrReq;
	}

	public void setNrReq(int nrReq) {
		this.nrReq = nrReq;
	}

	@Override
	public String toString() {
		return "Request [video=" + video + ", endpoint=" + endpoint + ", nrReq=" + nrReq + "]";
	}
}
