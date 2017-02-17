package expert.scalamorphing.archipelago.canvas

import expert.scalamorphing.archipelago.author.AFakeAuthor
import expert.scalamorphing.archipelago.letter.AFakeLetter

abstract class AFakeCanvas extends ACanvas[AFakeLetter] {
  val UUID: String
}
