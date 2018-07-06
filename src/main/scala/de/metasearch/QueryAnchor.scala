package de.metasearch

import akka.actor.{Actor, ActorLogging, ActorRef, Props}
import de.metasearch.QueryAnchor.Query

class QueryAnchor extends Actor with ActorLogging {

  val schroogleQueryActor = context.system.actorOf(SchroogleQueryActor.props, "schroogleQueryActor")

  def receive = {
    case Query(queryString) => {
      log.info("Query received (from " + sender() + "): " + queryString)
      schroogleQueryActor ! Query(queryString)
    }
  }
}

object QueryAnchor {

  def props: Props = Props[QueryAnchor]

  final case class Query(query: String)

}
