package com.czajor.profiling;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.concurrent.TimeUnit;

public class ProfilingApplication {

  public static int update(Deque events, long nanos, long interval) {
    events.add(nanos);
//    events.removeIf(aTime -> ((long) aTime) < nanos - interval); <- this line is going throught all elements
    while((long) events.peekFirst() < nanos - interval) { // we can change it like this, as we don't need to check all elements - we are using sorted Dequeue implementation
      events.removeFirst();
    }
    return events.size();
  }

  public static void main(String[] args) throws IOException {
    long start = System.nanoTime();
    int total = 100_000;
    long interval = TimeUnit.MILLISECONDS.toNanos(100);
    int[] count = new int[total];
    Deque collection = new ArrayDeque();
    for (int counter = 0; counter < count.length; counter++) {
      count[counter] = update(collection, System.nanoTime(), interval);
      Path p = Paths.get("./a/b");
      if(!Files.exists(p)) { // added to compare before/after - time was significantly reduced because invisible,
        // native calls were reduced (in general, system errors/antivirus checks were performed but we couldn't see or catch it in java code)
        Files.createDirectories(p);
      }
    }
    long spent = System.nanoTime() - start;
    //noinspection OptionalGetWithoutIsPresent
    System.out.println("Average count: " + (int) (Arrays.stream(count).average().getAsDouble()) + " op");
    System.out.println("Spent time: " + TimeUnit.NANOSECONDS.toMillis(spent) + " ms");
  }
}



