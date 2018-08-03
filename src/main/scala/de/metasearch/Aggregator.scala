package de.metasearch

import akka.actor.{Actor, ActorLogging, Props}
import de.metasearch.Aggregator.{NoResult, QueryResults}
import de.metasearch.Presenter.AggregatedResults
import de.metasearch.ResultTypes.QueryResult

import scala.collection.mutable

class Aggregator(query: String) extends Actor with ActorLogging {

  private val aggregatedResults: mutable.Map[String, Seq[String]] = mutable.Map.empty

  private var resultsReceived: Int = 0


  def checkIfFinished() = {
    resultsReceived += 1
    if (resultsReceived >= 3) {
      context.system.actorOf(Presenter.props, "presenterActor") ! AggregatedResults(query, aggregatedResults.toMap)
    }
  }


  override def receive: Receive = {

    case QueryResults( queryResults) =>
      queryResults.foreach { queryResult =>
        aggregatedResults.put(queryResult.link, titlesForLink(queryResult))
      }
      checkIfFinished()
    case NoResult =>
      checkIfFinished()
  }

  private def titlesForLink(queryResult: QueryResult): Seq[String] = {
    aggregatedResults.get(queryResult.link) match {
      case None => Seq(queryResult.title)
      case Some(results) => results :+ queryResult.title
    }
  }
}


object Aggregator {
  def props(query: String): Props = Props(new Aggregator(query))


  final case class QueryResults( resultList: Seq[QueryResult])

  final case object NoResult

}

