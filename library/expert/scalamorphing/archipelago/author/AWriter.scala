package expert.scalamorphing.archipelago.author

import monix.execution.Scheduler.Implicits.global
import expert.scalamorphing.archipelago.input.{ CharKeyboard, CharLetter, TheCaret, TheCursor }
import monix.execution.{ Ack, Cancelable }
import monix.execution.cancelables.SingleAssignmentCancelable
import monix.reactive.Observable
import monix.reactive.OverflowStrategy.Unbounded
import org.scalajs.dom
import org.scalajs.dom.KeyboardEvent

import scala.scalajs.js

trait AWriter extends AnAuthor[CharLetter]

abstract class TheLocalWriter extends AnAuthor[CharLetter] { thisWriter =>
  val UUID: String

  val letters = Observable.create[Set[String]](Unbounded) { subscriber =>
    val c = SingleAssignmentCancelable()

    val handler: js.Function1[KeyboardEvent, Ack] = (e: KeyboardEvent) =>
      subscriber.onNext(
        Set[String](e.key)
      ).syncOnStopOrFailure(_ =>
          c.cancel())

    dom.window.addEventListener("keypress", handler)
    c := Cancelable(() => dom.window.removeEventListener("keypress", handler))
  }

  val caret: Observable[TheCaret]
  val cursor: Observable[TheCursor]
  val keyboard: Observable[CharKeyboard] = thisWriter.letters.map {
    keyCombination =>
      CharKeyboard(CharLetter(thisWriter, keyCombination))
  }
}
