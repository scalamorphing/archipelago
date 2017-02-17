package expert.scalamorphing.archipelago.author

import expert.scalamorphing.archipelago.input.{ CharKeyboard, CharLetter }
import monix.reactive.Observable

abstract class TheWriter extends AnAuthor[CharLetter] {
  val UUID: String

  val caret: Observable[TheCaret]
  val cursor: Observable[TheCursor]
  val keyboard: Observable[CharKeyboard]
}
