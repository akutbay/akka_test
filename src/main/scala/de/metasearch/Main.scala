package de.metasearch

import akka.actor.{ActorRef, ActorSystem}


object Main extends App {
  val system: ActorSystem = ActorSystem("helloAkka")


  // create query anchor (which creates query actors
  private val queryAnchor: ActorRef = system.actorOf(QueryAnchor.props, "queryAnchor")


  private val aggragator: ActorRef = system.actorOf(Aggregator.props, "aggregator")
  // create aggregator

}

