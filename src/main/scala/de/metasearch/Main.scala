package de.metasearch


import akka.actor.{ActorRef, ActorSystem}
import akka.event.jul.Logger
import de.metasearch.QueryAnchor.MetaQuery


object Main extends App {

  private val logger = Logger("mainlogger")

  private val system: ActorSystem = ActorSystem("actorsystem")

  private val aggregator: ActorRef = system.actorOf(Aggregator.props, "aggregator")

  private val queryAnchor: ActorRef = system.actorOf(QueryAnchor.props(aggregator), "queryAnchor")

  logger.info("sending message")
  queryAnchor ! MetaQuery("der großartige suchstring")


}

