package expert.scalamorphing.archipelago.letter

import monix.reactive.Observable

trait ABlock extends ALetter {
  val children: Observable[ALine[ALetter]]
}

trait ALine[TheLetter <: ALetter] {
  val UUID: String
  val length: Observable[Int]
  val indices: Observable[ALetter.AnIndex[TheLetter]]
}
