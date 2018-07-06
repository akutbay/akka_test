package de.metasearch

import akka.actor.{Actor, ActorLogging, Props}
import de.metasearch.Aggregator.ResultListMessage

class Aggregator extends Actor with ActorLogging {

  override def receive: Receive = {
    case ResultListMessage(query, resultList) => {
      resultList.foreach(r => log.info(s"result for query '$query' is title: ${r.title} with link: ${r.link}"))
    }
  }
}

object Aggregator {

  def props: Props = Props[Aggregator]

  final case class ResultListMessage(query: String, resultList: Seq[Result])

}

