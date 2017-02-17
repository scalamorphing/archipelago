package expert.scalamorphing.archipelago.canvas

import expert.scalamorphing.archipelago.author.AnAuthor
import expert.scalamorphing.archipelago.letter.ALetter
import monix.reactive.Observable

import scala.collection.immutable.TreeMap

trait ACanvas[TheLetter <: ALetter] { self =>
  val authorsByUUID: Observable[TreeMap[String, AnAuthor[TheLetter]]]
}
