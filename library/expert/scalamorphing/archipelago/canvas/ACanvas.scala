package expert.scalamorphing.archipelago.canvas

import monix.reactive.Observable

import scala.collection.immutable.TreeMap

trait ACanvas { self =>

  import expert.scalamorphing.archipelago.canvas.ACanvas._
  import AnAuthor._

  val caret: Observable[TreeMap[AnAuthor, Observable[ACaret]]]
  val cursor: Observable[TreeMap[AnAuthor, Observable[ACursor]]]
  val keyboard: Observable[TreeMap[AnAuthor, Observable[AKeyboard]]]
}

object ACanvas {
  trait ALetter {
    val UUID: String
    val author: AnAuthor
  }

  object ALetter {
    trait AnIndex[TheLetter <: ALetter] {
      val lettersByUUID: Observable[TreeMap[String, TheLetter]]

      val lettersByAuthor: Observable[TreeMap[AnAuthor, TheLetter]]

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

  trait ABlock extends ALetter {
    val children: Observable[ALine[ALetter]]
  }

  trait ACaret {
    val previous: ALetter
    val parent: ABlock
    val next: ALetter
  }

  trait ACursor {
  }

  trait AKeyboard {
  }

  trait AnAuthor {
    val UUID: String
  }

  object AnAuthor {
    implicit val ordering: Ordering[AnAuthor] = new Ordering[AnAuthor] {
      override def compare(x: AnAuthor, y: AnAuthor) =
        x.UUID.compareTo(y.UUID)
    }
  }

  trait ALine[TheLetter <: ALetter] {
    val UUID: String
    val length: Observable[Int]
    val indices: Observable[ALetter.AnIndex[TheLetter]]
  }
}
