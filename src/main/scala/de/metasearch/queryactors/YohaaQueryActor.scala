package de.metasearch.queryactors

import akka.actor.{Actor, ActorLogging, ActorRef, Props}
import de.metasearch.Aggregator.QueryResults
import de.metasearch.Query
import de.metasearch.ResultTypes.QueryResult


class YohaaQueryActor(aggregator: ActorRef) extends Actor with ActorLogging {

  def receive = {
    case Query(queryString) => {
      val query = s"https://www.yohaa.de/search?q=$queryString"
      log.debug("Query received (from " + sender() + "). calling " + query)
      aggregator ! QueryResults(Seq(QueryResult("Großartiger Titel (kommt von Yohaa)", s"https://www.$queryString.de")))
    }
  }
}

object YohaaQueryActor {
  def props(aggregator: ActorRef): Props = Props(new YohaaQueryActor(aggregator))


}
