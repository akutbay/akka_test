package de.metasearch.queryactors

import akka.actor.{Actor, ActorLogging, ActorRef, Props}
import de.metasearch.Aggregator.NoResult
import de.metasearch.Query

class BrokenQueryActor(aggregator: ActorRef) extends Actor with ActorLogging {

  def receive = {
    case Query(queryString) => {
      val query = s"https://www.broken.de/search?q=$queryString"
      log.info("Query received (from " + sender() + "). calling " + query)
      aggregator ! NoResult
    }
  }

}

object BrokenQueryActor {
  def props(aggregator: ActorRef): Props = Props(new BrokenQueryActor(aggregator))
}
