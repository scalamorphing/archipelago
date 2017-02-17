package expert.scalamorphing.archipelago.author

import expert.scalamorphing.archipelago.input.{ ACaret, ACursor, AKeyboard }
import expert.scalamorphing.archipelago.letter.ALetter
import monix.reactive.Observable

trait AnAuthor[TheLetter <: ALetter] {
  val UUID: String

  type TheCaret = ACaret
  type TheCursor = ACursor
  type TheKeyBoard = AKeyboard[TheLetter]

  val caret: Observable[TheCaret]
  val cursor: Observable[TheCursor]
  val keyboard: Observable[TheKeyBoard]
}

object AnAuthor {
  implicit val ordering: Ordering[AnAuthor[_ <: ALetter]] = new Ordering[AnAuthor[_ <: ALetter]] {
    override def compare(x: AnAuthor[_ <: ALetter], y: AnAuthor[_ <: ALetter]) =
      x.UUID.compareTo(y.UUID)
  }
}
