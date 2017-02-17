package expert.scalamorphing.archipelago.input

import expert.scalamorphing.archipelago.letter.{ ABlock, ALetter }

trait ACaret {
  val previous: ALetter
  val parent: ABlock
  val next: ALetter
}
