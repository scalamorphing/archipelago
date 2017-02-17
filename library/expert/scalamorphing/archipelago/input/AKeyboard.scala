package expert.scalamorphing.archipelago.input

import expert.scalamorphing.archipelago.author.{ AnAuthor, TheWriter }
import expert.scalamorphing.archipelago.letter.ALetter
import monix.execution.cancelables.SingleAssignmentCancelable
import monix.execution.{ Ack, Cancelable }
import monix.reactive.Observable
import monix.reactive.OverflowStrategy.Unbounded
import monix.execution.Scheduler.Implicits.global
import org.scalajs.dom.KeyboardEvent
import org.scalajs.dom

import scala.scalajs.js

trait AKeyboard[TheLetter <: ALetter] {
  val pressed: Observable[Set[TheLetter]]
}

final case class CharKeyboard(author: TheWriter)
    extends AKeyboard[CharLetter] {

  override val pressed = Observable.create[Set[CharLetter]](Unbounded) {
    subscriber =>
      val c = SingleAssignmentCancelable()

      val handler: js.Function1[KeyboardEvent, Ack] = (e: KeyboardEvent) =>
        subscriber.onNext(
          Set[CharLetter](
            CharLetter(author, e.key)
          )
        ).syncOnStopOrFailure(_ =>
            c.cancel())

      dom.window.addEventListener("keypress", handler)
      c := Cancelable(() => dom.window.removeEventListener("keypress", handler))
  }
}

final case class CharLetter(override val author: AnAuthor[CharLetter], char: String) extends ALetter {
  // TODO: implement UUID fetching from a server
  override val UUID: String = ""
}
