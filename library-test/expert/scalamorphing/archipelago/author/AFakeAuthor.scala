package expert.scalamorphing.archipelago.author

import expert.scalamorphing.archipelago.letter.AFakeLetter

trait AFakeAuthor extends AnAuthor[AFakeLetter] {
  override val UUID: String = ""
}
