package de.metasearch

import akka.actor.{Actor, ActorLogging, Props}
import de.metasearch.Aggregator.ResultListMessage

class Aggregator extends Actor with ActorLogging {

  override def receive: Receive = {
    case ResultListMessage(resultList) => {
      resultList.foreach(r => log.info("result: " + r))
    }
  }
}

object Aggregator {

  def props: Props = Props[Aggregator]

  final case class ResultListMessage(resultList: Seq[Result])

}

