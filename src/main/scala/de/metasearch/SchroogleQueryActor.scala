package de.metasearch

import akka.actor.{Actor, ActorLogging, ActorRef, Props}
import de.metasearch.Aggregator.ResultListMessage
import de.metasearch.SchroogleQueryActor.Query

class SchroogleQueryActor(aggregator: ActorRef) extends Actor with ActorLogging {

  def receive = {
    case Query(queryString) => {
      val query = s"https://www.schroogle.de/search?q=$queryString"
      log.info("Query received (from " + sender() + "). calling " + query)
      aggregator ! ResultListMessage(queryString, Seq(Result("großartiger titel", "https://www.großartig.de")))
    }
  }
}

object SchroogleQueryActor {
  def props(aggregator: ActorRef): Props = Props(new SchroogleQueryActor(aggregator))

  final case class Query(query: String)

}
