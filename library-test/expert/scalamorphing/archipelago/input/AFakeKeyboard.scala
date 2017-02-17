package expert.scalamorphing.archipelago.input

import expert.scalamorphing.archipelago.letter.AFakeLetter
import monix.reactive.Observable

trait AFakeKeyboard extends AKeyboard[AFakeLetter] {
  val pressed: Observable[Set[AFakeLetter]]
}
