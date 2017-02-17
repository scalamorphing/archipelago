package expert.scalamorphing.archipelago.input

import expert.scalamorphing.archipelago.author.AnAuthor
import expert.scalamorphing.archipelago.letter.ALetter

trait AKeyboard[TheLetter <: ALetter] {
  val pressed: TheLetter
}

final case class CharKeyboard(override val pressed: CharLetter) extends AKeyboard[CharLetter]

final case class CharLetter(override val author: AnAuthor[CharLetter], char: Set[String]) extends ALetter {
  // TODO: implement UUID fetching from a server
  override val UUID: String = ""
}
