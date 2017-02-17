package expert.scalamorphing.archipelago.input

import expert.scalamorphing.archipelago.letter.{ ABlock, ALetter }

final case class TheCaret(previous: ALetter, parent: ABlock, next: ALetter)
