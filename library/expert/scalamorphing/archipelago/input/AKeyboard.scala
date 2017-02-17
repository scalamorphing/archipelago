package expert.scalamorphing.archipelago.input

import expert.scalamorphing.archipelago.canvas.ACanvas.{ ALetter, AnAuthor }
import monix.execution.cancelables.SingleAssignmentCancelable
import monix.execution.{ Ack, Cancelable }
import monix.reactive.Observable
import monix.reactive.OverflowStrategy.Unbounded
import monix.execution.Scheduler.Implicits.global
import org.scalajs.dom.KeyboardEvent
import org.scalajs.dom
import scala.scalajs.js

trait AKeyboard {
  val pressed: Observable[Set[ALetter]]
}

final case class CharKeyboard(author: AnAuthor) {
  val pressed = Observable.create[Set[CharLetter]](Unbounded) {
    subscriber =>
      val c = SingleAssignmentCancelable()

      val handler: js.Function1[KeyboardEvent, Ack] = (e: KeyboardEvent) =>
        subscriber.onNext(
          Set[CharLetter](
            CharLetter(author, e.key)
          )
        ).syncOnStopOrFailure(exception =>
            c.cancel())

      dom.window.addEventListener("keypress", handler)
      c := Cancelable(() => dom.window.removeEventListener("keypress", handler))
  }
}

final case class CharLetter(override val author: AnAuthor, char: String) extends ALetter {
  // TODO: implement UUID fetching from a server
  override val UUID: String = ""
}
