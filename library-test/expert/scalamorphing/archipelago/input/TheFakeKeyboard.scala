package expert.scalamorphing.archipelago.input

import expert.scalamorphing.archipelago.letter.AFakeLetter

final case class TheFakeKeyboard(pressed: AFakeLetter) extends AKeyboard[AFakeLetter]
