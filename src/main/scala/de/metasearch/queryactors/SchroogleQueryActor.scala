package de.metasearch.queryactors

import akka.actor.{Actor, ActorLogging, ActorRef, Props}
import de.metasearch.Aggregator.QueryResults
import de.metasearch.Query
import de.metasearch.ResultTypes.QueryResult

class SchroogleQueryActor(aggregator: ActorRef) extends Actor with ActorLogging {

  def receive = {
    case Query(queryString) => {
      val query = s"https://www.schroogle.de/search?q=$queryString"
      log.debug("Query received (from " + sender() + "). calling " + query)

      aggregator ! QueryResults(
        Seq(
          QueryResult("Großartiger Titel 1 (kommt von Schroogle)", s"https://www.$queryString.kommt.von.schroogle.de"),
          QueryResult("Titel 2 (kommt von Schroogle)", s"https://www.$queryString.org"),
          QueryResult("Titel 3 (kommt von Schroogle) - identischer link mit yooha", s"https://www.$queryString.de")))

    }
  }
}

object SchroogleQueryActor {
  def props(aggregator: ActorRef): Props = Props(new SchroogleQueryActor(aggregator))
}
