package de.metasearch

import akka.actor.{Actor, ActorLogging, ActorRef, Props}
import de.metasearch.QueryDispatcher.MetaQuery
import de.metasearch.queryactors.{BrokenQueryActor, SchroogleQueryActor, YohaaQueryActor}


class QueryDispatcher extends Actor with ActorLogging {


  def receive = {
    case MetaQuery(queryString) => {
      log.info("Query received (from " + sender() + "): " + queryString)

      val aggregator: ActorRef = context.system.actorOf(Aggregator.props(queryString), "aggregator")
      // _create_ Actors and send them messages
      context.system.actorOf(SchroogleQueryActor.props(aggregator), "schroogleQueryActor") ! Query(queryString)

      context.system.actorOf(BrokenQueryActor.props(aggregator), "brokenQueryActor") ! Query(queryString)
      context.system.actorOf(YohaaQueryActor.props(aggregator), "yohaaQueryActor") ! Query(queryString)

    }
  }
}

object QueryDispatcher {

  def props = Props[QueryDispatcher]

  final case class MetaQuery(query: String)

}
