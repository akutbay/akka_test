package de.metasearch

import akka.actor.{Actor, ActorLogging, Props}
import de.metasearch.Presenter.AggregatedResults

class Presenter extends Actor with ActorLogging {
  override def receive: Receive = {

    case AggregatedResults(query, resultList) =>
      println(s"Result for query: '$query'")
      resultList.foreach {
        case (link, titles) =>
          println(s"found : >>> $link <<<, with titles:")
          titles.foreach(title=> println(s"* $title"))
      }
  }
}

object Presenter {

  def props: Props = Props[Presenter]

  final case class AggregatedResults(query: String, resultList: Map[String, Seq[String]])

}