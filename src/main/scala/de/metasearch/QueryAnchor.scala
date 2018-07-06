package de.metasearch

import akka.actor.{Actor, ActorLogging, ActorRef, Props}
import de.metasearch.QueryAnchor.MetaQuery
import de.metasearch.SchroogleQueryActor.Query


class QueryAnchor(aggregator: ActorRef) extends Actor with ActorLogging {

  val schroogleQueryActor = context.system.actorOf(SchroogleQueryActor.props(aggregator), "schroogleQueryActor")

  def receive = {
    case MetaQuery(queryString) => {
      log.info("Query received (from " + sender() + "): " + queryString)
      schroogleQueryActor ! Query(queryString)
    }
  }
}

object QueryAnchor {

  def props(aggregator: ActorRef): Props = Props(new QueryAnchor(aggregator))

  final case class MetaQuery(query: String)

}
