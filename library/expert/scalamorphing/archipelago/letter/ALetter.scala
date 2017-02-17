package expert.scalamorphing.archipelago.letter

import expert.scalamorphing.archipelago.author.AnAuthor
import monix.reactive.Observable

import scala.collection.immutable.TreeMap

trait ALetter {
  val UUID: String
  val author: AnAuthor[_]
}

object ALetter {
  trait AnIndex[TheLetter <: ALetter] {
    val lettersByUUID: Observable[TreeMap[String, TheLetter]]

    val lettersByAuthor: Observable[TreeMap[AnAuthor[TheLetter], TheLetter]]

    val lettersByPath: Observable[TreeMap[APath[TheLetter], TheLetter]]
  }

  trait APath[TheLetter <: ALetter] {
    val root: APath.TheChain[TheLetter]
    val length: Observable[Int]
  }

  object APath {
    trait TheChain[TheLetter <: ALetter] {
      val root: Observable[APath[TheLetter]]
      val next: Observable[TheChain[TheLetter]]
    }
  }
}

