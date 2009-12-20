package se.janlindblom.android.goodreads.handler;

/**
 * Copyright (c) 2009, Jan Lindblom
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice,
 *   this list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 * * Neither the name of the project nor the names of its contributors may be
 *   used to endorse or promote products derived from this software without
 *   specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 */

import java.util.LinkedList;
import java.util.Observable;
import se.janlindblom.android.goodreads.meta.Operation;
import se.janlindblom.android.goodreads.meta.Response;

/**
 * @author Jan Lindblom (lindblom.jan@gmail.com)
 * @version 0.1
 */
public class Requester extends Observable implements Runnable {
	private LinkedList<Operation> queue;
	private boolean running;
	private Thread thread;
	
	public Requester() {
		queue = new LinkedList<Operation>();
		this.setRunning(false);
		thread = null;
	}

	/**
	 * 
	 * @return a {@link Thread} for this Requester.
	 */
	public Thread thread() {
		this.setRunning(true);
		thread = new Thread(this);
		thread.setName("Requester");
		return thread;
	}
	
	public void start() {
		if (thread == null) {
			this.thread();
		}
		thread.start();
	}
	
	public void stop() {
		this.setRunning(false);
	}

	@Override
	public void run() {
		while (this.isRunning()) {
			try {
				/* Goodreads limits the API to 1 access per second,
				 * honor that by sleeping between requests.
				 */
				if (queue.size() > 0) {
					this.handleRequest(queue.removeFirst());
				}
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				this.setRunning(false);
			}
		}
	}
	
	private void handleRequest(Operation first) {
		Response response = first.execute();
		this.setChanged();
		this.notifyObservers(response);
	}

	public void request(Operation o) {
		queue.add(o);
	}
	
	public boolean cancel(Operation o) {
		return queue.remove(o);
	}

	/**
	 * @param running the running to set
	 */
	public void setRunning(boolean running) {
		this.running = running;
	}

	/**
	 * @return the running
	 */
	public boolean isRunning() {
		return running;
	}

}
