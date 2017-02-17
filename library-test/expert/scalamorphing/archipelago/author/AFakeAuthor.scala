package expert.scalamorphing.archipelago.author

import expert.scalamorphing.archipelago.input.{ TheFakeKeyboard, TheCaret, TheCursor }
import expert.scalamorphing.archipelago.letter.AFakeLetter
import monix.reactive.Observable

trait AFakeAuthor extends AnAuthor[AFakeLetter] {
  override val UUID: String = ""

  val caret: Observable[TheCaret]
  val cursor: Observable[TheCursor]
  val keyboard: Observable[TheFakeKeyboard]
}
