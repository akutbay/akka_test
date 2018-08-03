package de.metasearch


import akka.actor.{ActorRef, ActorSystem}
import akka.event.jul.Logger
import de.metasearch.QueryDispatcher.MetaQuery


object Main extends App {

  private val logger = Logger("Main Logger")

  private val system: ActorSystem = ActorSystem("metasearch_spawning_actors_on_each_query")

  private val queryString = "Pferde mit Hut"


  private val queryAnchor: ActorRef = system.actorOf(QueryDispatcher.props, "queryAnchor")

  logger.info(s"sending message Metaquery $queryString")
  queryAnchor ! MetaQuery(queryString)
}

